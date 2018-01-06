package lo23.battleship.online.network;

import lo23.battleship.online.network.messages.Message;
import interfacesData.IDataCom;
import structData.Game;
import structData.User;

import java.io.IOException;
import java.net.InetAddress;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * This class implements the network controller. It contains references
 * to all main components in the network package,
 * and the network state (HashMap of all currently connected users)
 *
 * It is based on Singleton Design Pattern
 *
 * @author COM Module
 */
public class NetworkController {
    private int port;

    public int getPort() {
        return port;
    }

    private NetworkModuleInterface networkInterface;
    private static NetworkController instance;
    private HashMap<User, InetAddress> networkState;
    private IDataCom dataInterface;
    private NetworkServer networkServer;

    /**
     * Implementation of Singleton Design Pattern <code>getInstance</code> static method
     * Returns the unique instance of NetworkController
     * @return {@code NetworkController}
     * */
    public static NetworkController getInstance() {
        if (instance == null)
            instance = new NetworkController();

        return instance;
    }

    /**
     * Allocates a new {@code NetworkController} object.
     * Initializes <code>networkState</code> and <code>networkInterface</code>
     * */
    private NetworkController() {
        networkState = new HashMap<>();
        networkInterface = new NetworkModuleInterface(this);
    }

    /**
     * Initializes and open a NetworkServer instance.
     * Sets the IDataCom instance accordingly
     * */
    void launchServer() {
        port = dataInterface.getLocalUser().getPort();
        if (networkServer == null)
            this.networkServer = new NetworkServer(this, port);
        this.networkServer.setDataInterface(dataInterface);
        try {
            if(!networkServer.isOpened())
                this.networkServer.open();
        } catch (IOException e) {
            Logger.getLogger("mainLogger").log(Level.SEVERE, "Could not open server", e);
        }
    }

    /**
     * Sends a message ({@code Message}) to a destination host (<code>InetAddress</code>) using a {@code NetworkSender} thread
     * @param message : {@code Message}
     *                message to be sent
     * @param destinationIpAddress : {@code InetAddress}
     *                             destination IP Address
     * */
    public void sendMessage(Message message, InetAddress destinationIpAddress) {
        NetworkSender networkSender = new NetworkSender(destinationIpAddress, this.getPort(), message);
        networkSender.start();
    }

    /**
     * Returns the list of IP addresses of connected users
     * @return List<InetAddress>
     * */
    public List<InetAddress> getIPTable() {
        return new ArrayList<>(networkState.values());
    }

    /**
     * Finds the corresponding IP Address to a user
     * @param user : {@code User}
     *             user of which the IP¨address is needed
     * @return {@code InetAddress}
     * */
    InetAddress getAddressForUser(User user) {
        for (User u : networkState.keySet()) {
            if (u.getIdUser().equals(user.getIdUser())) {
                return networkState.get(u);
            }
        }

        return null;
    }

    /**
     * Sets IDataCom instance for the network controller and the instance of
     * COMInterface
     * @param IData : {@code IDataCom}
     *              instance of IDataCom
     * */
    public void setDataInterface(IDataCom IData) {
        this.dataInterface = IData;
        networkInterface.setDataInterface(dataInterface);
    }

    /**
     * Returns the instance of the class which implements COMInterface
     * @return {@code COMInterface}
     * */
    public COMInterface getCOMInterface() {
        return networkInterface;
    }

    /**
     * Filter out unknown IP addresses.
     * Returns IP Addresses of the parameter <code>iPAddressesTable</code>
     * which are not in the networkState and different than the local
     * address
     * @return {@code List<InetAddress>}
     * */
    public List<InetAddress> filterUnknownIPAddresses(List<InetAddress> iPAddressesTable) {
        List<InetAddress> filteredAddresses = new ArrayList<>();
        for (InetAddress ipAddress : iPAddressesTable) {
            if (!ipAddress.equals(networkServer.getIpAddress()) && !networkState.containsValue(ipAddress)) {
                filteredAddresses.add(ipAddress);
            }
        }

        return filteredAddresses;
    }

    /**
     * Filter out known IP addresses which are not in the submitted IP Address list
     * Returns IP Addresses which are in the networkState and different than the local
     * address but are not the parameter <code>iPAddressesTable</code>
     * @return {@code List<InetAddress>}
     * */
    public List<InetAddress> filterKnownIPAddressesToNotify(List<InetAddress> iPAddressesTable) {
        List<InetAddress> filteredAddresses = new ArrayList<>();
        for (InetAddress ipAddress : networkState.values()) {
            if (!ipAddress.equals(networkServer.getIpAddress()) &&
                    !iPAddressesTable.contains(ipAddress)) {
                filteredAddresses.add(ipAddress);
            }

        }
        return filteredAddresses;
    }

    /**
     * Add a new user with his corresponding IP address to the network state, if
     * the user is not already in the network state
     * Returns true if the user was added successfully and false otherwise
     * return {@code boolean}
     * */
    private boolean addUserToNetwork(User user, InetAddress senderAddress) {
        for (User u : networkState.keySet()) {
            if (u.getIdUser().equals(user.getIdUser())) {
                return false;
            }
        }
        networkState.put(user, senderAddress);
        return true;
    }

    /**
     * Add a new user with his corresponding IP address to the network state
     * and to the list of users accessible in the entire application (data package).
     * Optionally it adds the game the new user created.
     * @param sender: {@code User}
     *               new User to be added (user who send a particular network message)
     *
     * @param senderAddress {@code InetAddress}:
     *                                         IP address of the new user
     * @param game : {@code Game}:
     *             game the user might have created
     * */
    public void addToNetwork(User sender, InetAddress senderAddress, Game game) {
        if (addUserToNetwork(sender, senderAddress)) {
            dataInterface.addUserToUserList(sender);
            if(game != null && game.getPlayer1().getProfile().getIdUser().equals(sender.getIdUser())) {
                dataInterface.addNewGameList(game);
            }
        }
    }

    /**
     * Removes a user from the network state and the list of connected users. Optionally
     * it removes the game the user might have created
     * */
    public void removeFromNetwork(User user, Game game) {
        for (User u : networkState.keySet()) {
            if(user.getIdUser().equals(u.getIdUser())) {
                networkState.remove(u);
                dataInterface.removeUser(u);
                if(game != null)
                    dataInterface.removeGameFromList(game);
                return;
            }
        }
    }

    /**
     * Close server so that the application stops receiving messages
     * */
    void closeListener() {
        networkState.clear();
        networkServer.close();
    }

    /**
     * Removes the user corresponding the IP address <code>address</code> when the
     * address is unreachable
     *
     * Returns the IP address of the removed user
     * @return {@code InetAddress}
     * */
    InetAddress removeUnreachableHost(InetAddress address) {
        for (Map.Entry<User, InetAddress> entry : networkState.entrySet()) {
            if (entry.getValue().equals(address)) {
                return networkState.remove(entry.getKey());
            }
        }

        return null;
    }

    /**
     * Returns the collection of connected users
     * @return {@code Set<User>}
     * */
    public Set<User> getConnectedUsers() {
        return networkState.keySet();
    }

    /**
     * Clear network state : remove all known players and their corresponding IP addresses
     * */
    void clearNetwork() {
        networkState.clear();
    }
}

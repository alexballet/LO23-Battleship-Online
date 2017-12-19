package lo23.battleship.online.network;

import lo23.battleship.online.network.messages.Message;
import interfacesData.IDataCom;
import structData.Game;
import structData.User;

import java.io.IOException;
import java.net.InetAddress;
import java.util.*;

/**
 * Created by xzirva on 17/10/17.
 * NetworkController
 */
public class NetworkController {

    // CONSTANTS
    private int port = 2345;

    /**
     * Getter for port attribute.
     * @return port attribute
     */
    public int getPort() {
        return port;
    }

    private NetworkModuleInterface networkInterface;
    private static NetworkController instance;
    private HashMap<User, InetAddress> networkState;
    IDataCom dataInterface;
    private NetworkServer networkServer;

    /**
     * Singleton getter for NetworkController.
     * @return static instance of NetworkController
     */
    public static NetworkController getInstance() {
        if (instance == null)
            instance = new NetworkController();

        return instance;

    }

    /**
     * Contructor for NetworkController.
     */
    private NetworkController() {
        networkInterface = new NetworkModuleInterface(this);
        networkState = new HashMap<>();
        networkInterface = new NetworkModuleInterface(this);
        // Create server
        if (networkServer == null)
            this.networkServer = new NetworkServer(this);
    }

    /**
     * Method that launches the NetworkServer object.
     */
    public void launchServer() {
        try {
            if(!networkServer.isOpened())
                this.networkServer.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that sends a Message object to a specified IP address.
     * @param message message that needs to be sent
     * @param destinationIpAddress IP address of the destination user
     */
    public void sendMessage(Message message, InetAddress destinationIpAddress) {
        NetworkSender networkSender = new NetworkSender(destinationIpAddress, this.getPort(), message);
        networkSender.start();
    }

    /**
     * Method that returns the list of IP addresses that are currently on the network.
     * @return list of IP addresses that are presently on the network
     */
    public List<InetAddress> getIPTable() {
        return new ArrayList<InetAddress>(networkState.values());
    }

    /**
     * Method that recovers the IP address of a specified User object.
     * @param user user object that we want the IP address of.
     * @return IP address of the specified User object
     */
    public InetAddress getAddressForUser(User user) {

        for (User u : networkState.keySet()) {

            if (u.getIdUser().equals(user.getIdUser())) {

                return networkState.get(u);
            }
        }

        return null;
    }
    
    public void setDataInterface(IDataCom IData) {
        this.dataInterface = IData;
        networkServer.setDataInterface(dataInterface);
        networkInterface.setDataInterface(dataInterface);
    }

    public IDataCom getDataInterface() {
        return dataInterface;
    }

    public COMInterface getCOMInterface() {
        return networkInterface;
    }

    public List<InetAddress> filterUnknownIPAddresses(List<InetAddress> iPAddressesTable) {
        List<InetAddress> filteredAddresses = new ArrayList<>();
        for (InetAddress ipAddress : iPAddressesTable) {
//            for(InetAddress i : networkState.values()) {
//                System.out.println(i.equals(ipAddress));
//            }
            if (!ipAddress.equals(networkServer.getIpAddress()) && !networkState.containsValue(ipAddress)) {
                filteredAddresses.add(ipAddress);
            }

        }
        return filteredAddresses;
    }


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

    private boolean addUserToNetwork(User user, InetAddress senderAddress) {
        for (User u : networkState.keySet()) {
            System.out.println(u.getIdUser() + "/" + user.getIdUser());
            if (u.getIdUser().equals(user.getIdUser())) {
                return false;
            }
        }
        System.out.println("Add to network " + user.getIdUser());
        networkState.put(user, senderAddress);
        return true;
    }

    public void addToNetwork(User sender, InetAddress senderAddress, Game game) {
        if (addUserToNetwork(sender, senderAddress)) {
            try {
                dataInterface.addUserToUserList(sender);
                if(game != null && game.getPlayer1().getProfile().getIdUser().equals(sender.getIdUser())) {
                    dataInterface.addNewGameList(game);
                }
            } catch (UnsupportedOperationException e) {

            }
        }
    }

    public void removeFromNetwork(User user, Game game) {
        for (User u : networkState.keySet()) {
            if(user.getIdUser().equals(u.getIdUser())) {
                networkState.remove(u);
                dataInterface.removeUser(u);
                dataInterface.removeGameFromList(game);
                return;
            }
        }
    }

    public void closeListener() {
        networkState.clear();
        networkServer.close();
    }

    public Set<User> getConnectedUsers() {
        return networkState.keySet();
    }

}

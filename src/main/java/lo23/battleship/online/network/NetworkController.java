package lo23.battleship.online.network;

import lo23.battleship.online.network.messages.Message;
import interfacesData.IDataCom;
import structData.Game;
import structData.User;

import java.io.IOException;
import java.net.InetAddress;
import java.util.*;

/** TODO: a completer
 * NetworkController allows to overview the known P2P network which the user is connected to or trying to connect to.
 * It maintains the different IP addresses tables required for the different type of network message sending.
 */
public class NetworkController {

    // CONSTANTS
    private int port = 2345;

    /**
     * Getter.
     * @return port member.
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
     * Getter.
     * @return the unique instance of the NetworkController.
     */
    public static NetworkController getInstance() {
        if (instance == null)
            instance = new NetworkController();

        return instance;
    }

    /**
     * Class constructor.
     */
    private NetworkController() {
        networkInterface = new NetworkModuleInterface(this);
        networkState = new HashMap<>();
        networkInterface = new NetworkModuleInterface(this);
        // Create server
        if (networkServer != null) return;
        this.networkServer = new NetworkServer(this);
    }

    /** TODO: a définir
     * Method to open a thread.
     */
    public void launchServer() {
        try {
            this.networkServer.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to send any network message (Message is an abstract class).
     * @param message the network message to be sent.
     * @param destinationIpAddress the destination user IP address who the message has to be sent.
     */
    public void sendMessage(Message message, InetAddress destinationIpAddress) {
        NetworkSender networkSender = new NetworkSender(destinationIpAddress, this.getPort(), message);
        networkSender.start();
    }

    /**
     * Getter.
     * @return the list of the connected users IP addresses.
     */
    public List<InetAddress> getIPTable() {
        return new ArrayList<InetAddress>(networkState.values());
    }

    /** TODO: a completer
     * Getter.
     * @param user
     * @return
     */
    public InetAddress getAddressForUser(User user) {

        for (User u : networkState.keySet()) {

            if (u.getIdUser().equals(user.getIdUser())) {

                return networkState.get(u);
            }
        }

        return null;
    }

    /**
     * Method to set the dataInterface on the networkServer and networkInterface members.
     * @param IData interface with Data.
     */
    public void setDataInterface(IDataCom IData) {
        this.dataInterface = IData;
        networkServer.setDataInterface(dataInterface);
        networkInterface.setDataInterface(dataInterface);
    }

    /**
     * Getter
     * @return the dataInterface member, this si th interface with data.
     */
    public IDataCom getDataInterface() {
        return dataInterface;
    }

    /**
     * Getter
     * @return networkInterface member.
     */
    public COMInterface getCOMInterface() {
        return networkInterface;
    }

    /**
     * Method to add an IP address to the list of filtered IP addresses of the NetworkController. If one of the
     * addresses of the list given in parameter doesn't belong to the filtered addresses list, it will be added to it.
     * @param iPAddressesTable IP addresses list to be checked by the method.
     * @return the updated list of the filtered addresses.
     */
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

    /** /! a completer
     * Method to filter the IPAddresses to notify by a network message (for example, at the user connection to the
     * network). If one of the addresses of the list given in parameter doesn't belong to the filtered addresses list,
     * it will be added to it.
     * @param iPAddressesTable IP addresses list to be checked by the method.
     * @return the updated list of the filtered addresses.
     */
    public List<InetAddress> filterKnownIPAddressesToNotify(List<InetAddress> iPAddressesTable) {
        List<InetAddress> filteredAddresses = new ArrayList<>();
        for (InetAddress ipAddress : networkState.values()) {
            if (!ipAddress.equals(networkServer.getIpAddress()) && !iPAddressesTable.contains(ipAddress)) {
                filteredAddresses.add(ipAddress);
            }

        }
        return filteredAddresses;
    }

    /**
     * Method to add a user to the P2P network the user is willing to join.
     * @param user the User class to be added to the network.
     * @param senderAddress the sender IP address.
     * @return a boolean (false if the user is already on the network, true is the user is successfully added to the
     * network).
     */
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

    /**
     * Method to add a user to the P2P network and also add the game he has created to the network (to the known list
     * of available and played games).
     * @param sender the User class to be added to the network.
     * @param senderAddress the sender IP address.
     * @param game the game created by the user joining the P2P network.
     */
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

    /**
     * Method to remove a user and its User class from the P2P network.
     * @param user the User class to be removed from the network.
     */
    public void removeFromNetwork(User user) {
        for (User u : networkState.keySet()) {
            if(user.getIdUser().equals(u.getIdUser())) {
                networkState.remove(u);
                dataInterface.removeUser(u);
                return;
            }
        }
    }

    /** TODO: a definir
     * Close the Listener (clean the RAM ?)
     */
    public void closeListener() {
        networkState.clear();
        networkServer.close();
    }

    /**
     * Getter
     * @return the set of network connected users.
     */
    public Set<User> getConnectedUsers() {
        return networkState.keySet();
    }

}

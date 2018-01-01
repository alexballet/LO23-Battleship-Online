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
 * Created by xzirva on 17/10/17.
 * NetworkController
 */
public class NetworkController {

    // CONSTANTS
    private int port;

    public int getPort() {
        return port;
    }

    private NetworkModuleInterface networkInterface;
    private static NetworkController instance;
    private HashMap<User, InetAddress> networkState;
    IDataCom dataInterface;
    private NetworkServer networkServer;


    public static NetworkController getInstance() {
        if (instance == null)
            instance = new NetworkController();

        return instance;

    }

    private NetworkController() {
        networkState = new HashMap<>();
        networkInterface = new NetworkModuleInterface(this);
        // Create server
    }

    public void launchServer() {
        port = dataInterface.getLocalUser().getPort();
        if (networkServer == null)
            this.networkServer = new NetworkServer(this, port);
        this.networkServer.setDataInterface(dataInterface);
        try {
            if(!networkServer.isOpened())
                this.networkServer.open();
        } catch (IOException e) {
            Logger.getLogger("mainLogger").log(Level.SEVERE, "an exception was thrown", e);
        }
    }

    public void sendMessage(Message message, InetAddress destinationIpAddress) {
        NetworkSender networkSender = new NetworkSender(destinationIpAddress, this.getPort(), message);
        networkSender.start();
    }

    public List<InetAddress> getIPTable() {
        return new ArrayList<InetAddress>(networkState.values());
    }

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

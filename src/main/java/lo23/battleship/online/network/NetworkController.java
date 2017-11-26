package lo23.battleship.online.network;

import com.sun.org.apache.xpath.internal.SourceTree;
import data.DataController;
import lo23.battleship.online.network.messages.ConnectionRequestMessage;
import lo23.battleship.online.network.messages.Message;
import structData.Game;
import structData.DataUser;
import interfacesData.IDataCom;
import structData.User;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;

/**
 * Created by xzirva on 17/10/17.
 * NetworkController
 */
public class NetworkController {

    // CONSTANTS
    private int port = 2345;

    public int getPort() {
        return port;
    }

    private NetworkModuleInterface networkInterface;
    private static NetworkController instance;
    // TODO: uncomment when User class is defined
    private HashMap<User, InetAddress> networkState;
    IDataCom dataInterface;
    private NetworkServer networkServer;


    public static NetworkController getInstance() {
        if(instance == null)
            instance = new NetworkController();

        return instance;

    }

    private NetworkController() {
        networkInterface = new NetworkModuleInterface(this);
        networkState = new HashMap<>();
        networkInterface = new NetworkModuleInterface(this);
        // Launch server
        this.launchServer();
    }

    public void launchServer() {
        if(networkServer != null) return;
        this.networkServer = new NetworkServer(this);
        try {
            this.networkServer.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Message message, InetAddress destinationIpAddress) {
        Socket destinationSocket = null;
        NetworkSender networkSender = new NetworkSender(destinationIpAddress, this.getPort(), message);
        networkSender.start();
    }

    public List<InetAddress> getIPTable(){
//        List<InetAddress> ret=new ArrayList<InetAddress>();
//        for(HashMap.Entry<User, InetAddress> entry : networkState.entrySet()){
//            ret.add(entry.getValue());
//        }
//        return ret;
        return new ArrayList<InetAddress>(networkState.values());

    }

    public InetAddress getAddressForUser(User user) {

        for (User u : networkState.keySet()) {

            if (u.getIdUser() == user.getIdUser()) {

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

    public IDataCom getDataInterface(){
        return dataInterface;
    }

    public COMInterface getCOMInterface() {
        return networkInterface;
    }

    public List<InetAddress> filterUnknownIPAddresses(List<InetAddress> iPAddressesTable) {
        List<InetAddress> filteredAddresses = new ArrayList<>();
        for (InetAddress ipAddress : iPAddressesTable) {
            for(InetAddress i : networkState.values()) {
                System.out.println(i.equals(ipAddress));
            }
            if (!networkState.containsValue(ipAddress)) {
                filteredAddresses.add(ipAddress);
            }

        }
        return filteredAddresses;
    }

    public void updateNetwork(User sender, InetAddress senderAddress, Game game) {
            networkState.put(sender, senderAddress);
            dataInterface.addUserToUserList(sender);
        try {

            dataInterface.addNewGameList(game);

        } catch (UnsupportedOperationException e) {

        }
    }

}

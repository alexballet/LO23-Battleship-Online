package lo23.battleship.online.network;

import data.DataController;
import lo23.battleship.online.network.messages.ConnectionRequestMessage;
import lo23.battleship.online.network.messages.Message;
import structData.DataGame;
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
        networkInterface.setDataInterface(dataInterface);
    }

    public void launchServer() {
        this.networkServer = new NetworkServer(this);
        networkServer.setDataInterface(dataInterface);

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

    public void setDataInterface(IDataCom IData) {
        this.dataInterface = IData;
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

            if (!networkState.containsValue(ipAddress)) {
                filteredAddresses.add(ipAddress);
            }

        }
        return filteredAddresses;
    }

    public void updateNetwork(User sender, InetAddress senderAddress, DataGame game) {
        networkState.put(sender, senderAddress);
        dataInterface.addUserToUserList(sender);
        dataInterface.addNewGameList(game);
    }

}

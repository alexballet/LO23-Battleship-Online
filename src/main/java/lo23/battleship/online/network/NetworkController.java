package lo23.battleship.online.network;

import data.DataController;
import lo23.battleship.online.network.messages.ConnectionRequestMessage;
import lo23.battleship.online.network.messages.Message;
import structData.DataUser;
import interfacesData.IDataCom;
import structData.User;

import java.io.IOException;
import java.net.Inet4Address;
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

        this.networkServer = new NetworkServer(this);

        try {
            this.networkServer.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Message message, InetAddress destinationIpAddress) {

        Socket destinationSocket = null;

        try {
            destinationSocket = new Socket(destinationIpAddress, NetworkController.getInstance().getPort());
            NetworkSender networkSender = new NetworkSender(destinationSocket,message);
            networkSender.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void discoverNetwork(HashSet ipsHash) {

    }

    public List<InetAddress> getIPTable(){
        List<InetAddress> ret=new ArrayList<InetAddress>();
        for(HashMap.Entry<User, InetAddress> entry : networkState.entrySet()){
            ret.add(entry.getValue());
        }
        return ret;
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
}

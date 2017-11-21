package lo23.battleship.online.network;

import interfacesData.IDataCom;
import structData.User;
import lo23.battleship.online.network.messages.ConnectionRequestMessage;
import interfacesData.IDataCom;
import lo23.battleship.online.network.messages.CustomMessage;
import lo23.battleship.online.network.messages.Message;
import structData.*;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/**
 * Created by xzirva on 17/10/17.
 * TODO: To be completed with the methods' implementation
 */

public class NetworkModuleInterface implements COMInterface {
    private IDataCom dataInterface;
    private String[] listMessages = {"Connect", "Ready", "Disconnect", "Chat", "RageQuit"};
    private NetworkController controller;
    public NetworkModuleInterface(NetworkController cont) {
        controller = cont;
    }
    public boolean notifyReady(User user) {
        return true;
    }

    public boolean sendChatMessage(String message, DataGame game) {
        return true;
    }

    /**
     * NOTE: this method is only use in test environment
     * @param host
     * @param port
     * @return true= message sent, false= message not sent
     */
    public void sendRandomMessage(String host, int port, User u) {
        System.out.println("TEST");
        System.out.println("host: "+ host + "/" + port);
        //InetAddress address = InetAddress.getByAddress(host)
        //controller.sendMessage(newRandomMessage(u), host);
    }
    public Profile getProfile(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //TODO: To change body of generated methods, choose Tools | Templates.
    }

    public boolean notifyJoinGameResponse(Boolean isOk, User user, DataGame game){
        throw new UnsupportedOperationException("Not supported yet."); //TODO: To change body of generated methods, choose Tools | Templates.
    }

    public boolean notifyNewGame(DataGame game) {
        throw new UnsupportedOperationException("Not supported yet."); //TODO: To change body of generated methods, choose Tools | Templates.
    }
    public boolean joinGame(User user, DataGame game) {
        throw new UnsupportedOperationException("Not supported yet."); //TODO: To change body of generated methods, choose Tools | Templates.
    }

    public boolean askDisconnection(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //TODO: To change body of generated methods, choose Tools | Templates.
    }

    public boolean sendShot(Player player, DataGame game, Shot shot) {
        throw new UnsupportedOperationException("Not supported yet."); //TODO: To change body of generated methods, choose Tools | Templates.
    }

    public void searchForPlayers(User user) {
        // Launch server
        controller.launchServer();

        ConnectionRequestMessage connectionRequestMessage = new ConnectionRequestMessage(user);

        HashSet<InetAddress> knownUsersAddresses = user.getIPs();

        for (InetAddress ipAddress : knownUsersAddresses) {

            controller.sendMessage(connectionRequestMessage, ipAddress);

        }
    }
    public void setDataInterface(IDataCom IData) {
        this.dataInterface = IData;
    }


    // TODO: Delete this when integration is complete
    /*
    public void searchForPlayers(User user) {
        // Launch server
        controller.launchServer();
        sendRandomMessage("172.25.30.230",2345, user);
    }
    */

    //Random Messages
    private Message newRandomMessage(User u){
        Random rand = new Random();
        CustomMessage mes = new CustomMessage(listMessages[rand.nextInt(listMessages.length)].toUpperCase());
        mes.setUser(u);
        return mes;
    }
}

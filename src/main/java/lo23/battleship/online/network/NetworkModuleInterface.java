package lo23.battleship.online.network;

import interfacesData.IDataCom;
import lo23.battleship.online.network.messages.CustomMessage;
import lo23.battleship.online.network.messages.Message;
import structData.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by xzirva on 17/10/17.
 * TODO: To be completed with the methods' implementation
 */
public class NetworkModuleInterface implements COMInterface{
    private IDataCom dataInterface;

    public NetworkModuleInterface(NetworkController cont) {
        controller = cont;
    }
    private String[] listMessages = {"Connect", "Ready", "Disconnect", "Chat", "RageQuit"};
    private NetworkController controller;
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
    public boolean sendRandomMessage(String host, int port, User u) {
        try {
            System.out.println("TEST");
            System.out.println("host: "+ host + "/" + port);
            Socket destinationSocket = new Socket(host, port);
            System.out.println("Before thread");
            System.out.flush();
            NetworkSender t = new NetworkSender(destinationSocket, newRandomMessage(u));
            System.out.println("After thread");
            t.start();
            return true;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Profile getProfile(User user) {
        return null;
    }

    public boolean notifyJoinGameResponse(Boolean isOk, User user, DataGame game){
        return true;
    }

    public boolean notifyNewGame(DataGame game) {
        return true;
    }

    public boolean joinGame(User user, DataGame game) {
        return true;
    }

    public boolean askDisconnection(User user) {
        return true;
    }

    public boolean sendShot(Player player, DataGame game, Shot shot) {
        return true;
    }

    public void setDataInterface(IDataCom IData) {
        this.dataInterface = IData;
    }

    public void searchForPlayers(User user) {
        // Launch server
        controller.launchServer();
        sendRandomMessage("172.25.35.108",2345, user);
    }
    //Random Messages
    private Message newRandomMessage(User u){
        Random rand = new Random();
        CustomMessage mes = new CustomMessage(listMessages[rand.nextInt(listMessages.length)].toUpperCase());
        mes.setUser(u);
        return mes;
    }
}

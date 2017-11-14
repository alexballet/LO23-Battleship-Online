package lo23.battleship.online.network;

import data.DataController;
import lo23.battleship.online.network.messages.ConnectionRequestMessage;
import lo23.battleship.online.network.messages.CustomMessage;
import lo23.battleship.online.network.messages.Message;
import structData.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

/**
 * Created by xzirva on 17/10/17.
 * TODO: To be completed with the methods' implementation
 */

public class NetworkModuleInterface implements COMInterface {

    private String[] listMessages = {"Connect", "Ready", "Disconnect", "Chat", "RageQuit"};

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
    public boolean sendRandomMessage(String host, int port) {
        try {
            System.out.println("TEST");
            Socket destinationSocket = new Socket(host, port);
            Thread t = new Thread(new NetworkSender(destinationSocket, newRandomMessage()));
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

    public void searchForPlayers(ArrayList<InetAddress> knownUsersAddresses, User user) {

        ConnectionRequestMessage connectionRequestMessage = new ConnectionRequestMessage(user);

        for (InetAddress ipAddress : knownUsersAddresses) {

            NetworkController.getInstance().sendMessage(connectionRequestMessage,ipAddress);

        }
    }

    //Random Messages
    private Message newRandomMessage(){
        Random rand = new Random();
        return new CustomMessage(listMessages[rand.nextInt(listMessages.length)].toUpperCase());
    }
}

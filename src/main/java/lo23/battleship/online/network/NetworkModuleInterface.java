package lo23.battleship.online.network;

import interfacesData.IDataCom;
import lo23.battleship.online.network.messages.*;
import structData.User;
import structData.*;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
        throw new UnsupportedOperationException("Not supported yet."); //TODO: To change body of generated methods, choose Tools | Templates.
    }

    public boolean sendChatMessage(String message, Game g) {
        throw new UnsupportedOperationException("Not supported yet."); //TODO: To change body of generated methods, choose Tools | Templates.
    }

    public void getProfile(User userRequested) {

        GetProfileRequestMessage getProfileRequestMessage = new GetProfileRequestMessage(dataInterface.getUserProfile());

        controller.sendMessage(getProfileRequestMessage, controller.getAddressForUser(userRequested));
    }

    public boolean notifyJoinGameResponse(Boolean isOk, User user, Game game) {

        throw new UnsupportedOperationException("Not supported yet."); //TODO: To change body of generated methods, choose Tools | Templates.
    }

    public boolean changeStatusGame(Game game) {

        List<InetAddress> ipAddresses = controller.getIPTable();

        UpdateGameMessage updateGameMessage = new UpdateGameMessage(game);

        for (InetAddress ipAddress : ipAddresses) {

            controller.sendMessage(updateGameMessage, ipAddress);

        }

        return true;
    }

    public boolean notifyNewGame(Game g) {
        List<InetAddress> ipAddresses = controller.getIPTable();

        CreatedGameNotificationMessage createdGameNotification = new CreatedGameNotificationMessage(g);

        for (InetAddress ipAddress : ipAddresses) {

            controller.sendMessage(createdGameNotification, ipAddress);

        }

        return true;
    }

    public boolean joinGame(User user, Game g) {
        InetAddress destinationAddress = controller.getAddressForUser(g.getPlayer1().getProfile());

        JoinGameRequestMessage joinGameRequest = new JoinGameRequestMessage(user, g);

        controller.sendMessage(joinGameRequest, destinationAddress);

        return true;
    }

    public boolean askDisconnection() {

        User user = dataInterface.getLocalUser();
        List<InetAddress> ipAddresses = controller.getIPTable();

        DisconnectionMessage disconnection = new DisconnectionMessage(user);

        for (InetAddress ipAddress : ipAddresses) {

            controller.sendMessage(disconnection, ipAddress);

        }

        return true;
    }


    public boolean sendShot(Player player, Game game, Shot shot) {
        throw new UnsupportedOperationException("Not supported yet."); //TODO: To change body of generated methods, choose Tools | Templates.
    }

    public void searchForPlayers() {

        User user = dataInterface.getLocalUser();
        ConnectionRequestMessage connectionRequestMessage =
                new ConnectionRequestMessage(user, new ArrayList<InetAddress>());

        HashSet<InetAddress> knownUsersAddresses = user.getIPs();

        for (InetAddress ipAddress : knownUsersAddresses) {

            controller.sendMessage(connectionRequestMessage, ipAddress);

        }
    }

    public void setDataInterface(IDataCom IData) {
        this.dataInterface = IData;
    }
}
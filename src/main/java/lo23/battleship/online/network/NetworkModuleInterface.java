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
 */

public class NetworkModuleInterface implements COMInterface {
    private IDataCom dataInterface;
    private String[] listMessages = {"Connect", "Ready", "Disconnect", "Chat", "RageQuit"};
    private NetworkController controller;

    public NetworkModuleInterface(NetworkController cont) {
        controller = cont;
    }

    public boolean notifyReady(User user, Player playerToNotify) {

        NotifyReadyMessage notifyReadyMessage = new NotifyReadyMessage(user, playerToNotify.getprofile());

        controller.sendMessage(notifyReadyMessage, controller.getAddressForUser(playerToNotify.getprofile()));

        return true;
    }

    public boolean sendChatMessage(ChatMessage message, Game g) {

        SendTextMessage sendTextMessage = new SendTextMessage(message);

        HashSet<User> listUsers = g.getListSpectators();

        if (dataInterface.getLocalUser().getIdUser() == g.getPlayer1().getprofile().getIdUser()) {
            listUsers.add(g.getPlayer2().getprofile());
        } else {
            listUsers.add(g.getPlayer1().getprofile());
        }

        for (User user : listUsers) {

            controller.sendMessage(sendTextMessage, controller.getAddressForUser(user));

        }

        return true;
    }

    /**
     * NOTE: this method is only use in test environment
     *
     * @param host
     * @param port
     * @return true= message sent, false= message not sent
     */
    public void sendRandomMessage(String host, int port, User u) {
        System.out.println("TEST");
        System.out.println("host: " + host + "/" + port);
        //InetAddress address = InetAddress.getByAddress(host)
        //controller.sendMessage(newRandomMessage(u), host);
    }

    public void getProfile(User userRequested) {

        GetProfileRequestMessage getProfileRequestMessage = new GetProfileRequestMessage(dataInterface.getUserProfile());

        controller.sendMessage(getProfileRequestMessage, controller.getAddressForUser(userRequested));
    }

    public boolean notifyJoinGameResponse(Boolean isOk, User user, Game game) {

        JoinGameResponseMessage joinGameResponseMessage = new JoinGameResponseMessage(isOk, dataInterface.getLocalUser(), game);

        controller.sendMessage(joinGameResponseMessage, controller.getAddressForUser(user));

        return true;
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
        User user = dataInterface.getLocalUser();
        List<InetAddress> ipAddresses = controller.getIPTable();

        CreatedGameNotificationMessage createdGameNotification = new CreatedGameNotificationMessage(user, g);

        for (InetAddress ipAddress : ipAddresses) {

            controller.sendMessage(createdGameNotification, ipAddress);

        }

        return true;
    }

    public boolean joinGame(User user, Game g) {

        InetAddress destinationAddress = controller.getAddressForUser(g.getPlayer1().getprofile());

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

    public void removeGame(Game game) {

        User user = dataInterface.getLocalUser();
        RemoveGameMessage removeGameMessage =
                new RemoveGameMessage(game);

        HashSet<InetAddress> knownUsersAddresses = user.getIPs();

        for (InetAddress ipAddress : knownUsersAddresses) {

            controller.sendMessage(removeGameMessage, ipAddress);

        }
    }
}
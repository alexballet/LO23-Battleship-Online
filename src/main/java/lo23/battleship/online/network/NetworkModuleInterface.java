package lo23.battleship.online.network;

import interfacesData.IDataCom;
import lo23.battleship.online.network.messages.*;
import structData.User;
import structData.*;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by xzirva on 17/10/17.
 */

public class NetworkModuleInterface implements COMInterface {
    private IDataCom dataInterface;
    private NetworkController controller;

    public NetworkModuleInterface(NetworkController cont) {
        controller = cont;
    }
    
    public boolean notifyReady(User user, Player playerToNotify) {

        NotifyReadyMessage notifyReadyMessage = new NotifyReadyMessage(user, playerToNotify.getProfile());

        controller.sendMessage(notifyReadyMessage, controller.getAddressForUser(playerToNotify.getProfile()));

        return true;
    }

    public boolean sendChatMessage(ChatMessage chatMessage, Game g) {

        SendTextMessage sendTextMessage = new SendTextMessage(chatMessage);

        HashSet<User> listUsers = g.getListSpectators();

        if (dataInterface.getLocalUser().getIdUser() == g.getPlayer1().getProfile().getIdUser()) {
            listUsers.add(g.getPlayer2().getProfile());
        } else {
            listUsers.add(g.getPlayer1().getProfile());
        }

        for (User user : listUsers) {

            controller.sendMessage(sendTextMessage, controller.getAddressForUser(user));

        }

        return true;
    }
    public void getProfile(User userRequested) {

        GetProfileRequestMessage getProfileRequestMessage = new GetProfileRequestMessage(dataInterface.getUserProfile());

        controller.sendMessage(getProfileRequestMessage, controller.getAddressForUser(userRequested));
    }

    
    public boolean notifyJoinGameResponse(Boolean isOk, Profile user, Game game) {
        System.out.println("NMI isok " + isOk);
        JoinGameResponseMessage joinGameResponseMessage = new JoinGameResponseMessage(isOk, dataInterface.getUserProfile(), game);

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
        List<InetAddress> ipAddresses = controller.getIPTable();

        CreatedGameNotificationMessage createdGameNotification = new CreatedGameNotificationMessage(g);

        for (InetAddress ipAddress : ipAddresses) {

            controller.sendMessage(createdGameNotification, ipAddress);

        }

        return true;
    }

    
    public boolean joinGame(Game g) {
        InetAddress destinationAddress = controller.getAddressForUser(g.getPlayer1().getProfile());

        Profile profile = dataInterface.getUserProfile();
        JoinGameRequestMessage joinGameRequest = new JoinGameRequestMessage(profile, g);

        controller.sendMessage(joinGameRequest, destinationAddress);

        return true;
    }

    
    public boolean askDisconnection() {

        User user = dataInterface.getLocalUser();
        List<InetAddress> ipAddresses = controller.getIPTable();

        DisconnectionMessage disconnection = new DisconnectionMessage(user, dataInterface.getCreatedGame());

        for (InetAddress ipAddress : ipAddresses) {

            controller.sendMessage(disconnection, ipAddress);

        }
        controller.closeListener();
        return true;
    }


    
    public boolean sendShot(Player player, Game game, Shot shot) {

        ShotNotificationMessage shotNotificationMessage = new ShotNotificationMessage(shot);

        InetAddress destAddress;

        if (dataInterface.getLocalUser().getIdUser() == game.getPlayer1().getProfile().getIdUser()) {
            destAddress = controller.getAddressForUser(game.getPlayer2().getProfile());
        } else {
            destAddress = controller.getAddressForUser(game.getPlayer1().getProfile());
        }

        controller.sendMessage(shotNotificationMessage, destAddress);

        return true;
    }

    
    public void searchForPlayers() {
        controller.launchServer();
        User user = dataInterface.getLocalUser();
        ConnectionRequestMessage connectionRequestMessage =
                new ConnectionRequestMessage(user, new ArrayList<InetAddress>(), null);

        HashSet<InetAddress> knownUsersAddresses = user.getIPs();
        System.out.println("Sending CRM to " + knownUsersAddresses);
        for (InetAddress ipAddress : knownUsersAddresses) {

            controller.sendMessage(connectionRequestMessage, ipAddress);

        }
    }

    public void setDataInterface(IDataCom IData) {
        this.dataInterface = IData;
    }


    public void notifyGameWon() {

        Player winner = null;
        Game game = dataInterface.getCreatedGame();
        if(dataInterface.getUserProfile().getIdUser().equals(
                game.getPlayer1().getProfile().getIdUser())) {
            winner = game.getPlayer2();
        }
        else {
            winner = game.getPlayer1();
        }


        GameWonMessage gameWonMessage = new GameWonMessage(winner);

        controller.sendMessage(gameWonMessage, controller.getAddressForUser(winner.getProfile()));

    }
    

    public boolean coordinates(Player destPlayer, Shot resultShot, Game game, Boat boat) {

        ShotNotificationResultMessage shotNotificationResultMessage = new ShotNotificationResultMessage(resultShot, boat);

        InetAddress destAddress;

        if (dataInterface.getLocalUser().getIdUser() == game.getPlayer1().getProfile().getIdUser()) {
            destAddress = controller.getAddressForUser(game.getPlayer2().getProfile());
        } else {
            destAddress = controller.getAddressForUser(game.getPlayer1().getProfile());
        }

        controller.sendMessage(shotNotificationResultMessage, destAddress);

        return true;
    }
    
    public void removeGame(Game game) {
        List<InetAddress> ipAddresses = controller.getIPTable();
        GameQuitMessage gameQuitMessage = new GameQuitMessage(game);

        for (InetAddress ipAddress : ipAddresses) {

            controller.sendMessage(gameQuitMessage, ipAddress);

        }
    }
}
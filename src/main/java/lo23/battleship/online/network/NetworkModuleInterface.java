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
 *
 * This class implements the COMInterface interface
 * and the different methods (services) it offers.
 * @author COM Module
 *
 * @see COMInterface
 */

public class NetworkModuleInterface implements COMInterface {
    private IDataCom dataInterface;
    private NetworkController controller;

    /**
     * Allocates new {@code NetworkModuleInterface} object
     * @param cont : {@code NetworkController}
     *             Network Controller instance
     * */
    NetworkModuleInterface(NetworkController cont) {
        controller = cont;
    }

    @Override
    public void notifyReady(User user, Player playerToNotify) {
        NotifyReadyMessage notifyReadyMessage = new NotifyReadyMessage(user, playerToNotify.getProfile());

        controller.sendMessage(notifyReadyMessage,
                controller.getAddressForUser(playerToNotify.getProfile()));
    }

    @Override
    public void sendChatMessage(ChatMessage chatMessage, Game g) {
        SendTextMessage sendTextMessage = new SendTextMessage(chatMessage);
        HashSet<User> listUsers = (HashSet<User>) g.getListSpectators().clone();

        //Local user is player1
        if (dataInterface.getLocalUser().getIdUser().equals(g.getPlayer1().getProfile().getIdUser())) {
            listUsers.add(g.getPlayer2().getProfile());
        }
        //Local user is player2
        else if(dataInterface.getLocalUser().getIdUser().equals(g.getPlayer1().getProfile().getIdUser())) {
            listUsers.add(g.getPlayer1().getProfile());
        }
        //Local user is a spectator
        else {
            listUsers.add(g.getPlayer2().getProfile());
            listUsers.add(g.getPlayer1().getProfile());
        }

        for (User user : listUsers) {
            controller.sendMessage(sendTextMessage, controller.getAddressForUser(user));
        }
    }

    @Override
    public void getProfile(User userRequested) {
        GetProfileRequestMessage getProfileRequestMessage =
                new GetProfileRequestMessage(dataInterface.getUserProfile());

        controller.sendMessage(getProfileRequestMessage,
                controller.getAddressForUser(userRequested));
    }

    @Override
    public void notifyJoinGameResponse(boolean isOk, Profile user, Game game) {
        JoinGameResponseMessage joinGameResponseMessage = new JoinGameResponseMessage(isOk, dataInterface.getUserProfile(), game);
        controller.sendMessage(joinGameResponseMessage, controller.getAddressForUser(user));
    }

    @Override
    public void changeStatusGame(Game game) {
        List<InetAddress> ipAddresses = controller.getIPTable();
        UpdateGameMessage updateGameMessage = new UpdateGameMessage(game);

        for (InetAddress ipAddress : ipAddresses) {
            controller.sendMessage(updateGameMessage, ipAddress);
        }
    }

    @Override
    public void notifyNewGame(Game g) {
        List<InetAddress> ipAddresses = controller.getIPTable();
        CreatedGameNotificationMessage createdGameNotification = new CreatedGameNotificationMessage(g);

        for (InetAddress ipAddress : ipAddresses) {
            controller.sendMessage(createdGameNotification, ipAddress);
        }
    }

    @Override
    public void joinGame(Game g) {
        InetAddress destinationAddress = controller.getAddressForUser(g.getPlayer1().getProfile());
        Profile profile = dataInterface.getUserProfile();
        JoinGameRequestMessage joinGameRequest = new JoinGameRequestMessage(profile, g);
        controller.sendMessage(joinGameRequest, destinationAddress);
    }

    @Override
    public void askDisconnection() {
        User user = dataInterface.getLocalUser();
        List<InetAddress> ipAddresses = controller.getIPTable();
        DisconnectionMessage disconnection = new DisconnectionMessage(user, dataInterface.getCreatedGame());

        for (InetAddress ipAddress : ipAddresses) {
            controller.sendMessage(disconnection, ipAddress);
        }

        controller.closeListener();
    }

    @Override
    public void sendShot(Player player, Game game, Shot shot) {
        ShotNotificationMessage shotNotificationMessage = new ShotNotificationMessage(shot);
        InetAddress destAddress;

        if (dataInterface.getLocalUser().getIdUser().equals(game.getPlayer1().getProfile().getIdUser())) {
            destAddress = controller.getAddressForUser(game.getPlayer2().getProfile());
        } else {
            destAddress = controller.getAddressForUser(game.getPlayer1().getProfile());
        }

        controller.sendMessage(shotNotificationMessage, destAddress);
    }

    @Override
    public void searchForPlayers() {
        controller.launchServer();
        User user = dataInterface.getLocalUser();
        ConnectionRequestMessage connectionRequestMessage =
                new ConnectionRequestMessage(user, new ArrayList<>(), null);

        HashSet<InetAddress> knownUsersAddresses = user.getIPs();
        System.out.println("Sending CRM to " + knownUsersAddresses);

        for (InetAddress ipAddress : knownUsersAddresses) {
            controller.sendMessage(connectionRequestMessage, ipAddress);
        }
    }

    @Override
    public void notifyGameWon() {
        Player winner;
        Game game = dataInterface.getCreatedGame();
        if(dataInterface.getUserProfile().getIdUser().equals(
                game.getPlayer1().getProfile().getIdUser())) {
            winner = game.getPlayer2();
        }
        else {
            winner = game.getPlayer1();
        }

        GameWonMessage gameWonMessage = new GameWonMessage(winner);
        GameWonMessageToSpectator gameWonMessageToSpectator = new GameWonMessageToSpectator(winner);
        HashSet<User> listSpectators = game.getListSpectators();
        for(User spec : listSpectators) {
            InetAddress address = controller.getAddressForUser(spec);
            System.out.println(" ICI : ADRESSE -> " + address.getHostAddress() + " - username " + spec.getUsername());
            controller.sendMessage(gameWonMessageToSpectator, address);
        }
        controller.sendMessage(gameWonMessage, controller.getAddressForUser(winner.getProfile()));
    }

    @Override
    public void coordinates(Player destPlayer, Shot resultShot, Game game, Boat boat) {
        ShotNotificationResultForSpectatorMessage messageToSpectators =
                    new ShotNotificationResultForSpectatorMessage(destPlayer, resultShot, boat);
        ShotNotificationResultMessage shotNotificationResultMessage = new ShotNotificationResultMessage(resultShot, boat);
        HashSet<User> listSpectator = game.getListSpectators();

        InetAddress destAddress;

        for (User s : listSpectator) {
            controller.sendMessage(messageToSpectators, controller.getAddressForUser(s));
        }

        if (dataInterface.getLocalUser().getIdUser().equals(game.getPlayer1().getProfile().getIdUser())) {
            destAddress = controller.getAddressForUser(game.getPlayer2().getProfile());
        } else {
            destAddress = controller.getAddressForUser(game.getPlayer1().getProfile());
        }
        controller.sendMessage(shotNotificationResultMessage, destAddress);
    }

    @Override
    public void removeGame(Game game) {
        List<InetAddress> ipAddresses = controller.getIPTable();
        GameQuitMessage gameQuitMessage = new GameQuitMessage(game);

        for (InetAddress ipAddress : ipAddresses) {

            controller.sendMessage(gameQuitMessage, ipAddress);

        }
    }

    @Override
    public void quitGame() {
        Game game = dataInterface.getCreatedGame();
        User otherUser = dataInterface.getOtherPlayer().getProfile();
        InetAddress address = controller.getAddressForUser(otherUser);
        RageQuitMessage message = new RageQuitMessage(game);
        controller.sendMessage(message, address);
    }

    @Override
    public void getInfoGameForSpectator(Player player, User spec) {

        InetAddress address = controller.getAddressForUser(player.getProfile());
        GetInfoGameForSpectatorMessage message =
                new GetInfoGameForSpectatorMessage(player, spec);
        controller.sendMessage(message, address);
    }

    @Override
    public void sendInfoGameForSpectator(Game game, User spec) {

        InetAddress address = controller.getAddressForUser(spec);
        SendInfoGameForSpectatorMessage sendInfoGameForSpectatorMessage =
                new SendInfoGameForSpectatorMessage(game, spec);
        controller.sendMessage(sendInfoGameForSpectatorMessage, address);
    }

    @Override
    public void sendNewSpectator(User u, Player p, HashSet<User> listSpectators)  {

        SendNewSpectatorMessage sendNewSpectatorMessage =
                new SendNewSpectatorMessage(u);
        InetAddress otherPlayerAddress = controller.getAddressForUser(p.getProfile());
        System.out.println("Sending sendNewSpectatorMessage to otherPlayer : " + otherPlayerAddress);
        controller.sendMessage(sendNewSpectatorMessage, otherPlayerAddress);
        for(User spec : listSpectators) {
            InetAddress address = controller.getAddressForUser(spec);
            controller.sendMessage(sendNewSpectatorMessage, address);
        }
    }

    @Override
    public void gameQuitSpectator(User spec, Game game) {

        GameQuitSpectatorMessage gameQuitSpectatorMessage =
                new GameQuitSpectatorMessage(game, spec);
        HashSet<User> listSpectators = game.getListSpectators();


        User userPlayer1 = game.getPlayer1().getProfile();
        InetAddress address = controller.getAddressForUser(userPlayer1);
        System.out.println("Sending gameQuitSpectator to player1 : " + address);
        controller.sendMessage(gameQuitSpectatorMessage, address);


        User userPlayer2 = game.getPlayer1().getProfile();
        address = controller.getAddressForUser(userPlayer2);
        System.out.println("Sending gameQuitSpectator to player2 : " + address);
        controller.sendMessage(gameQuitSpectatorMessage, address);


        for(User otherSpec : listSpectators) {
            address = controller.getAddressForUser(otherSpec);
            System.out.println("Sending gameQuitSpectator to spectator: " + address);
            controller.sendMessage(gameQuitSpectatorMessage, address);
        }

    }

    @Override
    public void clearNetwork() {
        controller.clearNetwork();
    }

    /**
     * Sets the IDataCom instance (to call IDataCom services(methods))
     * */
    void setDataInterface(IDataCom IData) {
        this.dataInterface = IData;
    }
}
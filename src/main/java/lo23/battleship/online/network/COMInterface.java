package lo23.battleship.online.network;

import structData.*;

import java.util.HashSet;


/**
 * Data interface
 * @author xzirva
 */


public interface COMInterface {
    /**
     * Notify if a player is ready or not
     * @param user player who is notify
     * @return true= message sent, false= message not sent
     * */
    public boolean notifyReady(User user, Player playerToNotify);

    /**
     * Send a chat message
     * @param chatMessage message send
     * @param g : game related to the message
     * @return true= message sent, false= message not sent
     */
    public boolean sendChatMessage(ChatMessage chatMessage, Game g);

    /**
     * allow to view an user profile
     * @param user
     * @return the profile(statistics) of the user
     */
    public void getProfile(User user);

    /**
     * update game object
     * @param game
     * @return true= message sent, false= message not sent
     */
    public boolean changeStatusGame(Game game);

    /**
     * notify a new game
     * @param g : newly created game with one player
     * @return true= message sent, false= message not sent
     */
    public boolean notifyNewGame(Game g);

    /**
     * allow an user to join a game
     * @param user who want to join the game
     * @param g Game <code>user</code> wants to join
     * @return true= message sent, false= message not sent
     */
    public boolean joinGame(Game g);

    /**
     * allow an user to join a game
     * @param isOk access to <code>game</code> true=access granted false= access denied

     * @param user who asked to join the game
     * @param g Game <code>user</code> joined if isOk
     * @return true= message sent, false= message not sent
     */
    public boolean notifyJoinGameResponse(Boolean isOk, Profile user, Game g);

    /**
     * allow an user to be disconnected to the network
     * @param user who want to be disconnected
     * @return true= message sent, false= message not sent
     */
    public boolean askDisconnection();

    /**
     * send a shot from a player on the right game
     * @param player who send the shot
     * @param g where the ships are
     * @param shot where the player shot
     * @return true= message sent, false= message not sent
     */
    public boolean sendShot(Player player, Game g, Shot shot);

    /**
     * send a shot result to a player on the right game
     * @param game where the ships are
     * @param resultShot result of the shot
     * @param boat optional
     * @return true= message sent, false= message not sent
     */
    public boolean coordinates(Player destPlayer, Shot resultShot, Game game, Boat boat);

    /**
     * search for players who are connected
     * @param user User that is connecting
     * @return void
     */
    public void searchForPlayers();

    /**
     * notifies every user to remove the game from their list
     * @param game Game to delete
     */
    public void removeGame(Game game);

    /**
     * notifies every user to remove the game from their list
     * @param game Game to delete
     */
    public void notifyGameWon();

    /**
     * spectator send a request to a player in order to have the game informations
     * @param player player game
     * @param spec spectator who ask for request
     */
    public void getInfoGameForSpectator(Player player, User spec);

    /**
     * player send the game to the spectator after his request
     * @param game game send to the spectator
     * @param spec spectator who want to see the game
     */
    public void sendInfoGameForSpectator(Game game, User spec);

    /**
     * alert everybody (the other player and the spectator) that there is a new spectator
     * @param u new spectator
     * @param p other player
     * @param listSpectator list of all actual spectators
     */
    public void sendNewSpectator(User u, Player p, HashSet<User> listSpectator);

    /**
    * alert everybody that the spectator quit the game
    * @param spec spectator who quit the game
    * @param game game quit by the spectator (permit to retrieve player and listSpectator)
    */
    public void gameQuitSpectator(User spec, Game game);

    /**
     * Quit game already launch
     */
    public void quitGame();
}

package lo23.battleship.online.network;

import structData.*;


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
    public boolean notifyReady(User user);

    /**
     * Send a chat message
     * @param message message send
     * @param g : game related to the message
     * @return true= message sent, false= message not sent
     */
    public boolean sendChatMessage(String message, Game g);

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
     * search for players who are connected
     * @param user User that is connecting
     * @return list of all users who are connected
     */
    public void searchForPlayers();


    /**
     * */
    public void removeGame(Game game);
}

package lo23.battleship.online.network;

import structData.Game;
import structData.Profile;
import structData.Shot;
import structData.User;

import java.util.ArrayList;

/**
 * Data interface
 * @author xzirva
 */


public interface Interface {
    /**
     * Notify if a player is ready or not
     * @param user player who is notify
     * @return true= ready, false= notready
     * */
    public boolean notifyReady(User user);

    /**
     * Send a chat message
     * @param message message send
     * @return true= received, false= not received
     */
    public boolean sendChatMessage(String message);

    /**
     * allow to view an user profile
     * @param user
     * @return the profile of the user
     */
    public Profile getProfile(User user);

    /**
     * notify a new game
     * @param user who create the game
     * @param game : all the parameters of the game
     * @return true= send succeed, false= send failed
     */
    public boolean notifyNewGame(User user, Game game);

    /**
     * allow an user to join a game
     * @param user who want to join the game
     * @return true= join succeed, false= join failed
     */
    public boolean joinGame(User user);

    /**
     * allow an user to be disconnected to the network
     * @param user who want to be disconnected
     * @return true= disconnection succeed, false= disconnection failed
     */
    public boolean askDisconnection(User user);

    /**
     * send a shot from a player on the right game
     * @param player who send the shot
     * @param game where the ships are
     * @param shot where the player shot
     * @return true= send succeed, false= send failed
     */
    public boolean sendShot(User player, Game game, Shot shot);

    /**
     * search for players who are connected
     * @param user who send the request
     * @return list of all users who are connected
     */
    public ArrayList<User> searchForPlayers(User user);
}

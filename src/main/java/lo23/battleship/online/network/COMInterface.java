package lo23.battleship.online.network;

import structData.*;

import javax.xml.crypto.Data;
import java.net.InetAddress;
import java.util.ArrayList;

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
     * @return true= message sent, false= message not sent
     */
    public boolean sendChatMessage(String message, DataGame game);

    /**
     * allow to view an user profile
     * @param user
     * @return the profile(statistics) of the user
     */
    public Profile getProfile(User user);

    /**
     * notify a new game
     * @param game : newly created game with one player
     * @return true= message sent, false= message not sent
     */
    public boolean notifyNewGame(DataGame game);

    /**
     * allow an user to join a game
     * @param user who want to join the game
     * @param game Game <code>user</code> wants to join
     * @return true= message sent, false= message not sent
     */
    public boolean joinGame(User user, DataGame game);

    /**
     * allow an user to join a game
     * @param isOk access to <code>game</code> true=access granted false= access denied
     * @param user who created the game and send the response to the join request
     * @param game Game <code>user</code> joined if isOk
     * @return true= message sent, false= message not sent
     */
    public boolean notifyJoinGameResponse(Boolean isOk, User user, DataGame game);

    /**
     * allow an user to be disconnected to the network
     * @param user who want to be disconnected
     * @return true= message sent, false= message not sent
     */
    public boolean askDisconnection(User user);

    /**
     * send a shot from a player on the right game
     * @param player who send the shot
     * @param game where the ships are
     * @param shot where the player shot
     * @return true= message sent, false= message not sent
     */
    public boolean sendShot(Player player, DataGame game, Shot shot);

    /**
     * search for players who are connected
     * @param knownUsersIPAddresses Initially known users' IPAddresses
     * @return list of all users who are connected
     */
    public void searchForPlayers(User user);
}

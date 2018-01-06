package lo23.battleship.online.network;

import structData.*;

import java.util.HashSet;


/**
 * The COMInterface compiles the services offered by the network package.
 * It should be implemented by the actual class which will be use to call those services
 */


public interface COMInterface {
    /**
     * Notify if a player is ready or not
     * @param user player who is notify
     * */
    void notifyReady(User user, Player playerToNotify);

    /**
     * Send a chat message
     * @param chatMessage message send
     * @param g : game related to the message
     */
    void sendChatMessage(ChatMessage chatMessage, Game g);

    /**
     * allow to view an user profile
     * @param user : User of which local user asks for profile
     */
    void getProfile(User user);

    /**
     * update game object
     * @param game : Game of which the status changed
     */
    void changeStatusGame(Game game);

    /**
     * notify a new game
     * @param g : newly created game with one player
     */
    void notifyNewGame(Game g);

    /**
     * allow an user to join a game
     * @param g Game the local user wants to join
     */
    void joinGame(Game g);

    /**
     * allow an user to join a game
     * @param isOk access to <code>game</code> true=access granted false= access denied

     * @param user who asked to join the game
     * @param g Game <code>user</code> joined if isOk
     */
    void notifyJoinGameResponse(boolean isOk, Profile user, Game g);

    /**
     * allow an user to be disconnected to the network
     */
    void askDisconnection();

    /**
     * send a shot from a player on the right game
     * @param player : {@code Player}
     *               who send the shot
     * @param g : {@code Game}
     *          where the ships are
     * @param shot : {@code Shot}
     *             where the player shot
     */
    void sendShot(Player player, Game g, Shot shot);

    /**
     * send a shot result to a player on the right game
     * @param destPlayer destination player
     * @param game where the ships are
     * @param resultShot result of the shot
     * @param boat optional
     */
    void coordinates(Player destPlayer, Shot resultShot, Game game, Boat boat);

    /**
     * Initiates network discovery. Search for players who are connected
     */
    void searchForPlayers();

    /**
     * notifies every user to remove the game from their list
     * @param game Game to delete
     */
    void removeGame(Game game);

    /**
     * notifies every user to remove the game from their list
     */
    void notifyGameWon();

    /**
     * spectator send a request to a player in order to have the game informations
     * @param player player game
     * @param spec spectator who ask for request
     */
    void getInfoGameForSpectator(Player player, User spec);

    /**
     * player send the game to the spectator after his request
     * @param game game send to the spectator
     * @param spec spectator who want to see the game
     */
    void sendInfoGameForSpectator(Game game, User spec);

    /**
     * alert everybody (the other player and the spectator) that there is a new spectator
     * @param u new spectator
     * @param p other player
     * @param listSpectator list of all actual spectators
     */
    void sendNewSpectator(User u, Player p, HashSet<User> listSpectator);

    /**
    * alert everybody that the spectator quit the game
    * @param spec spectator who quit the game
    * @param game game quit by the spectator (permit to retrieve player and listSpectator)
    */
    void gameQuitSpectator(User spec, Game game);

    /**
     * Quit game already launch
     */
    void quitGame();

    /**
     * Clears network state : removes all players from local user list of connected players
     * */
    void clearNetwork();
}

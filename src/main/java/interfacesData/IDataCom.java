/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesData;

import structData.Boat;
import structData.Shot;
import structData.ChatMessage;
import structData.User;
import structData.Game;
import structData.Player;
import structData.Profile;

/**
 * Data's interface for Com
 * @author Lucie
 */
public interface IDataCom {
    /**
     * Returns the current Game
     * @return the current Game
     */
    public Game getCreatedGame();

    /**
     * The distant user has accepted or not the request to join the game and the 
     * method updateGameData will be used to update the game data
     * @param ok : Acceptance of the request to join the game
     * @param player1 : Creator of the game
     * @param player2 : The player who joins the game
     */
    public void setGameJoinResponse(Boolean ok, Player player1, Player player2);

    /**
     * The distance user has refused the request to join the game
     * @param no : Refuse of the request to join the game
     */
    public void setGameJoinResponse(Boolean no);

    /**
     * After an user has connected, this user will be added to the list of user
     * @param u : The new user
     */
    public void addUserToUserList(User u);

    /**
     * Sends the profile of a distant user to the local user so that the local 
     * user can see the profile of this distant user
     * @param p The profile to be sent
     */
    public void sendStatistics(Profile p);

    /**
     * Add the player to the game if it is available.
     * @param sender : The player who sends this request
     * @param g : The game that the player wants to join
     */
    public void notifToJoinGame(Profile sender, Game g);

     /**
      * Adds the game given as a parameter to the list of games.
      * @param g : The new game
      */
     public void addNewGameList(Game g);
     
     /**
      * Removes the game given as a parameter from the list of games.
      * @param g : game to remove
      */
     public void removeGameFromList(Game g);

    /**
     * Takes the error message given as a parameter in order to transmit it to 
     * IHM-Table or IHM-Main
     * @param error : The error message to transmit
     */
    public void errorPrint(String error);

    /**
     * Takes the chat message given as a parameter in order to transmit it to 
     * IHM-Table
     * @param message : The chat message to transmit
     */
    public void receiveMessage(ChatMessage message);

    /**
     * Indicates that a player is ready to play (all his boats are placed on 
     * his table) so that the shots phase can be displayed
     */
    public void receiveReady();

    /**
     * Takes a Shot and in option a Boat to transmit it to IHM-Table
     * @param s : The position played by the user
     * @param b : In option, the boat that was sunk
     */
    public void coordinates(Shot s, Boat b);
    
    /**
     * Takes a Shot to transmit it to IHM-Table
     * @param s : The position played by the user
     */
    public void coordinates(Shot s);

    /**
     * Returns the local user's profile, containing his statistics
     * @return the local user's profile
     */
    public Profile getUserProfile();

    /**
     * Takes a game given as a parameter and updates his status
     * @param g : The game which status has to be modified
     */
    public void changeStatusGame(Game g);

    /**
     * Accessor for the local User
     * @return public void removeUser(User u)
     */
    public User getLocalUser();
    
    /**
     * To remove a User
     * @param u User to remove
     */
    public void removeUser(User u);

    /**
     * Set the local Game with the game given as a parameter
     * @param g : new value for the local Game
     */
    public void setLocalGame(Game g);
    
    /**
     * Remove a Game from local list
     * @param g : Game to remove
     */
    public void removeGame(Game g);
     
     /**
      * Notification that you won, update stats and display win
      */
     public void notifiedGameWon();
     

      /**
      * Notify that a new spectator has joined the game
      * @param spec New spectator
      */
     public void notifyToSpecGame(User spec);
     
     /**
      * A new spectator want to join the game, he need to get the informations of the game
      * @param u The spectator who want to come
      */
     public void newRequestSpectator(User u);
     
     /**
      * The spectator receives the informations of the game that he wants to join
      * @param g The joined game
      */
     public void joinGameSpectator(Game g);
     
     /**
      * Notif everyone when a spectator leaves
      * @param spec The spectator who leaves
      */
     public void notifyQuitSpectator(User spec);

     public Player getOtherPlayer();

     public void updateAttendedGame(Player p, Shot s, Boat b, boolean gameOver);

}

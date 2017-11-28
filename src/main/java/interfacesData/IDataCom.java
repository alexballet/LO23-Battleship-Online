/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesData;

import structData.Boat;
import structData.Shot;
import structData.Position;
import structData.ChatMessage;
import structData.User;
import structData.Game;
import structData.Player;
import structData.Profile;
import java.util.Set;
import java.util.List;

/**
 * Data's interface for Com
 * @author Lucie
 */
public interface IDataCom {
    /**
     * Sends known user's IP table to new user and the current game if the new user is playing a game
     * @param withGame : if (withGame==true) return the game the user is playing
     * @param iPs (returned) : ipadresses known by local user to send to send to a new user
     * @param dataGame (returned) : the game the user is playing
     */
    // TODO Change void to struct containing a Set and DataGame
     public void getIPTableAdresses(Boolean withGame, Set iPs, Game dataGame);


    // TODO Create method to return current user game if exists : getCurrentUserGame
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
      * @return 1 if the parameter game is an avaiable game and add the player 
      * to this game, 0 if not
      */
     public void notifToJoinGame(User sender, Game g);

     /**
      * Adds the game given as a parameter to the list of games.
      * @param g : The new game
      */
     public void addNewGameList(Game g);

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
      * his table) so that the shots
phase can be displayed
      */
     public void receiveReady();

     /**
      * Takes a position to transmit it to IHM-Table. Returns a Shot and if a 
      * boat was sunk, it returns the object Boat
      * @param p : The position played by the user
      * @param s (returned) : shot corresponding to the position
      * @param b (optionnally returned) : The sunk boat
      */
     public void coordinate(Position p, Shot s, Boat b);

     /**
      * Takes a Shot and in option a Boat to transmit it to IHM-Table
      * @param s : The position played by the user
      * @param b : In option, the boat that was sunk
      */
     public void coordinates(Shot s, Boat b);

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

     public User getLocalUser();
}



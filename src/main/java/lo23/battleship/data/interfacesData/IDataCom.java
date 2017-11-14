/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lo23.battleship.data.interfacesData;

import lo23.battleship.data.packageStructDonnées.Boat;
import lo23.battleship.data.packageStructDonnées.User;
import lo23.battleship.data.packageStructDonnées.Shot;
import lo23.battleship.data.packageStructDonnées.Profile;
import lo23.battleship.data.packageStructDonnées.Message;
import lo23.battleship.data.packageStructDonnées.DataGame;
import lo23.battleship.data.packageStructDonnées.Position;
import lo23.battleship.data.packageStructDonnées.Game;
import java.util.HashSet;
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
     * @param IPs (returned) : ipadresses known by local user to send to send to a new user
     * @param dataGame (returned) : the game the user is playing
     */
     public void getIPTableAdresses(Boolean withGame, HashSet IPs, DataGame dataGame);

     /**
      * Returns all of the created games
      * @return the list of  games
      */
     public List<DataGame> getCreatedGames();

     /**
      * If there is a new game, the list of games in the local application should be updated
      * @param createdGame : New games
      */
     public void addGame(List<DataGame> createdGames);

     /**
      * If there is a new user, the list of users in the local application should be updated
      * @param New users
      */
     public void addUser(Set<User> users);

     /**
      * The distant user has accepted the request to join the game and the method updateGameData
will be used to update the game data
      * @param ok : Acceptance of the request to join the game
      * @param player1 : Creator of the game
      * @param player2 : The player who joins the game
      */
     public void setGameJoinResponse(Boolean ok, User player1, User player2);

     /**
      * The distant user has refused the request to join the game
      * @param no : Refuse of the request to join the game
      */
     public void setGameJoinResponse(Boolean no);

     /**
      * After an user has connected, this user will be added to the list of user
      * @param user : The new user
      */
     public void addUserToUserList(User user);

     /**
      * Sends the profile of a distant user to the local user so that the local user can see the profile of
this distant user
      * @param profile The profil to be sent
      */
     public void sendStatistics(Profile profile);

     /**
      * Add the player to the game if it is available.
      * @param sender : The player who sends this request
      * @param game : The game that the player wants to join
      * @return 1 if the parameter game is an avaiable game and add the player to this game, 0 if not
      */
     public Boolean notifToJoinGame(User sender, Game game);

     /**
      * Adds the game given as a parameter to the list of games.
      * @param game : The new game
      */
     public void addNewGameList(Game game);

     /**
      * Takes the error message given as a parameter in order to transmit it to IHM-Table or IHM-Main
      * @param error : The error message to transmit
      */
     public void errorPrint(String error);

     /**
      * Takes the chat message given as a parameter in order to transmit it to IHM-Table
      * @param message : The chat message to transmit
      */
     public void receiveMessage(Message message);

     /**
      * Indicates that a player is ready to play (all his boats are placed on his table) so that the shots
phase can be displayed
      */
     public void receiveReady();

     /**
      * Takes a position to transmit it to IHM-Table. Returns a Shot and if a boat was sunk, it returns
the object Boat
      * @param position : The position played by the user
      * @param shot (returned) : shot corresponding to the position
      * @param boat (optionnally returned) : The sunk boat
      */
     public void coordinate(Position position, Shot shot, Boat boat);

     /**
      * Takes a Shot and in option a Boat to transmit it to IHM-Table
      * @param shot : The position played by the user
      * @param boat : In option, the boat that was sunk
      */
     public void coordinates(Shot shot, Boat boat);

     /**
      * Returns the local userâ€™s profile, containing his statistics
      * @return the local userâ€™s profile
      */
     public Profile getUserProfile();

     /**
      * Takes a game given as a parameter and updates his status
      * @param game : The game which status has to be modified
      */
     void changeStatusGame(Game game);
}



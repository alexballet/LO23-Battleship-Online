/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesData;

import java.net.UnknownHostException;
import java.util.Date;
import structData.ContactGroup;
import structData.Game;
import structData.User;
import java.util.HashSet;
import java.util.List;
import structData.Profile;

/**
 * Data's interface for Main
 */
public interface IDataMain {
    
    /**
     * Modify the local profile
     * @param username : new username
     * @param password : new password
     * @param avatar : new avatar
     * @param lastName : new lastName
     * @param firstName : new firstName
     * @param borthDate : new birthDate
     */
    void editProfile(String username, String password, 
            String avatar, String lastName, String firstName, Date borthDate);
    
    /**
     * Create a local account
     * @param login : the user's login
     * @param username : the user's username
     * @param ips : list of the IP adresses known by the user
     * @param port : the port
     * @param password : the user's password
     * @param contactList : list of the user's contacts
     * @param avatar : a path to the user's avatar
     * @param lastname : the user's lastname
     * @param firstname : the user's firstname
     * @param birthDate : the user's birthdate
     */
    void createAccount(String login, String username, HashSet ips, int port, 
            String password, List<ContactGroup> contactList, String avatar, 
            String lastname, String firstname, Date birthDate);
        
    /**
     * Returns the profile of an user
     * @param u the user
     */
    public void getProfile(User u);
    
    /**
     * Notifies the away application that an user wants to join a game
     * @param g : Game the user wants to join
     */
    void notifGameChosen(Game g);
    
    /**
     * Notifies away applications that the local user disconnects and 
     * erases his session.
     */
    void askDisconnection();
    
    /**
     * Loads the saved data of the user and researches players.
     * @param login the login
     * @param password the password
     * @return a boolean
     * @throws java.net.UnknownHostException
     */
    Boolean connection(String login, String password) throws UnknownHostException;
    
    /**
     * Add a new game to the list of games
     * @param newClassicType the type
     * @param newName the name
     * @param newHumanOpponent if is against a human or not
     * @param newTimePerShot the time per shot
     * @param newTimeToPlaceBoats the time to place boats
     * @param newSpectator if spectators are allowed
     * @param newSpectatorChat if chat is allowed
     * @return the new game
     */
    Game newGame(Boolean newClassicType, String newName, 
            Boolean newHumanOpponent, Integer newTimePerShot, Integer newTimeToPlaceBoats,
            Boolean newSpectator, Boolean newSpectatorChat);
    
    /**
     * Remove a Game from local list
     * @param g : Game to remove
     */
    public void removeGame(Game g);
    
    /**
     * 
     * @return the local list of games
     */
    public List<Game> getGames();

    /**
     * 
     * @return the local profile
     */
    Profile getLocalProfile();
    
    /**
     * Set the local game
     * @param g : game to set
     */
    public void setLocalGame(Game g);
    
    /**
     * Set the list of IP adress
     * @param Ips : list of IP
     */
    public void setListIps(HashSet Ips);

    /**
     * Add a spectator in the game
     * @param g : game that the spectator wants to join
     */
    void gameToSpec(Game g);
    
    /**
     * Set the port
     * @param p : the port to set
     */
    void setPort(int p);
    
    /**
     * 
     * @return the local list of users
     */
    public List<User> getListUsers();

    public void clear();
}

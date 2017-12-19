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
 *
 * @author Irvin
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
     * @param idUser : unique ID of the user
     * @param login : the user's login
     * @param username : the user's username
     * @param ips : list of the IP adresses known by the user
     * @param password : the user's password
     * @param contactList : list of the user's contacts
     * @param avatar : a path to the user's avatar
     * @param lastname : the user's lastname
     * @param firstname : the user's firstname
     * @param birthDate : the user's birthdate
     */
    void createAccount(String login, String username, HashSet ips, 
            String password, List<ContactGroup> contactList, String avatar, 
            String lastname, String firstname, Date birthDate);
    
    /**
     * Returns the statistics of an user
     * @return a profile
     */
    public void getStatistics(Profile p);
    
    /**
     * Returns the profile of an user
     * @return a profile
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
     */
    Boolean connection(String login, String password) throws UnknownHostException;
    
    /**
     * Add a new game to the list of games
     * @param g : game to add
     */
    Game newGame(Boolean newClassicType, String newName, 
            Boolean newHumanOpponent, Integer newTimePerShot, Integer newTimeToPlaceBoats,
            Boolean newSpectator, Boolean newSpectatorChat);
    
    /**
     * Remove a Game from local list
     * @param g : Game to remove
     */
    public void removeGame(Game g);
    public List<Game> getGames();

	Profile getLocalProfile();

    public void setLocalGame(Game g);
    
    public void setListIps(HashSet Ips);

    /**
     * Add a spectator in the game
     * @param g : game that the spectator wants to join
     */
    void gameToSpec(Game g);
    
    void setPort(int p);
}

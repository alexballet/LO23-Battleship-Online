/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesData;

import java.util.Date;
import structData.ContactGroup;
import structData.Game;
import structData.User;
import java.awt.Image;
import java.util.HashSet;
import javax.swing.ImageIcon;

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
    void editProfile(String username, String password, ImageIcon avatar, String lastName, String firstName, Date borthDate);
    
    /**
     * Create a local account
     * @param idUser : unique ID of the user
     * @param login
     * @param username
     * @param ips : list of the IP adresses known by the user
     * @param password
     * @param contactList : list of the user's contacts
     * @param avatar
     * @param lastname
     * @param firstname
     * @param birthDate
     */
    void createAccount(String login, String username, HashSet ips, String password, String contactList, ImageIcon avatar, String lastname, String firstname, Date birthDate);
    
    /**
     * Add a distant user to the local list of user
     * @param u : user to add
     */
    void addUser(User u);
    
    /**
     * Returns the statistics of an user
     * @param u : user whose statistics are asked
     * @param nbGamePlayed
     * @param nbGameWon
     * @param nbGameLost
     * @param nbGameAbandoned 
     */
    void getStatistics(User u, int nbGamePlayed, int nbGameWon, int nbGameLost, int nbGameAbandoned);
    
    /**
     * Notifies the away application that an user wants to join a game
     * @param g : Game the user wants to join
     */
    void notifGameChosen(Game g);
    
    /**
     * Notifies away applications that the local user disconnects and erases his session.
     */
    void askDisconnection();
    
    /**
     * Loads the saved data of the user and researches players.
     */
    void connection();
    
    /**
     * Add a new game to the list of games
     * @param g : game to add
     */
    void newGame(Game g);
}

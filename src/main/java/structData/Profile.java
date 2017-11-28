/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structData;

import java.util.Date;
import javafx.scene.image.Image;
import java.util.HashSet;
import java.util.UUID;

/**
 * Profile is a class for the user's profile
 * @author loulou
 */
public class Profile extends DataUser {
    private Image avatar;
    private String lastname;
    private String name;
    private Date birthdate;
    private int gamesPlayed;
    private int gamesWon;
    private int gamesLost;
    private int gamesAborted;
    
    /**
     * Constructor with only a DataUser
     * @param dUser a DataUser
     */
    public Profile(DataUser dUser){
        super(dUser);
        avatar = null;
        lastname = new String("");
        name = new String("");
        birthdate = new Date();
        gamesPlayed = 0;
        gamesWon = 0;
        gamesLost = 0;
        gamesAborted = 0;
    }
    
    /**
     * Constructor with all paramters
     * @param dUser a DataUser
     * @param newAvatar an avatar
     * @param newLastname a lastname
     * @param newName a name
     * @param newBirthdate a birthdate
     */
    public Profile(DataUser dUser, Image newAvatar, String newLastname, 
            String newName, Date newBirthdate){
        super(dUser);
        avatar = null;
        lastname = new String(newLastname);
        name = new String(newName);
        // (Date) newBirthdate.clone() if newbirthdate belongs to an other object
        birthdate = newBirthdate; 
        gamesPlayed = 0;
        gamesWon = 0;
        gamesLost = 0;
        gamesAborted = 0;
    }
    
    /**
     * Method to copy a Profile
     * @param p a Profile to copy
     * @return a copied Profile
     */
    public Profile clone(Profile p){
        
        idUser = p.idUser;
        login =  p.login;
        username = p.username;
        iPs = p.iPs;
        password = p.password;
        listContacts = p.listContacts;
        avatar = p.avatar;
        lastname = p.lastname;
        name = p.name;
        birthdate = p.birthdate; 
        gamesPlayed = p.gamesPlayed;
        gamesWon = p.gamesWon;
        gamesLost = p.gamesLost;
        gamesAborted = p.gamesAborted;
        
        return this;
    }
    
    /**
     * Mutator for the avatar
     * @param i an image
     */
    public void setAvatar(Image i){
        this.avatar = i;
    }
    
    /**
     * Accessor for the avatar
     * @return an avatar as an image
     */
    public Image getAvatar(){
        return this.avatar;
    }
    
    /**
     * Mutator for the lastname
     * @param ln a lastname
     */
    public void setLastname(String ln){
        this.lastname = ln;
    }
    /**
     * Accessor for the lastname
     * @return a lastname as a string
     */
    public String getLastname(){
        return this.lastname;
    }
    /**
     * Mutator for the name
     * @param n a name
     */
    public void setName(String n){
        this.name = n;
    }
    /**
     * Accessor for the name
     * @return a name as a string
     */
    public String getName(){
        return this.name;
    }
    /**
     * Mutator for the birthdate
     * @param b a birthdate
     */
    public void setBirthdate(Date b){
        this.birthdate = b;
    }
    /**
     * Accessor for the birthdate
     * @return a birthdate as a date
     */
    public Date getBirthdate(){
        return this.birthdate;
    }
    /**
     * Mutator for gamesPlayed
     * @param nb the number of games played
     */
    public void setGamesPlayed(int nb){
        this.gamesPlayed = nb;
    }
    /**
     * Accessor for gamesPlayed
     * @return the number of games played as an integer
     */
    public int getGamesPlayed(){
        return this.gamesPlayed;
    }
    /**
     * Mutator for gamesLost
     * @param nb the number of games lost as an integer
     */
    public void setGamesLost(int nb){
        this.gamesLost = nb;
    }
    /**
     * Accessor for gamesLost
     * @return the number of games lost as an integer
     */
    public int getGamesLost(){
        return this.gamesLost;
    }
    /**
     * Mutator for gamesAborted
     * @param nb the number of games aborted as an integer
     */
    public void setGamesAborted(int nb){
        this.gamesAborted = nb;
    }
    /**
     * Accessor for gamesAborted
     * @return the number of games aborted as an integer
     */
    public int getGamesAborted(){
        return this.gamesAborted;
    }
    
    /**
     * Mutator for gamesWon
     * @param nb the number of games won as an integer
     */
    public void setGamesWon(int nb){
        this.gamesWon = nb;
    }
    /**
     * Accessor for gamesWon
     * @return the number of games won as an integer
     */
    public int getGamesWon(){
        return this.gamesWon;
    }    
}

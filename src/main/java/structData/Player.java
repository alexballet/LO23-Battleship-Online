/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structData;
import java.util.HashSet;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author loulou
 */
public class Player implements Serializable{
    private Profile profile;
    private List<Boat> listBoats;
    private HashSet<Shot> listShots;
    
    /**
     * Constructor by default 
     * @param dUser : the parent class DataUser of the profile of the new player
     */
    public Player(DataUser dUser) {
        profile = new Profile(dUser);
        listBoats = new ArrayList();
        listShots = new HashSet();
    }
    
    /**
     * Constructor with parameters
     * @param p : the profile of the new player
     * @param listBoatsData : the list of boats of the new player
     * @param listShotsData : the list of shots of the new player
     */
    public Player(Profile p, List<Boat> listBoatsData, HashSet<Shot> listShotsData){
        profile = p;
        listBoats = listBoatsData;
        listShots = listShotsData;
    }
    
    /**
     * Accessors for Profile
     * @return the profile of the player
     */
    public Profile getProfile(){
        return profile;
    }
    
    /**
     * Accessors for listBoats
     * @return the list of boats of the player
     */
    public List<Boat> getlistBoats(){
        return listBoats;
    }
    
    /**
     * Accessors for listShots
     * @return the list of shots of the player
     */
    public HashSet<Shot> getlistShots(){
        return listShots;
    }
    
    /**
     * Mutator for profile
     * @param p : the new value of the profile of the player
     */
    public void setProfile(Profile p){
        this.profile = p;
    }
    
    /**
     * Mutator for listBoats
     * @param listBoatsData : the new value of the list of boats of the player
     */
    public void setlistBoats(List<Boat> listBoatsdata){
        this.listBoats = listBoatsdata;
    }
    
    /**
     * Mutator for listShots
     * @param listShotsData : the new value of the list of shots of the player
     */
    public void setlistShots(HashSet<Shot> listShotsdata){
        this.listShots = listShotsdata;
    }
    
    /**
     * Clone function to copy a Player
     * @param playerClone : the player to be cloned
     * @return the player who has called this method to clone all the 
     *         information of the playerClone
     */
    public Player clonePlayer(Player playerClone){
        profile = playerClone.profile;
        listBoats = playerClone.listBoats;
        listShots = playerClone.listShots;
        return this;
   }
    
    
    /**
     * Add a new boat to the list of boats
     * @param newBoat : the new boat to be added into the list of boats
     */
    public void addBoat (Boat newBoat){
        listBoats.add(newBoat);
    }
    
    /**
     * Add a new shot to the list of shots
     * @param newShot : the new shot to be added into the list of boats
     */
    public void addShot (Shot newShot){
        listShots.add(newShot);
    }
   
}


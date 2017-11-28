/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structData;
import java.util.HashSet;
import java.io.Serializable;
/**
 *
 * @author loulou
 */
public class Player implements Serializable{
    private Profile profile;
    private HashSet listBoats;
    private HashSet listShots;
    
    /*constructor by default*/
    /**
     * 
     * @param dUser : the parent class DataUser of the profil of the new player
     */
    public Player(DataUser dUser) {
        profile = new Profile(dUser);
        listBoats = new HashSet();
        listShots = new HashSet();
    }
    
    /*constructor with parameters*/
    /**
     * 
     * @param p : the profil of the new player
     * @param listBoatsdata : the list of boats of the new player
     * @param listShotsdata : the list of shots of the new player
     */
    public Player(Profile p, HashSet listBoatsdata, HashSet listShotsdata){
        profile = p;
        listBoats = listBoatsdata;
        listShots = listShotsdata;
    }
    
    /*accessors*/
    /**
     * 
     * @return the profil of the player
     */
    public Profile getprofile(){
        return profile;
    }
    
    /**
     * 
     * @return the list of boats of the player
     */
    public HashSet getlistBoats(){
        return listBoats;
    }
    
    /**
     * 
     * @return the list of shots of the player
     */
    public HashSet getlistShots(){
        return listShots;
    }
    
    /*mutator*/
    /**
     * 
     * @param p : the new value of the profil of the player
     */
    public void setprofile(Profile p){
        this.profile = p;
    }
    
    /**
     * 
     * @param listBoatsdata : the new value of the list of boats of the player
     */
    public void setlistBoats(HashSet listBoatsdata){
        this.listBoats = listBoatsdata;
    }
    
    /**
     * 
     * @param listShotsdata : the new value of the list of shots of the player
     */
    public void setlistShots(HashSet listShotsdata){
        this.listShots = listShotsdata;
    }
    
    /*clone*/
    /**
     * 
     * @param playerclone : the player to be cloned
     * @return the player who has called this method to clone all the 
     *         information of the playerclone
     */
    public Player cloneplayer(Player playerclone){
        profile = playerclone.profile;
        listBoats = playerclone.listBoats;
        listShots = playerclone.listShots;
        return this;
   }
    
   /*add*/
    
    /**
     * 
     * @param newboat : the new boat to be added into the list of boats
     */
    public void addboat (Boat newboat){
        listBoats.add(newboat);
    }
    
    /**
     * 
     * @param newshot : the new shot to be added into the list of boats
     */
    public void addshot (Shot newshot){
        listShots.add(newshot);
    }
    
    
    
}


package structData;
import java.util.HashSet;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Player is a class for players in a game
 */
public class Player implements Serializable{
    static final long serialVersionUID = 5L;
    private Profile profile;
    private List<Boat> listBoats;
    private HashSet<Shot> listShots;
    private boolean ready;

    /**
     * Constructor by default 
     * @param dUser : the parent class DataUser of the profile of the new player
     */
    public Player(DataUser dUser) {
        profile = new Profile(dUser);
        listBoats = new ArrayList();
        listShots = new HashSet();
         ready = false;
    }
    
    /**
     * Constructor for a Player
     * @param dUser : the parent class DataUser of the profil of the new player
     */
    public Player(Profile p) {
        profile = p;
        listBoats = new ArrayList();
        listShots = new HashSet();
        ready = false;
    }

    /**
     * Accessor for ready
     * @return a boolean that is true if the player is ready to play
     */
    public boolean isReady() {
        return ready;
    }

    /**
     * Mutator for ready
     * @param value : the ready value
     */
    public void setReady(boolean value) {
        ready = value;
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
    public List<Boat> getListBoats(){
        return listBoats;
    }
    
    /**
     * Accessors for listShots
     * @return the list of shots of the player
     */
    public HashSet<Shot> getListShots(){
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
    public void setListBoats(List<Boat> listBoatsData){
        this.listBoats = listBoatsData;
    }
    
    /**
     * Mutator for listShots
     * @param listShotsData : the new value of the list of shots of the player
     */
    public void setListShots(HashSet<Shot> listShotsData){
        this.listShots = listShotsData;
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
    
    /**
     * Function to compare a profile with the current player's profile
     * @param p profile to compare with the current player's profile
     * @return a boolean wich is true if profiles are equals
     */
    public Boolean compareProfileToPlayer(Profile p){
        Boolean isEqual = this.profile.idUser.equals(p.idUser); 
        return isEqual;
    }
   
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structData;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Boat is the class which represents a ship of a player.
 */
public class Boat implements Serializable{
    static final long serialVersionUID = 1L;
    private BoatType type;
    private Boolean status;
    private List<Position> listCases;
    
    
    /**
     * constructor by default
     * status = false means that the boat isn't sunk
     */
    public Boat(){
        type = BoatType.PORTEAVIONS;
        status = false;
        this.listCases = new ArrayList();
    }
    


    /**
     * constructor with parameters
     * @param typedata : the type of the new boat
     * @param statusdata : the status of the new boat
     * @param listCasesdata  : the list of positions of the new boat
     */
    public Boat(BoatType typedata, Boolean statusdata, List<Position> listCasesdata){
        type = typedata;
        status = statusdata;
        listCases = listCasesdata;
    }
    
    /**
     * Accessor for the type
     * @return the boat's type
     */
    public BoatType getType(){
        return type;
    }
    
    /**
     * Accessor for the status
     * @return the boat's status
     */
    public Boolean getStatus(){
        return status;
    }
    
    /**
     * Accessor for the List of cases
     * @return the boat's list of cases
     */
    public List<Position> getListCases(){
        return listCases;
    }
    
    //mutator
    /**
     * 
     * @param typedata : the new value of the type of the boat
     */
    public void setType(BoatType typedata){
        this.type = typedata;
    }
    
    /**
     * Mutator for the boat's status
     * @param statusdata : the new boat's status
     */
    public void setStatus(Boolean statusdata){
        this.status = statusdata;
    }
    
    /**
     * Mutator for the boat's list of positions
     * @param listCasesdata : the new list of posiitons
     */
    public void setListcases(List<Position> listCasesdata){
        this.listCases = listCasesdata;
    }
    
    
    /**
     * Clone method for a boat
     * @param boatclone : boat to clone
     * @return a clone of the boat
     */
    public Boat cloneBoat(Boat boatclone){
        type = boatclone.type;
        status = boatclone.status;
        listCases = boatclone.listCases;
        return this;
   }
    
    /**
     * Add a position to listCases
     * @param pos: position to add
     */
    public void addPosition (Position pos){
        listCases.add(pos);
    }
    
    /**
     * Verify if a position belongs to a boat
     * @param pos : position to check
     * @return a boolean set to true if the position belongs to a boat
     */
    public Boolean verifyPosition (Position pos){
        Boolean isbelonged;
        isbelonged = false;
        
       ListIterator<Position> it = this.listCases.listIterator();
       while(it.hasNext()){
            Position posboat = it.next();
            Byte posboatx = posboat.x;
            Byte posboaty = posboat.y;
            if ((posboatx.equals(pos.x)) && (posboaty.equals(pos.y))){
                isbelonged = true;
                break;
            }
       }
        return isbelonged;
        
    }
    
    
    /**
     * If the position belongs to the boat, set this positon touched
     * @param pos : the position to be verified
     */
    public void setTouchedPosition (Position pos){
        if (this.verifyPosition(pos) == true){
            pos.touched = true;
        }
    }
    
    
    /**
     * verify is the boat has been sunk
     * @return the new status of the current boat
     */
    public Boolean verifyBoatStatus(){
        ListIterator<Position> it = this.listCases.listIterator();
        
        this.status = true;
        while(it.hasNext()){
            Position posboat = it.next();
            if (posboat.touched == false){
                this.status = false;
                break;
            }
        }
        return this.status;
    }
    
}

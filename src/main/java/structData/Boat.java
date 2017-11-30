/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structData;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Boat is the class which represents a ship of a player.
 */
public class Boat implements Serializable{
    private BoatType type;
    private Boolean status;
    private List<Position> listCases;
    
    
    /**
     * Constructor by default
     */
    public Boat(){
        type = BoatType.PORTEAVIONS;
        status = false;
        this.listCases = new ArrayList();
    }
    
    /**
     * Constructor with parameters
     * @param typedata : type of boat
     * @param statusdata : status of the boat
     * @param listCasesdata : list of positions
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
    public List<Position> getListcases(){
        return listCases;
    }
    
    /**
     * Mutator for the boat's type
     * @param typedata : the new boat's type
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
        //fonction not yet finished
        return true;
        
    }
    
    /**
     * Set the position's touched attribute to true
     * @param pos : position to modify
     */
    public void setTouchedposition (Position pos){
        
    }
    
}

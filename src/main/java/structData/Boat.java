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
    private BoatType type;
    private Boolean status;
    private List<Position> listCases;
    
    
    /**
     * constructor by default
     */
    public Boat(){
        type = BoatType.PORTEAVIONS;
        status = false;//staus = false means that the boat isn't sunk
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
    
    //accessors
    /**
     * 
     * @return the type of the current boat
     */
    public BoatType getType(){
        return type;
    }
    
    /**
     * 
     * @return the status of the current boat
     */
    public Boolean getStatus(){
        return status;
    }
    
    /**
     * 
     * @return the list of positions of the current boat
     */
    public List<Position> getListcases(){
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
     * 
     * @param statusdata : the new value of the status of the boat
     */
    public void setStatus(Boolean statusdata){
        this.status = statusdata;
    }
    
    /**
     * 
     * @param listCasesdata : the new value of the list of the positions of the boat
     */
    public void setListcases(List<Position> listCasesdata){
        this.listCases = listCasesdata;
    }
    
    
    //clone
    /**
     * 
     * @param boatclone : the boat to be cloned
     * @return : the new cloned boat
     */
    public Boat cloneBoat(Boat boatclone){
        type = boatclone.type;
        status = boatclone.status;
        listCases = boatclone.listCases;
        return this;
   }
    
    
    /**
     * add a position to listCases
     * @param pos : the position to be added in the list
     */
    public void addPosition (Position pos){
        listCases.add(pos);
    }
    
    
    /**
     * verify if a position belong to a boat
     * @param pos : the position to be verified
     * @return : the boolean variable indicating if the position belongs to the current boat
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
     * if the position belongs to the boat, set this positon touched
     * @param pos : the position to be verified
     */
    public void setTouchedposition (Position pos){
        if (this.verifyPosition(pos) == true){
            pos.touched = true;
        }
    }
    
    
    /**
     * verify is the boat has been sunk
     * @return the new status of the current boat
     */
    public Boolean verifyBoatstatus(){
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

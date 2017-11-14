/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structData;
import java.util.HashSet;

/**
 * Boat is the class which represents a ship of a player.
 */
public class Boat {
    private BoatType type;
    private Boolean status;
    private HashSet listCases;
    
    //constructor by default
    public Boat(){
        type = BoatType.PORTEAVIONS;
        status = false;
        listCases = new HashSet();
    }
    
    //constructor with parameters
    public Boat(BoatType typedata, Boolean statusdata, HashSet listCasesdata){
        type = typedata;
        status = statusdata;
        listCases = listCasesdata;
    }
    
    //accessors
    public BoatType getType(){
        return type;
    }
    
    public Boolean getStatus(){
        return status;
    }
    
    public HashSet getListcases(){
        return listCases;
    }
    
    //mutator
    public void setType(BoatType typedata){
        this.type = typedata;
    }
    
    public void setStatus(Boolean statusdata){
        this.status = statusdata;
    }
    
    public void setListcases(HashSet listCasesdata){
        this.listCases = listCasesdata;
    }
    
    
    //clone
    public Boat cloneBoat(Boat boatclone){
        type = boatclone.type;
        status = boatclone.status;
        listCases = boatclone.listCases;
        return this;
   }
    
    //add a position to listCases
    public void addPosition (Position pos){
        listCases.add(pos);
    }
    
    //verify if a position belong to a
    public Boolean verifyPosition (Position pos){
        
        return listCases.contains(pos);
        
    }
    
    //if the position belongs to the boat, set this positon touched
    public void setTouchedposition (Position pos){
        
    }
    
}

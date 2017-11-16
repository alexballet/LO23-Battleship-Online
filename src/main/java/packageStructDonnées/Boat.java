/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageStructDonnées;
import java.util.ArrayList;
import java.util.List;

/**
 * Boat is the class which represents a ship of a player.
 * @author lola
 */

public class Boat {
    private BoatType type;
    private Boolean status;
// BROKEN CHANGE : I changed list case type because hashset doesn't accept duplicate value
    private List<String> listCases;
    
    public Boat(BoatType myType){
        this.type = myType;
        this.status = false;
        this.listCases = new ArrayList();
        
        for (int i = 0 ; i < myType.getNbCases(); i++) {
            listCases.add("TODO");
        }
    }

    /**
     * @return the type
     */
    public BoatType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(BoatType type) {
        this.type = type;
    }

    /**
     * @return the status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * @return the listCases
     */
    public List getListCases() {
        return listCases;
    }

    /**
     * @param listCases the listCases to set
     */
    public void setListCases(List listCases) {
        this.listCases = listCases;
    }
}


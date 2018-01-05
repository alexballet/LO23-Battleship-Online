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
    private Boolean isSunk;
    private List<Position> listCases;
    
    
    /**
     * Constructor by default
     * status = false means that the boat isn't sunk
     */
    public Boat(){
        type = BoatType.PORTEAVIONS;
        isSunk = false;
        this.listCases = new ArrayList();
    }
    

    /**
     * Constructor with parameters
     * @param typedata : the type of the new boat
     * @param rotation : is the boat is rotated
     * @param pos : the boat's position
     */
    public Boat(BoatType typedata, Boolean rotation, Position pos){
        type = typedata;
        isSunk = false;
        this.listCases = new ArrayList();
        this.setListcases(rotation, pos);
    }
    

    /**
     * Constructor with parameters
     * @param typedata : the type of the new boat
     * @param statusdata : the status of the new boat
     * @param listCasesdata  : the list of positions of the new boat
     */
    public Boat(BoatType typedata, Boolean statusdata, List<Position> listCasesdata){
        type = typedata;
        isSunk = statusdata;
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
    public Boolean getSunk(){
        return isSunk;
    }
    
    /**
     * Accessor for the List of cases
     * @return the boat's list of cases
     */
    public List<Position> getListCases(){
        return listCases;
    }
    
    /**
     * Mutator for Type
     * @param typedata : the new value of the type of the boat
     */
    public void setType(BoatType typedata){
        this.type = typedata;
    }
    
    /**
     * Mutator for the boat's status
     * @param statusdata : the new boat's status
     */
    public void setSunk(Boolean statusdata){
        this.isSunk = statusdata;
    }
    
    /**
     * Mutator for the boat's list of positions
     * @param listCasesdata : the new list of posiitons
     */
    public void setListcases(List<Position> listCasesdata){
        this.listCases = listCasesdata;
    }
    
    /**
     * Mutator for listcases : the list of cases
     * @param rotation if the boat is rotated or not
     * @param pos the boat's position
     */
    public void setListcases(Boolean rotation, Position pos){
        for (int i = 0; i < this.type.getNbCases(); i++) {
            if(rotation) {
                this.listCases.add(new Position(pos.x, pos.y+i, false));
            } else {
                this.listCases.add(new Position(pos.x+i, pos.y, false));
            }
            
        }
    }
    
    
    /**
     * Clone method for a boat
     * @param boatclone : boat to clone
     * @return a clone of the boat
     */
    public Boat cloneBoat(Boat boatclone){
        type = boatclone.type;
        isSunk = boatclone.isSunk;
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
     * @param shot : position to check
     * @return a boolean set to true if the position belongs to a boat
     */
    public Boat updateShot (Position shot){
        Boolean sunk = true;
        
        for (Position pos : listCases) {
            int posboatx = pos.x;
            int posboaty = pos.y;
            if ((posboatx == shot.x) && (posboaty==shot.y)){
                pos.setTouched(true);
                shot.setTouched(true); // voir si utile ?
            }
            if(!pos.getTouched())  {
                sunk = false;
            }
        }
        this.setSunk(sunk);
        return this;
        
    }
    
    
    
    /**
     * verify is the boat has been sunk
     * @return the new status of the current boat
     */
    public Boolean verifyBoatStatus(){
        ListIterator<Position> it = this.listCases.listIterator();
        
        this.isSunk = true;
        while(it.hasNext()){
            Position posboat = it.next();
            if (posboat.touched == false){
                this.isSunk = false;
                break;
            }
        }
        return this.isSunk;
    }
    
}

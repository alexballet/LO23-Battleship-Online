package structData;
import java.io.Serializable;

/**
 * Position is a class of a position in a table.
 */
public class Position implements Serializable{
    static final long serialVersionUID = 6L;
    protected int x;
    protected int y;
    protected Boolean touched;
    
    /**
     * Position : default constructor
     */
    public Position(){
        x = 0;
        y = 0;
        touched = false;
    }
    
    /**
     * Position : constructor with all parameters
     * @param newX x coordinate
     * @param newY y coordinate
     * @param newTouched boolean equal to 1 if the position is touched
     */
    public Position(int newX, int newY, Boolean newTouched){
        x = newX;
        y = newY;
        touched = newTouched;
    }

    /**
     * Position : constructor with a Position
     * @param p a Position
     */
    public Position(Position p){
 
        x = p.x;
        y = p.y;
        touched = p.touched;

    }
    
    /**
     * Method to copy a Position
     * @param p a Position to copy
     * @return a copied Position
     */
    public Position clone(Position p){
 
        x = p.x;
        y = p.y;
        touched = p.touched;
        
        return this;
    }
    
    /**
     * Mutator for x
     * @param x the position's x
     */
    public void setX(int x){
        this.x = x;
    }
    /**
     * Accessor for x
     * @return the position's x as a int
     */
    public int getX(){
        return this.x;
    }
    
    /**
     * Mutator for y
     * @param y the position's y
     */
    public void setY(int y){
        this.y = y;
    }
    /**
     * Accessor for y
     * @return the position's y as a int
     */
    public int getY(){
        return this.y;
    }
    
    /**
     * Mutator for touched
     * @param touched boolean indicating if the position is touched or not
     */
    public void setTouched(Boolean touched){
        this.touched = touched;
    }
    /**
     * Accessor for touched
     * @return touched : boolean indicating if the position is touched or not
     */
    public Boolean getTouched(){
        return this.touched;
    }
}

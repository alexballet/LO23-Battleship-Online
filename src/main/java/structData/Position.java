/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structData;

import java.io.Serializable;
/**
 * Position is a class of a position in a table.
 */
public class Position implements Serializable{
    protected Byte x;
    protected Byte y;
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
     * @param newX x
     * @param newY y 
     * @param newTouched boolean equal to 1 if the position is touched
     */
    public Position(Byte newX, Byte newY, Boolean newTouched){
        x = newX;
        y = newY;
        touched = newTouched;
    }
    
    /**
     * Position : constructor with a Position
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
    public void setX(Byte x){
        this.x = x;
    }
    /**
     * Accessor for x
     * @return the position's x as a Byte
     */
    public Byte getX(){
        return this.x;
    }
    
    /**
     * Mutator for y
     * @param y the position's y
     */
    public void setY(Byte y){
        this.y = y;
    }
    /**
     * Accessor for y
     * @return the position's y as a Byte
     */
    public Byte getY(){
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

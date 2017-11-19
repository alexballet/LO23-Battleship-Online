/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiTable;

import java.util.HashMap;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import packageStructDonnées.Boat;
import packageStructDonnées.BoatType;

/**
 *
 * @author caioz
 */
public class BoatDrawing{
    
    private boolean active;
    private boolean rotation;
    private Boat boat;
    private Rectangle boatRectangle;
    private BoatType boatType;
    private Integer gridRow;
    private Integer gridCol;
    private boolean placed;
    
    //not supposed to change
    final private double initialLayoutX;
    final private double initialLayoutY;
    
    final private Color activeColor = Color.web("#d8d875") ;
    final private Color disactiveColor = Color.web("#ababab") ;
    
    public BoatDrawing(BoatType boatType, Rectangle boatRectangle) {
        this.active = false;
        this.rotation = false;
        this.boat = new Boat(boatType); //Include boattype??
        this.boatRectangle = boatRectangle;
        this.boatType = boatType;
        
        this.initialLayoutX = boatRectangle.getLayoutX();
        this.initialLayoutY = boatRectangle.getLayoutY();   
        
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isRotation() {
        return rotation;
    }

    public void setRotation(boolean rotation) {
        this.rotation = rotation;
    }

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }

    public Rectangle getBoatRectangle() {
        return boatRectangle;
    }

    public void setBoatRectangle(Rectangle boatRectangle) {
        this.boatRectangle = boatRectangle;
    }

    public double getInitialLayoutX() {
        return initialLayoutX;
    }


    public double getInitialLayoutY() {
        return initialLayoutY;
    }

    public Integer getGridRow() {
        return gridRow;
    }

    public void setGridRow(Integer gridRow) {
        this.gridRow = gridRow;
    }

    public Integer getGridCol() {
        return gridCol;
    }

    public void setGridCol(Integer gridCol) {
        this.gridCol = gridCol;
    }

    public BoatType getBoatType() {
        return boatType;
    }

    public void setBoatType(BoatType boatType) {
        this.boatType = boatType;
    }

    public boolean isPlaced() {
        return placed;
    }

    public void setPlaced(boolean placed) {
        this.placed = placed;
    }

    /**
     * @return the activeColor
     */
    public Color getActiveColor() {
        return activeColor;
    }

    /**
     * @return the disactiveColor
     */
    public Color getDisactiveColor() {
        return disactiveColor;
    }
    
    public int getBoatSize() {
        return this.boatType.getNbCases();
    }

    public void reinit() {
        this.setGridCol(null);
        this.setGridRow(null);
        this.setActive(false);
        this.setRotation(false);
        this.setPlaced(false);
    }
    

        /**
     * 
     * @param boatMap 
     * @return  
     */
    public BoatDrawing setActiveBoat(HashMap<Rectangle, BoatDrawing> boatMap){
        
        this.setActive(true);
        Rectangle rectangle = this.getBoatRectangle();
        rectangle.setMouseTransparent(true);
        rectangle.setFill(getActiveColor());
        return this; 

    }
}

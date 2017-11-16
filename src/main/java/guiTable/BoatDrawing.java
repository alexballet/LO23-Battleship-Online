/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiTable;

import java.util.HashMap;
import java.util.Map;
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
    private double initialLayoutX;
    private double initialLayoutY;
    private Integer gridRow;
    private Integer gridCol;
    private final float offset;
    
    private static final float GRID_OFFSET = (float) 17.5;
    
    public BoatDrawing(BoatType boatType, Rectangle boatRectangle) {
        this.active = false;
        this.rotation = false;
        this.boat = new Boat(boatType); //Include boattype??
        this.boatRectangle = boatRectangle;
        this.boatType = boatType;
        
        this.initialLayoutX = boatRectangle.getLayoutX();
        this.initialLayoutY = boatRectangle.getLayoutY();   
        
        //calculate boat offset for rotation
        this.offset = (this.boat.getListCases().size()-1)*GRID_OFFSET;
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

    public void setInitialLayoutX(double initialLayoutX) {
        this.initialLayoutX = initialLayoutX;
    }

    public double getInitialLayoutY() {
        return initialLayoutY;
    }

    public void setInitialLayoutY(double initialLayoutY) {
        this.initialLayoutY = initialLayoutY;
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
    
    public float getOffset(){
        return this.offset;
    }
    
        /**
     * 
     * @param boatMap
     * @param boat 
     * @return  
     */
    public BoatDrawing setActiveBoat(HashMap<Rectangle, BoatDrawing> boatMap){
        
        for (Map.Entry<Rectangle, BoatDrawing> entry : boatMap.entrySet()) {
            Rectangle myRectangle = entry.getKey();
            BoatDrawing myBoat = entry.getValue();
            
            myBoat.setActive(false);
            //Sets this boat invisible to mouse events and the others are not
            myRectangle.setMouseTransparent(false);
            // Change its color to yellow and the color of the other to gray
            myRectangle.setFill(Color.web("#ababab"));
        }
        this.setActive(true);
        Rectangle rectangle = this.getBoatRectangle();
        rectangle.setMouseTransparent(true);
        rectangle.setFill(Color.web("#d8d875"));
        
        return this; 
    }
    
}

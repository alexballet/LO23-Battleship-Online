/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiTable;

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
    private Rectangle boatDrawing;
    private double initialLayoutX;
    private double initialLayoutY;
    private Integer gridRow;
    private Integer gridCol;

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
    
    public BoatDrawing(BoatType boatType, Rectangle boatDrawing) {
        
        this.active = false;
        this.rotation = false;
        this.boat = new Boat(); //Include boattype??
        this.boatDrawing = boatDrawing;
        
    }

    public double getInitialLayoutX() {
        return initialLayoutX;
    }

    public double getInitialLayoutY() {
        return initialLayoutY;
    }

    public void setInitialLayoutY(double initialLayoutY) {
        this.initialLayoutY = initialLayoutY;
    }

    public void setInitialLayoutX(double initialLayoutX) {
        this.initialLayoutX = initialLayoutX;
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
    
    public void rotate(){
         if (boatDrawing.getRotate()==0){
            this.rotation = true;
        } else if (boatDrawing.getRotate()==90){
            this.rotation = false;
        }
    }
}

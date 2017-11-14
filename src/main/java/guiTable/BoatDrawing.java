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
    private Rectangle boatRectangle;
    private BoatType boatType;
    private double initialLayoutX;
    private double initialLayoutY;
    private Integer gridRow;
    private Integer gridCol;
    
    private static final float PORTE_AVIONS_OFFSET = 70;
    private static final float CROISEUR_OFFSET = (float) 52.5;
    private static final float CONTRE_TORPILLEUR_OFFSET = 35;
    private static final float SOUS_MARIN_OFFSET = 35;
    private static final float TOURPILLEUR_OFFSET = (float) 17.5;

    public BoatDrawing(BoatType boatType, Rectangle boatRectangle) {
        this.active = false;
        this.rotation = false;
        this.boat = new Boat(); //Include boattype??
        this.boatRectangle = boatRectangle;
        this.boatType = boatType;
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
    
    public static float getBoatDrawingOffset(BoatType boatType){
        switch (boatType) {
            case PORTEAVIONS:
                return PORTE_AVIONS_OFFSET;
            case CROISEURFR:
                return CROISEUR_OFFSET;
            case CONTRETORPILLEUR:
                return CONTRE_TORPILLEUR_OFFSET;
            case SOUSMARINFR:
                return SOUS_MARIN_OFFSET;
            case TORPILLEUR:
                return TOURPILLEUR_OFFSET;
            default:
                return 0;
        }
    }
    
    
}

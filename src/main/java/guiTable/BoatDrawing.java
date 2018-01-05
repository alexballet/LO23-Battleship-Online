package guiTable;

import java.util.HashMap;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import structData.BoatType;

/**
 * Boat drawing : class to draw a boat
 */
public class BoatDrawing{
    
    private boolean active;
    private boolean rotation;
   // private Boat boat;
    private Rectangle boatRectangle;
    private BoatType boatType;
    private Integer gridRow;
    private Integer gridCol;
    private Boolean placed;
    
    //not supposed to change
    final private double initialLayoutX;
    final private double initialLayoutY;
    
    final private Color activeColor = Color.web("#d8d875") ;
    final private Color disactiveColor = Color.web("#ababab") ;
    final private Color badPlacementColor = Color.CRIMSON;
    
    /**
     * Constructor of a object.
     * @param boatType The type of the boat.
     * @param boatRectangle The object Rectangle associated with this boat.
     */
    public BoatDrawing(BoatType boatType, Rectangle boatRectangle) {
        this.active = false;
        this.rotation = false;
       // this.boat = new Boat(boatType, false, new ArrayList<Position>());
        this.boatRectangle = boatRectangle;
        this.boatType = boatType;
        this.placed = false;
        
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

 //   public Boat getBoat() {
   //     return boat;
   // }

  //  public void setBoat(Boat boat) {
  //      this.boat = boat;
  //  }

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

    public Integer getGridCol() {
        return gridCol;
    }

    public BoatType getBoatType() {
        return boatType;
    }

    public void setBoatType(BoatType boatType) {
        this.boatType = boatType;
    }

    public boolean isPlaced() {
        return this.placed;
    }

    /**
     * @return the activeColor
     */
    public Paint getActiveColor() {
        return activeColor;
    }

    /**
     * @return the disactiveColor
     */
    public Paint getDisactiveColor() {
        return disactiveColor;
    }
    
    public int getBoatSize() {
        return this.boatType.getNbCases();
    }

    public Paint getBadPlacementColor() {
        return this.badPlacementColor;
    }

    /**
     * Reiniciates the boat, meaning that it has returned to its original position.
     */
    public void reinit() {
        this.setPosition(null, null);
        this.setActive(false);
        this.setRotation(false);
        this.setPlaced(false);
    }
    

    /**
     * Activates the boat.
     * @param boatMap HashMap containing all the boats.
     * @return  This (the active boat).
     */
    public BoatDrawing setActiveBoat(HashMap<Rectangle, BoatDrawing> boatMap){  
        this.setActive(true);
        Rectangle rectangle = this.getBoatRectangle();
        rectangle.setMouseTransparent(true);
        rectangle.setFill(getActiveColor());
        return this; 
    }

    public void setPosition(Integer colIndex, Integer rowIndex) {
        this.gridCol = colIndex;
        this.gridRow = rowIndex;
    }
    
    public void setPlaced(Boolean bool) {
       /* if (placed) {
            int toIncrement, fixed;
            if (!this.isRotation()) {
                toIncrement = this.gridCol;
                fixed = this.gridRow;
                for(int i = toIncrement; i < (toIncrement + this.boatType.getNbCases());i++) {
                    Position p = new Position(i, fixed, null);
                    boat.addPosition(p);
                }
            } else {
                toIncrement = this.gridRow;
                fixed = this.gridCol;
                for(int i = toIncrement; i < (toIncrement + this.boatType.getNbCases());i++) {
                    boat.addPosition(new Position(fixed, i, null));
                }
            }
        } else {
            boat.setListcases(new ArrayList<Position>());
        }*/
       this.placed = bool;
    }
}

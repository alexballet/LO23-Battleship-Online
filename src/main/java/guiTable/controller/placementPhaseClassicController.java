/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiTable.controller;

import guiTable.BoatDrawing;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import packageStructDonnées.BoatType;

/**
 *
 * @author caioz
 */
public class placementPhaseClassicController implements Initializable{
    
    @FXML
    private Rectangle porteAvionsRectangle;
    @FXML
    private Rectangle croiseurRectangle;
    @FXML
    private Rectangle contreTorpilleurRectangle;
    @FXML
    private Rectangle sousMarinRectangle;
    @FXML
    private Rectangle torpilleurRectangle; 
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private GridPane table;
    @FXML
    private Button valider;
    
    private static final int GRID_X = 100;
    private static final int GRID_Y = 100;
    private static final int SPACE = 3;
    private static final int GRID_ELEMENT_SIZE = 35;
    private static final int NB_CASES_GRID = 10;
    
    private boolean rotationIsValide;
    private BoatDrawing activeBoat;
    
    HashMap<Rectangle, BoatDrawing> boatMap;
     
    /**
     * The method initialize starts the window and assigns values BoatDrawing 
     * objects and methods to the window's objects.
     * @param location
     * @param resources 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        boatMap = new HashMap<>();
        // Initializes the boat set
        boatMap.put(porteAvionsRectangle,new BoatDrawing(BoatType.PORTEAVIONS,porteAvionsRectangle));
        boatMap.put(croiseurRectangle, new BoatDrawing(BoatType.CROISEURFR,croiseurRectangle));
        boatMap.put(contreTorpilleurRectangle, new BoatDrawing(BoatType.CONTRETORPILLEUR,contreTorpilleurRectangle));
        boatMap.put(sousMarinRectangle, new BoatDrawing(BoatType.SOUSMARINFR,sousMarinRectangle));
        boatMap.put(torpilleurRectangle, new BoatDrawing(BoatType.TORPILLEUR,torpilleurRectangle));
        
        // Sets the events handles of the grid's squares.
        for (Rectangle rectangle : boatMap.keySet()) {
            rectangle.setOnMousePressed(activateBoat());
        }        
        activeBoat = null;

        for (int i = 0 ; i < NB_CASES_GRID ; i++) {
            for (int j = 0; j < NB_CASES_GRID; j++) {
                Pane pane = new Pane();
                pane.setOnMouseEntered(drawBoatsNewPosition());
                table.add(pane, i, j);
            }
        }
        
        // Sets the events handlers
        table.setOnMousePressed(placeBoat());
        table.setOnMouseEntered(enableRotation());
        table.setOnMouseExited(disableRotation());
        // This probably will change after the addition of the chat.
        anchorPane.addEventHandler(KeyEvent.KEY_PRESSED, playKeyEvent());
        
        rotationIsValide = false;
    }
    
    /**
     * Actives the boat when the user clicks on it.
     * @return mousePressHandler The handler of the event (Click over the boat).
     */   
    private EventHandler<MouseEvent> activateBoat() {
        EventHandler<MouseEvent> mousePressHandler = new EventHandler<MouseEvent>() {        
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    //If the user has clicked in the window and no other boat is already selected
                    if (activeBoat == null) {
                        Rectangle myRectangle =(Rectangle) event.getSource();
                        BoatDrawing myboat  = boatMap.get(myRectangle);
                        activeBoat = myboat.setActiveBoat(boatMap);
                        activeBoat.setPlaced(false);
                    }
                    
                }
            }
        };
        return mousePressHandler;
    }
    
    /**
     * Enables the boat rotation, this only happens when the mouse is
     * over the grid.
     * QUESTION : utile ?
     * REPONSE : Oui, sans rotationIsValide le bateau peut tourner sans être sur 
     * le grid (quand il est à la position initiale), ce qui va être bizarre. Qu'est-ce que tu en penses?
     * @return mouseLocationHandler The handler of the event (Mouse enters ther grid).
     */
    private EventHandler<MouseEvent> enableRotation() {
        EventHandler<MouseEvent> mouseLocationHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                rotationIsValide = true;
                event.consume();
            }
        };
        return mouseLocationHandler;
    }
    
    /**
     * Disables the boat rotation when the mouse exits the grid
     * @return mouseLocationHandler The handler of the event (Mouse exits the grid).
     */
    private EventHandler<MouseEvent> disableRotation() {
        EventHandler<MouseEvent> mouseLocationHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {   
                rotationIsValide = false;
                event.consume();
            }
        };
        return mouseLocationHandler;
    }
    
    /**
     * Moves the boat on the grid when the mouse moves over the squares.
     * @return mousePositionHandler The handler of the event (Mouse enters a grid position).
     */
    private EventHandler<MouseEvent> drawBoatsNewPosition() {
        EventHandler<MouseEvent> mousePositionHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(activeBoat!=null){
                    Node source = (Node)event.getSource() ;
                    Integer colIndex = GridPane.getColumnIndex(source);
                    Integer rowIndex = GridPane.getRowIndex(source);
                    draw(activeBoat, colIndex, rowIndex);  
                }
                event.consume();
            }
        };
        return mousePositionHandler;
    }
    
    /**
     * Draws and updates the boat's position
     * @param boat The boat that will be drawn.
     * @param colIndex The grid column of its position.
     * @param rowIndex The grid row of tis position.
     */
    private void draw(BoatDrawing boat, Integer colIndex, Integer rowIndex){
        // if boat have rotation, we must deplace boat 
        
        // offset is value to move rotation from center of boat to first case
        /*
             ___ ___ ___ ___ ___   
            |___|___|_*_|___|___| normal point of rotation
             ___ ___ ___ ___ ___   
            |_*_|___|___|___|___| our point of rotation
        
            (nb case-1)/2
        
        */
        float offset = boat.isRotation()? ((float) boat.getBoatSize()-1)/2:0;
            
        float positionX = GRID_X + SPACE + GRID_ELEMENT_SIZE*(colIndex - offset);
        float positionY = GRID_Y + SPACE + GRID_ELEMENT_SIZE*(rowIndex + offset);
            
        boat.getBoatRectangle().relocate(positionX, positionY);
        boat.setGridCol(colIndex);
        boat.setGridRow(rowIndex);
    }
       
    /**
     * Rotates the active boat when the user press R and deletes the active boat
     * when the user presses delete.
     * @return keyEeventHandler The handler of the event (Users types any key).
     */  
    public EventHandler<KeyEvent> playKeyEvent() {
        EventHandler<KeyEvent> keyEventHandler;
        keyEventHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                // Rotates the boat.
                if (keyEvent.getCode() == KeyCode.R) {
                    if(rotationIsValide){
                        if(activeBoat!=null){
                            drawRotation(activeBoat);                            
                        }
                    }   
                }
                // Relocates the boat to origin.
                if (keyEvent.getCode() == KeyCode.DELETE) {
                   if(activeBoat!=null) {
                       reinitBoat(activeBoat);
                   }
                }
                System.out.println(keyEvent.getCode());
                keyEvent.consume();
            }
        };
        return keyEventHandler;
    }
                  
    /**
     * Rotates the boat with 90° and updates the view.
     * @param boat The boat that will be rotated
     */
    private void drawRotation(BoatDrawing boat){
        boat.setRotation(!boat.isRotation());
        Rectangle rectangleBoat = boat.getBoatRectangle();
        rectangleBoat.setRotate(rectangleBoat.getRotate()+90);
        draw(boat,boat.getGridCol(),boat.getGridRow());
    }
    
    /**
     * Relocates the boat in the initial position and deactives it.
     * @param boat The boat that will be relocated and deactivated.
     */
    private void reinitBoat(BoatDrawing boat) {
        boat.reinit();
        Rectangle myRectangle = boat.getBoatRectangle();
        myRectangle.setRotate(0);
        myRectangle.relocate(boat.getInitialLayoutX(), boat.getInitialLayoutY());
        deactiveBoat();
    }        
    
    /**
     * Checks if the user cans put the boat at the selected position.
     * @param activeBoat The active boat that will have its positions checked.
     * @return True if the position is correct.
     */
    private boolean positionCorrect(BoatDrawing activeBoat) {
        // Gets the active rectangle's bound
        Rectangle activeRectangle = activeBoat.getBoatRectangle();
        Bounds boundR1 = activeRectangle.getBoundsInParent();
        System.out.println("bound = "+ boundR1 + " boat rectangle : "+ activeRectangle); // debug

        // Tries to find any intersection with the other boats.
        for (Rectangle myRectangle : boatMap.keySet()) {
            if(activeRectangle.equals(myRectangle)) {} else {
                Bounds boundR2 = myRectangle.getBoundsInParent();
                if (boundR1.intersects(boundR2)) {
                    return false ;
                }
            }
        } 
        // Checks the boat's position in relation to the grid.
        int index ;
        if(activeBoat.isRotation()) {
            index= activeBoat.getGridRow();
        } else {
            index= activeBoat.getGridCol();
        }

        System.out.println("index = "+ index + " boat Size : "+ activeBoat.getBoatSize()); //debug
        return activeBoat.getBoatSize()+index <= NB_CASES_GRID ;        
    }
   
    /**
     * Unactivates the boat when it is placed over the grid.
     * @return mousePressGridHandler The handler of the event (Click over the grid).
     */    
    private EventHandler<MouseEvent> placeBoat() {
        EventHandler<MouseEvent> mousePressGridHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    if(activeBoat!=null){
                        if(positionCorrect(activeBoat)) {
                            activeBoat.setPlaced(true);
                            deactiveBoat(); 
                        }
                    }
                }
                event.consume();
            }
        };
        return mousePressGridHandler;
    }
           
    /**
     * Deactivates the active boat.
     * @throws nullpointer exception
     */
    private void deactiveBoat() {
        if (activeBoat!=null) {
            activeBoat.setActive(false);
            activeBoat.getBoatRectangle().setMouseTransparent(false);
            activeBoat.getBoatRectangle().setFill(Color.web("#ababab"));
            activeBoat=null;
        } // else do nothing
    // function might never be used without any boat active so throw execption
    // Le if est necéssaire? Parce qu'on vérifie toujours si le activeBoat est null 
    // avant d'appeler la function.
    }
}

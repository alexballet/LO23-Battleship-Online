/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiTable.controller;

import guiTable.BoatDrawing;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private static final int GRID_LIMIT = 9;
    
    private boolean rotationIsValide;
    private BoatDrawing activeBoat;
            
    private BoatDrawing porteAvions;
    private BoatDrawing croiseur;
    private BoatDrawing contreTorpilleur;
    private BoatDrawing sousMarin;
    private BoatDrawing torpilleur; 
    
    
    /**
     * The method initialize starts the window and assigns values BoatDrawing 
     * objects and methods to the window's objects.
     * @param location
     * @param resources 
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        porteAvions = new BoatDrawing(BoatType.PORTEAVIONS,porteAvionsRectangle);
        croiseur = new BoatDrawing(BoatType.CROISEURFR,croiseurRectangle);
        contreTorpilleur = new BoatDrawing(BoatType.CONTRETORPILLEUR,contreTorpilleurRectangle);
        sousMarin = new BoatDrawing(BoatType.SOUSMARINFR,sousMarinRectangle);
        torpilleur = new BoatDrawing(BoatType.TORPILLEUR,torpilleurRectangle);
        
        porteAvionsRectangle.setOnMousePressed(activateBoat());
        
        croiseurRectangle.setOnMousePressed(activateBoat());
 
        contreTorpilleurRectangle.setOnMousePressed(activateBoat());
        
        sousMarinRectangle.setOnMousePressed(activateBoat());
        
        torpilleurRectangle.setOnMousePressed(activateBoat());
        
        activeBoat = null;

        for (int i = 0 ; i < 10 ; i++) {
            for (int j = 0; j < 10; j++) {
                Pane pane = new Pane();
                pane.setOnMouseEntered(drawBoatsNewPosition());
                table.add(pane, i, j);
            }
        }
        
        table.setOnMousePressed(unactiveBoat());
        table.setOnMouseEntered(enableRotation());
        table.setOnMouseExited(disableRotation());
        
        anchorPane.addEventHandler(KeyEvent.KEY_PRESSED, rotateBoat());
        
        valider.setOnMouseClicked(verifyBoatsPosition());
        
        rotationIsValide = false;
    }
    
    /**
     * Enables the boat rotation, this only happens when the mouse is
     * over the grid.
     * @return mouseLocationHandler
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
     * @return mouseLocationHandler
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
     * @return mousePositionHandler
     */
    private EventHandler<MouseEvent> drawBoatsNewPosition() {
        EventHandler<MouseEvent> mousePositionHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(activeBoat!=null){
                    Node source = (Node)event.getSource() ;
                    Integer colIndex = GridPane.getColumnIndex(source);
                    Integer rowIndex = GridPane.getRowIndex(source);
                    activeBoat.setGridCol(colIndex);
                    activeBoat.setGridRow(rowIndex);
                    draw(activeBoat);  
                    if(!activeBoat.isPlaced()){
                        activeBoat.setPlaced(true);
                    }
                }
                event.consume();
            }
        };
        return mousePositionHandler;
    }
    
    /**
     * Checks the boat's position and draws it.
     * @param boat
     */
    private void draw(BoatDrawing boat){
        if(!boat.isRotation()){
            boat.getBoatRectangle().setLayoutX(GRID_X + SPACE + GRID_ELEMENT_SIZE*boat.getGridCol());
            boat.getBoatRectangle().setLayoutY(GRID_Y + SPACE + GRID_ELEMENT_SIZE*boat.getGridRow());
        } else if (boat.isRotation()){
            boat.getBoatRectangle().setLayoutX(GRID_X + SPACE + GRID_ELEMENT_SIZE*boat.getGridCol() - BoatDrawing.getBoatDrawingOffset(boat.getBoatType()));
            boat.getBoatRectangle().setLayoutY(GRID_Y + SPACE + GRID_ELEMENT_SIZE*boat.getGridRow() + BoatDrawing.getBoatDrawingOffset(boat.getBoatType()));
        }
    }
       
    /**
     * Rotates the boat active when the user press R.
     * @return keyEeventHandler
     */    
    public EventHandler<KeyEvent> rotateBoat() {
        EventHandler<KeyEvent> keyEventHandler;
        keyEventHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.R) {
                    if(rotationIsValide){
                        if(activeBoat!=null){
                            drawRotation(activeBoat);                            
                        }
                    }   
                }
                keyEvent.consume();
            }
        };
        return keyEventHandler;
    }
    
    /**
     * Draws the boat when it is rotated.
     * @param boat 
     */
    private void drawRotation(BoatDrawing boat){
        if(boat.isRotation()){
            boat.setRotation(false);
            boat.getBoatRectangle().setRotate(0);
            draw(boat);
        } else if(!boat.isRotation()){
            boat.setRotation(true);
            boat.getBoatRectangle().setRotate(90);
            draw(boat);
        }
    }
    
    /**
     * Actives the boat when the user clicks  on it.
     * @return mousePressHandler
     */   
    private EventHandler<MouseEvent> activateBoat() {
        EventHandler<MouseEvent> mousePressHandler;
        mousePressHandler = new EventHandler<MouseEvent>() {
            
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    //If the user has clicked in the window
                    if(event.getSource().equals(porteAvionsRectangle)){
                        setActiveBoat(porteAvions);
                    }   
                    if(event.getSource().equals(croiseurRectangle)){
                        setActiveBoat(croiseur);
                    }    
                    if(event.getSource().equals(contreTorpilleurRectangle)){
                        setActiveBoat(contreTorpilleur);
                    }  
                    if(event.getSource().equals(sousMarinRectangle)){
                        setActiveBoat(sousMarin);
                    }  
                    if(event.getSource().equals(torpilleurRectangle)){                  
                        setActiveBoat(torpilleur);
                    }  
                }
            }
        };
        return mousePressHandler;
    }
    
    /**
     * Assigns the corrects attributes to the boats when one of them is
     * activated.
     * @param boat 
     */
    private void setActiveBoat(BoatDrawing boat){
        if(activeBoat!=null){
            activeBoat.setActive(false);
            activeBoat.getBoatRectangle().setMouseTransparent(false);
            activeBoat.getBoatRectangle().setFill(Color.web("#ababab"));
        }    
        boat.setActive(true);
        boat.getBoatRectangle().setMouseTransparent(true);        
        boat.getBoatRectangle().setFill(Color.web("#d8d875"));
        
        activeBoat = boat;  
    }
    
    /**
     * Unactivates the boat when it is placed over le grid.
     * @return mousePressGridHandler
     */    
    private EventHandler<MouseEvent> unactiveBoat() {
        EventHandler<MouseEvent> mousePressGridHandler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    if(activeBoat!=null){
                        activeBoat.setActive(false);
                        activeBoat.getBoatRectangle().setMouseTransparent(false);
                        activeBoat.getBoatRectangle().setFill(Color.web("#ababab"));
                        activeBoat=null;
                    }                 
                }
                event.consume();
            }
        };
        return mousePressGridHandler;
    }
    
    private EventHandler<MouseEvent> verifyBoatsPosition(){
         EventHandler<MouseEvent> mousePressButton = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                validateBoatsPosition(porteAvions);
                validateBoatsPosition(croiseur);
                validateBoatsPosition(contreTorpilleur);
                validateBoatsPosition(sousMarin);
                validateBoatsPosition(torpilleur);
                
                ArrayList<BoatDrawing> boatsList = new ArrayList<BoatDrawing>();
                boatsList.add(porteAvions);
                boatsList.add(croiseur);
                boatsList.add(contreTorpilleur);
                boatsList.add(sousMarin);
                boatsList.add(torpilleur);
            }
        };
        return mousePressButton;
    }
    
    private void validateBoatsPosition(BoatDrawing boat){
        if(!boat.isPlaced()){
            boat.getBoatRectangle().setFill(Color.web("#e05353"));
        } else {
            if(!boat.isRotation() && ((boat.getGridCol() + BoatDrawing.getBoatDrawingLength(boat.getBoatType()) - 1)>GRID_LIMIT)){
                boat.getBoatRectangle().setFill(Color.web("#e05353"));
            } else if(boat.isRotation() && ((boat.getGridRow() + BoatDrawing.getBoatDrawingLength(boat.getBoatType()) - 1)>GRID_LIMIT)){
                boat.getBoatRectangle().setFill(Color.web("#e05353"));
            }
        }
    }
}

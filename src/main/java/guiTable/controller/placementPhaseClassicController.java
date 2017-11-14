/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiTable.controller;

import guiTable.BoatDrawing;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    
    private static final int GRID_X = 100;
    private static final int GRID_Y = 100;
    private static final int SPACE = 3;
    private static final int GRID_ELEMENT_SIZE = 35;
    
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
        
        porteAvions.setInitialLayoutX(porteAvionsRectangle.getLayoutX());
        porteAvions.setInitialLayoutY(porteAvionsRectangle.getLayoutY());
        
        croiseur.setInitialLayoutX(croiseurRectangle.getLayoutX());
        croiseur.setInitialLayoutY(croiseurRectangle.getLayoutY());
        
        contreTorpilleur.setInitialLayoutX(contreTorpilleurRectangle.getLayoutX());
        contreTorpilleur.setInitialLayoutY(contreTorpilleurRectangle.getLayoutY());
        
        sousMarin.setInitialLayoutX(sousMarinRectangle.getLayoutX());
        sousMarin.setInitialLayoutY(sousMarinRectangle.getLayoutY());
        
        torpilleur.setInitialLayoutX(torpilleurRectangle.getLayoutX());
        torpilleur.setInitialLayoutY(torpilleurRectangle.getLayoutY());
        
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
        
        rotationIsValide = false;
    }
    
    /**
     * Method that enables the boat rotation, this only happens when the mouse is
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
     * Method that disables the boat rotation, because the mouse is not over
     * the grid.
     * @return 
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
     * Method that draw the boats over the grid when the mouse enters one 
     * element of the grid.
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
                    draw(activeBoat, colIndex, rowIndex);                    
                }
                event.consume();
            }
        };
        return mousePositionHandler;
    }
    
    /**
     * 
     * @param boat
     * @param colIndex
     * @param rowIndex 
     */
    private void draw(BoatDrawing boat, Integer colIndex, Integer rowIndex){
        if(!boat.isRotation()){
            boat.getBoatRectangle().setLayoutX(GRID_X + SPACE + GRID_ELEMENT_SIZE*colIndex);
            boat.getBoatRectangle().setLayoutY(GRID_Y + SPACE + GRID_ELEMENT_SIZE*rowIndex);
            boat.setGridCol(colIndex);
            boat.setGridRow(rowIndex);
        } else if (boat.isRotation()){
            boat.getBoatRectangle().setLayoutX(GRID_X + SPACE + GRID_ELEMENT_SIZE*colIndex - BoatDrawing.getBoatDrawingOffset(boat.getBoatType()));
            boat.getBoatRectangle().setLayoutY(GRID_Y + SPACE + GRID_ELEMENT_SIZE*rowIndex + BoatDrawing.getBoatDrawingOffset(boat.getBoatType()));
            boat.setGridCol(colIndex);
            boat.setGridRow(rowIndex);
        }
    }
       
    /**
     * Method that rotates the boat active when the user press R.
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
     * 
     * @param boat 
     */
    private void drawRotation(BoatDrawing boat){
        if(boat.isRotation()){
            boat.setRotation(false);
            boat.getBoatRectangle().setRotate(0);
            draw(boat,boat.getGridCol(),boat.getGridRow());
        } else if(!boat.isRotation()){
            boat.setRotation(true);
            boat.getBoatRectangle().setRotate(90);
            draw(boat,boat.getGridCol(),boat.getGridRow());
        }
    }
    
    /**
     * Method that actives the boat when the user clicks  on it.
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
     * 
     * @param boat 
     */
    private void setActiveBoat(BoatDrawing boat){
        porteAvions.setActive(false);
        croiseur.setActive(false);
        contreTorpilleur.setActive(false);
        sousMarin.setActive(false);
        torpilleur.setActive(false);
        //Sets this boat invisible to mouse events and the others are not
        porteAvions.getBoatRectangle().setMouseTransparent(false);
        croiseur.getBoatRectangle().setMouseTransparent(false);
        contreTorpilleur.getBoatRectangle().setMouseTransparent(false);
        sousMarin.getBoatRectangle().setMouseTransparent(false);
        torpilleur.getBoatRectangle().setMouseTransparent(false);
        // Change its color to yellow and the color of the other to gray
        porteAvions.getBoatRectangle().setFill(Color.web("#ababab"));
        croiseur.getBoatRectangle().setFill(Color.web("#ababab"));
        contreTorpilleur.getBoatRectangle().setFill(Color.web("#ababab"));
        sousMarin.getBoatRectangle().setFill(Color.web("#ababab"));
        torpilleur.getBoatRectangle().setFill(Color.web("#ababab"));
        
        boat.setActive(true);
        boat.getBoatRectangle().setMouseTransparent(true);        
        boat.getBoatRectangle().setFill(Color.web("#d8d875"));
        
        activeBoat = boat; 
    }
    
    /**
     * Method that unactivates the boat when it is placed sur le grid.
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
}

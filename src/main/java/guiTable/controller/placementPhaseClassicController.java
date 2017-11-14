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
    private Rectangle porteAvionsDrawing;
    @FXML
    private Rectangle croiseurDrawing;
    @FXML
    private Rectangle contreTorpilleurDrawing;
    @FXML
    private Rectangle sousMarinDrawing;
    @FXML
    private Rectangle torpilleurDrawing; 
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private GridPane table;
    
    private static final int GRID_X = 100;
    private static final int GRID_Y = 100;
    private static final int SPACE = 3;
    private static final int GRID_ELEMENT_SIZE = 35;
    
    private boolean rotationIsValide;
    private BoatDrawing isActive;
            
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
        
        porteAvions = new BoatDrawing(BoatType.PORTEAVIONS,porteAvionsDrawing);
        croiseur = new BoatDrawing(BoatType.CROISEURFR,croiseurDrawing);
        contreTorpilleur = new BoatDrawing(BoatType.CONTRETORPILLEUR,contreTorpilleurDrawing);
        sousMarin = new BoatDrawing(BoatType.SOUSMARINFR,sousMarinDrawing);
        torpilleur = new BoatDrawing(BoatType.TORPILLEUR,torpilleurDrawing);
        
        porteAvions.setInitialLayoutX(porteAvionsDrawing.getLayoutX());
        porteAvions.setInitialLayoutY(porteAvionsDrawing.getLayoutY());
        
        croiseur.setInitialLayoutX(croiseurDrawing.getLayoutX());
        croiseur.setInitialLayoutY(croiseurDrawing.getLayoutY());
        
        contreTorpilleur.setInitialLayoutX(contreTorpilleurDrawing.getLayoutX());
        contreTorpilleur.setInitialLayoutY(contreTorpilleurDrawing.getLayoutY());
        
        sousMarin.setInitialLayoutX(sousMarinDrawing.getLayoutX());
        sousMarin.setInitialLayoutY(sousMarinDrawing.getLayoutY());
        
        torpilleur.setInitialLayoutX(torpilleurDrawing.getLayoutX());
        torpilleur.setInitialLayoutY(torpilleurDrawing.getLayoutY());
        
        porteAvionsDrawing.setOnMousePressed(pressMouse());
        
        croiseurDrawing.setOnMousePressed(pressMouse());
 
        contreTorpilleurDrawing.setOnMousePressed(pressMouse());
        
        sousMarinDrawing.setOnMousePressed(pressMouse());
        
        torpilleurDrawing.setOnMousePressed(pressMouse());

        for (int i = 0 ; i < 10 ; i++) {
            for (int j = 0; j < 10; j++) {
                Pane pane = new Pane();
                pane.setOnMouseEntered(detectMouse());
                table.add(pane, i, j);
            }
        }
        
        table.setOnMousePressed(pressMouseGrid());
        table.setOnMouseEntered(enableRotation());
        table.setOnMouseExited(disableRotation());
        
        anchorPane.addEventHandler(KeyEvent.KEY_PRESSED, pressR());
        
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
    
    private EventHandler<MouseEvent> detectMouse() {
        EventHandler<MouseEvent> mousePositionHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Gets the row and the column of the mouse
                Node source = (Node)event.getSource() ;
                Integer colIndex = GridPane.getColumnIndex(source);
                Integer rowIndex = GridPane.getRowIndex(source);
                if(porteAvions.isActive()){
                    //If this boat is active( the user has selected it)
                    if(!porteAvions.isRotation()){  
                        // Draws the boat in the new Position
                        porteAvionsDrawing.setLayoutX(GRID_X + SPACE + GRID_ELEMENT_SIZE*colIndex);
                        porteAvionsDrawing.setLayoutY(GRID_Y + SPACE + GRID_ELEMENT_SIZE*rowIndex);
                        // Sets the boat's new coordinates
                        porteAvions.setGridCol(colIndex);
                        porteAvions.setGridRow(rowIndex);
                    } else if(porteAvions.isRotation()){
                        porteAvionsDrawing.setLayoutX(GRID_X + SPACE + GRID_ELEMENT_SIZE*colIndex - 70);
                        porteAvionsDrawing.setLayoutY(GRID_Y + SPACE + GRID_ELEMENT_SIZE*rowIndex + 70);
                        porteAvions.setGridCol(colIndex);
                        porteAvions.setGridRow(rowIndex);
                    }
                }
                if(croiseur.isActive()){
                    if(!croiseur.isRotation()){  
                        croiseurDrawing.setLayoutX(GRID_X + SPACE + GRID_ELEMENT_SIZE*colIndex);
                        croiseurDrawing.setLayoutY(GRID_Y + SPACE + GRID_ELEMENT_SIZE*rowIndex);
                        croiseur.setGridCol(colIndex);
                        croiseur.setGridRow(rowIndex);
                    } else if(croiseur.isRotation()){
                        croiseurDrawing.setLayoutX(GRID_X + SPACE + GRID_ELEMENT_SIZE*colIndex - 52.5);
                        croiseurDrawing.setLayoutY(GRID_Y + SPACE + GRID_ELEMENT_SIZE*rowIndex + 52.5);
                        croiseur.setGridCol(colIndex);
                        croiseur.setGridRow(rowIndex);
                    }
                }
                if(contreTorpilleur.isActive()){
                    if(!contreTorpilleur.isRotation()){  
                        contreTorpilleurDrawing.setLayoutX(GRID_X + SPACE + GRID_ELEMENT_SIZE*colIndex);
                        contreTorpilleurDrawing.setLayoutY(GRID_Y + SPACE + GRID_ELEMENT_SIZE*rowIndex);
                        contreTorpilleur.setGridCol(colIndex);
                        contreTorpilleur.setGridRow(rowIndex);
                    } else if(contreTorpilleur.isRotation()){
                        contreTorpilleurDrawing.setLayoutX(GRID_X + SPACE + GRID_ELEMENT_SIZE*colIndex - 35);
                        contreTorpilleurDrawing.setLayoutY(GRID_Y + SPACE + GRID_ELEMENT_SIZE*rowIndex + 35);
                        contreTorpilleur.setGridCol(colIndex);
                        contreTorpilleur.setGridRow(rowIndex);
                    }
                }
                if(sousMarin.isActive()){
                    if(!sousMarin.isRotation()){  
                        sousMarinDrawing.setLayoutX(GRID_X + SPACE + GRID_ELEMENT_SIZE*colIndex);
                        sousMarinDrawing.setLayoutY(GRID_Y + SPACE + GRID_ELEMENT_SIZE*rowIndex);
                        sousMarin.setGridCol(colIndex);
                        sousMarin.setGridRow(rowIndex);
                    } else if(sousMarin.isRotation()){
                        sousMarinDrawing.setLayoutX(GRID_X + SPACE + GRID_ELEMENT_SIZE*colIndex - 35);
                        sousMarinDrawing.setLayoutY(GRID_Y + SPACE + GRID_ELEMENT_SIZE*rowIndex + 35);
                        sousMarin.setGridCol(colIndex);
                        sousMarin.setGridRow(rowIndex);
                    }
                }
                if(torpilleur.isActive()){
                    if(!torpilleur.isRotation()){  
                        torpilleurDrawing.setLayoutX(GRID_X + SPACE + GRID_ELEMENT_SIZE*colIndex);
                        torpilleurDrawing.setLayoutY(GRID_Y + SPACE + GRID_ELEMENT_SIZE*rowIndex);
                        torpilleur.setGridCol(colIndex);
                        torpilleur.setGridRow(rowIndex);
                    } else if(torpilleur.isRotation()){
                        torpilleurDrawing.setLayoutX(GRID_X + SPACE + GRID_ELEMENT_SIZE*colIndex - 17.5);
                        torpilleurDrawing.setLayoutY(GRID_Y + SPACE + GRID_ELEMENT_SIZE*rowIndex + 17.5);
                        torpilleur.setGridCol(colIndex);
                        torpilleur.setGridRow(rowIndex);
                    }
                }
                event.consume();
            }
        };
        return mousePositionHandler;
    }
    
    /**
     * Method that rotates the boat active when the user press R.
     * @return keyEeventHandler
     */
    
    public EventHandler<KeyEvent> pressR() {
        EventHandler<KeyEvent> keyEventHandler;
        keyEventHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.R) {
                    if(rotationIsValide){
<<<<<<< HEAD
                        
                        isActive.setRotate(isActive.getRotate(+90));
                        
                        
                        
                        
=======
                        // If the rotation is valid (The mouse is overe the grid)
>>>>>>> aeb156fc4a00891223a23c28680eb8c8af7a8b6a
                        if(porteAvions.isActive()){
                            // and this boat is active
                            if (porteAvionsDrawing.getRotate()==0){
<<<<<<< HEAD
                                porteAvionsDrawing.setRotate(porteAvionsDrawing.getRotate()+90);
                                porteAvionsDrawing.setLayoutX(GRIDX + SPACE + 35*porteAvions.getGridCol()-70);
                                porteAvionsDrawing.setLayoutY(GRIDY + SPACE + 35*porteAvions.getGridRow()+70);
                                porteAvions.setRotation(!porteAvions.isRotation());
                            } else if (porteAvionsDrawing.getRotate()==90){
                                porteAvionsDrawing.setRotate(180);
                                porteAvionsDrawing.setLayoutX(GRIDX + SPACE + 35*porteAvions.getGridCol());
                                porteAvionsDrawing.setLayoutY(GRIDY + SPACE + 35*porteAvions.getGridRow());
=======
                                // Switches its rotation angle
                                porteAvionsDrawing.setRotate(90);
                                // Draws the boat in the new position
                                porteAvionsDrawing.setLayoutX(GRID_X + SPACE + GRID_ELEMENT_SIZE*porteAvions.getGridCol()-70);
                                porteAvionsDrawing.setLayoutY(GRID_Y + SPACE + GRID_ELEMENT_SIZE*porteAvions.getGridRow()+70);
                                // Sets its new rotation
                                porteAvions.setRotation(true);
                            } else if (porteAvionsDrawing.getRotate()==90){
                                porteAvionsDrawing.setRotate(0);
                                porteAvionsDrawing.setLayoutX(GRID_X + SPACE + GRID_ELEMENT_SIZE*porteAvions.getGridCol());
                                porteAvionsDrawing.setLayoutY(GRID_Y + SPACE + GRID_ELEMENT_SIZE*porteAvions.getGridRow());
>>>>>>> aeb156fc4a00891223a23c28680eb8c8af7a8b6a
                                porteAvions.setRotation(false);                            
                            }
                        } else if(croiseur.isActive()){
                            if (croiseurDrawing.getRotate()==0){
                                croiseurDrawing.setRotate(90);
                                croiseurDrawing.setLayoutX(GRID_X + SPACE + GRID_ELEMENT_SIZE*croiseur.getGridCol()-52.5);
                                croiseurDrawing.setLayoutY(GRID_Y + SPACE + GRID_ELEMENT_SIZE*croiseur.getGridRow()+52.5);
                                croiseur.setRotation(true);
                            } else if (croiseurDrawing.getRotate()==90){
                                croiseurDrawing.setRotate(0);
                                croiseurDrawing.setLayoutX(GRID_X + SPACE + GRID_ELEMENT_SIZE*croiseur.getGridCol());
                                croiseurDrawing.setLayoutY(GRID_Y + SPACE + GRID_ELEMENT_SIZE*croiseur.getGridRow());
                                croiseur.setRotation(false);                            
                            }
                        } else if(contreTorpilleur.isActive()){
                            if (contreTorpilleurDrawing.getRotate()==0){
                                contreTorpilleurDrawing.setRotate(90);
                                contreTorpilleurDrawing.setLayoutX(GRID_X + SPACE + GRID_ELEMENT_SIZE*contreTorpilleur.getGridCol()-35);
                                contreTorpilleurDrawing.setLayoutY(GRID_Y + SPACE + GRID_ELEMENT_SIZE*contreTorpilleur.getGridRow()+35);
                                contreTorpilleur.setRotation(true);
                            } else if (contreTorpilleurDrawing.getRotate()==90){
                                contreTorpilleurDrawing.setRotate(0);
                                contreTorpilleurDrawing.setLayoutX(GRID_X + SPACE + GRID_ELEMENT_SIZE*contreTorpilleur.getGridCol());
                                contreTorpilleurDrawing.setLayoutY(GRID_Y + SPACE + GRID_ELEMENT_SIZE*contreTorpilleur.getGridRow());
                                contreTorpilleur.setRotation(false);                            
                            }
                        } else if(sousMarin.isActive()){
                            if (sousMarinDrawing.getRotate()==0){
                                sousMarinDrawing.setRotate(90);
                                sousMarinDrawing.setLayoutX(GRID_X + SPACE + GRID_ELEMENT_SIZE*sousMarin.getGridCol()-35);
                                sousMarinDrawing.setLayoutY(GRID_Y + SPACE + GRID_ELEMENT_SIZE*sousMarin.getGridRow()+35);
                                sousMarin.setRotation(true);
                            } else if (sousMarinDrawing.getRotate()==90){
                                sousMarinDrawing.setRotate(0);
                                sousMarinDrawing.setLayoutX(GRID_X + SPACE + 35*sousMarin.getGridCol());
                                sousMarinDrawing.setLayoutY(GRID_Y + SPACE + 35*sousMarin.getGridRow());
                                sousMarin.setRotation(false);                            
                            }
                        } else if(torpilleur.isActive()){
                            if (torpilleurDrawing.getRotate()==0){
                                torpilleurDrawing.setRotate(90);
                                torpilleurDrawing.setLayoutX(GRID_X + SPACE + GRID_ELEMENT_SIZE*torpilleur.getGridCol()-17.5);
                                torpilleurDrawing.setLayoutY(GRID_Y + SPACE + GRID_ELEMENT_SIZE*torpilleur.getGridRow()+17.5);
                                torpilleur.setRotation(true);
                            } else if (torpilleurDrawing.getRotate()==90){
                                torpilleurDrawing.setRotate(0);
                                torpilleurDrawing.setLayoutX(GRID_X + SPACE + GRID_ELEMENT_SIZE*torpilleur.getGridCol());
                                torpilleurDrawing.setLayoutY(GRID_Y + SPACE + GRID_ELEMENT_SIZE*torpilleur.getGridRow());
                                torpilleur.setRotation(false);                            
                            }
                        }
                    }   
                }
            }
        };
        return keyEventHandler;
    }
    
    /**
     * Method that actives the boat when the user clicks  on it.
     * @return mousePressHandler
     */   
    private EventHandler<MouseEvent> pressMouse() {
        EventHandler<MouseEvent> mousePressHandler;
        mousePressHandler = new EventHandler<MouseEvent>() {
            
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {

                    BoatDrawing boat = (BoatDrawing) event.getSource();
                    
                    /*
                    active boat
                    porteAvions.setActive(true);
                        croiseur.setActive(false);
                        contreTorpilleur.setActive(false);
                        sousMarin.setActive(false);
                        torpilleur.setActive(false);
                        
                        porteAvionsDrawing.setMouseTransparent(true);
                        croiseurDrawing.setMouseTransparent(false);
                        contreTorpilleurDrawing.setMouseTransparent(false);
                        sousMarinDrawing.setMouseTransparent(false);
                        torpilleurDrawing.setMouseTransparent(false);
                        
                        porteAvionsDrawing.setFill(Color.web("#d8d875"));
                        croiseurDrawing.setFill(Color.web("#ababab"));
                        contreTorpilleurDrawing.setFill(Color.web("#ababab"));
                        sousMarinDrawing.setFill(Color.web("#ababab"));
                        torpilleurDrawing.setFill(Color.web("#ababab"));
                    
                        boat.setActive(true)
                    */
                    
                    isActive = boat;

                    //If the user has clicked in the window
                    if(event.getSource().equals(porteAvionsDrawing)){
                        //Over this boat, sets that this boat is active and the others are not
                        porteAvions.setActive(true);
                        croiseur.setActive(false);
                        contreTorpilleur.setActive(false);
                        sousMarin.setActive(false);
                        torpilleur.setActive(false);
                        //Sets this boat invisible to mouse events and the others are not
                        porteAvionsDrawing.setMouseTransparent(true);
                        croiseurDrawing.setMouseTransparent(false);
                        contreTorpilleurDrawing.setMouseTransparent(false);
                        sousMarinDrawing.setMouseTransparent(false);
                        torpilleurDrawing.setMouseTransparent(false);
                        // Change its color to yellow and the color of the other to gray
                        porteAvionsDrawing.setFill(Color.web("#d8d875"));
                        croiseurDrawing.setFill(Color.web("#ababab"));
                        contreTorpilleurDrawing.setFill(Color.web("#ababab"));
                        sousMarinDrawing.setFill(Color.web("#ababab"));
                        torpilleurDrawing.setFill(Color.web("#ababab"));
                    }   
                    if(event.getSource().equals(croiseurDrawing)){
                        porteAvions.setActive(false);
                        croiseur.setActive(true);
                        contreTorpilleur.setActive(false);
                        sousMarin.setActive(false);
                        torpilleur.setActive(false);
                        
                        porteAvionsDrawing.setMouseTransparent(false);
                        croiseurDrawing.setMouseTransparent(true);
                        contreTorpilleurDrawing.setMouseTransparent(false);
                        sousMarinDrawing.setMouseTransparent(false);
                        torpilleurDrawing.setMouseTransparent(false);
                        
                        porteAvionsDrawing.setFill(Color.web("#ababab"));
                        croiseurDrawing.setFill(Color.web("#d8d875"));
                        contreTorpilleurDrawing.setFill(Color.web("#ababab"));
                        sousMarinDrawing.setFill(Color.web("#ababab"));
                        torpilleurDrawing.setFill(Color.web("#ababab"));
                    }    
                    if(event.getSource().equals(contreTorpilleurDrawing)){
                        porteAvions.setActive(false);
                        croiseur.setActive(false);
                        contreTorpilleur.setActive(true);
                        sousMarin.setActive(false);
                        torpilleur.setActive(false);
                        
                        porteAvionsDrawing.setMouseTransparent(false);
                        croiseurDrawing.setMouseTransparent(false);
                        contreTorpilleurDrawing.setMouseTransparent(true);
                        sousMarinDrawing.setMouseTransparent(false);
                        torpilleurDrawing.setMouseTransparent(false);
                        
                        porteAvionsDrawing.setFill(Color.web("#ababab"));
                        croiseurDrawing.setFill(Color.web("#ababab"));
                        contreTorpilleurDrawing.setFill(Color.web("#d8d875"));
                        sousMarinDrawing.setFill(Color.web("#ababab"));
                        torpilleurDrawing.setFill(Color.web("#ababab"));
                    }  
                    if(event.getSource().equals(sousMarinDrawing)){
                        porteAvions.setActive(false);
                        croiseur.setActive(false);
                        contreTorpilleur.setActive(false);
                        sousMarin.setActive(true);
                        torpilleur.setActive(false);
                        
                        porteAvionsDrawing.setMouseTransparent(false);
                        croiseurDrawing.setMouseTransparent(false);
                        contreTorpilleurDrawing.setMouseTransparent(false);
                        sousMarinDrawing.setMouseTransparent(true);
                        torpilleurDrawing.setMouseTransparent(false);
                        
                        porteAvionsDrawing.setFill(Color.web("#ababab"));
                        croiseurDrawing.setFill(Color.web("#ababab"));
                        contreTorpilleurDrawing.setFill(Color.web("#ababab"));
                        sousMarinDrawing.setFill(Color.web("#d8d875"));
                        torpilleurDrawing.setFill(Color.web("#ababab"));
                    }  
                    if(event.getSource().equals(torpilleurDrawing)){
                        porteAvions.setActive(false);
                        croiseur.setActive(false);
                        contreTorpilleur.setActive(false);
                        sousMarin.setActive(false);
                        torpilleur.setActive(true);
                        
                        
                        porteAvionsDrawing.setMouseTransparent(false);
                        croiseurDrawing.setMouseTransparent(false);
                        contreTorpilleurDrawing.setMouseTransparent(false);
                        sousMarinDrawing.setMouseTransparent(false);
                        torpilleurDrawing.setMouseTransparent(true);
                        
                        porteAvionsDrawing.setFill(Color.web("#ababab"));
                        croiseurDrawing.setFill(Color.web("#ababab"));
                        contreTorpilleurDrawing.setFill(Color.web("#ababab"));
                        sousMarinDrawing.setFill(Color.web("#ababab"));
                        torpilleurDrawing.setFill(Color.web("#d8d875"));
                    }  
                }
            }
        };
        return mousePressHandler;
    }
    
    /**
     * Method that unactivates the boat when it is placed sur le grid.
     * @return mousePressGridHandler
     */    
    private EventHandler<MouseEvent> pressMouseGrid() {
        EventHandler<MouseEvent> mousePressGridHandler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
<<<<<<< HEAD
                    
                    isActive.setActive(false);
                    isActive.setMouseTransparent(false);
                    isActive.setFill(Color.web("#ababab"));
                    
                                        
=======
                    //If the user has placed the boat
                    if(porteAvions.isActive()){
                        //And this boat is active, the method makes the boat unactive and not
                        // transparent to mouse events and changes the boat's color.
                        porteAvions.setActive(false);
                        porteAvionsDrawing.setMouseTransparent(false);
                        porteAvionsDrawing.setFill(Color.web("#ababab"));
                    }
                    if(croiseur.isActive()){
                        croiseur.setActive(false);
                        croiseurDrawing.setMouseTransparent(false);
                        croiseurDrawing.setFill(Color.web("#ababab"));
                    }
                    if(contreTorpilleur.isActive()){
                        contreTorpilleur.setActive(false);
                        contreTorpilleurDrawing.setMouseTransparent(false);
                        contreTorpilleurDrawing.setFill(Color.web("#ababab"));
                    }
                    if(sousMarin.isActive()){
                        sousMarin.setActive(false);
                        sousMarinDrawing.setMouseTransparent(false);
                        sousMarinDrawing.setFill(Color.web("#ababab"));
                    }
                    if(torpilleur.isActive()){
                        torpilleur.setActive(false);
                        torpilleurDrawing.setMouseTransparent(false);
                        torpilleurDrawing.setFill(Color.web("#ababab"));
                    }        
>>>>>>> aeb156fc4a00891223a23c28680eb8c8af7a8b6a
                }
            }
        };
        return mousePressGridHandler;
    }
}

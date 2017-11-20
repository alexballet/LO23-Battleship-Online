/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiTable.controller;

import guiTable.BoatDrawing;
import guiTable.PlacementPhase;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import packageStructDonnées.BoatType;

/**
 *
 * @author caioz
 */
public class ClassicPlacementPhaseController extends PlacementPhase implements Initializable{
    
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

}

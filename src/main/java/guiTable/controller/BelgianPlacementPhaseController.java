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
public class BelgianPlacementPhaseController extends PlacementPhase implements Initializable{
    

    @FXML
    private Rectangle croiseurRectangle1;
    @FXML
    private Rectangle croiseurRectangle2;
    @FXML
    private Rectangle torpilleurRectangle1;
    @FXML
    private Rectangle torpilleurRectangle2;
    @FXML
    private Rectangle torpilleurRectangle3;
    @FXML
    private Rectangle sousMarinRectangle1;
    @FXML
    private Rectangle sousMarinRectangle2;
    @FXML
    private Rectangle sousMarinRectangle3;
    @FXML
    private Rectangle sousMarinRectangle4; 
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
        boatMap.put(cuirasseRectangle,new BoatDrawing(BoatType.CUIRASSE,cuirasseRectangle));
        boatMap.put(croiseurRectangle1, new BoatDrawing(BoatType.CROISEURB,croiseurRectangle1));
        boatMap.put(croiseurRectangle2, new BoatDrawing(BoatType.CROISEURB,croiseurRectangle2));
        boatMap.put(torpilleurRectangle1, new BoatDrawing(BoatType.TORPILLEUR,torpilleurRectangle1));
        boatMap.put(torpilleurRectangle2, new BoatDrawing(BoatType.TORPILLEUR,torpilleurRectangle2));
        boatMap.put(torpilleurRectangle3, new BoatDrawing(BoatType.TORPILLEUR,torpilleurRectangle3));
        boatMap.put(sousMarinRectangle1, new BoatDrawing(BoatType.SOUSMARINB,sousMarinRectangle1));
        boatMap.put(sousMarinRectangle2, new BoatDrawing(BoatType.SOUSMARINB,sousMarinRectangle2));
        boatMap.put(sousMarinRectangle3, new BoatDrawing(BoatType.SOUSMARINB,sousMarinRectangle3));
        boatMap.put(sousMarinRectangle4, new BoatDrawing(BoatType.SOUSMARINB,sousMarinRectangle4));
        
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
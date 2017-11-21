/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiTable.controllers;

import guiTable.BoatDrawing;
import guiTable.controllers.PlacementPhaseController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Rectangle;
import packageStructDonnées.BoatType;

/**
 * implementation of placement controller for belgian phase
 * @author caioz
 */
public class BelgianPlacementPhaseController extends PlacementPhaseController implements Initializable{
    
    @FXML
    protected Rectangle cuirasseRectangle;
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

    @Override
    protected void initBoatMap() {
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
        
    }

}
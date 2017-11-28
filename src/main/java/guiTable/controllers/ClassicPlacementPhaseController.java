/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiTable.controllers;

import guiTable.BoatDrawing;
import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;
import structData.BoatType;

/**
 * implementation of placement controller for classic phase
 * @author caioz
 */
public class ClassicPlacementPhaseController extends PlacementPhaseController {
    
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
     
    
    @Override
    protected void initBoatMap() {
        boatMap.put(porteAvionsRectangle,new BoatDrawing(BoatType.PORTEAVIONS,porteAvionsRectangle));
        boatMap.put(croiseurRectangle, new BoatDrawing(BoatType.CROISEURFR,croiseurRectangle));
        boatMap.put(contreTorpilleurRectangle, new BoatDrawing(BoatType.CONTRETORPILLEUR,contreTorpilleurRectangle));
        boatMap.put(sousMarinRectangle, new BoatDrawing(BoatType.SOUSMARINFR,sousMarinRectangle));
        boatMap.put(torpilleurRectangle, new BoatDrawing(BoatType.TORPILLEUR,torpilleurRectangle));
    }
}

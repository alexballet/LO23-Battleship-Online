/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiTable;

import guiTable.controllers.GamePhaseController;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author corentinhembise
 */
public class CaseDrawing extends Label {
    
    public enum Type { TOUCHED, MISSED, SHOT }
    
    public CaseDrawing() {
        this.setPrefHeight(GamePhaseController.GRID_ELEMENT_SIZE);
        this.setPrefWidth(GamePhaseController.GRID_ELEMENT_SIZE);
    }
    
    public CaseDrawing(Type t) {
        this();
        switch(t) {
            case SHOT: 
                Image image = new Image(getClass().getResourceAsStream("/img/target.png"));
                this.setGraphic(new ImageView(image));
                break;
            case TOUCHED:
                this.setStyle("-fx-background-color: #c0392b");
                break;
            case MISSED:
                this.setStyle("-fx-background-color: #34495e");
                break;
        }
    }
}

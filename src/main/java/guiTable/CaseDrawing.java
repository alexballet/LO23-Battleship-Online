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
    
    public enum Type { TOUCHED, MISSED, SHOT, BOAT, SUNK_BOAT }
    
    public CaseDrawing() {
        this.setPrefHeight(GamePhaseController.GRID_ELEMENT_SIZE);
        this.setPrefWidth(GamePhaseController.GRID_ELEMENT_SIZE);
    }
    
    public CaseDrawing(Type t) {
        this();
        Image image;
        switch(t) {
            case SHOT: 
                image = new Image(getClass().getResourceAsStream("/img/target.png"));
                this.setGraphic(new ImageView(image));
                break;
            case TOUCHED:
                image = new Image(getClass().getResourceAsStream("/img/explosion.png"));
                this.setGraphic(new ImageView(image));
                break;
            case MISSED:
                image = new Image(getClass().getResourceAsStream("/img/sea.png"));
                this.setGraphic(new ImageView(image));
                break;
            case BOAT:
                this.setStyle("-fx-background-color: #34495e");
                break;
            case SUNK_BOAT:
                this.setStyle("-fx-background-color: #c0392b");
                break;
        }
    }
}

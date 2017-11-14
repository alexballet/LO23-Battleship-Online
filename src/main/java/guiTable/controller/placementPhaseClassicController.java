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
    
    private static final int GRIDX = 100;
    private static final int GRIDY = 100;
    private static final int SPACE = 3;
    
    private boolean rotationIsValide;
    private BoatDrawing isActive;
            
    private BoatDrawing porteAvions;
    private BoatDrawing croiseur;
    private BoatDrawing contreTorpilleur;
    private BoatDrawing sousMarin;
    private BoatDrawing torpilleur; 
    
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
    
    private EventHandler<MouseEvent> enableRotation() {
        EventHandler<MouseEvent> mousePressHandler = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                
               rotationIsValide = true;
                
                event.consume();
            }
        };
        return mousePressHandler;
    }
    
    private EventHandler<MouseEvent> disableRotation() {
        EventHandler<MouseEvent> mousePressHandler = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                
               rotationIsValide = false;
                
                event.consume();
            }
        };
        return mousePressHandler;
    }
    
    private EventHandler<MouseEvent> detectMouse() {
        EventHandler<MouseEvent> mousePressHandler = new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Node source = (Node)event.getSource() ;
                Integer colIndex = GridPane.getColumnIndex(source);
                Integer rowIndex = GridPane.getRowIndex(source);
                if(porteAvions.isActive()){
                    if(!porteAvions.isRotation()){  
                        porteAvionsDrawing.setLayoutX(GRIDX + SPACE + 35*colIndex);
                        porteAvionsDrawing.setLayoutY(GRIDY + SPACE + 35*rowIndex);
                        porteAvions.setGridCol(colIndex);
                        porteAvions.setGridRow(rowIndex);
                    } else if(porteAvions.isRotation()){
                        porteAvionsDrawing.setLayoutX(GRIDX + SPACE + 35*colIndex - 70);
                        porteAvionsDrawing.setLayoutY(GRIDY + SPACE + 35*rowIndex + 70);
                        porteAvions.setGridCol(colIndex);
                        porteAvions.setGridRow(rowIndex);
                    }
                }
                if(croiseur.isActive()){
                    if(!croiseur.isRotation()){  
                        croiseurDrawing.setLayoutX(GRIDX + SPACE + 35*colIndex);
                        croiseurDrawing.setLayoutY(GRIDY + SPACE + 35*rowIndex);
                        croiseur.setGridCol(colIndex);
                        croiseur.setGridRow(rowIndex);
                    } else if(croiseur.isRotation()){
                        croiseurDrawing.setLayoutX(GRIDX + SPACE + 35*colIndex - 52.5);
                        croiseurDrawing.setLayoutY(GRIDY + SPACE + 35*rowIndex + 52.5);
                        croiseur.setGridCol(colIndex);
                        croiseur.setGridRow(rowIndex);
                    }
                }
                if(contreTorpilleur.isActive()){
                    if(!contreTorpilleur.isRotation()){  
                        contreTorpilleurDrawing.setLayoutX(GRIDX + SPACE + 35*colIndex);
                        contreTorpilleurDrawing.setLayoutY(GRIDY + SPACE + 35*rowIndex);
                        contreTorpilleur.setGridCol(colIndex);
                        contreTorpilleur.setGridRow(rowIndex);
                    } else if(contreTorpilleur.isRotation()){
                        contreTorpilleurDrawing.setLayoutX(GRIDX + SPACE + 35*colIndex - 35);
                        contreTorpilleurDrawing.setLayoutY(GRIDY + SPACE + 35*rowIndex + 35);
                        contreTorpilleur.setGridCol(colIndex);
                        contreTorpilleur.setGridRow(rowIndex);
                    }
                }
                if(sousMarin.isActive()){
                    if(!sousMarin.isRotation()){  
                        sousMarinDrawing.setLayoutX(GRIDX + SPACE + 35*colIndex);
                        sousMarinDrawing.setLayoutY(GRIDY + SPACE + 35*rowIndex);
                        sousMarin.setGridCol(colIndex);
                        sousMarin.setGridRow(rowIndex);
                    } else if(sousMarin.isRotation()){
                        sousMarinDrawing.setLayoutX(GRIDX + SPACE + 35*colIndex - 35);
                        sousMarinDrawing.setLayoutY(GRIDY + SPACE + 35*rowIndex + 35);
                        sousMarin.setGridCol(colIndex);
                        sousMarin.setGridRow(rowIndex);
                    }
                }
                if(torpilleur.isActive()){
                    if(!torpilleur.isRotation()){  
                        torpilleurDrawing.setLayoutX(GRIDX + SPACE + 35*colIndex);
                        torpilleurDrawing.setLayoutY(GRIDY + SPACE + 35*rowIndex);
                        torpilleur.setGridCol(colIndex);
                        torpilleur.setGridRow(rowIndex);
                    } else if(torpilleur.isRotation()){
                        torpilleurDrawing.setLayoutX(GRIDX + SPACE + 35*colIndex - 17.5);
                        torpilleurDrawing.setLayoutY(GRIDY + SPACE + 35*rowIndex + 17.5);
                        torpilleur.setGridCol(colIndex);
                        torpilleur.setGridRow(rowIndex);
                    }
                }
                
                event.consume();
            }
        };
        return mousePressHandler;
    }
    
    public EventHandler<KeyEvent> pressR() {
        EventHandler<KeyEvent> keyEventHandler;
        keyEventHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.R) {
                    if(rotationIsValide){
                        
                        isActive.setRotate(isActive.getRotate(+90));
                        
                        
                        
                        
                        if(porteAvions.isActive()){
                            if (porteAvionsDrawing.getRotate()==0){
                                porteAvionsDrawing.setRotate(porteAvionsDrawing.getRotate()+90);
                                porteAvionsDrawing.setLayoutX(GRIDX + SPACE + 35*porteAvions.getGridCol()-70);
                                porteAvionsDrawing.setLayoutY(GRIDY + SPACE + 35*porteAvions.getGridRow()+70);
                                porteAvions.setRotation(!porteAvions.isRotation());
                            } else if (porteAvionsDrawing.getRotate()==90){
                                porteAvionsDrawing.setRotate(180);
                                porteAvionsDrawing.setLayoutX(GRIDX + SPACE + 35*porteAvions.getGridCol());
                                porteAvionsDrawing.setLayoutY(GRIDY + SPACE + 35*porteAvions.getGridRow());
                                porteAvions.setRotation(false);                            
                            }
                        } else if(croiseur.isActive()){
                            if (croiseurDrawing.getRotate()==0){
                                croiseurDrawing.setRotate(90);
                                croiseurDrawing.setLayoutX(GRIDX + SPACE + 35*croiseur.getGridCol()-52.5);
                                croiseurDrawing.setLayoutY(GRIDY + SPACE + 35*croiseur.getGridRow()+52.5);
                                croiseur.setRotation(true);
                            } else if (croiseurDrawing.getRotate()==90){
                                croiseurDrawing.setRotate(0);
                                croiseurDrawing.setLayoutX(GRIDX + SPACE + 35*croiseur.getGridCol());
                                croiseurDrawing.setLayoutY(GRIDY + SPACE + 35*croiseur.getGridRow());
                                croiseur.setRotation(false);                            
                            }
                        } else if(contreTorpilleur.isActive()){
                            if (contreTorpilleurDrawing.getRotate()==0){
                                contreTorpilleurDrawing.setRotate(90);
                                contreTorpilleurDrawing.setLayoutX(GRIDX + SPACE + 35*contreTorpilleur.getGridCol()-35);
                                contreTorpilleurDrawing.setLayoutY(GRIDY + SPACE + 35*contreTorpilleur.getGridRow()+35);
                                contreTorpilleur.setRotation(true);
                            } else if (contreTorpilleurDrawing.getRotate()==90){
                                contreTorpilleurDrawing.setRotate(0);
                                contreTorpilleurDrawing.setLayoutX(GRIDX + SPACE + 35*contreTorpilleur.getGridCol());
                                contreTorpilleurDrawing.setLayoutY(GRIDY + SPACE + 35*contreTorpilleur.getGridRow());
                                contreTorpilleur.setRotation(false);                            
                            }
                        } else if(sousMarin.isActive()){
                            if (sousMarinDrawing.getRotate()==0){
                                sousMarinDrawing.setRotate(90);
                                sousMarinDrawing.setLayoutX(GRIDX + SPACE + 35*sousMarin.getGridCol()-35);
                                sousMarinDrawing.setLayoutY(GRIDY + SPACE + 35*sousMarin.getGridRow()+35);
                                sousMarin.setRotation(true);
                            } else if (sousMarinDrawing.getRotate()==90){
                                sousMarinDrawing.setRotate(0);
                                sousMarinDrawing.setLayoutX(GRIDX + SPACE + 35*sousMarin.getGridCol());
                                sousMarinDrawing.setLayoutY(GRIDY + SPACE + 35*sousMarin.getGridRow());
                                sousMarin.setRotation(false);                            
                            }
                        } else if(torpilleur.isActive()){
                            if (torpilleurDrawing.getRotate()==0){
                                torpilleurDrawing.setRotate(90);
                                torpilleurDrawing.setLayoutX(GRIDX + SPACE + 35*torpilleur.getGridCol()-17.5);
                                torpilleurDrawing.setLayoutY(GRIDY + SPACE + 35*torpilleur.getGridRow()+17.5);
                                torpilleur.setRotation(true);
                            } else if (torpilleurDrawing.getRotate()==90){
                                torpilleurDrawing.setRotate(0);
                                torpilleurDrawing.setLayoutX(GRIDX + SPACE + 35*torpilleur.getGridCol());
                                torpilleurDrawing.setLayoutY(GRIDY + SPACE + 35*torpilleur.getGridRow());
                                torpilleur.setRotation(false);                            
                            }
                        }
                    }   
                }
            }
        };
        return keyEventHandler;
    }
    
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
                    
                    if(event.getSource().equals(porteAvionsDrawing)){
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
    
    private EventHandler<MouseEvent> pressMouseGrid() {
        EventHandler<MouseEvent> mousePressHandler = new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    
                    isActive.setActive(false);
                    isActive.setMouseTransparent(false);
                    isActive.setFill(Color.web("#ababab"));
                    
                                        
                }
            }
        };
        return mousePressHandler;
    }
}

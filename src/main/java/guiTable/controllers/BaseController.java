/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiTable.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 * every class controller will herit from BaseController
 * @author raphael
 */
public class BaseController {
    /**
    * Allows to replace pane by another one
    * @param paneToFill
    * @param contentAdress
    * @return FXMLLoader
    * @throws Exception 
    */
    protected FXMLLoader fillElement(AnchorPane paneToFill, String contentAdress) {
       FXMLLoader loader = new FXMLLoader();
       loader.setLocation(getClass().getResource(contentAdress));
       try{
           AnchorPane contentPane = loader.load();
           paneToFill.getChildren().add(contentPane);
       }
       catch(Exception e){
           System.err.println(e.getMessage());
       }
       return loader;
    }
}


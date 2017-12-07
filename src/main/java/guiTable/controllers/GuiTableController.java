/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiTable.controllers;

import guiTable.GuiTableInterface;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import structData.Boat;
import structData.ChatMessage;
import structData.Shot;

/**
 *
 * @author Raphael
 */
public class GuiTableController implements GuiTableInterface {

    private AnchorPane rootLayout;
    private static GuiTableController INSTANCE = null;
    
    private Stage mainStage;
    
    private Boolean classic;
    
    private GamePhaseController controller;

    /**
     * Private constructor for GuiTableController.
     */
    private GuiTableController(){
    }
    
    
    /**
     * Entry point for a unique instance of singleton GuiTableController;
     * @return GuiTableController : the singleton GuiTableController.
     */
    public static GuiTableInterface getInstance()
    {			
        if (INSTANCE == null)
        { 
            INSTANCE = new GuiTableController();	
        }
        return INSTANCE;
    }
    
    /**
     * this function call an other fxml context and refresh page
     * @param currentStage
     * @throws Exception 
     */
    @Override
    public void displayPlacementPhase(Stage currentStage, Boolean classic) throws Exception {
        this.mainStage = currentStage;
        this.classic = classic;
        
        FXMLLoader loader = new FXMLLoader();
        if(classic) {
            loader.setLocation(getClass().getResource("/fxml/IhmTable/ClassicPlacementPhase.fxml"));
        } else {
            loader.setLocation(getClass().getResource("/fxml/IhmTable/BelgianPlacementPhase.fxml"));
        }
        rootLayout = (AnchorPane) loader.load();
        Scene scene = new Scene(rootLayout);
        mainStage.setTitle("Battleship-Online");
        mainStage.setScene(scene);
        mainStage.show();
    }

    @Override
    public void opponentReady(Boolean myTurn) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/IhmTable/GamePhase.fxml"));

        try {
            rootLayout = (AnchorPane) loader.load();
            controller = loader.<GamePhaseController>getController();
            controller.setMyTurn(myTurn);
        
            Scene scene = new Scene(rootLayout);
            mainStage.setScene(scene);
            mainStage.show();
        } catch(IOException e) {
            System.err.println("ERROR : "+e.getMessage());
        }
    }

    @Override
    public void displayObserverPhase() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void displayVictory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void displayDefeat() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addChatMessage(ChatMessage message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void displayMyShotResult(Shot myShotResult, Boat boat) {
        controller.addShot(myShotResult);
        if (boat != null){
            controller.sunckBoat(boat);
        }
    }

    @Override
    public void displayOpponentShot(Shot opponentShot, structData.Boat boat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void displayMessage(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}

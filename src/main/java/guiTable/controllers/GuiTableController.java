/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiTable.controllers;

import data.DataController;
import guiTable.GuiTableInterface;
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
    private String chatFxmlURL = "/fxml/IhmTable/chat.fxml";
    private ChatController chatController;
    private static GuiTableController INSTANCE = null;
    private DataController dataController;

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
//    @Override
//    public void displayPlacementPhase(Stage currentStage, Boolean classic) throws Exception {
//        FXMLLoader loader = new FXMLLoader();
//        if(classic) {
//        loader.setLocation(getClass().getResource("/fxml/IhmTable/ClassicPlacementPhase.fxml"));
//        } else {
//        loader.setLocation(getClass().getResource("/fxml/IhmTable/BelgianPlacementPhase.fxml"));
//        }
//        rootLayout = (AnchorPane) loader.load();
//        Scene scene = new Scene(rootLayout);
//        currentStage.setTitle("Battleship-Online");
//        currentStage.setScene(scene);
//        currentStage.show();
//    }
    
    /**
    * this function call an other fxml context and refresh page
    * @param currentStage
    * @throws Exception 
    */
    @Override
    public void displayPlacementPhase(Stage currentStage, Boolean classic) throws Exception {
        FXMLLoader displayPlacementPhaseLoader = new FXMLLoader();
        if(classic) {
            displayPlacementPhaseLoader.setLocation(getClass().getResource("/fxml/IhmTable/ClassicPlacementPhase.fxml"));
        } else {
            displayPlacementPhaseLoader.setLocation(getClass().getResource("/fxml/IhmTable/BelgianPlacementPhase.fxml"));
        }
        rootLayout = (AnchorPane) displayPlacementPhaseLoader.load();
        PlacementPhaseController placementPhaseController = displayPlacementPhaseLoader.getController();
        chatController = placementPhaseController.fillChatSlot(chatFxmlURL);

        Scene scene = new Scene(rootLayout);
        currentStage.setTitle("Battleship-Online");
        currentStage.setScene(scene);
        currentStage.show();
    }

    @Override
    public void opponentReady(Boolean myTurn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public void displayMessage(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addChatMessage(ChatMessage message) { 
        chatController.receiveAMessage(message);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void displayOpponentShot(Shot opponentShot, structData.Boat boat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void displayMyShotResult(Shot myShotResult, structData.Boat boat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void setDataController(DataController data) {
        this.dataController = data;
    }
    
}

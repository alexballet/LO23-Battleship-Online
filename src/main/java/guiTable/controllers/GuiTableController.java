/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiTable.controllers;

import guiTable.GuiTableInterface;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import packageStructDonnées.Boat;
import packageStructDonnées.Message;
import packageStructDonnées.Shot;

/**
 *
 * @author Raphael
 */
public class GuiTableController implements GuiTableInterface {

    
    private AnchorPane rootLayout;
    private static GuiTableController INSTANCE = null;

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
    public void displayPlacementPhase(Stage currentStage, Boolean classic, Integer placementTime) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        if(classic) {
            loader.setLocation(getClass().getResource("/fxml/IhmTable/ClassicPlacementPhase.fxml"));
        } else {
            loader.setLocation(getClass().getResource("/fxml/IhmTable/BelgianPlacementPhase.fxml"));
        }        
        rootLayout = (AnchorPane) loader.load(); 
        PlacementPhaseController controller = loader.getController();
        controller.setPlacementTime(placementTime);
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
    public void displayOpponentShot(Shot opponentShot, Boat boat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void displayMyShotResult(Shot myShotResult, Boat boat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addChatMessage(Message message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void displayMessage(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

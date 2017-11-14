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
 * @author raphael
 */
public class GuiTableController implements GuiTableInterface {

    
    private AnchorPane rootLayout;
    
    @Override
    public void displayPlacementPhase(Stage currentStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/Ihm-plateau/placementPhase.fxml"));
        rootLayout = (AnchorPane) loader.load();
        PlacementPhaseController controller = loader.getController();
        controller.init(); 
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

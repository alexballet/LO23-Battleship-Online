/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiTable.controllers;

import data.CDataTable;
import guiTable.GuiTableInterface;
import java.io.IOException;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import structData.Boat;
import structData.ChatMessage;
import structData.Position;
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
    private CDataTable dataController;
    private ChatController chatController;
    private String chatFxmlURL = "/fxml/IhmTable/chat.fxml";
    private List<Boat> boats = null;

    /**
     * Private constructor for GuiTableController.
     */
    private GuiTableController(){
    }
    
    
    /**
     * Entry point for a unique instance of singleton GuiTableController;
     * @return GuiTableController : the singleton GuiTableController.
     */
    public static GuiTableController getInstance()
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
        PlacementPhaseController controller = loader.getController();
        controller.setPlacementTime(placementTime);

        chatController = controller.fillChatSlot(chatFxmlURL);
        chatController.setDataController(dataController);

    }

    @Override
    public void opponentReady(Boolean myTurn, Integer roundTime) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/IhmTable/GamePhase.fxml"));

        try {
            rootLayout = (AnchorPane) loader.load();
            controller = loader.<GamePhaseController>getController();
            controller.setMyTurn(myTurn);
            controller.setMyBoats(boats);
            controller.setRoundTime(roundTime);

            Scene scene = new Scene(rootLayout);
            mainStage.setScene(scene);
            mainStage.show();
        } catch(IOException e) {
            System.err.println("ERROR : "+e.getMessage());
        }
    }

    @Override
    public void opponentReady(Boolean myTurn) {
        this.opponentReady(myTurn, null);
    }

    @Override
    public void displayObserverPhase(Stage currentStage) {
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
        chatController.receiveAMessage(message);
    }

    @Override
    public void displayMyShotResult(Shot myShotResult, Boat boat) {
        controller.addShot(myShotResult);
        if (boat != null){
            controller.sunckBoat(boat);
        }
        controller.setMyTurn(false);
    }

    @Override
    public void displayOpponentShot(Shot opponentShot, Boat boat) {
        controller.addOpponentShot(opponentShot);
        if (boat != null){
            controller.sunkMyBoat(boat);
        }
        controller.setMyTurn(true);
    }

    @Override
    public void displayMessage(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDataController(CDataTable data) {
        this.dataController = data;
    }
    
    public void validateBoats(List<Boat> boats) {
        this.boats = boats;
        dataController.coordinateShips(boats);
    }
    
    public void validateShot(Position pos) {
        dataController.coordinate(pos);
    }
    
    public Boolean exitGame() {
        return dataController.exit();
    }
}

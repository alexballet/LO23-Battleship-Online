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
import javafx.application.Platform;
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
    
    private GamePhaseController gamePhaseController;
    private ObserverPhaseController observerPhaseController;
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
    public void opponentReady(final Boolean myTurn) {
    		Runnable command = new Runnable() {
			@Override
			public void run() {
		        FXMLLoader loader = new FXMLLoader();
		        loader.setLocation(getClass().getResource("/fxml/IhmTable/GamePhase.fxml"));
		
		        try {
		            rootLayout = (AnchorPane) loader.load();
		            gamePhaseController = loader.<GamePhaseController>getController();
		            gamePhaseController.setMyTurn(myTurn);
		            gamePhaseController.setMyBoats(boats);
		        
		            Scene scene = new Scene(rootLayout);
		            mainStage.setScene(scene);
		            mainStage.show();
		        } catch(IOException e) {
		            System.err.println("ERROR : "+e.getMessage());
		        }
			}
		};
		Platform.runLater(command);
    }

    @Override
    public void displayObserverPhase(final boolean turn) {
        Runnable command = new Runnable() {
			@Override
                    public void run() {
		        FXMLLoader loader = new FXMLLoader();
		        loader.setLocation(getClass().getResource("/fxml/IhmTable/ObserverPhase.fxml"));
		
		        try {
		            rootLayout = (AnchorPane) loader.load();
		            observerPhaseController = loader.<ObserverPhaseController>getController();
		            observerPhaseController.setTurn(turn);
		        
		            Scene scene = new Scene(rootLayout);
		            mainStage.setScene(scene);
		            mainStage.show();
		        } catch(IOException e) {
		            System.err.println("ERROR : "+e.getMessage());
		        }
                    }
		};
		Platform.runLater(command);
    }
    
    @Override
    public void displayPlayer1Shot(final Shot shotResult,final Boat boat) {
    		Runnable command = new Runnable() {
			@Override
			public void run() {
		        observerPhaseController.addPlayer1Shot(shotResult);
		        if (boat != null && boat.getSunk()){
		            observerPhaseController.sunkPlayer1Boat(boat);
		        }
		        observerPhaseController.setTurn(false);
			}
		};
		Platform.runLater(command);
    }

    @Override
    public void displayPlayer2Shot(final Shot opponentShot,final Boat boat) {
    		Runnable command = new Runnable() {
			@Override
			public void run() {
		        observerPhaseController.addPlayer2Shot(opponentShot);
		        if (boat != null && boat.getSunk()){
		            observerPhaseController.sunkPlayer2Boat(boat);
		        }
		        observerPhaseController.setTurn(true);
			}
		};
		Platform.runLater(command);
    }

    @Override
    public void displayVictory() {
        gamePhaseController.showVictory();
    }

    @Override
    public void displayDefeat() {
        gamePhaseController.showDefeat();
    }

    @Override
    public void addChatMessage(ChatMessage message) {
        chatController.receiveAMessage(message);
    }

    @Override
    public void displayMyShotResult(final Shot myShotResult,final Boat boat) {
    		Runnable command = new Runnable() {
			@Override
			public void run() {
		        gamePhaseController.addShot(myShotResult);
		        if (boat != null && boat.getSunk()){
		            gamePhaseController.sunckBoat(boat);
		        }
		        gamePhaseController.setMyTurn(false);
			}
		};
		Platform.runLater(command);
    }

    @Override
    public void displayOpponentShot(final Shot opponentShot,final Boat boat) {
    		Runnable command = new Runnable() {
			@Override
			public void run() {
		        gamePhaseController.addOpponentShot(opponentShot);
		        if (boat != null){
		            gamePhaseController.sunkMyBoat(boat);
		        }
		        gamePhaseController.setMyTurn(true);
			}
		};
		Platform.runLater(command);
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

    public CDataTable getDataController() {
        return dataController;
        
    }
}

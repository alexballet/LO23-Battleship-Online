/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiTable.controllers;

import data.CDataTable;
import guiTable.GuiTableInterface;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.WindowEvent;
import structData.Boat;
import structData.ChatMessage;
import structData.Game;
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
    private ObservationPhase observationControlleur;
    private CDataTable dataController;
    private ChatController chatController;
    private PlacementPhaseController placementPhaseController;
    private final String CHAT_FXML_URL = "/fxml/IhmTable/chat.fxml";
    private final String CLASSIC_PLACEMENT_URL = "/fxml/IhmTable/ClassicPlacementPhase.fxml";
    private final String BELGE_PLACEMENT_URL = "/fxml/IhmTable/BelgianPlacementPhase.fxml";
    private final String GAME_PHASE_URL = "/fxml/IhmTable/GamePhase.fxml";
    private final String OBSERVER_PHASE_URL = "/fxml/IhmTable/ObserverPhase.fxml";
    private final String OBSERVATION_PHASE_URL = "/fxml/IhmTable/observationPhase.fxml";
    private final String TITLE = "Battleship-Online";
    private List<Boat> boats = null;

    /**
     * Private constructor for GuiTableController.
     */
    private GuiTableController() {
    }

    /**
     * Entry point for a unique instance of singleton GuiTableController;
     *
     * @return GuiTableController : the singleton GuiTableController.
     */
    public static GuiTableController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GuiTableController();
        }
        return INSTANCE;
    }

    /**
     * this function call an other fxml context and refresh page
     *
     * @param currentStage
     * @param placementTime
     * @throws Exception
     */
    @Override
    public void displayPlacementPhase(Stage currentStage, Boolean classic, int placementTime) throws Exception {
        this.mainStage = currentStage;
        this.classic = classic;

        FXMLLoader loader = new FXMLLoader();
        if (classic) {
            loader.setLocation(getClass().getResource(CLASSIC_PLACEMENT_URL));
        } else {
            loader.setLocation(getClass().getResource(BELGE_PLACEMENT_URL));
        }
        rootLayout = (AnchorPane) loader.load();
        Scene scene = new Scene(rootLayout);
        mainStage.setTitle(TITLE);
        mainStage.setScene(scene);
        mainStage.setOnCloseRequest((WindowEvent event1) -> {
            dataController.gameEnded();
            mainStage.show();
        });
        placementPhaseController = loader.getController();
        if (placementTime >= 0) {
            placementPhaseController.setPlacementTime(placementTime);
        } else {
            placementPhaseController.timerLabel.setVisible(false);
        }

        chatController = placementPhaseController.fillChatSlot(placementPhaseController.getChatPane(), CHAT_FXML_URL, ""); // string final message initial
        chatController.setDataController(dataController);
        chatController.doProfileArea();
    }

    @Override
    public void opponentReady(final Boolean myTurn, long timePerShot) {
        Runnable command = new Runnable() {
            @Override
            public void run() {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(GAME_PHASE_URL));

                try {
                    rootLayout = (AnchorPane) loader.load();
                    gamePhaseController = loader.<GamePhaseController>getController();
                    gamePhaseController.setMyTurn(myTurn);
                    gamePhaseController.setMyBoats(boats);
                    if (timePerShot >= 0) {
                        LocalTime localtime = LocalTime.MIN.plusSeconds(timePerShot);
                        gamePhaseController.timePerShot = localtime;
                        if (myTurn) {
                            gamePhaseController.setRoundTime();
                        }
                    }

                    Scene scene = new Scene(rootLayout);
                    mainStage.setScene(scene);
                    mainStage.setOnCloseRequest((WindowEvent event1) -> {
                        dataController.gameEnded();
                    });
                    mainStage.show();
                    String conv = chatController.getConversation();
                    chatController = gamePhaseController.fillChatSlot(gamePhaseController.getChatPane(), CHAT_FXML_URL, conv);
                    chatController.setDataController(dataController);
                    chatController.doProfileArea();
                } catch (IOException e) {
                    System.err.println("ERROR : " + e.getMessage());
                }
            }
        };
        Platform.runLater(command);
    }

    @Override
    public void displayObserverPhase(Stage currentStage, Game game) {
        Runnable command = new Runnable() {
            @Override
            public void run() {
                System.out.println("DÉBUT DE LA PHASE D'OBSERVATION");
                mainStage = currentStage;
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(OBSERVER_PHASE_URL));
                try {
                    rootLayout = (AnchorPane) loader.load();
                    observationControlleur = loader.getController();
                    observationControlleur.tableController = GuiTableController.getInstance();
                    observationControlleur.init();
                    Scene scene = new Scene(rootLayout);
                    mainStage.setScene(scene);
                    mainStage.show();

                    //conversation d'abord initialisée vide puis remplie
                    if (game.getSpectatorChat()) {
                        chatController = observationControlleur.fillChatSlot(gamePhaseController.getChatPane(), CHAT_FXML_URL, "");
                        chatController.setDataController(dataController);
                        chatController.createConversation(game.getListMessages());
                        chatController.doProfileArea();
                    }
                } catch (IOException e) {
                    System.err.println("ERROR : " + e.getMessage());
                }
            }
        };
        Platform.runLater(command);
    }

    @Override
    public void updateSpectatorGame(Game game) {
        Runnable command = new Runnable() {
            @Override
            public void run() {
                HashSet<Shot> listShot1 = game.getPlayer1().getListShots();
                HashSet<Shot> listShot2 = game.getPlayer2().getListShots();
                Date time1 = null, time2 = null; // get time off last shot to which player turn it is

                // display all shots
                for (Shot shot : listShot1) {
                    displayObserverShot(shot, 1);
                    time1 = shot.getTime();
                }
                for (Shot shot : listShot2) {
                    displayObserverShot(shot, 2);
                    time2 = shot.getTime();
                }

                List<Boat> listBoat1 = game.getPlayer1().getListBoats();
                List<Boat> listBoat2 = game.getPlayer1().getListBoats();
                //sunk boats
                listBoat1.forEach((boat) -> {
                    if (boat.getSunk()) {
                        observationControlleur.sunkPlayerBoat(1, boat);
                    }
                });
                listBoat2.forEach((boat) -> {
                    if (boat.getSunk()) {
                        observationControlleur.sunkPlayerBoat(2, boat);
                    }
                });
                boolean turn = true;
                if (time1 != null && time2 != null) {
                    turn = time1.after(time2);
                }
                System.out.println("turn " + turn);
                System.out.println("observerPhase " + observationControlleur);

                //set game turn
                observationControlleur.setTurn(turn);
            }
        };
        Platform.runLater(command);
    }

    /*  // à supprimer si l'autre fonctionne
    @Override
    public void displayObserverPhase(Stage currentStage, final boolean turn, LinkedHashMap<Shot,Boat> shotsDoneByPlayer1, LinkedHashMap<Shot,Boat> shotsDoneByPlayer2) {
        this.mainStage = currentStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/IhmTable/ObserverPhase.fxml"));

        try {
            rootLayout = (AnchorPane) loader.load();
            observerPhaseController = loader.<ObserverPhaseController>getController();

            Scene scene = new Scene(rootLayout);
            mainStage.setScene(scene);
            mainStage.show();
        } catch(IOException e) {
            System.err.println("ERROR : "+e.getMessage());
        }
        //Displays all the shots made before the entry of the observer
        for(Map.Entry<Shot, Boat> entry : shotsDoneByPlayer1.entrySet()){
            displayPlayer1Shot(entry.getKey(), entry.getValue());
        }
        for(Map.Entry<Shot, Boat> entry : shotsDoneByPlayer2.entrySet()){
            displayPlayer2Shot(entry.getKey(), entry.getValue());
        }
        observerPhaseController.setTurn(turn);
    }*/
 /*
    @Override
    public void displayPlayer1Shot(final Shot shot,final Boat boat) {
        //Displays a shot
        observerPhaseController.addPlayer1Shot(shot);
        //If a boat was sunk, displays the sunk boat
        if (boat != null && boat.getSunk()){
            observerPhaseController.sunkPlayer2Boat(boat);
        }
        // Changes the turn
        observerPhaseController.setTurn(false);
    }
    @Override
    public void displayPlayer2Shot(final Shot shot,final Boat boat) {
        //Displays a shot
        observerPhaseController.addPlayer2Shot(shot);
        //If a boat was sunk, displays the sunk boat
        if (boat != null && boat.getSunk()){
            observerPhaseController.sunkPlayer1Boat(boat);
        }
        // Changes the turn
        observerPhaseController.setTurn(true);
    }
    
     */
    @Override
    public void displayObserverShot(final Shot shot, int player) {
        Runnable command = new Runnable() {
            @Override
            public void run() {

                //Displays a shot
                observationControlleur.displayShot(shot, player);
                // Changes the turn
                observationControlleur.setTurn(false);
            }
        };
        Platform.runLater(command);
    }

    @Override
    public void displayObserverPhaseVictory(int winner) {
        Runnable command = new Runnable() {
            @Override
            public void run() {

                observationControlleur.showVictory(winner);
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
        if (chatController != null) {
            chatController.receiveAMessage(message);
        }
    }

    @Override
    public void displayMyShotResult(final Shot myShotResult, final Boat boat) {
        Runnable command = new Runnable() {
            @Override
            public void run() {
                gamePhaseController.addShot(myShotResult);
                if (boat != null && boat.getSunk()) {
                    gamePhaseController.sunckBoat(boat);
                }
                gamePhaseController.setMyTurn(false);
            }
        };
        Platform.runLater(command);
    }

    @Override
    public void displayOpponentShot(final Shot opponentShot, final Boat boat) {
        Runnable command = new Runnable() {
            @Override
            public void run() {
                gamePhaseController.addOpponentShot(opponentShot);
                if (boat != null && boat.getSunk()) {
                    gamePhaseController.sunkMyBoat(boat);
                }
                gamePhaseController.setMyTurn(true);
            }
        };
        Platform.runLater(command);
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

    @Override
    public void displayRageQuit() {
        if (placementPhaseController != null) {
            //  placementPhaseController.displayQuitOpponent();
        } else {
            System.out.println("Erreur inattendu, le placement phase copntroller n'a pas été initialisé");
        }
    }

    public void sunkPlayerBoat(int i, Boat boat) {
        observationControlleur.sunkPlayerBoat(i, boat);
    }
}

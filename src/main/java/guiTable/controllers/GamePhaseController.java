/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiTable.controllers;

import guiTable.CaseDrawing;
import java.net.URL;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import structData.Boat;
import structData.Position;
import structData.Shot;


/**
 *
 * @author corentinhembise
 */
public class GamePhaseController extends BaseController implements Initializable  {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private GridPane table;
    @FXML
    private GridPane myTable;
    @FXML
    private Button valider;
    @FXML
    private Button exitButton;
    @FXML
    private Button yesButton;
    @FXML
    private Button noButton;
    @FXML
    private AnchorPane chatPane;
    @FXML
    private CaseDrawing selectedCase;
    @FXML
    private Label gameState;
    @FXML
    private Pane messageContainer;
    @FXML
    private Text messageTextContainer;
    @FXML
    private Rectangle messageMask;
    
    private Boolean myTurn;
    
    GuiTableController tableController;
    

    protected final String STYLE_MY_TURN = "-fx-background-color: #FFFFFF;";
    protected final String STYLE_OTHER_TURN = "-fx-background-color: #EEEEEE;";
    protected String MY_TURN_MSG = "A votre tour de jouer, cliquer sur une case puis sur le bouton valider";
    protected String OTHER_TURN_MSG = "Au tour de l'adversaire de jouer, merci de patienter";
    protected String EXIT_GAME_MSG = "Voulez-vous vraiment quitter la partie ?";
    protected String VICTORY_MSG = "Victoire !";
    protected String DEFEAT_MSG = "Defaite !";

    private Timeline timeline;
    @FXML
    private Label timerLabel;
    private LocalTime time;

    @Override
    public void initialize(URL location, ResourceBundle resources){        
        
        selectedCase = new CaseDrawing(CaseDrawing.Type.SHOT);

        for (int i = 0 ; i < NB_CASES_GRID ; i++) {
            for (int j = 0; j < NB_CASES_GRID; j++) {
                Pane pane = new Pane();
                pane.setOnMouseClicked(onClickCase());
                table.add(pane, i, j);
            }
        }
        tableController = GuiTableController.getInstance();
        messageContainer.setVisible(false);
    }
    
    
    
    
    protected EventHandler<MouseEvent> onClickCase() {
        EventHandler<MouseEvent> mousePositionHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (myTurn) {
                    Node source = (Node)event.getSource() ;
                    Integer colIndex = GridPane.getColumnIndex(source);
                    Integer rowIndex = GridPane.getRowIndex(source);
                    if (selectedCase.getParent() == null) {
                        table.add(selectedCase, colIndex, rowIndex);
                    } else {
                        GridPane.setColumnIndex(selectedCase, colIndex);
                        GridPane.setRowIndex(selectedCase, rowIndex);
                    }

                    valider.setDisable(false);
                }
                event.consume();
            }
        };
        return mousePositionHandler;
    }
    
    @FXML
    protected void validateShot() {
        Integer col = GridPane.getColumnIndex(selectedCase);
        Integer row = GridPane.getRowIndex(selectedCase);
        Byte colB = Byte.decode(col.toString());
        Byte rowB = Byte.decode(row.toString());
        
        Position shoot = new Position(colB, rowB, false);
        GuiTableController.getInstance().validateShot(shoot);
        valider.setDisable(true);
    }

    public void setMyTurn(Boolean myTurn) {
        this.myTurn = myTurn;
        
        // Grise le plateau non actif
        if (myTurn) {
            logMsg(MY_TURN_MSG);
            table.setStyle(STYLE_MY_TURN);
            setRoundTime(time);
        } else {
            logMsg(OTHER_TURN_MSG);
            table.setStyle(STYLE_OTHER_TURN);
        }
    }

    public void addShot(Shot shot) {
        removeSelectedCase();
        placeShotTo(shot, table);
    }
    
    protected void removeSelectedCase() {
        table.getChildren().remove(selectedCase);
    }
    
    protected void placeShotTo(Shot shot, GridPane grid) {
        Integer col = shot.getX();
        Integer row = shot.getY();
        CaseDrawing.Type t;
        if(shot.getTouched()) {
            t = CaseDrawing.Type.TOUCHED;
        } else {
            t = CaseDrawing.Type.MISSED;
        }
        CaseDrawing c = new CaseDrawing(t);
        grid.add(c, col, row);
    }

    public void sunckBoat(Boat boat) {
        sunkABoat(table, boat);
    }

    public void addOpponentShot(Shot opponentShot) {
        removeSelectedCase();
        placeShotTo(opponentShot, myTable);
    }

    public void sunkMyBoat(Boat boat) {
        sunkABoat(myTable, boat);
    }
    
    protected void sunkABoat(GridPane grid, Boat boat) {
        for(Position position : boat.getListCases()) {
            CaseDrawing c = new CaseDrawing(CaseDrawing.Type.SUNK_BOAT);
            grid.add(c, position.getX(), position.getY());
        }
    }

    public void setMyBoats(List<Boat> boats) {
        if (boats == null) {
            System.err.println("Error : boats are null");
        } else {
            for(Boat boat : boats) {
                for(Position position : boat.getListCases()) {
                    CaseDrawing c = new CaseDrawing(CaseDrawing.Type.BOAT);
                    myTable.add(c, position.getX(), position.getY());
                }
            }
        }
    }
    
    
    /**
     * log message into interface.
     * @param msg message to be displayed
     */
    public void logMsg(String msg) {
        messageContainer.setVisible(true);
        noButton.setVisible(false);
        yesButton.setVisible(false);
        messageTextContainer.setText(msg);
    }
    
    /**
     * log yesNoMessage into interface.
     * @param msg message to be displayed
     */
    public void logYesNoMsg(String msg) {
        messageContainer.setVisible(true);
        noButton.setVisible(true);
        yesButton.setVisible(true);
        messageTextContainer.setText(msg);
    }
    
    /**
     * close message when click on it
     */
    @FXML
    protected void closeMsg() {
        messageContainer.setVisible(false);
    }
    
    /**
     * Validate end of game
     */
    @FXML
    protected void yesClicked() {
        if (tableController.exitGame()) {
            tableController.getDataController().gameEnded();
        } else {
            System.err.println("GuiTableController.getInstance().exitGame() renvoi false");
            // Message d'erreur
        }
    }

    public void setRoundTime(Integer roundTime) {
        if (roundTime != null) {
            LocalTime timePerShot = LocalTime.MIN.plusSeconds(roundTime);
            this.time = timePerShot ;
            // update timerLabel
            timerLabel.setText(time.toString().substring(3));
            timeline = new Timeline();
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler() {
                // KeyFrame event handler
                @Override
                public void handle(Event event) {
                    // update timerLabel
                    time = time.minusSeconds(1);
                    timerLabel.setText(time.toString().substring(3));
                    if (time.isBefore(LocalTime.MIN.plusSeconds(10))) {
                        timerLabel.setTextFill(Color.RED);
                    }
                    if (time.isBefore(LocalTime.MIN.plusSeconds(1)) ) {
                        timeline.stop();
                        timeIsOver();
                    }
                }
            }));
            timeline.playFromStart();
        }
    }

    /**
     *
     */
    protected void timeIsOver() {
        // TODO : implements
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * Cancel end of game
     */
    @FXML
    protected void noClicked() {
        messageContainer.setVisible(false);
        exitButton.setDisable(false);
        valider.setDisable(false);
        table.setDisable(false);
    }
    
    /*Clicking on exit button will ask the user if he wants to end the game*/
    @FXML
    public void exitGame() {
        valider.setDisable(true);
        table.setDisable(true);
        exitButton.setDisable(true);
        messageContainer.setVisible(true);
        logYesNoMsg(EXIT_GAME_MSG);
    }
    
    /**
    * Shows victory message
    */
    public void showVictory(){
        logMsg(VICTORY_MSG);
        valider.setDisable(true);
    }
    
    
    /**
    * Shows defeat message
    */
    public void showDefeat(){
        logMsg(DEFEAT_MSG);
        valider.setDisable(true);
        table.setDisable(true);
    }

    /**
     * @return the chatPane
     */
    public AnchorPane getChatPane() {
        return chatPane;
    }
}

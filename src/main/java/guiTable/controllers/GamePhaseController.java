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
import structData.Boat;
import structData.Position;
import structData.Shot;


/**
 * GamePhaseController
 */
public class GamePhaseController extends gameInterface implements Initializable  {

    @FXML
    private GridPane table;
    @FXML
    private GridPane myTable;
    @FXML
    private CaseDrawing selectedCase;
    @FXML
    private Label gameState;
    @FXML
    private Rectangle messageMask;
    @FXML
    private Button valider;
    @FXML
    private Label timerLabel;
    
    private Boolean myTurn;
    
  

    private Timeline timeline;
    public LocalTime time;
    public LocalTime timePerShot;

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
        if(timeline != null) {
            timeline.stop();
        }
        timerLabel.setVisible(false);
        GuiTableController.getInstance().validateShot(shoot);
        valider.setDisable(true);
    }

    @Override
    public void showVictory(){
        logMsg(VICTORY_MSG);
        if (timeline != null) timeline.stop();
        valider.setDisable(true);
    }


    @Override
    public void showDefeat(){
        logMsg(DEFEAT_MSG);
        if (timeline != null) timeline.stop();
        timerLabel.setVisible(false);
        valider.setDisable(true);
        table.setDisable(true);
    }

    /**
     *
     * @param myTurn
     */
    public void setMyTurn(Boolean myTurn) {
        this.myTurn = myTurn;
        
        // Grise le plateau non actif
        if (myTurn) {
            logMsg(MY_TURN_MSG);
            table.setStyle(STYLE_MY_TURN);
            setRoundTime();
        } else {
            logMsg(OTHER_TURN_MSG);
            table.setStyle(STYLE_OTHER_TURN);
        }
    }

    /**
     * Add a shot
     * @param shot
     */
    public void addShot(Shot shot) {
        removeSelectedCase();
        placeShotTo(shot, table);
    }

    /**
     * Remove the selected case
     */
    protected void removeSelectedCase() {
        table.getChildren().remove(selectedCase);
    }

    /**
     * Sunk a boat
     * @param boat
     */
    public void sunckBoat(Boat boat) {
        sunkABoat(table, boat);
    }

    /**
     * Add the opponent shot
     * @param opponentShot
     */
    public void addOpponentShot(Shot opponentShot) {
        removeSelectedCase();
        placeShotTo(opponentShot, myTable);
    }

    /**
     * Sunk my boat
     * @param boat
     */
    public void sunkMyBoat(Boat boat) {
        sunkABoat(myTable, boat);
    }

    /**
     * Set my boats
     * @param boats
     */
    public void setMyBoats(List<Boat> boats) {
        if (boats == null) {
            System.err.println("Error : boats are null");
        } else {
            boats.forEach((boat) -> {
                boat.getListCases().forEach((position) -> {
                    CaseDrawing c = new CaseDrawing(CaseDrawing.Type.BOAT);
                    myTable.add(c, position.getX(), position.getY());
                });
            });
        }
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
        if(waitExit) {
            waitExit = false;
            logMsg(waitMsg);
        }
    }

    /**
     * Set the round time
     */
    public void setRoundTime() {
        
        if (timePerShot != null) {
            if(timeline != null) {
                timeline.stop();
            }
            this.time = timePerShot ;
            timerLabel.setVisible(true);
            timerLabel.setTextFill(Color.BLACK);
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
     * Notify that the time is over
     */
    protected void timeIsOver() {
        showDefeat();
        tableController.getDataController().timerOver();
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
     * Get the chat pane
     * @return the chatPane
     */
    public AnchorPane getChatPane() {
        return chatPane;
    }
}

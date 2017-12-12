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
import structData.Boat;
import structData.Position;
import structData.Shot;

import static guiTable.controllers.PlacementPhaseController.MULTIPLE_FACTOR_PLACEMENT;

/**
 *
 * @author corentinhembise
 */
public class GamePhaseController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private GridPane table;
    @FXML
    private GridPane myTable;
    @FXML
    private Button valider;
    @FXML
    private AnchorPane chatPane;
    @FXML
    private CaseDrawing selectedCase;
    @FXML
    private Label gameState;
    
    private Boolean myTurn;
    
    private List<Boat> boats = null;

    public static final int GRID_ELEMENT_SIZE = 35;
    protected static final int GRID_X = 100;
    protected static final int GRID_Y = 100;
    protected static final int SPACE = 3;
    protected static final int NB_CASES_GRID = 10;
    protected static final int MULTIPLE_FACTOR_PLACEMENT = 7;

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
        
        Position shoot = new Position(colB, rowB, null);
        GuiTableController.getInstance().validateShot(shoot);
        valider.setDisable(true);
    }

    public void setMyTurn(Boolean myTurn) {
        this.myTurn = myTurn;
        
        // Grise le plateau non actif
        if (myTurn) {
            gameState.setText("A votre tour de jouer");
            table.setStyle("-fx-background-color: #FFFFFF;");
        } else {
            gameState.setText("Au tour de l'adversaire de jouer");
            table.setStyle("-fx-background-color: #EEEEEE;");
        }
    }

    public void addShot(Shot shot) {
        removeSelectedCase();
        plateShotTo(shot, table);
    }
    
    protected void removeSelectedCase() {
        table.getChildren().remove(selectedCase);
    }
    
    protected void plateShotTo(Shot shot, GridPane gird) {
        Integer col = shot.getX().intValue();
        Integer row = shot.getY().intValue();
        CaseDrawing.Type t;
        if(shot.getTouched()) {
            t = CaseDrawing.Type.TOUCHED;
        } else {
            t = CaseDrawing.Type.MISSED;
        }
        CaseDrawing c = new CaseDrawing(t);
        gird.add(c, col, row);
    }

    public void sunckBoat(Boat boat) {
        sunkABoat(table, boat);
    }

    public void addOpponentShot(Shot opponentShot) {
        removeSelectedCase();
        plateShotTo(opponentShot, myTable);
    }

    public void sunkMyBoat(Boat boat) {
        sunkABoat(myTable, boat);
    }
    
    protected void sunkABoat(GridPane gird, Boat boat) {
        for(Position position : boat.getListCases()) {
            CaseDrawing c = new CaseDrawing(CaseDrawing.Type.SUNK_BOAT);
            gird.add(c, position.getX().intValue(), position.getY().intValue());
        }
    }

    public void setMyBoats(List<Boat> boats) {
        if (boats == null) {
            System.err.println("Error : boats is null");
        } else {
            for(Boat boat : boats) {
                for(Position position : boat.getListCases()) {
                    CaseDrawing c = new CaseDrawing(CaseDrawing.Type.BOAT);
                    myTable.add(c, position.getX().intValue(), position.getY().intValue());
                }
            }
        }
    }
    
    @FXML
    public void exitGame() {
        if (GuiTableController.getInstance().exitGame()) {
            // Passer la main à ihm main
        } else {
            // Message d'erreur
        }
    }

    public void setRoundTime(Integer roundTime) {
        if (roundTime != null) {
            LocalTime timePerShot = LocalTime.MIN.plusSeconds(roundTime);
            this.time = timePerShot.plusSeconds(roundTime*MULTIPLE_FACTOR_PLACEMENT) ;
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
}

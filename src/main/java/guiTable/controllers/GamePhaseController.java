/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiTable.controllers;

import guiTable.CaseDrawing;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import structData.Boat;
import structData.Position;
import structData.Shot;

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
    private Button valider;
    @FXML
    private AnchorPane chatPane;
    @FXML
    private CaseDrawing selectedCase;
    
    private Boolean myTurn;
    
    protected static final int GRID_X = 100;
    protected static final int GRID_Y = 100;
    protected static final int SPACE = 3;
    public static final int GRID_ELEMENT_SIZE = 35;
    protected static final int NB_CASES_GRID = 10;
    
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
                
                event.consume();
            }
        };
        return mousePositionHandler;
    }
    
    @FXML
    protected void validateShoot() {
        Integer col = GridPane.getColumnIndex(selectedCase);
        Integer row = GridPane.getRowIndex(selectedCase);
        Byte colB = Byte.decode(col.toString());
        Byte rowB = Byte.decode(row.toString());
        
        Position shoot = new Position(colB, rowB, null);
        // TODO: call coordinate(Position shoot) in Data
        valider.setDisable(true);
    }

    public void setMyTurn(Boolean myTurn) {
        this.myTurn = myTurn;
        
        // Grise le plateau non actif
        if (myTurn) {
            table.setStyle("-fx-background-color: #FFFFFF;");
        } else {
            table.setStyle("-fx-background-color: #EEEEEE;");
        }
    }

    public void addShot(Shot shot) {
        Integer col = shot.getX().intValue();
        Integer row = shot.getY().intValue();
        CaseDrawing.Type t;
        if(shot.getTouched()) {
            t = CaseDrawing.Type.TOUCHED;
        } else {
            t = CaseDrawing.Type.MISSED;
        }
        CaseDrawing c = new CaseDrawing(t);
        table.add(c, col, row);
    }

    public void sunckBoat(Boat boat) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

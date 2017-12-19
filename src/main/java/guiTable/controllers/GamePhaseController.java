/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiTable.controllers;

import guiTable.CaseDrawing;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
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
    
    private List<Boat> boats = null;
    
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
        if (GuiTableController.getInstance().exitGame()) {
            // Passer la main à ihm main
        } else {
            // Message d'erreur
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
    }
    
    /*Clicking on exit button will ask the user if he wants to end the game*/
    @FXML
    public void exitGame() {
        valider.setDisable(true);
        table.setDisable(true);
        exitButton.setDisable(true);
        messageContainer.setVisible(true);
        logYesNoMsg("Voulez-vous vraiment quitter la partie ?");
    }
    
    /**
    * Shows victory message
    */
    public void showVictory(){
        logMsg("Victoire !");
        valider.setDisable(true);
    }
    
    
    /**
    * Shows defeat message
    */
    public void showDefeat(){
        logMsg("Defaite !");
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

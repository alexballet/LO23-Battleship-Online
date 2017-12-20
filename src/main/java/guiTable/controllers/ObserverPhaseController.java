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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
 * @author caiozimmerman
 */
public class ObserverPhaseController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private GridPane table1; //Board of the player 1 (Left Board)
    @FXML
    private GridPane table2; //Board of the player 2 (Right Board)
    @FXML
    private Button exitButton;
    @FXML
    private Button yesButton;
    @FXML
    private Button noButton;
    @FXML
    private AnchorPane chatPane;
    @FXML
    private Label gameState;
    @FXML
    private Pane messageContainer;
    @FXML
    private Text messageTextContainer;
    @FXML
    private Rectangle messageMask;
    
    private List<Boat> boats = null;
    
    protected static final int GRID_X = 100;
    protected static final int GRID_Y = 100;
    protected static final int SPACE = 3;
    public static final int GRID_ELEMENT_SIZE = 35;
    protected static final int NB_CASES_GRID = 10;
    
    @Override
    public void initialize(URL location, ResourceBundle resources){          
        messageContainer.setVisible(false);  
    }

    /**
     * Sets the turn of the players and and colors the boards
     * @param turn ; Boolean that defines the turn. True means player 1, False means player 2
     */
    public void setTurn(Boolean turn) {
        if (turn) {
            gameState.setText("Au tour du jouer 1");
            table1.setStyle("-fx-background-color: #EEEEEE;");
            table2.setStyle("-fx-background-color: #FFFFFF;");
        } else {
            gameState.setText("Au tour du jouer 2");
            table1.setStyle("-fx-background-color: #FFFFFF;");
            table2.setStyle("-fx-background-color: #EEEEEE;");
        }
    }
    
    /**
     * Adds the shot of the player 1 on the player 2's board
     * @param shot : The shot made by the player 1
     */
    public void addPlayer1Shot(Shot shot) {        
        plateShotTo(shot, table2);
    }
    
    /**
     * Adds the shot of the player 2 on the player 1's board
     * @param shot : The shot made by the player 2
     */
    public void addPlayer2Shot(Shot shot) {
        plateShotTo(shot, table1);
    }
    
    /**
     * Displays the shot on the selected board
     * @param shot : The shot made by one of the players
     * @param grid : The board selected
     */
    protected void plateShotTo(Shot shot, GridPane grid) {
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

    /**
     * Sunk one of the player 1's boats
     * @param boat : The boat to be sunk
     */
    public void sunkPlayer1Boat(Boat boat) {
        sunkABoat(table1, boat);
    }
    
    /**
     * Sunk one of the player 2's boats
     * @param boat : The boat to be sunk
     */
    public void sunkPlayer2Boat(Boat boat) {
        sunkABoat(table2, boat);
    }
    
    /**
     * Displays a sunk boat
     * @param grid : The grid where the boat has sunk
     * @param boat : The boat to be sunk
     */
    protected void sunkABoat(GridPane grid, Boat boat) {
        for(Position position : boat.getListCases()) {
            CaseDrawing c = new CaseDrawing(CaseDrawing.Type.SUNK_BOAT);
            grid.add(c, position.getX(), position.getY());
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
            GuiTableController controller = GuiTableController.getInstance();
            controller.getDataController().gameEnded();
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
    }
    
    /**
     * Clicking on exit button will ask the user if he wants to end the game
     */
    @FXML
    public void exitGame() {
        exitButton.setDisable(true);
        messageContainer.setVisible(true);
        logYesNoMsg("Voulez-vous vraiment quitter la partie ?");
    }
    
    /**
     * Shows victory of one of the  message
     */
    public void showVictory(boolean winner){
        if(winner){
            logMsg("Victoire du jouer 1!");
        } else {                    
            logMsg("Victoire du jouer 2!");
        }
    }      
}

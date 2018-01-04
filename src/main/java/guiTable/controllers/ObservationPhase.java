package guiTable.controllers;

import guiTable.CaseDrawing;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import structData.Boat;
import structData.Position;
import structData.Shot;

public class ObservationPhase {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button exitButton;

    @FXML
    private Label gameState;

    @FXML
    private Label timerLabel;

    @FXML
    private Pane messageContainer;

    @FXML
    private Text messageTextContainer;

    @FXML
    private Button yesButton;

    @FXML
    private Button noButton;

    @FXML
    private GridPane table1;

    @FXML
    private GridPane table2;

    @FXML
    private AnchorPane chatPane;

    @FXML
    private AnchorPane chatPane1;
    @FXML
      
    protected GuiTableController tableController;
    
    protected final String STYLE_MY_TURN = "-fx-background-color: #FFFFFF;";
    protected final String STYLE_OTHER_TURN = "-fx-background-color: #EEEEEE;";
    protected String MY_TURN_MSG = "A votre tour de jouer, cliquer sur une case puis sur le bouton valider";
    protected String OTHER_TURN_MSG = "Au tour de l'adversaire de jouer, merci de patienter";
    protected String EXIT_GAME_MSG = "Voulez-vous vraiment quitter la partie ?";
    protected String VICTORY_P1_MSG = "Victoire !";
    protected String VICTORY_P2_MSG = "Defaite !";
    
        
    
    
    public void initialize(URL location, ResourceBundle resources){          
        messageContainer.setVisible(false);  
        tablePlayer.put(1, table1);
        tablePlayer.put(2, table2);
        MY_TURN_MSG = "Au tour du joueur 1";
        OTHER_TURN_MSG = "Au tour du joueur 2";
        EXIT_GAME_MSG = "Voulez-vous vraiment quitter l'observation de la partie ?";
        VICTORY_P1_MSG = "Victoire du joueur 1 bravo !";
        VICTORY_P2_MSG = "victoire du joueur 2 !";
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
    
        /**
    * Shows victory message
    */
    public void showVictory(int player){
        if(player == 1) {
        logMsg(VICTORY_P2_MSG);
        } else {
        logMsg(VICTORY_P2_MSG);
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
    
    
    protected void sunkABoat(GridPane grid, Boat boat) {
        for(Position position : boat.getListCases()) {
            CaseDrawing c = new CaseDrawing(CaseDrawing.Type.SUNK_BOAT);
            grid.add(c, position.getX(), position.getY());
        }
    }
        
    /**
     * Validate end of game
     */
    @FXML
    void yesClicked(ActionEvent event) {
        if (tableController.exitGame()) {
            tableController.getDataController().gameEnded();
        } else {
            System.err.println("GuiTableController.getInstance().exitGame() renvoi false");
            // Message d'erreur
        }
    }
    
    private HashMap<Integer, GridPane> tablePlayer; //map to associate grid displayed and player
    

    /**
     * Sets the turn of the players and and colors the boards
     * @param turn ; Boolean that defines the turn. True means player 1, False means player 2
     */
    public void setTurn(Boolean turn) {
        if (turn) {
            gameState.setText("Au tour du joueur 1");
            table1.setStyle(STYLE_OTHER_TURN);
            table2.setStyle(STYLE_MY_TURN);
        } else {
            gameState.setText("Au tour du joueur 2");
            table1.setStyle(STYLE_MY_TURN);
            table2.setStyle(STYLE_OTHER_TURN);
        }
        
    }
    
    public void sunkPlayerBoat(int i, Boat boat) {
        sunkABoat(tablePlayer.get(i), boat);
    }

    void displayShot(Shot shot, int player) {
        this.placeShotTo(shot, tablePlayer.get(player));
    }
    
    @FXML
    void exitGame(ActionEvent event) {
        messageContainer.setVisible(true);
        logYesNoMsg(EXIT_GAME_MSG);
    }
    
    /**
     * Cancel end of game
     */
    @FXML
    void noClicked(ActionEvent event) {
        messageContainer.setVisible(false);
        exitButton.setDisable(false);
    }

}


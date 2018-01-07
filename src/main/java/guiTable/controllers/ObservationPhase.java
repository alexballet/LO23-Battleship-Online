package guiTable.controllers;

import guiTable.CaseDrawing;
import java.util.HashMap;
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

public class ObservationPhase extends BaseController{

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
    @FXML
    private Label lblj1;
    @FXML
    private Label lblj2;
    
    protected final String STYLE_MY_TURN = "-fx-background-color: #FFFFFF;";
    protected final String STYLE_OTHER_TURN = "-fx-background-color: #EEEEEE;";
    protected String MY_TURN_MSG = "A votre tour de jouer, cliquer sur une case puis sur le bouton valider";
    protected String OTHER_TURN_MSG = "Au tour de l'adversaire de jouer, merci de patienter";
    protected String EXIT_GAME_MSG = "Voulez-vous vraiment quitter la partie ?";
    protected String VICTORY_P1_MSG = "Victoire !";
    protected String VICTORY_P2_MSG = "Defaite !";
    public boolean turn = false;
    
    public HashMap<Integer, GridPane> tablePlayer; //map to associate grid displayed and player
    private boolean waitExit = false;
    private String waitMsg;
        
    
    void init() {
        messageContainer.setVisible(false);  
        tablePlayer = new HashMap<>();
        tablePlayer.put(1, table1);
        tablePlayer.put(2, table2);
        MY_TURN_MSG = "Au tour du joueur ";
        OTHER_TURN_MSG = "Au tour du joueur ";
        EXIT_GAME_MSG = "Voulez-vous vraiment quitter l'observation de la partie ?";
        VICTORY_P1_MSG = "Victoire du joueur ";
        VICTORY_P2_MSG = "victoire du joueur ";
        tableController = GuiTableController.getInstance();
    }
    /**
     * Sets ths GuiTableController
     * @param c The GuiTableController
     */
    public void setTableController(GuiTableController c) {
        tableController = c;
    }
    
    /**
     * Adds a shot on a board
     * @param shot The shot to be added
     * @param grid The board where the shot will be added
     */
    protected void placeShotTo(Shot shot, GridPane grid) {
        //Gets the shot's positions
        Integer col = shot.getX();
        Integer row = shot.getY();
        CaseDrawing.Type t;
        //Verifies if it has touched any boat
        if(shot.getTouched()) {
            t = CaseDrawing.Type.TOUCHED;
        } else {
            t = CaseDrawing.Type.MISSED;
        }
        //Adds it to the board
        CaseDrawing c = new CaseDrawing(t);
        grid.add(c, col, row);
    }
    
    /**
    * Shows victory message
    */
    public void showVictory(int player){
        if(player == 1) {
        logMsg(VICTORY_P2_MSG + getLblj2());
        } else {
        logMsg(VICTORY_P1_MSG + getLblj1());
        }
    }

    /**
     * log message into interface.
     * @param msg message to be displayed
     */
    public void logMsg(String msg) {
        if(!waitExit) {
        messageContainer.setVisible(true);
        noButton.setVisible(false);
        yesButton.setVisible(false);
        messageTextContainer.setText(msg);
        } else {
            waitExit = true;
            waitMsg = msg;
        }
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
        waitExit = true;
    }
    
    /**
     * Draws a sunked boat
     * @param grid The board where the boat sunked
     * @param boat The boat that has been sunked
     */
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
            // Error message
        }
    }
    
    
    

    /**
     * Sets the turn of the players and and colors the boards
     * @param t ; Boolean that defines the turn. True means player 1, False means player 2
     */
    public void setTurn(Boolean t) {
        if (t) {
            gameState.setText("Au tour du joueur " + getLblj2());
            table1.setStyle(STYLE_OTHER_TURN);
            table2.setStyle(STYLE_MY_TURN);
        } else {
            gameState.setText("Au tour du joueur " + getLblj1());
            table2.setStyle(STYLE_OTHER_TURN);
            table1.setStyle(STYLE_MY_TURN);
        }
        turn = t;
    }
    
    /**
     * Calls another function to draw the sunked boat according to the parameters
     * @param i Int that indicates the player
     * @param boat The boat to be sunked
     */
    public void sunkPlayerBoat(int i, Boat boat) {
        sunkABoat(tablePlayer.get(i), boat);
    }
    
    /**
     * Calls another function to draw the shot made according to the parameters
     * @param shot The shot to be added
     * @param player Int that indicates the player
     */
    void displayShot(Shot shot, int player) {
        this.placeShotTo(shot, tablePlayer.get(player));
    }
    
    /**
     * Shows exit game message
     * @param event 
     */
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
        if(waitExit) {
            waitExit = false;
            logMsg(waitMsg);
        }
    }

    public AnchorPane getChatPane() {
        return chatPane;
    }

    /**
     * @return the lblj1
     */
    public String getLblj1() {
        return lblj1.getText();
    }

    /**
     * @param lblj1 the lblj1 to set
     */
    public void setLblj1(String lblj1) {
        this.lblj1.setText(lblj1);
    }

    /**
     * @return the lblj2
     */
    public String getLblj2() {
        return lblj2.getText();
    }

    /**
     * @param lblj2 the lblj2 to set
     */
    public void setLblj2(String lblj2) {
        this.lblj2.setText(lblj2);
    }

}


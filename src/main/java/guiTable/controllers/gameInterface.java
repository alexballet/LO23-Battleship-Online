/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiTable.controllers;

import guiTable.CaseDrawing;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import structData.Boat;
import structData.Position;
import structData.Shot;

/**
 *
 * @author oiseauroch
 */
public abstract class gameInterface extends BaseController implements Initializable {
    
    @FXML
    protected Button noButton;
    @FXML
    protected Button exitButton;
    @FXML
    protected Button yesButton;
    @FXML
    protected Text messageTextContainer;
    @FXML
    protected Pane messageContainer;
    @FXML
    protected AnchorPane chatPane;
    @FXML
    private AnchorPane anchorPane;
    
    protected GuiTableController tableController;
    
    protected final String STYLE_MY_TURN = "-fx-background-color: #FFFFFF;";
    protected final String STYLE_OTHER_TURN = "-fx-background-color: #EEEEEE;";
    protected String MY_TURN_MSG = "A votre tour de jouer, cliquer sur une case puis sur le bouton valider";
    protected String OTHER_TURN_MSG = "Au tour de l'adversaire de jouer, merci de patienter";
    protected String EXIT_GAME_MSG = "Voulez-vous vraiment quitter la partie ?";
    protected String VICTORY_MSG = "Victoire !";
    protected String DEFEAT_MSG = "Defaite !";
    protected boolean waitExit = false;
    protected String waitMsg;
    
        
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
    public void showVictory(){
        logMsg(VICTORY_MSG);
    }
    
    
    /**
    * Shows defeat message
    */
    public void showDefeat(){
        logMsg(DEFEAT_MSG);
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
    


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiTable.controllers;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import structData.Boat;
import structData.Shot;

/**
 *
 * @author caiozimmerman
 */
public class ObserverPhaseController extends gameInterface implements Initializable {
    @FXML
    private GridPane table1; //Board of the player 1 (Left Board)
    @FXML
    private GridPane table2; //Board of the player 2 (Right Board)
    @FXML
    private Label gameState;
    
    private HashMap<Integer, GridPane> tablePlayer; //map to associate grid displayed and player
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources){          
        messageContainer.setVisible(false);  
        tablePlayer.put(1, table1);
        tablePlayer.put(2, table2);
        MY_TURN_MSG = "Au tour du joueur 1";
        OTHER_TURN_MSG = "Au tour du joueur 2";
        EXIT_GAME_MSG = "Voulez-vous vraiment quitter l'observation de la partie ?";
        VICTORY_MSG = "Victoire du joueur 1 bravo !";
        DEFEAT_MSG = "victoire du joueur 2 !";
    }

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
    
    public void sunkPlayerBoat(int player, Boat boat) {
        sunkABoat(tablePlayer.get(player), boat);
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

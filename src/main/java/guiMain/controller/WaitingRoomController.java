package guiMain.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import structData.Game;

public class WaitingRoomController implements Initializable{

    @FXML
    private Label gameName;

    @FXML
    private Label gameType;

    @FXML
    private Label spectatorAutorisation;

    @FXML
    private Label chatAutorisation;

    @FXML
    private Label reflectionTime;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //
    }
    
    /**
     * Initialize window elements with game data 
     * @param game 
     */
    void initData(Game game){
        // Set game name
        gameName.setText(game.getName());
        // Set time of reflection
        reflectionTime.setText(Integer.toString(game.getTimePerShot()));
        // Set game type
        if(game.getClassicType()){
            gameType.setText("Classique");
        }else{
            gameType.setText("Belgique");
        }
        // Set spectators autorisation
        if(game.getSpectator()){
            spectatorAutorisation.setText("Spectateurs autorisés");
        }else{
            spectatorAutorisation.setText("Spectateurs non autorisés");
        }
        // Set chat autorisation
        if(game.getSpectatorChat()){
            chatAutorisation.setText("chat autorisé");
        }else{
            chatAutorisation.setText("chat non autorisé");
        }       
    }
    
    

}
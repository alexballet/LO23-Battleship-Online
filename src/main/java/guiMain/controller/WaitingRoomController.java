package guiMain.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
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
    
    // object to close window
    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // not implemented
    }
    
    /**
     * Initialize window elements with game data 
     * @param game 
     */
    public void initData(Game game){
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
    
    /**
     * Close waiting room window
     */
    public void closeWindow() {
        this.stage.close();
    }

    /**
     * Set stage
     * @param s 
     */
    public void setStage(Stage s) {
        this.stage = s;
        System.out.println("stage : " + this.stage);
    }
    
    

}
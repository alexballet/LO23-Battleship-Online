package guiMain.controller;

import data.DataController;
import guiMain.GuiMainController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import structData.Game;

public class CreateGameController implements Initializable{
    @FXML
    private TextField gameName;

    @FXML
    private RadioButton classicGameType;

    @FXML
    private ToggleGroup gameType;

    @FXML
    private RadioButton belgiumGameType;

    @FXML
    private RadioButton humanGameAdversaire;

    @FXML
    private ToggleGroup gameAdversaire;

    @FXML
    private RadioButton robotGameAdversaire;

    @FXML
    private CheckBox spectatorsAutorise;

    @FXML
    private CheckBox chatAutorise;

    @FXML
    private TextField reflectionTime;

    @FXML
    private Label errorMessage;

    @FXML
    private Button createGame;
    
    @FXML
    private Button returnButton;
    
    private GuiMainController mainController;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Force the reflectionTime field to be numeric (integer) only
        reflectionTime.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, 
                String newValue) {
                if (!newValue.matches("\\d*")) {
                    reflectionTime.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }    
    
    /**
     * Get form data and calls IDataMain to create new game 
     * @param event : button #creategame event click
     * @throws IOException 
     */
    @FXML
    void newGame(ActionEvent event) throws IOException {
        // Get values from form fields
        String name = gameName.getText();
        Boolean spectators = spectatorsAutorise.isSelected();
        Boolean chat = chatAutorise.isSelected();
        Boolean classicGame = classicGameType.isSelected();
        Boolean oponent = humanGameAdversaire.isSelected();          
        int timePerShot = Integer.parseInt(reflectionTime.getText());

        // Create new game object
        // Game game = new Game(classicGame, name, oponent, timePerShot, spectators, chat);
                
        // Calls interface with data to create new game
        Game game = mainController.getIdata().newGame(classicGame, name, oponent, timePerShot, spectators, chat);   
        
        // Open waiting room window 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Ihm-main/waitingRoom.fxml"));
        Parent layout = loader.load();
        Scene scene = new Scene(layout);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        // Set game data in new window
        WaitingRoomController controller = loader.<WaitingRoomController>getController();
      //  controller.initData(game); *********TMP : SUITE AU CHANGEMENT DU CONSTRUCTEUR
        // Show window
        window.show();
    }
    
    /**
     * Change window to menu window 
     * @param event : #returnButton event click
     */
    @FXML
    void returnToMenu(ActionEvent event) {
        mainController.openMenuWindow();
    }
}

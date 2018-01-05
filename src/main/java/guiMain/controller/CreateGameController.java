package guiMain.controller;

import guiMain.GuiMainController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
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
	private CheckBox spectatorsAutorise;

	@FXML
	private CheckBox chatAutorise;
	
	@FXML
	private CheckBox timeDisabled;

	@FXML
	private TextField positioningTime;
	
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
		 int timePerShot = -1;
		 int timeForPositioning= -1;
		 
		 //add try catch exception for default value time per shot
		 if (!timeDisabled.isSelected()){
			 try {
				 timePerShot = Integer.parseInt(reflectionTime.getText());
				 timeForPositioning = Integer.parseInt(positioningTime.getText());
			 } catch (NumberFormatException e) {
				 System.out.println("le champ temps est vide initialisation à 30s");
				 timePerShot = 30;
				 timeForPositioning = 30; 
			 } 
		 }

		 if (!name.trim().isEmpty()) {
			 // Calls interface with data to create new game
			 Game game = mainController.getIdata().newGame(classicGame, name, oponent, timePerShot, timeForPositioning, spectators, chat);   
			 // Open waiting room window 
			 mainController.openWaitingRoomWindow(game);
		 } else {
			 errorMessage.setText("Veuillez choisir un nom pour votre partie.");
			 errorMessage.setTextFill(Color.web("#ff0000"));
		 }
	 }

	 /**
	  * Change window to menu window 
	  * @param event : #returnButton event click
	  */
	 @FXML
	 void returnToMenu(ActionEvent event) {
		 mainController.openMenuWindow();
	 }
	 
	 @FXML
	 void timerDisable(ActionEvent event){
		 boolean isSelected = timeDisabled.isSelected();
		 positioningTime.setDisable(isSelected);
		 reflectionTime.setDisable(isSelected);
	 }

	 public void setMainController (GuiMainController c) {
		 mainController = c;
	 }
}

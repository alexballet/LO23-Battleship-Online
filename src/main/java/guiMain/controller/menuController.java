package guiMain.controller;

import java.util.ArrayList;
import java.util.List;

import guiMain.GameCell;
import guiMain.GuiMainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import structData.*;

public class menuController {
	
	private GuiMainController mainController;

	@FXML 
	private ListView<User> playersView;
	@FXML 
	private ListView<Game> gamesView;
	@FXML 
	private Button optionButton;
	@FXML
	private Button disconnectionButton;
	
	public void setMainController (GuiMainController c) {
		mainController = c;
	}
	
	/**
	 *  	Init listView configuration
	 *  	UserTest à remplacer par User lorsque les getter/setter seront dispo
	 */
	public void init() {
		//randomListUser();
		this.initUserList();
		this.initGamesList();
	}
	
	/**
	 * Initialise the user list in the main GUI
	 */
	private void initUserList() {
		ObservableList<User> playersObservable = FXCollections.observableList(new ArrayList<User>());
		playersView.setItems(playersObservable);

		playersView.setCellFactory(new Callback<ListView<User>, ListCell<User>>(){

			@Override
			public ListCell<User> call(ListView<User> p) {

				ListCell<User> cell = new ListCell<User>(){

					@Override
					protected void updateItem(User t, boolean bln) {
						super.updateItem(t, bln);
						if (t != null) {
							setText(t.getlogin() + "  :  " +  t.getusername());
						}
					}

				};
				return cell;
			}
		});
	}
	
	private void initGamesList() {
		ObservableList<Game> playersObservable = FXCollections.observableArrayList(new ArrayList<Game>());
		gamesView.setItems(playersObservable);
		gamesView.setCellFactory(new Callback<ListView<Game>, ListCell<Game>>() { 
			  
		    @Override 
		    public ListCell<Game> call(ListView<Game> lv) { 
		        return new GameCell(); 
		    } 
		});
	}
	
	public void joinGame(Game game) {
		
	}
	

	/**
	 *  	Access to option windows
	 *  	Actuellement, dans le cadre d'un test, permet de tester l'ajout d'utilisateurs
	 */
	@FXML
	private void option(){
		gamesView.getItems().add(new Game(true, "Game test 1", false, 100, true, true));
		gamesView.getItems().add(new Game(false, "Game test 2", false, 100, true, true));
		gamesView.getItems().add(new Game(true, "Game test 3", true, 100, true, true));
		gamesView.getItems().add(new Game(false, "Game test 4", true, 100, true, true));
	}


	@FXML
	private void disconnection(){
		mainController.getIdata().askDisconnection();
	}

	
	/** 
	 * Adds the user passed as a parameter to the list of users.
	 * @param user : user to add to the list.
	 * 
	 *  A décommenter pour l'intégration
	 */
	/*
	public void addUser(User user){
		playersView.getItems().add(user);
	}
	 */



}

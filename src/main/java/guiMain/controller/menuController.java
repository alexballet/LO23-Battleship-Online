package guiMain.controller;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import structData.*;

public class menuController {

	@FXML 
	private ListView<User> playersView;
	@FXML 
	private ListView<Game> gamesView;
	

	@FXML 
	private Button optionButton;
	
	
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
		ObservableList<Game> playersObservable = FXCollections.observableList(new ArrayList<Game>());
		gamesView.setItems(playersObservable);

		gamesView.setCellFactory(new Callback<ListView<Game>, ListCell<Game>>(){
			@Override
			public ListCell<Game> call(ListView<Game> p) {

				ListCell<Game> cell = new ListCell<Game>(){

					@Override
					protected void updateItem(Game game, boolean bln) {
						super.updateItem(game, bln);
						if (game != null) {
							setText(game.getName());
						}
					}

				};
				return cell;
			}
		});
	}
	

	/**
	 *  	Access to option windows
	 *  	Actuellement, dans le cadre d'un test, permet de tester l'ajout d'utilisateurs
	 */
	@FXML
	private void option(){
		// playersView.getItems().add(new User());
		gamesView.getItems().add(new Game());
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

package guiMain.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.util.Callback;
import lo23.battleship.MainApp;
import packageStructDonnées.User;

public class menuController {


	private MainApp mainApp;


	List<UserTest> myList;

	ObservableList<UserTest> players;

	@FXML 
	private ListView<UserTest> playersView;

	@FXML 
	private Button optionButton;


	/**
	 *  	Init listView configuration
	 *  	UserTest à remplacer par User lorsque les getter/setter seront dispo
	 */


	public void init() {
		randomListUser();
		players = FXCollections.observableList(myList);
		playersView.setItems(players);

		playersView.setCellFactory(new Callback<ListView<UserTest>, ListCell<UserTest>>(){

			@Override
			public ListCell<UserTest> call(ListView<UserTest> p) {

				ListCell<UserTest> cell = new ListCell<UserTest>(){

					@Override
					protected void updateItem(UserTest t, boolean bln) {
						super.updateItem(t, bln);
						if (t != null) {
							setText(t.login + "  :  " +  t.username);
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
		playersView.getItems().add(new UserTest());
 
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





	/**
	 *  Classe et fonctions de test en attendant que Data confirme la création de getter/setter dans leurs classes.
	 */


	public class UserTest {
		public UUID idUser;
		public String login;
		public String username;
		public HashSet IPs;

		public UserTest(){
			idUser = UUID.randomUUID();
			login = "A";
			username = "B";
			IPs = new HashSet();
		}
	}

	private void randomListUser(){
		myList = new ArrayList<>();
		myList.add(new UserTest());
		myList.add(new UserTest());
		myList.add(new UserTest());
		myList.add(new UserTest());
	}




}

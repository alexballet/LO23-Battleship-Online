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
import structData.User;

public class menuController {


	List<User> playersList;

	ObservableList<User> playersObservable;

	@FXML 
	private ListView<User> playersView;

	@FXML 
	private Button optionButton;


	/**
	 *  	Init listView configuration
	 *  	UserTest à remplacer par User lorsque les getter/setter seront dispo
	 */


	public void init() {
		//randomListUser();
		playersList = new ArrayList<>();
		playersObservable = FXCollections.observableList(playersList);
		playersView.setItems(playersObservable);

		playersView.setCellFactory(new Callback<ListView<User>, ListCell<User>>(){

			@Override
			public ListCell<User> call(ListView<User> p) {

				ListCell<User> cell = new ListCell<User>(){

					@Override
					protected void updateItem(User t, boolean bln) {
						super.updateItem(t, bln);
						if (t != null) {
							setText(t.getLogin() + "  :  " +  t.getUsername());
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
		playersView.getItems().add(new User());
 
	}



	/** 
	 * Adds the user passed as a parameter to the list of users.
	 * @param user : user to add to the list.
	 * 
	 *  A décommenter pour l'intégration
	 */

	
	public void addUser(User user){
		playersView.getItems().add(user);
	}
	



}

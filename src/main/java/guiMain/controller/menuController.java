package guiMain.controller;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import java.net.UnknownHostException;
import guiMain.GameCell;
import guiMain.GuiMainController;
import guiMain.PlayerCell;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import structData.*;

/**
*
* This class call the lobby view and enable interaction
* between button and method.
*
* @author IHM-Main module
*/
public class menuController implements Initializable{

	private GuiMainController mainController;

	@FXML 
	private ImageView avatarImage;
	@FXML 
	private ListView<User> playersView;
	@FXML 
	private ListView<Game> gamesView;
	@FXML 
	private Button optionButton;
	@FXML 
	private Button modifyProfileButton;
	@FXML 
	private Button refreshButton;
	@FXML
	private Label warningLabel;

	
	 /**
     * Set GuiMainController mainController  
     * @param c : GuiMainController
     */
	public void setMainController (GuiMainController c) {
		mainController = c;
	}

	/**
	 *  Init listView configuration
	 */
	public void init() {
		this.initUserList();
		this.initGamesList();
		
		if (mainController.getIdata().getLocalProfile().getAvatar() != null &&
				mainController.getIdata().getLocalProfile().getAvatar().getImage() != null) this.setImage();
	}

	/**
	 * Initialise the user list in the main GUI
	 */
	private void initUserList() {
		final menuController controller = this;
		List<User> users = mainController.getIdata().getListUsers();
		ObservableList<User> playersObservable = FXCollections.observableArrayList(users);
		playersView.setItems(playersObservable);
		playersView.setCellFactory(new Callback<ListView<User>, ListCell<User>>() { 

			@Override 
			public ListCell<User> call(ListView<User> lv) { 
				return new PlayerCell(controller); 
			} 
		});
	}
	
	/** 
	 * Init the game list and attach it to an observable list.
	 */
	private void initGamesList() {
		final menuController controller = this;

		List<Game> games = mainController.getIdata().getGames();
		Game game = null;
		Profile local = mainController.getIdata().getLocalProfile();
		for(int i=0; i < games.size(); i++) {
			game = games.get(i);
			if (game.doesProfileBelongToGame(local)) games.remove(i);
			else i++;
		}
		
		ObservableList<Game> gamesObservable = FXCollections.observableArrayList(games);
		gamesView.setItems(gamesObservable);
		gamesView.setCellFactory(new Callback<ListView<Game>, ListCell<Game>>() { 
			@Override 
			public ListCell<Game> call(ListView<Game> lv) { 
				return new GameCell(controller); 
			} 
		});
	}
	
	/** 
	 * Display the user's avatar on the view. 
	 */
	private void setImage(){
		ImageIcon icon = mainController.getIdata().getLocalProfile().getAvatar();
		BufferedImage bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.createGraphics();
		icon.paintIcon(null, g, 0,0);
		g.dispose();
		avatarImage.setImage(SwingFXUtils.toFXImage(bi, null));
	}

	/** 
	 * Access game as player.
	 * @param game : game to access.
	 */
	public void joinGame(Game game) {
		mainController.askJoinGame(game);
	}

	/** 
	 * Access game as spectator.
	 * @param game : game to access.
	 */
	public void lookGame(Game game) {
		mainController.lookGame(game);
	}

	/** 
	 * Display profile of others users
	 * @param user : user's profil to display.
	 */
	public void lookUser(User user) {
		mainController.openProfileWindow(user);
	}


	/**
	 *  	Access to option windows
	 */
	@FXML
	private void option(){
		mainController.openConfigWindow();
	}
	

	/** 
	 * Send disconnection message to other users and display the login view.
	 */
	@FXML
	private void disconnection(){
		mainController.getIdata().askDisconnection();
		mainController.startIHM();
	}

	/** 
	 * Explicit ask to receive informations again.
	 */
	@FXML
	private void refresh(){
		String login = mainController.getIdata().getLocalProfile().getLogin();
		String password = mainController.getIdata().getLocalProfile().getPassword();
		mainController.getIdata().clear();
		try {
			mainController.getIdata().connection(login, password);
		} catch (UnknownHostException e) {
			warningLabel.setText("Raffraichissement impossible");
			warningLabel.setTextFill(Color.web("#ff0000"));
		}
		this.initUserList();
		this.initGamesList();
	}

	/**
	 * Display changeProfile window
	 * @param event : #modifyProfileButton event
	 */
	@FXML
	void openChangeProfileWindow(ActionEvent event) {
		User user = mainController.getIdata().getLocalProfile();
		mainController.openChangeProfileWindow(user);
	}


	/** 
	 * Adds the user passed as a parameter to the list of users.
	 * @param user : user to add to the list.
	 */
	public void addUser(User user){
		boolean isOk = true;
		ObservableList<User> users = playersView.getItems();
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getIdUser().equals(user.getIdUser())) {
				isOk = false;
			}
		}
		if (isOk) {
			playersView.getItems().add(user);
		}
		playersView.refresh();
	}

	/** 
	 * Remove the user passed as a parameter to the list of users.
	 * @param user : user to remove to the list.
	 */
	public void removeUser(User user){
		playersView.getItems().remove(user);
		playersView.refresh();
	}

	/** 
	 * Add the game passed as a parameter to the list of games.
	 * @param game : game to add to the list.
	 */
	public void addGame(Game game){
		if (!game.doesProfileBelongToGame(mainController.getIdata().getLocalProfile()) && !game.getStatus().equals(StatusGame.FINISHED)) {
			Boolean isOk = true;
			ObservableList<Game> games = gamesView.getItems();
			for (int i = 0; i < games.size(); i++) {
				if (games.get(i).getIdGame().equals(game.getIdGame())) {
					isOk = false;
				}
			}
			if (isOk) {
				gamesView.getItems().add(game);
			}
			gamesView.refresh();
		}

	}


	/**
	 * Open window to create new game 
	 * @param event : button #createGame event click
	 * @throws IOException 
	 */
	@FXML
	private void openCreateGameWindow(ActionEvent event) throws IOException {
		mainController.openCreateGameWindow();
	}

	/** 
	 * Update the game passed as a parameter in the list of games.
	 * @param game : user to game in the list.
	 */
	
	public void updateGameStatus(Game game) {
		ObservableList<Game> list =  gamesView.getItems();
		for (int i = 0; i < list.size(); i++){
			Game g = list.get(i);
			if (game.getIdGame().equals(g.getIdGame())) {
				gamesView.getItems().remove(g);
				gamesView.getItems().add(i, game);
				break;
			}
		}
		gamesView.refresh();
	}

	/** 
	 * Remove the game passed as a parameter to the list of games.
	 * @param game : game to remove to the list.
	 */
	
	public void removeGame(Game removedGame) {
		ObservableList<Game> list =  gamesView.getItems();
		Game toBeRemoved = null;
		for (Game g : list){
			if (removedGame.getIdGame().equals(g.getIdGame())) {
				toBeRemoved = g;
				break;
			}
		}
		if(toBeRemoved != null) {
			gamesView.getItems().remove(toBeRemoved);
		}
		gamesView.refresh();
	}

	/** 
	 * Unused but necessary method.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}

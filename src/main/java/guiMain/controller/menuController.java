package guiMain.controller;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

import structData.*;


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

	public void setMainController (GuiMainController c) {
		mainController = c;
	}

	/**
	 *  Init listView configuration
	 *  UserTest à remplacer par User lorsque les getter/setter seront dispo
	 */
	public void init() {
		//randomListUser();
		this.initUserList();
		this.initGamesList();
		
		if (mainController.getIdata().getLocalProfile().getAvatar() != null &&
				mainController.getIdata().getLocalProfile().getAvatar().getImage() != null) this.setImage();
	}

	/**
	 * Initialise the user list in the main GUI
	 */
	private void initUserList() {
		/*
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
                                setText(t.getLogin() + "  :  " +  t.getUsername());
                            }
                        }
                    };
                    return cell;
                }
            });
		 */
		
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
	
	private void setImage(){
		ImageIcon icon = mainController.getIdata().getLocalProfile().getAvatar();
		BufferedImage bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.createGraphics();
		icon.paintIcon(null, g, 0,0);
		g.dispose();
		avatarImage.setImage(SwingFXUtils.toFXImage(bi, null));
	}

	public void joinGame(Game game) {
		mainController.askJoinGame(game);
	}

	public void lookGame(Game game) {
		System.out.println("LOOK GAME " + game.getName());
	}

	public void lookUser(User user) {
		mainController.openProfileWindow(user);
	}


	/**
	 *  	Access to option windows
	 *  	Actuellement, dans le cadre d'un test, permet de tester l'ajout d'utilisateurs
	 */
	@FXML
	private void option(){
		mainController.openConfigWindow();
	}

	@FXML
	private void displayProfil(User user) {
		// Display profile of others users
		mainController.openProfileWindow(user);
	}


	@FXML
	private void disconnection(){
		mainController.getIdata().askDisconnection();
		mainController.startIHM();
	}


	/**
	 * Use the createGame Button as a way to start the displayPlacementPhase method from a guiTableController. 
	 * To be removed for integration.
	 */        
	@FXML
	private void createGame(){

		/*   try{
                GuiTableController.getInstance().displayPlacementPhase( this.currentStage, false ); // use boolean to specifie classic type or not
            }
            catch(Exception e){
                System.err.println(e.getMessage());
            }*/

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
		Boolean isOk = true;
		ObservableList<User> users = playersView.getItems();
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getIdUser().equals(user.getIdUser())) {
				isOk = false;
			}
		}
		if (isOk) {
			playersView.getItems().add(user);
		}
	}

	/** 
	 * Remove the user passed as a parameter to the list of users.
	 * @param user : user to remove to the list.
	 */
	public void removeUser(User user){
		playersView.getItems().remove(user);
	}


	public void addGame(Game game){
		if (!game.doesProfileBelongToGame(mainController.getIdata().getLocalProfile())) {
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//
	}

	public void updateGameStatus(Game game) {
		ObservableList<Game> list =  gamesView.getItems();
		int i = 0;
		for (Game g : list){
			if (game.getIdGame().equals(g.getIdGame())) {
				gamesView.getItems().set(i, game);
			}
			i++;
		}
	}

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
	}
}

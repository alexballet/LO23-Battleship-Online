package guiMain.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import guiMain.GameCell;
import guiMain.GuiMainController;
import java.util.ResourceBundle;
import interfacesData.IDataMain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;
import structData.*;


public class menuController implements Initializable{

    private GuiMainController mainController;
        
    @FXML 
    private ListView<User> playersView;
    @FXML 
    private ListView<Game> gamesView;
    @FXML 
    private Button optionButton;

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
                                setText(t.getLogin() + "  :  " +  t.getUsername());
                            }
                        }
                    };
                    return cell;
                }
            });
	}
	
	private void initGamesList() {
		final menuController controller = this;
		ObservableList<Game> playersObservable = FXCollections.observableArrayList(new ArrayList<Game>());
		gamesView.setItems(playersObservable);
		gamesView.setCellFactory(new Callback<ListView<Game>, ListCell<Game>>() { 
			  
		    @Override 
		    public ListCell<Game> call(ListView<Game> lv) { 
		        return new GameCell(controller); 
		    } 
		});
	}
	
	public void joinGame(Game game) {
		System.out.println("JOIN GAME " + game.getName());
		mainController.askJoinGame(game);
	}
	
	public void lookGame(Game game) {
		System.out.println("LOOK GAME " + game.getName());
	}
	

	/**
	 *  	Access to option windows
	 *  	Actuellement, dans le cadre d'un test, permet de tester l'ajout d'utilisateurs
	 */
	@FXML
	private void option(){
		// gamesView.getItems().add(new Game(true, "Game test 1", false, 100, true, true));
	}
	
	@FXML
    private void displayProfil() {
        // Display profile of others users
        mainController.displayProfil();
    }

	@FXML
	private void disconnection(){
		mainController.getIdata().askDisconnection();
	}
    @FXML
    void openChangeProfileWindow(ActionEvent event) {
        // Display changeProfile window  
        mainController.changeProfile();
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
	

        /**
         * Open window to create new game 
         * @param event : button #createGame event click
         * @throws IOException 
         */
        @FXML
        private void openCreateGameWindow(ActionEvent event) throws IOException {
            Parent layout = FXMLLoader.load(getClass().getResource("/fxml/Ihm-main/createGame.fxml"));
            Scene scene = new Scene(layout);
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //
    }
}

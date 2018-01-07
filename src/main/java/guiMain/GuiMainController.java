package guiMain;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import guiMain.controller.ChangeProfileController;

import guiMain.controller.CreateGameController;
import guiMain.controller.IpConfigController;
import guiMain.controller.LoginController;
import guiMain.controller.ProfilController;
import guiMain.controller.SignupController;
import guiMain.controller.WaitingRoomController;
import guiMain.controller.menuController;
import guiTable.controllers.GuiTableController;
import interfacesData.IDataMain;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import structData.Game;
import structData.Profile;
import structData.User;


/**
*
* This class implements the network controller. It contains references
* to all Main-Gui controllers and interactions with data module.
*
* @author IHM-Main module
*/
public class GuiMainController implements GuiMainInterface {

	List<User> playersList;
	List<Game> gamesList;
	List<String> ipsList = new ArrayList<String>();
	int port = Profile.DEFAULT_PORT;

	private Stage stage;
	private AnchorPane rootLayout;
	private IDataMain idata;
	private menuController menuController;
	private SignupController signUpController;
	private IpConfigController ipConfigController;
	private LoginController loginController;
	private ProfilController profilController;
	private ChangeProfileController changeProfileController;
	private CreateGameController createGameController;
	private WaitingRoomController waitingRoomController;

	
	/** 
	 * Get the interface with data module..
	 * @return interface with data.
	 */
	public IDataMain getIdata() {
		return idata;
	}
	
	/** 
	 * Add the user passed as a parameter to the list of users.
	 * @param user : user to add to the list.
	 */
	@Override
	public void addUser(final User user) {

		Runnable command = new Runnable() {
			@Override
			public void run() {
				menuController.addUser(user);
			}
		};
		Platform.runLater(command);
	}
	
	/** 
	 * Remove the user passed as a parameter to the list of users.
	 * @param user : user to remove to the list.
	 */
	@Override
	public void removeUser(final User user) {
		Runnable command = new Runnable() {
			@Override
			public void run() {
				menuController.removeUser(user);
			}
		};
		Platform.runLater(command);
	}

	/** 
	 * Add the game passed as a parameter to the list of games.
	 * @param createdGame : game to add to the list.
	 */
	@Override
	public void addGame(final Game createdGame) {
		if (createdGame.doesProfileBelongToGame(idata.getLocalProfile())) return;

		if (createdGame != null) {
			Runnable command = new Runnable() {
				@Override
				public void run() {
					menuController.addGame(createdGame);
				}
			};
			Platform.runLater(command);
		}
	}

	/** 
	 * Remove the game passed as a parameter to the list of games.
	 * @param removedGame : game to remove to the list.
	 */
        @Override
	public void removeGame(final Game removedGame) {
		if (removedGame != null) {
			Runnable command = new Runnable() {
				@Override
				public void run() {
					menuController.removeGame(removedGame);
				}
			};
			Platform.runLater(command);
		}
	}
        
        /** 
    	 * Get statics from an other player..
    	 * @param profil : user to show statistics.
    	 */
	@Override
	public void sendStatistics(Profile profil) {
		if (profilController != null) {
			Runnable command = new Runnable() {
				@Override
				public void run() {
					profilController.setProfil(profil);
				}
			};
			Platform.runLater(command);
			
		}

	}

	/** 
	 * Change the game's status (joinable or not, spectator allowed or not)
	 * @param game : game to update in the list.
	 */
	@Override
	public void transmitNewStatus(final Game game) {
		Runnable command = new Runnable() {
			@Override
			public void run() {
				menuController.updateGameStatus(game);
			}
		};
		Platform.runLater(command);
	}

	@Override
	public void setGameJoinResponse(boolean isOk) {
	}

	/**
     * Method called at the launch of the mainapp to init the gui.  
     */
	public void startIHM(){

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/Ihm-main/login.fxml"));
		try {
			rootLayout = (AnchorPane) loader.load();

			loginController = loader.getController();
			loginController.setMainController(this);

			Scene scene = new Scene(rootLayout);
			stage.setTitle("Battleship-Online");
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
     * Access to the lobby view.  
     */
        @Override
	public void openMenuWindow(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/Ihm-main/menu.fxml"));
		try {
			rootLayout = (AnchorPane) loader.load();

			menuController = loader.getController();

			menuController.setMainController(this);
			menuController.init();

			Scene scene = new Scene(rootLayout);
			stage.setTitle("Battleship-Online");
			stage.setOnCloseRequest((WindowEvent event1) -> {
	            	idata.askDisconnection();
	        });

			stage.setScene(scene);
			stage.show();
			
			System.out.println("start connection");
			System.out.println("Connection etablished");


		} catch (IOException e) {
			e.printStackTrace();
		}
	}
        
        /**
         * Access to the create game view.  
         */
	public void openCreateGameWindow(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/Ihm-main/createGame.fxml"));
		try {
			rootLayout = (AnchorPane) loader.load();

			createGameController = loader.getController();
			createGameController.setMainController(this);

			Scene scene = new Scene(rootLayout);
			stage.setTitle("Battleship-Online");
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * Access to the sign up view.  
     */
	public void openSignupWindow(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/Ihm-main/signup.fxml"));
		try {
			rootLayout = (AnchorPane) loader.load();

			signUpController = loader.getController();
			signUpController.setMainController(this);
			signUpController.init();

			Scene scene = new Scene(rootLayout);
			stage.setTitle("Battleship-Online");
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
     * Access to the player configuration view. 
     */
	public void openConfigWindow(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/fxml/Ihm-main/ip_config.fxml"));
			Parent root = (Parent) loader.load();

			ipConfigController = loader.getController();
			ipConfigController.setMainController(this);
			ipConfigController.init();

			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Configurations des IPs");
			stage.setScene(new Scene(root));
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * Access to the change profile view.  
     * @param user : local current user
     */
    @Override
    public void openPlacementPhase(final Game game) {
            try {
                Runnable command = new Runnable() {
			@Override
			public void run() {
                            try {
                                if (waitingRoomController != null) waitingRoomController.closeWindow();
                                System.out.println("timeplaceboat : " + game.getTimeToPlaceBoats());
                                GuiTableController.getInstance().displayPlacementPhase( stage, game.getClassicType(), game.getTimeToPlaceBoats()); 
                                
                            } catch (Exception ex) {
                                Logger.getLogger(GuiMainController.class.getName()).log(Level.SEVERE, null, ex);
                            }
			}
		};
		Platform.runLater(command);
                

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
    
    /**
     * Open the waiting room view.  
     * @param game : the new game created
     */
	public void openWaitingRoomWindow(Game game){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/fxml/Ihm-main/waitingRoom.fxml"));
			Parent root = (Parent) loader.load();

			waitingRoomController = loader.getController();
			waitingRoomController.initData(game);

			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Salle d'attente");
			stage.setScene(new Scene(root));
			waitingRoomController.setStage(stage);
			stage.show();

			stage.setOnCloseRequest((WindowEvent event1) -> {
				idata.removeGame(game);
			});

		} catch(Exception e) {
			e.printStackTrace();
		}
	} 
	  /**
     * Get the current user's ip list
     * @return current user's ip list 
     */
	
	public List<String> getIps(){
		Profile p = idata.getLocalProfile();
		if (p != null && p.getIdUser() != null) {
			ipsList = new ArrayList<>();
			HashSet<InetAddress> ips = p.getIPs();
			for (InetAddress ip : ips) {
				this.ipsList.add(ip.getHostAddress());
			}
		}
		
		return this.ipsList;
	}

	  /**
     * Set the current user's ip list
     * @param list : ip list to upddate
     */
	public void setIps(List<String> list){
		Profile p = idata.getLocalProfile();
		
		if ( p != null && p.getIdUser() != null) {
			HashSet<InetAddress> ips = new HashSet<>();
			if (list != null && list.size() > 0){
				for (String ip : list) {
					try {
						ips.add(InetAddress.getByName(ip));
					} catch (UnknownHostException e) {
						e.printStackTrace();
					}
				}
			}
			idata.setListIps(ips);
		}
		
		this.ipsList = list;
	}
	
	
	  /**
     * Set an to the interface of data module
     * @param idata : interface of data module
     */
	public void setIdata(IDataMain idata) {
		this.idata = idata;
	}

	  /**
     * Init the controller with the main stage  
     * @param s : current stage
     */
	public GuiMainController(Stage s) {
		super();
		this.stage = s;
	}
	
	 /**
	    * Ask information about the game join ans notify others players.  
	    * @param game : game joined
	    */
	public void askJoinGame(final Game game) {
		Runnable command = new Runnable() {

			@Override
			public void run() {
				idata.notifGameChosen(game);

			}
		};
		Platform.runLater(command);
	}

	 /**
    * Access to the profile view.  
    * @param user : windows display this user profile 
    */
	public void openProfileWindow(User user){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/fxml/Ihm-main/profil.fxml"));
			Parent root = (Parent) loader.load();

			profilController = loader.getController();
			profilController.setMainController(this);
			profilController.init(user);

			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Profil");
			stage.setScene(new Scene(root));  
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	 /**
     * Access to the change profile view.  
     * @param user : local current user
     */
	public void openChangeProfileWindow(User user){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/fxml/Ihm-main/changeProfile.fxml"));
			Parent root = (Parent) loader.load();

			changeProfileController = loader.getController();
			changeProfileController.setMainController(this);
			changeProfileController.init(user);

			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Modification de profil");
			stage.setScene(new Scene(root));  
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	 /**
     * Set the local user port
     * @param num_port : new value of the port
     */
	public void setPort(int num_port) {
		Profile p = idata.getLocalProfile();
		
		if ( p != null && p.getIdUser() != null) {
			idata.setPort(num_port);
		}
		
		this.port = num_port;
	}
	
	 /**
     * Get the local user port
     * @return value of the local user port
     */
	public int getPort() {
		Profile p = idata.getLocalProfile();
		if (p != null && p.getIdUser() != null) {
			this.port = p.getPort();
		}
		
		return this.port;
	}

	 /**
     * Ask of informations about the game  
     * @param game : game asked for viewing
     */
	public void lookGame(Game game) {
		idata.gameToSpec(game);
		GuiTableController.getInstance().displayObserverPhase(stage, game);
	}
}






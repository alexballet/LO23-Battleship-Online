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

public class GuiMainController implements GuiMainInterface {

	// Public
	List<User> playersList;
	List<Game> gamesList;
	List<String> ipsList = new ArrayList<String>();
	int port = Profile.DEFAULT_PORT;

	// Private 
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
	 * Return the interface of data module that main use
	 * @return IDataMain : the interface of data module
	 */
	public IDataMain getIdata() {
		return idata;
	}

	/**
	 * Add a user to the menu list
	 * @param user : the user that should be added
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
	 * Remove a user to the menu list
	 * @param user : the user that should be removed
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
	 * Add a game to the menu list
	 * @param createdGame : the game that should be added
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
	 * Remove a game to the menu list
	 * @param removedGame : the game that should be removed
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
	 * Set the statistic information for the profil window
	 * @param profil : the profil that should be displayed
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
	 * Update a game status
	 * @param game : the game that should be updated
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
	public void setGameJoinResponse(boolean isOk) { }

    /**
	 * Display the login window
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
	 * Display the menu window
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
			// Associate the close button to the disconnection action
			stage.setOnCloseRequest((WindowEvent event1) -> {
	            	idata.askDisconnection();
	        });

			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

    /**
	 * Open the window for creating a game
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
	 * Open the signup window
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
	 * Open the configuration window
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
	 * Ask the IHM-Table module to open the placement phase window
	 */
    @Override
    public void openPlacementPhase(final Game game) {
        try {
            Runnable command = new Runnable() {
                	@Override
                	public void run() {
                		try {
                			if (waitingRoomController != null) waitingRoomController.closeWindow();
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
     * Open the waiting room window
     * @param game : the game that the user want to join
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
			
			// Associate the close button to the remove game action
			stage.setOnCloseRequest((WindowEvent event1) -> {
				idata.removeGame(game);
			});

		} catch(Exception e) {
			e.printStackTrace();
		}
	} 

	/**
	 * Return the list of ip address writen by the user
	 * @return List<String> : ips list
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
	 * Set the list of ip address writen by the user
	 * @param list : ips list
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
	 * Set the data interface that would be use by the class
	 * @param idata : data interface for main module
	 */
	public void setIdata(IDataMain idata) {
		this.idata = idata;
	}

	/**
	 * Constructor for the class. Set the stage used by the application.
	 * @param s : stage that would be used
	 */
	public GuiMainController(Stage s) {
		super();
		this.stage = s;
	}

	/**
	 * Call data module to notify that the user want to join a game
	 * @param game : game that the user want to join
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
	 * Open the profile window for a user
	 * @param user : user that the profil would be display
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
	 * Open the profil window for the actual user. The user can update his profile.
	 * @param user : the actual user
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
	 * Set the port that would be used to communicated between application.
	 * @param num_port : port number
	 */
	public void setPort(int num_port) {
		Profile p = idata.getLocalProfile();
		
		if ( p != null && p.getIdUser() != null) {
			idata.setPort(num_port);
		}
		
		this.port = num_port;
	}

	/**
	 * Return the port that would be used to communicated between application.
	 * @return port used
	 */
	public int getPort() {
		Profile p = idata.getLocalProfile();
		if (p != null && p.getIdUser() != null) {
			this.port = p.getPort();
		}
		
		return this.port;
	}

	/**
	 * Ask IHM-Table to display the observer phase of a game
	 * @param game: game that the user want to observe
	 */
	public void lookGame(Game game) {
		idata.gameToSpec(game);
		GuiTableController.getInstance().displayObserverPhase(stage, game);
	}
}






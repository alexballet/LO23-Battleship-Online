package guiMain;

import java.io.IOException;
import java.net.InetAddress;
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

	List<User> playersList;
	List<Game> gamesList;
	List<String> ipsList = new ArrayList<String>();

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

	public IDataMain getIdata() {
		return idata;
	}

	@Override
	public void addUser(final User user) {

		Runnable command = new Runnable() {
			@Override
			public void run() {
				System.out.println("GUIMain");
				menuController.addUser(user);
				System.out.println("After GUIMain");
			}
		};
		Platform.runLater(command);
	}

	@Override
	public void removeUser(final User user) {
		Runnable command = new Runnable() {
			@Override
			public void run() {
				System.out.println("GUIMain");
				menuController.removeUser(user);
				System.out.println("After GUIMain");
			}
		};
		Platform.runLater(command);
	}

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

	@Override
	public void sendStatistics(Profile profil) {
		// TODO Auto-generated method stub
		profilController.setProfil(profil);

	}

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
		// TODO Auto-generated method stub

	}

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
			// idata.connection(loginController.);
			System.out.println("Connection etablished");

			/*
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent we) {
					idata.askDisconnection();
					System.out.println("Stage is closing");
				}
			});
			 */  


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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
        
    public void openPlacementPhase(final Game game) {
            try {
                Runnable command = new Runnable() {
			@Override
			public void run() {
                            try {
                                if (waitingRoomController != null) waitingRoomController.closeWindow();
                                GuiTableController.getInstance().displayPlacementPhase( stage, game.getClassicType(), game.getTimePerShot() ); 
                                
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

	public void openWaitingRoomWindow(Game game){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/fxml/Ihm-main/waitingRoom.fxml"));
			Parent root = (Parent) loader.load();

			waitingRoomController = loader.getController();
			waitingRoomController.initData(game);

			System.out.println("open waiting room");

			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Salle d'attente");
			stage.setScene(new Scene(root));
			waitingRoomController.setStage(stage);
			stage.show();


			stage.setOnCloseRequest((WindowEvent event1) -> {
				idata.removeGame(game);
				System.out.println("Exit waiting room");
			});

		} catch(Exception e) {
			e.printStackTrace();
		}
	} 

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

	public void setIps(List<String> ips){
		Profile p = idata.getLocalProfile();
		if ( p != null && p.getIdUser() != null) {
			// DATA : function for update IP
		}
		this.ipsList = ips;
	}

	public void setIdata(IDataMain idata) {
		this.idata = idata;
	}

	public GuiMainController(Stage s) {
		super();
		this.stage = s;
	}

	public void askJoinGame(final Game game) {
		Runnable command = new Runnable() {

			@Override
			public void run() {
				idata.notifGameChosen(game);

			}
		};
		Platform.runLater(command);
	}

	public void openProfileWindow(User user){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/fxml/Ihm-main/profil.fxml"));
			Parent root = (Parent) loader.load();

			profilController = loader.getController();
			profilController.setMainController(this);
			profilController.init(user);

			Stage stage = new Stage();
			stage.setTitle("Profil");
			stage.setScene(new Scene(root));  
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void openChangeProfileWindow(User user){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/fxml/Ihm-main/changeProfile.fxml"));
			Parent root = (Parent) loader.load();

			changeProfileController = loader.getController();
			changeProfileController.setMainController(this);
			changeProfileController.init(user);

			Stage stage = new Stage();
			stage.setTitle("Modification de profil");
			stage.setScene(new Scene(root));  
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}






package guiMain;

import java.io.IOException;
import java.util.List;

import guiMain.controller.IpConfigController;
import guiMain.controller.SignupController;
import guiMain.controller.menuController;
import interfacesData.IDataMain;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import structData.Game;
import structData.Profile;
import structData.User;

public class GuiMainController implements GuiMainInterface {
	
	List<User> playersList;
	List<Game> gamesList;
	List<String> ipsList;

	private Stage stage;
	private AnchorPane rootLayout;
	private IDataMain idata;
    private menuController menuController;
    private SignupController signUpController;
    private IpConfigController ipConfigController;
	
	public IDataMain getIdata() {
		return idata;
	}


	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addGame(Game createdGame) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendStatistics(Profile profil) {
		// TODO Auto-generated method stub

	}

	@Override
	public void transmitNewStatus(Game game) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setGameJoinResponse(boolean isOk) {
		// TODO Auto-generated method stub

	}

	public void startIHM(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/Ihm-main/menu.fxml"));
		try {
			rootLayout = (AnchorPane) loader.load();

			menuController = loader.getController();
			menuController.setMainController(this);
			menuController.init();

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

	public List<String> getIps(){
		return this.ipsList;
	}

	public void setIps(List<String> ips){
		this.ipsList = ips;
	}

	public void setIdata(IDataMain idata) {
		this.idata = idata;
	}


	public GuiMainController(Stage s) {
		super();
		this.stage = s;
	}

	public void askJoinGame(Game game) {
		idata.notifGameChosen(game);
	}
	
	
}






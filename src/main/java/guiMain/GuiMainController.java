package guiMain;

import guiMain.controller.ChangeProfileController;
import java.io.IOException;
import java.util.List;

import guiMain.controller.ProfilController;
import guiMain.controller.menuController;
import interfacesData.IDataMain;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import structData.Game;
import structData.Profile;
import structData.User;

public class GuiMainController implements GuiMainInterface {
	
	List<User> playersList;
	List<Game> gamesList;

	private Stage stage;
	private AnchorPane rootLayout;
	private IDataMain idata;
	private menuController menuController;
	
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
	
	public void displayProfil() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/Ihm-main/profil.fxml"));
		try {
			rootLayout = (AnchorPane) loader.load();

			ProfilController profilController = loader.getController();
			profilController.setDataController(this);
                        //  TODO : change user to selected user in list view
                        User user = new User();
			profilController.init(user);

			Scene scene = new Scene(rootLayout);
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
        
	public void changeProfile() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/fxml/Ihm-main/changeProfile.fxml"));
		try {
			rootLayout = (AnchorPane) loader.load();

			ChangeProfileController changeProfileController = loader.getController();
			changeProfileController.setDataController(this);
			changeProfileController.init();

			Scene scene = new Scene(rootLayout);
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void setIdata(IDataMain idata) {
		this.idata = idata;
	}


	public GuiMainController(Stage s) {
		super();
		this.stage = s;
	}

	
	
	
}






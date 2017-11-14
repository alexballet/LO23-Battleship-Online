package guiMain;

import java.io.IOException;

import guiMain.controller.connectionController;
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
			menuController.init();

			Scene scene = new Scene(rootLayout);
			stage.setTitle("Battleship-Online");
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






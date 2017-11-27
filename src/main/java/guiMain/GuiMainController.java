package guiMain;

import java.awt.event.ActionEvent;
import java.io.IOException;

import guiMain.controller.connectionController;
import guiMain.controller.menuController;
import interfacesData.IDataMain;
import javafx.application.Platform;
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
			menuController.init(idata);

			Scene scene = new Scene(rootLayout);
			stage.setTitle("Battleship-Online");
			stage.setScene(scene);
			stage.show();
			
			System.out.println("start connection");
			idata.connection();
			System.out.println("Connection etablished");
			

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






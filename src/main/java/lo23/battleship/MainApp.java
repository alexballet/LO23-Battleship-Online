package lo23.battleship;

import javafx.application.Application;
import static javafx.application.Application.launch;

import data.DataController;
import guiMain.GuiMainController;
import javafx.stage.Stage;
import lo23.battleship.online.network.NetworkController;


public class MainApp extends Application {


	@Override
	public void start(Stage stage) throws Exception {

		NetworkController networkController = NetworkController.getInstance();
		DataController dataController = new DataController();
		GuiMainController guiMainController = new GuiMainController(stage);
		networkController.setDataInterface(dataController.getInterfaceDataCom());
		guiMainController.setIdata(dataController.getInterfaceDataMain());
		dataController.setInterfaceCom(networkController.getCOMInterface());
		dataController.setInterfaceMain(guiMainController);


		guiMainController.startIHM();

	}

	/**
	 * The main() method is ignored in correctly deployed JavaFX application.
	 * main() serves only as fallback in case the application can not be
	 * launched through deployment artifacts, e.g., in IDEs with limited FX
	 * support. NetBeans ignores main().
	 *
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}

package lo23.battleship;

import javafx.application.Application;
import static javafx.application.Application.launch;

import data.DataController;
import guiMain.GuiMainController;
import guiMain.controller.menuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        /*
<<<<<<< HEAD
        	
    	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/Ihm-main/menu.fxml"));

        rootLayout = (AnchorPane) loader.load();
        menuController controller = loader.getController();
        controller.init(stage);   


        Scene scene = new Scene(rootLayout);
        stage.setTitle("Battleship-Online");
        stage.setScene(scene);
        stage.show();

=======
*/
    DataController dataController = new DataController();
    GuiMainController guiMainController = new GuiMainController(stage);
    guiMainController.setIdata(dataController.getInterfaceDataMain());
    
    
    
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

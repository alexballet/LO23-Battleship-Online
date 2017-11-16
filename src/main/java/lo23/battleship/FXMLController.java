package lo23.battleship;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class FXMLController implements Initializable {

    // to log, use log.info(message)
    final static private Log log = LogFactory.getLog(FXMLController.class);
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        log.info("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}

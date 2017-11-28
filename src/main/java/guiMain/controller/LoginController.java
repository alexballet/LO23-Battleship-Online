package guiMain.controller;

import guiMain.GuiMainController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable{

    @FXML
    private TextField userLogin;

    @FXML
    private PasswordField userPassword;

    @FXML
    private Label errorMessage;

    @FXML
    private Button loginButton;

    @FXML
    private Button createAccount;
    
    private GuiMainController mainController;

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Hide error message
        errorMessage.setVisible(false);
    }
    
    /**
     * Set mainController to open the windows 
     * @param c : GuiMainController
     */
    public void setMainController (GuiMainController c) {
        mainController = c;
    }
        
    /**
     * Login of users in the system 
     * @param event : #loginButton event 
     */
    @FXML
    void login(ActionEvent event) {
        String login = userLogin.getText();
        String password = userPassword.getText();
        /* WAIT FOR CHANGES IN IDATAMAIN TO PASS LOGIN PARAMETERS*/
     /*   if(mainController.getIdata().connection()){
            mainController.startIHM();
        }else{
           // User can not login. Show error message
           errorMessage.setText("Une erreur sauvage est apparue. Veuillez réessayer.");
           errorMessage.setVisible(true);
        }*/
    }

    /**
     * Open create account window 
     * @param event : #createAccount button event
     */
    @FXML
    void openCreateAccountWindow(ActionEvent event){
        mainController.openMenuWindow();
    }
}
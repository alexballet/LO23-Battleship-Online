package guiMain.controller;

import guiMain.GuiMainController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
     * Login of users in the system 
     * @param event : #loginButton event 
     * @throws IOException 
     */
    @FXML
    void login(ActionEvent event) throws IOException {
        String login = userLogin.getText();
        String password = userPassword.getText();
        /* WAIT FOR CHANGES IN IDATAMAIN TO PASS LOGIN PARAMETERS*/
      /*  if(mainController.getIdata().connection()){
            // Load menu page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Ihm-main/menu.fxml"));
            Parent layout = loader.load();
            Scene scene = new Scene(layout);
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        }else{
           // User can not login. Show error message
           errorMessage.setText("Une erreur sauvage est apparue. Veuillez réessayer.");
           errorMessage.setVisible(true);
        }*/
    }

    /**
     * Open create account window 
     * @param event : #createAccount button event
     * @throws IOException 
     */
    @FXML
    void openCreateAccountWindow(ActionEvent event) throws IOException {
        /* CHANGER LE NOM POUR LE CORRECT ARCHIEVE 
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Ihm-main/createAccount.fxml"));
        Parent layout = loader.load();
        Scene scene = new Scene(layout);
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        
        */
    }
}
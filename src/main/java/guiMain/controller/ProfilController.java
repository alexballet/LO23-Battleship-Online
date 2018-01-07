package guiMain.controller;
import guiMain.GuiMainController;
import java.net.URL;
import java.util.ResourceBundle;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import structData.Profile;
import structData.User;

/**
 * GameCell,implement interface Initializable, display the user profil
 * @author IHM-MAIN Module
 */
public class ProfilController implements Initializable {
    
        private GuiMainController mainController;

        @FXML
        private Label nameTitle;

        @FXML
        private Label userName;

        @FXML
        private Label firstName;

        @FXML
        private Label lastName;

        @FXML
        private Label birthdate;

        @FXML
        private Label numberOfGame;

        @FXML
        private Label numberOfGameWon;

        @FXML
        private Label numberOfGameLost;

        @FXML
        private Button backButton;

        @FXML
        private ImageView userAvatar;

        /**
         * Call at the initialization of the component. 
         * @param location : 
         * @param resources :
         */
        @Override
        public void initialize(URL location, ResourceBundle resources) { }

        /**
         * Return to menu window
         * @param event : #backButton event
         */
        @FXML
        void backToTheMenu(ActionEvent event) {
    			((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        }

        /**
         * Call the data module to recuperate the user informations
         * @param user : user that we want to recuperate the informations
         */
        public void init(User user) {
    			mainController.getIdata().getProfile(user);
        }
        
        /**
         * Set user profile data into corresponding champs 
         * @param profile: the user profile that should be display 
         */
        public void setProfil(Profile profile) {
        	
        		// Set all the correct informations in the correct field
            nameTitle.setText(profile.getUsername());
            userName.setText(profile.getUsername());
            lastName.setText(profile.getLastname());
            firstName.setText(profile.getName());
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            birthdate.setText(df.format(profile.getBirthdate()));
            numberOfGame.setText(String.valueOf(profile.getGamesPlayed()));
            numberOfGameWon.setText(String.valueOf(profile.getGamesWon()));
            numberOfGameLost.setText(String.valueOf(profile.getGamesLost()));
        }

        /**
         * Set GuiMainController mainController  
         * @param c : GuiMainController
         */
        public void setMainController(GuiMainController c) {
            mainController = c;
        }

}

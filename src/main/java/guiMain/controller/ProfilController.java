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

        @Override
        public void initialize(URL location, ResourceBundle resources) {
                //
        }

        /**
         * Return to menu window
         * @param event : #backButton event
         */
        @FXML
        void backToTheMenu(ActionEvent event) {
    			((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        }

        /**
         * Set user profile data into corresponding champs 
         * @param user 
         */
        public void init(User user) {
        			mainController.getIdata().getProfile(user);
        }
        
        public void setProfil(Profile profile) {
            nameTitle.setText(profile.getUsername());
            //userAvatar.setImage(profile.getAvatar());
            userName.setText(profile.getUsername());
            lastName.setText(profile.getLastname());
            firstName.setText(profile.getName());
            // Convert Date type to formatted strind
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

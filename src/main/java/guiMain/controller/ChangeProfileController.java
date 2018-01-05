package guiMain.controller;
import guiMain.GuiMainController;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.embed.swing.SwingFXUtils;
import sun.awt.image.ToolkitImage;
import structData.Profile;
import structData.User;

public class ChangeProfileController implements Initializable {
    
        private GuiMainController mainController;

        @FXML
        private Label nameTitle;

        @FXML
        private TextField userName;

        @FXML
        private TextField firstName;

        @FXML
        private TextField lastName;
        
        @FXML
        private PasswordField userPassword;

        @FXML
        private DatePicker birthdate;

        @FXML
        private Label numberOfGame;

        @FXML
        private Label numberOfGameWon;

        @FXML
        private Label numberOfGameLost;

        @FXML
        private Button modifyAvatarButton;

        @FXML
        private Button backButton;

        @FXML
        private Button saveButton;

        @FXML
        private ImageView userAvatar;
        
        @FXML 
        private Label errorMessage;
        
        private String avatarPath;

        @Override
        public void initialize(URL location, ResourceBundle resources) {
                errorMessage.setVisible(false);
        }

        /**
         * Return to menu window
         * @param event : #backButton event
         */
        @FXML
        void backToMenu(ActionEvent event) {
    			((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    			mainController.openMenuWindow();
        }

        /**
         * Open dialog to select avatar image 
         * @param event : #modifyAvatarButton event
         */
        @FXML
        void modifyAvatar(ActionEvent event) {
			FileChooser fileChooser = new FileChooser();
			FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
	        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
	        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
	        Stage stage = ((Stage)(((Button)event.getSource()).getScene().getWindow()));
	        File file = fileChooser.showOpenDialog(stage);
	        if (file != null) {
	        		openFile(file);
	        }
        }

        /**
         * Save data changed into user profile
         * @param event : #saveButton event
         */
        @FXML
        void saveProfile(ActionEvent event) {
                Image avatar = userAvatar.getImage();
                String user_name = userName.getText();
                String last_name = lastName.getText();
                String first_name = firstName.getText();
                Date birth_date = null;
                if (birthdate.getValue() != null){
                		birth_date = Date.from(birthdate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
                }
                // TODO : get local user password
                String password = ""; // set correct password
                if(!userPassword.getText().isEmpty()){
                    // Change password if textfield is not empty
                    password = userPassword.getText();
                }
                if (!user_name.trim().isEmpty() && !last_name.trim().isEmpty() && !first_name.trim().isEmpty()){   
                		mainController.getIdata().editProfile(user_name, password, avatarPath , last_name, first_name, birth_date);
                }else{
                    errorMessage.setText("Des champs obligatoires ne sont pas remplis");
                    errorMessage.setVisible(true);
                }
        }

        /**
         * Initialize profile data of the correspondent local user
         * @param user
         */
        public void init(User user) {   
                // TODO : Verifier methode getStatistics with Data
                Profile profile = mainController.getIdata().getLocalProfile();
                nameTitle.setText(profile.getUsername());
                if (profile.getAvatar() != null) {
                    BufferedImage bufferedImage = ((ToolkitImage) profile.getAvatar().getImage()).getBufferedImage();
                    Image img = SwingFXUtils.toFXImage(bufferedImage, null);
                    userAvatar.setImage(img);
                }
                userName.setText(profile.getUsername());
                lastName.setText(profile.getLastname());
                firstName.setText(profile.getName());
                // Convert Date type to formatted string
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                if (profile.getBirthdate() != null) birthdate.setValue(LocalDate.parse(df.format(profile.getBirthdate())));
                numberOfGame.setText(String.valueOf(profile.getGamesPlayed()));
                numberOfGameWon.setText(String.valueOf(profile.getGamesWon()));
                numberOfGameLost.setText(String.valueOf(profile.getGamesLost()));
                userPassword.setPromptText("Non modifié");
        }

        /**
         * Set GuiMainController mainController  
         * @param c : GuiMainController
         */
        public void setMainController(GuiMainController c) {
                mainController = c;
        }
        
        /**
         * Open image file and set avatar
         * @param file : image file selected by the user
         */
        private void openFile(File file) {
            try {
            		avatarPath = file.getAbsolutePath();
                Image selectedImage = new Image("file:" + avatarPath);
                userAvatar.setImage(selectedImage);
            } catch (IllegalArgumentException ex) {
                	Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
}

package guiMain.controller;
import guiMain.GuiMainController;
import java.net.URL;
import java.util.ResourceBundle;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import structData.Profile;

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
    private TextField birthdate;

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
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //
    }
    
    @FXML
    void backToTheMenu() {
        //mainController.openMenuWindow();
    }

    @FXML
    void modifyAvatar() {

    }

    @FXML
    void saveProfile() {
        // TODO : pass the correct parameters into editProfile (ask for changes in IdataMain) 
       // mainController.getIdata().editProfile();
    }

    /**
     * Initialize profile data of the correspondent local user
     */
    public void init() {   
        // TODO : Verifier methode getStatistics with Data
        
       /* Profile profile = mainController.getIdata().getStatistics();
        nameTitle.setText(profile.getusername());
        userAvatar.setImage(profile.getAvatar());
        userName.setText(profile.getusername());
        lastName.setText(profile.getLastname());
        firstName.setText(profile.getName());
        // Convert Date type to formatted strind
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        birthdate.setText(df.format(profile.getBirthdate()));
        numberOfGame.setText(String.valueOf(profile.getGamesPlayed()));
        numberOfGameWon.setText(String.valueOf(profile.getGamesWon()));
        numberOfGameLost.setText(String.valueOf(profile.getGamesLost()));*/
    }

    public void setDataController(GuiMainController c) {
        mainController = c;
    }
    
}

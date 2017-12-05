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
import javafx.scene.image.ImageView;
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
    
    @FXML
    void backToTheMenu() {
        //mainController.openMenuWindow();
    }

    public void init(User user) {
        // TODO : Envoyer un user pour recuperer leur profil (changement interface IdataMain) 
       /* Profile profile = mainController.getIdata().getStatistics(user);
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

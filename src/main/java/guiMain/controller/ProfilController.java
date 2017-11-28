package guiMain.controller;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

import interfacesData.IDataMain;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import structData.Profile;

public class ProfilController implements Initializable {
	IDataMain idata;
	
	@FXML
    private Label nameTitle;

    @FXML
    private Button modifyProfilButton;

    @FXML
    private Label username;

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
    private Button modifyAvatarButton;

    @FXML
    private Button backButton;

    @FXML
    private Button saveButton;

    @FXML
    void backToTheMenu() {

    }

    @FXML
    void modifyAvatar() {

    }

    @FXML
    void modifyProfil() {

    }

    @FXML
    void saveProfil() {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void init() {
		Profile profil = idata.getStatistics();
	}

	public void setDataController(IDataMain c) {
		idata = c;
	}
}

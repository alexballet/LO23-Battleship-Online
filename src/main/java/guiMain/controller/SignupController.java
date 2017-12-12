package guiMain.controller;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import guiMain.GuiMainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class SignupController {

	private GuiMainController mainController;
	
	@FXML 
	private ImageView avatarImage;
	
	@FXML
	private Button chooseAvatarButton;
	
	@FXML
	private Button addConnectionButton;
	
	@FXML
	private Button createButton;
	
	@FXML
	private Button connectionPageButton;
	
	@FXML
	private TextField idTextField;
	
	@FXML
	private PasswordField passwordTextField;
	
	@FXML
	private TextField usernameTextField;
	
	@FXML
	private TextField firstNameTextField;
	
	@FXML
	private TextField lastNameTextField;
	
	@FXML
	private DatePicker birthDatePicker;
	
	@FXML
	private Label messageLabel;
	
	
	
	public void setMainController (GuiMainController c) {
		mainController = c;
	}

	public void init() {
	}



	@FXML
	private void chooseAvatar(ActionEvent event){
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
	
	
	@FXML
	private void createAccount(){
		/*void createAccount(String idUser, String login, String username, Long[] ips, String password, ContactGroup[] contactList, int avatar, String lastname, String firstname, Date birthDate);*/
		/* A quoi correspond idUser ? */
		String idUser = idTextField.getText();
		
		String login = idTextField.getText();
		String username = usernameTextField.getText();
		
		/* Long ??? */
		HashSet<InetAddress> ips = new HashSet<>();
		
		
		String password = passwordTextField.getText();
		/* C'est quoi ? */
		//ContactGroup[] contactList,
		
		/* int ???? */
		//int avatar = ;
		
		
		String lastname = lastNameTextField.getText();
		String firstname = firstNameTextField.getText();
		Date birthDate = null;
		if (birthDatePicker.getValue() != null)
			birthDate = Date.from(birthDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		System.out.println("ID " + login + "-------");
		System.out.println("Password " + password + "------");
		System.out.println("Username " + username);
		System.out.println("Prenom " + firstname);
		System.out.println("Nom " + lastname);
		System.out.println("Date de Naissance " + birthDate);
		List<String> ipsStrings = mainController.getIps();
		if (ipsStrings != null && ipsStrings.size() > 0){
			for (String ip : ipsStrings) {
				try {
					ips.add(InetAddress.getByName(ip));
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		if (!login.trim().isEmpty() && !password.trim().isEmpty() && !username.trim().isEmpty()){
			mainController.getIdata().createAccount(login, username, ips, password, null, null, lastname, firstname, birthDate);
			mainController.startIHM();
		}
		else{
			messageLabel.setText("Des champs obligatoires ne sont pas remplis");
			messageLabel.setTextFill(Color.web("#ff0000"));
		}
	}
	
	@FXML
	private void addConnectionPoint(){
		mainController.openConfigWindow();
	}
	
	@FXML
	private void backToConnectionWindow(){
		mainController.startIHM();
	}
	
	private void openFile(File file) {
        try {
        	Image selectedImage = new Image("file:" + file.getAbsolutePath());
        	avatarImage.setImage(selectedImage);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(
                SignupController.class.getName()).log(
                    Level.SEVERE, null, ex
                );
        }
    }


}

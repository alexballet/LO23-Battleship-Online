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

/**
*
* This class implements the controller of the SignUp page
* @author IHM-Main module
*/
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
	
	private String avatarPath;
	
	 /**
     * Set GuiMainController mainController  
     * @param c : GuiMainController
     */
	public void setMainController (GuiMainController c) {
		mainController = c;
	}

	/**
	 *  Called at initialization
	 */
	public void init() {
	}

    /**
     * Open a File Chooser pop up
     * @param event : #validateButton
     */
	@FXML
	private void chooseAvatar(ActionEvent event){
		FileChooser fileChooser = new FileChooser();
		
		// Set the matching files to be only of type JPG or PNG
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
       
        // Get the current stage from chooseAvatarButton
        Stage stage = ((Stage)(((Button)event.getSource()).getScene().getWindow()));
		File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            openFile(file);
        }
	}
	
    /**
     * Create a new account
     */
	@FXML
	private void createAccount(){

		// Get all infos from the Text Fields
		String login = idTextField.getText();
		String username = usernameTextField.getText();
		HashSet<InetAddress> ips = new HashSet<>();
		String password = passwordTextField.getText();
		String avatar = avatarPath;
		String lastname = lastNameTextField.getText();
		String firstname = firstNameTextField.getText();
		Date birthDate = null;
		
		// Cast javafx LocalDate to java Date
		if (birthDatePicker.getValue() != null)
			birthDate = Date.from(birthDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		// Get the Ips list
		List<String> ipsStrings = mainController.getIps();
		
		// Get the Port
		int port = mainController.getPort();
		
		// Populate the HashSet
		if (ipsStrings != null && ipsStrings.size() > 0){
			for (String ip : ipsStrings) {
				try {
					ips.add(InetAddress.getByName(ip));
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}
		}
		
		// Check if all mandatory fields have been filled
		if (!login.trim().isEmpty() && !password.trim().isEmpty() && !username.trim().isEmpty()) {
			mainController.getIdata().createAccount(login, username, ips, port, password, null, avatar, lastname, firstname, birthDate);
			mainController.startIHM();
		}
		else{
			messageLabel.setText("Des champs obligatoires ne sont pas remplis");
			messageLabel.setTextFill(Color.web("#ff0000"));
		}
	}
	
	/** 
	 * Open the Ip configuration page
	 */
	@FXML
	private void addConnectionPoint(){
		mainController.openConfigWindow();
	}
	
	/** 
	 * Return to the starting page (Connection page)
	 */
	@FXML
	private void backToConnectionWindow(){
		mainController.startIHM();
	}
	
	/** 
	 * Open the image file and set the avatar to this image
	 */
	private void openFile(File file) {
        try {
        	// Get the absolute path of the file
        	avatarPath = file.getAbsolutePath();
        	
        	// Create a javafx Image with this file
        	Image selectedImage = new Image("file:" + avatarPath);
        	
        	// Add the image to the Image view
        	avatarImage.setImage(selectedImage);
        	
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(
                SignupController.class.getName()).log(
                    Level.SEVERE, null, ex
                );
        }
    }


}

package guiMain.controller;

import java.util.ArrayList;
import java.util.regex.Pattern;

import guiMain.GuiMainController;
import guiMain.IpCell;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.util.Callback;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
*
* This class implements the controller of the Ip Configuration page
* @author IHM-Main module
*/
@SuppressWarnings("restriction")
public class IpConfigController {

	private GuiMainController mainController;
	
	@FXML 
	private ListView<String> ipsListView;
	@FXML
	private TextField ipTextField;
	@FXML
	private Button addButton;
	@FXML
	private Button validateButton;    
    @FXML 
    private TextField port;

	
	
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
		this.initIpsList();
        // Force the port field to be numeric (integer) only
        port.textProperty().addListener(new ChangeListener<String>() {
        		@Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, 
	            String newValue) {
	            if (!newValue.matches("\\d*")) {
	                    port.setText(newValue.replaceAll("[^\\d]", ""));
	            }
            }
        });
        port.setText(mainController.getPort() + "");
	}
	
	/**
	 *  Initialize the Ips list with the already available Ips
	 */
	private void initIpsList() {
		
		ObservableList<String> ipsObservable = FXCollections.observableArrayList(new ArrayList<String>());
		if (mainController.getIps() != null)
			ipsObservable.setAll(mainController.getIps());
			ipsListView.setItems(ipsObservable);
			ipsListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() { 
			  
		   @Override 
		    public ListCell<String> call(ListView<String> lv) { 
		        return new IpCell(); 
		    }
		});
	}

	/** 
	 * Access an Ip to the list view
	 */
	@FXML
	private void addIp(){
		// Get the string from the Ip TextField
		String ipToAdd = ipTextField.getText();
		
		// Set the matching pattern of an Ip address
		Pattern pattern = Pattern.compile(
		        "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
		
		// Check if the new ip is not null, is not redundant and match the Ip string pattern
		if (ipToAdd != null
				&& !ipsListView.getItems().contains(ipToAdd)
				&& pattern.matcher(ipToAdd).matches()) {
			ipsListView.getItems().add(ipTextField.getText());
			ipTextField.clear();
		}
	}

    /**
     * Set the new Ip List and close the current window
     * @param event : #validateButton
     */
	@FXML
	private void validate(ActionEvent event){
		mainController.setIps(ipsListView.getItems());
		
        int num_port = Integer.parseInt(port.getText());
		mainController.setPort(num_port);
        
		// Add the close event when validating
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	
    	}
        
}

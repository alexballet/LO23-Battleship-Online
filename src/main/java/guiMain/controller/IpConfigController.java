package guiMain.controller;

import java.util.ArrayList;
import java.util.regex.Pattern;

import guiMain.GuiMainController;
import guiMain.IpCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.util.Callback;
import javafx.scene.control.*;
import javafx.stage.Stage;

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

	
	
	
	public void setMainController (GuiMainController c) {
		mainController = c;
	}

	public void init() {
		this.initIpsList();
	}
	
	private void initIpsList() {
		/* Ajouter l'initialisation avec les ips déjà présentes */
		/* Dans les paramètres du compte */
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

	@FXML
	private void addIp(){
		String ipToAdd = ipTextField.getText();
		Pattern pattern = Pattern.compile(
		        "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
		
		if (ipToAdd != null
				&& !ipsListView.getItems().contains(ipToAdd)
				&& pattern.matcher(ipToAdd).matches()) {
			ipsListView.getItems().add(ipTextField.getText());
		}
	}

	@FXML
	private void validate(ActionEvent event){
		System.out.println("Ips " + ipsListView.getItems());
		mainController.setIps(ipsListView.getItems());
		((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
	}
}

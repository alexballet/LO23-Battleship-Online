package guiMain;

import javafx.scene.control.ListCell;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import structData.*;
import guiMain.controller.menuController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


/**
 * PlayerCell,descendant class ListCell<User>, display the user name and offers the possibility watch his profil
 * @author IHM-MAIN Module
 */
public class PlayerCell extends ListCell<User> {
	
	HBox hbox = new HBox();
	HBox lookButtonBox = new HBox();
	Label name = new Label();
	Button lookButton = new Button("");
	menuController controller;

	
	/** 
	 * Init the gui parameter of the cell.
	 * @param c : controller necessary for futur update.
	 */
	public PlayerCell(menuController c) {
		super();
		controller = c;

		// Set with for containers
		HBox nameBox = new HBox();
		nameBox.setMinWidth(180);
		nameBox.setAlignment(Pos.CENTER_LEFT);
		nameBox.getChildren().addAll(name);

		// Set style for look button
		lookButtonBox.setPrefWidth(50);
		lookButtonBox.setAlignment(Pos.CENTER_LEFT);
		lookButtonBox.getChildren().addAll(lookButton);

		// Add all texts and buttons to the main container
		hbox.getChildren().addAll(nameBox, lookButtonBox);
		hbox.setAlignment(Pos.CENTER);
		HBox.setHgrow(name, Priority.ALWAYS);
	}


	/** 
	 * Update the cell.
	 * @param user : user's cell to update.
	 */
	protected void updateItem(final User user, boolean empty) {
		super.updateItem(user, empty);
		setText(null);
		setGraphic(null);

		// Verify if there is a game
		if (!empty && user != null) { 
			name.setText(user.getUsername());

			ImageView backgroundLook=new ImageView(new Image("/img/look.png"));
			backgroundLook.setFitHeight(20);
			backgroundLook.setPreserveRatio(true);
			lookButton.setGraphic(backgroundLook);
			
			// Associate the button with the controller action
			lookButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					controller.lookUser(user);
				}
			});

			setGraphic(hbox);
		}
	}
}
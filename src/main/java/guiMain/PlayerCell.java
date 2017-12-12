package guiMain;

import javafx.scene.control.ListCell;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import structData.*;


import guiMain.controller.menuController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class PlayerCell extends ListCell<User> {
	HBox hbox = new HBox();
	HBox lookButtonBox = new HBox();
	Label name = new Label();
	Button lookButton = new Button("");
	menuController controller;

	public PlayerCell(menuController c) {
		super();

		controller = c;

		HBox nameBox = new HBox();
		nameBox.setMinWidth(180);
		nameBox.setAlignment(Pos.CENTER_LEFT);
		nameBox.getChildren().addAll(name);

		lookButtonBox.setPrefWidth(50);
		lookButtonBox.setAlignment(Pos.CENTER_LEFT);
		lookButtonBox.getChildren().addAll(lookButton);

		hbox.getChildren().addAll(nameBox, lookButtonBox);
		hbox.setAlignment(Pos.CENTER);
		HBox.setHgrow(name, Priority.ALWAYS);
	}


	protected void updateItem(final User user, boolean empty) {
		super.updateItem(user, empty);
		setText(null);
		setGraphic(null);

		if (!empty && user != null) { 
			name.setText(user.getUsername());

			ImageView backgroundLook=new ImageView(new Image("/img/look.png"));
			backgroundLook.setFitHeight(20);
			backgroundLook.setPreserveRatio(true);
			lookButton.setGraphic(backgroundLook);

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
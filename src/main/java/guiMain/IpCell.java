package guiMain;

import javafx.scene.control.ListCell;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class IpCell extends ListCell<String> {
	
    HBox hbox = new HBox();
    Label ipLabel = new Label("");
    Pane pane = new Pane();
    Button removeButton = new Button("");
    
    public IpCell() {
        super();
        
        hbox.getChildren().addAll(ipLabel, pane, removeButton);
        HBox.setHgrow(pane, Priority.ALWAYS);
        removeButton.setOnAction(new EventHandler<ActionEvent>(){
        	@Override
        	public void handle(ActionEvent event){
        		getListView().getItems().remove(getItem());
        	}
        });
        
        ImageView backgroundRemove=new ImageView(new Image("/img/remove.png"));
        backgroundRemove.setFitHeight(10);
        backgroundRemove.setPreserveRatio(true);
    	removeButton.setGraphic(backgroundRemove);
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);
        setGraphic(null);

        if (item != null && !empty) {
            ipLabel.setText(item);
            setGraphic(hbox);
        }
    }
}


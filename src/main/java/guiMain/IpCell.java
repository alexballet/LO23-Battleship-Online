package guiMain;

import javafx.scene.control.ListCell;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * IpCell, Display a list of Ips on a list that can be removed with a button
 * @author IHM-MAIN Module
 */
public class IpCell extends ListCell<String> {
	
    HBox hbox = new HBox();
    Label ipLabel = new Label("");
    Pane pane = new Pane();
    Button removeButton = new Button("");
    
    /**
     * Class constructor.
     */
    public IpCell() {
        super();
        
        // Add all assets to the main container
        hbox.getChildren().addAll(ipLabel, pane, removeButton);
        HBox.setHgrow(pane, Priority.ALWAYS);
        
        // Set a click event to the button so the current cell is removed
        removeButton.setOnAction(new EventHandler<ActionEvent>(){
        	@Override
        	public void handle(ActionEvent event){
        		getListView().getItems().remove(getItem());
        	}
        });
        
        // Set an image and its properties for the remove button
        ImageView backgroundRemove=new ImageView(new Image("/img/remove.png"));
        backgroundRemove.setFitHeight(10);
        backgroundRemove.setPreserveRatio(true);
    	removeButton.setGraphic(backgroundRemove);
    }

    /**
     * Update the content of a Cell : set a style according to the parameters of a game
     * @param item : the new content of a cell to be displayed
     * @param empty : true if the cell is empty, false otherwise
     */
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);
        setGraphic(null);

        // Check if Cell is not empty and if the new Ip is not null
        if (item != null && !empty) {
            ipLabel.setText(item);
            setGraphic(hbox);
        }
    }
}


package guiMain;

import javafx.scene.control.ListCell;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import structData.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.net.URLClassLoader;

import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class GameCell extends ListCell<Game> {
	HBox hbox = new HBox();
    Label title = new Label();
    Label type = new Label();
    // Label isRobot = new Label();
    ImageView robot = new ImageView();
    Button joinButton = new Button("");
    Button lookButton = new Button("");
    
    public GameCell() {
        super();
        
        //title.setPadding(new Insets(10, 20, 10, 20));
		type.setPadding(new Insets(10, 20, 10, 20));
        
        HBox titleBox = new HBox();
        titleBox.setMinWidth(300);
        titleBox.setAlignment(Pos.CENTER_LEFT);
        titleBox.getChildren().addAll(title);
		
        HBox typeBox = new HBox();
        typeBox.setPrefWidth(100);
        typeBox.setAlignment(Pos.CENTER);
        typeBox.getChildren().addAll(type);
		
		HBox robotBox = new HBox();
		robotBox.setPrefWidth(20);
		robotBox.setAlignment(Pos.CENTER_LEFT);
		robotBox.getChildren().addAll(robot);
		
        hbox.getChildren().addAll(titleBox, robotBox, typeBox, lookButton, joinButton);
        hbox.setAlignment(Pos.CENTER);
        HBox.setHgrow(title, Priority.ALWAYS);
   }
    
   protected void updateItem(Game game, boolean empty) {
        super.updateItem(game, empty);
        setText(null);
        setGraphic(null);
        
        if (!empty && game != null) { 
        		title.setText(game.getName());
        		type.setText(game.getClassicType() ? "Classique" : "Belge");
            // isRobot.setText(game.getHumanOpponent() ? "Humain" : "Robot");
        		
        		if (!game.getHumanOpponent()) {
            		robot.setImage(new Image("/img/robot.png"));
            		robot.setFitWidth(20); 
                robot.setPreserveRatio(true);
        		}
        		
        		ImageView backgroundJoin=new ImageView(new Image("/img/join-arrow.png"));
        	    backgroundJoin.setFitHeight(20);
        	    backgroundJoin.setPreserveRatio(true);
        	    joinButton.setGraphic(backgroundJoin);
        		
            joinButton.setOnAction(new EventHandler<ActionEvent>() {
	        		@Override
	            public void handle(ActionEvent event) {
	        			System.out.println("Join " + game.getName());
	            }
	        });
            
	    		ImageView backgroundLook=new ImageView(new Image("/img/look.png"));
	    		backgroundLook.setFitHeight(20);
	    		backgroundLook.setPreserveRatio(true);
	    		lookButton.setGraphic(backgroundLook);
            
            lookButton.setOnAction(new EventHandler<ActionEvent>() {
	        		@Override
	            public void handle(ActionEvent event) {
	        			System.out.println("Look " + game.getName());
	            }
	        });
            
            setGraphic(hbox);
            
        }
    }
}


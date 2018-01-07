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

/**
 * GameCell,descendant class ListCell<Game>, display the game name and offers the possibility to join or watch a game
 * @author IHM-MAIN Module
 */

public class GameCell extends ListCell<Game> {
	
	HBox hbox = new HBox();
	HBox joinButtonBox = new HBox();
	HBox lookButtonBox = new HBox();
    Label title = new Label();
    Label type = new Label();
    ImageView robot = new ImageView();
    Button joinButton = new Button("");
    Button lookButton = new Button("");
    menuController controller;
    
    /**
     * Class constructor.
     * @param c is the menu controller where the game is display
     */
    public GameCell(menuController c) {
        super();
        controller = c;
        
		type.setPadding(new Insets(10, 20, 10, 20));
        
		// Set with for containers
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
		
		// Set width and style for buttons
		joinButtonBox.setPrefWidth(50);
		joinButtonBox.setAlignment(Pos.CENTER_LEFT);
		joinButtonBox.getChildren().addAll(joinButton);
		
		lookButtonBox.setPrefWidth(50);
		lookButtonBox.setAlignment(Pos.CENTER_LEFT);
		lookButtonBox.getChildren().addAll(lookButton);
		
		// Add all texts and buttons to the main container
        hbox.getChildren().addAll(titleBox, robotBox, typeBox, lookButtonBox, joinButtonBox);
        hbox.setAlignment(Pos.CENTER);
        HBox.setHgrow(title, Priority.ALWAYS);
    }
    
   
    /**
     * Update the content of an item : set a style according to the parameters of a game
     * @param game : the game wich would be display
     * @param empty : true if the cell is empty
     */
    @Override
    protected void updateItem(final Game game, boolean empty) {
        super.updateItem(game, empty);
        setText(null);
        setGraphic(null);
        
        // Verify if there is a game
        if (!empty && game != null) { 
        		title.setText(game.getName());
        		type.setText(game.getClassicType() ? "Classique" : "Belge");

        		// Set an image if the game is against a robot
        		if (!game.getHumanOpponent()) {
            		robot.setImage(new Image("/img/robot.png"));
            		robot.setFitWidth(20); 
                robot.setPreserveRatio(true);
        		}
        		
        	    
        		// Display the join button if the game is waiting for player
        		// and the game is against a human opponant
        	    if (game.getHumanOpponent() && game.getStatus().equals(StatusGame.WAITINGPLAYER)) {
            		ImageView backgroundJoin=new ImageView(new Image("/img/join-arrow.png"));
            	    backgroundJoin.setFitHeight(20);
            	    backgroundJoin.setPreserveRatio(true);
            	    joinButton.setGraphic(backgroundJoin);
            	    // Associate the button with the controller action
                joinButton.setOnAction(new EventHandler<ActionEvent>() {
        	        		@Override
        	            public void handle(ActionEvent event) {
        	        			controller.joinGame(game);
        	            }
        	        });
        	    } else {
        	    		joinButtonBox.getChildren().remove(joinButton);
        	    }

        	    // Display the watch button if the game status is Playing and the game authorizes spectators
        	    if (game.getSpectator() && game.getStatus().equals(StatusGame.PLAYING) ) {
	    	    		ImageView backgroundLook=new ImageView(new Image("/img/look.png"));
	    	    		backgroundLook.setFitHeight(20);
	    	    		backgroundLook.setPreserveRatio(true);
	    	    		lookButton.setGraphic(backgroundLook);
            	    // Associate the button with the controller action
                lookButton.setOnAction(new EventHandler<ActionEvent>() {
	    	        		@Override
	    	            public void handle(ActionEvent event) {
	    	        			controller.lookGame(game);
	    	            }
	    	        });
        	    } else {
        	    		lookButtonBox.getChildren().remove(lookButton);
        	    }        	    
            
            this.setGraphic(hbox);
        }
    }
}


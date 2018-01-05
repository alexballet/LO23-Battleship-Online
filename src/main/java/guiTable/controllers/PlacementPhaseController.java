package guiTable.controllers;

import guiTable.BoatDrawing;
import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import javafx.scene.text.Text;
import structData.Boat;
import structData.Position;

/**
 * PlacementPhaseController
 */
public abstract class PlacementPhaseController extends BaseController implements Initializable
{

       
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private GridPane table;
    @FXML
    private Button valider;
    @FXML
    private AnchorPane chatPane;
    @FXML
    private Text messageContainer;
        
    protected static final int RANDOM_ROTATION = 2;
    private final String EXPLAIN_PLACEMENT = "press R to rotate Boat and DEL to reinitialize boat";
    
    protected Timeline timeline;
    @FXML
    protected Label timerLabel;
    protected LocalTime time;
    private LocalTime timePlacement;
    
    protected boolean rotationIsValide;
    protected BoatDrawing activeBoat;
    
    protected HashMap<Rectangle, BoatDrawing> boatMap;
    
    private boolean isValidate = false;
    
    
    
    /**
     * log message into interface.
     * @param msg message to be displayed
     * @param param list of optionnal parameter, all strings of param are display in CLI
     */
    public void logMsg(String msg, String... param) {
        messageContainer.setVisible(true);
        messageContainer.setText(msg);
        for (String string : param) {
            System.out.println(string); 
        }
    }
    
    /**
     * close message when click on it
     */
    @FXML
    protected void closeMsg() {
        messageContainer.setVisible(false);
    }
    /**
     * method to put boat in the boatMap. 
     * method must be override by subclasses.
     */
    protected void initBoatMap() {}; 
    
    /**
     * The method initialize starts the window and assigns values BoatDrawing 
     * objects and methods to the window's objects.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){
        
        boatMap = new HashMap<>();
        // Initializes the boat set
        initBoatMap();
        // Sets the events handles of the grid's squares.
        for (Rectangle rectangle : boatMap.keySet()) {
            rectangle.setOnMousePressed(activateBoat());
        }        
        activeBoat = null;

        for (int i = 0 ; i < NB_CASES_GRID ; i++) {
            for (int j = 0; j < NB_CASES_GRID; j++) {
                Pane pane = new Pane();
                pane.setOnMouseEntered(drawBoatsNewPosition());
                table.add(pane, i, j);
            }
        }
        
        // Sets the events handlers
        table.setOnMousePressed(MousePlaceBoat());
        table.setOnMouseEntered(enableRotation());
        table.setOnMouseExited(disableRotation());
        // This probably will change after the addition of the chat.
        anchorPane.addEventHandler(KeyEvent.KEY_PRESSED, playKeyEvent());
        
        rotationIsValide = false;
        
        valider.setDisable(true);                        
    }
    
   
    
    /*public void displayQuitOpponent() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/fxml/Ihm-main/waitingRoom.fxml"));
			Parent root = (Parent) loader.load();

			waitingRoomController = loader.getController();
			waitingRoomController.initData(game);

			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Salle d'attente");
			stage.setScene(new Scene(root));
			waitingRoomController.setStage(stage);
			stage.show();

			stage.setOnCloseRequest((WindowEvent event1) -> {
				idata.removeGame(game);
			});

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
    }*/
    
    /**
     * Trigger validation of placement phase
     */
    @FXML
    protected void onValidate() {
        if (!this.isIsValidate()) {
            this.setIsValidate(true);
            
            List<Boat> boats = new ArrayList<Boat>();
            for (Map.Entry<Rectangle, BoatDrawing> entry : boatMap.entrySet()) {
                BoatDrawing myBoatDrawing = entry.getValue();
                boats.add(new Boat(myBoatDrawing.getBoatType(), myBoatDrawing.isRotation(), new Position(myBoatDrawing.getGridCol(), myBoatDrawing.getGridRow(), false)));
            }
            
            if(timeline != null) {
            timeline.stop();
            }
            timerLabel.setVisible(false);
            logMsg("en attente de la validation de l'autre joueur", "");
            GuiTableController.getInstance().validateBoats(boats);
        }
    }
 
    /**
     * Check if all boats are placed
     * (ie. every boats are on the gird)
     * @return boolean true if all boats are placed, false else
     */
    protected boolean allBoatsArePlaced() {
        for(BoatDrawing boat : boatMap.values()) {
            if (!boat.isPlaced()) {
                return false;
            }
        }
        logMsg("click now on 'valider' button to begin game");
        return true;
    }
    
     
    
    
     /**
     * Actives the boat when the user clicks on it.
     * @return mousePressHandler The handler of the event (Click over the boat).
     */   
    protected EventHandler<MouseEvent> activateBoat() {
        EventHandler<MouseEvent> mousePressHandler = new EventHandler<MouseEvent>() {        
            @Override
            public void handle(MouseEvent event) {
                if(!isValidate) {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        //If the user has clicked in the window and no other boat is already selected
                        if (activeBoat == null) {
                            Rectangle myRectangle =(Rectangle) event.getSource();
                            BoatDrawing myboat  = boatMap.get(myRectangle);
                            activeBoat = myboat.setActiveBoat(boatMap);
                            activeBoat.setPlaced(false);
                            logMsg(EXPLAIN_PLACEMENT);
                        }

                    }
               }
            }
        };
        return mousePressHandler;
                    
    }
    
    /**
     * Enables the boat rotation, this only happens when the mouse is
     * over the grid.
     * @return mouseLocationHandler The handler of the event (Mouse enters ther grid).
     */
    protected EventHandler<MouseEvent> enableRotation() {
        EventHandler<MouseEvent> mouseLocationHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                rotationIsValide = true;
                event.consume();
            }
        };
        return mouseLocationHandler;
    }
    
    /**
     * Disables the boat rotation when the mouse exits the grid
     * @return mouseLocationHandler The handler of the event (Mouse exits the grid).
     */
    protected EventHandler<MouseEvent> disableRotation() {
        EventHandler<MouseEvent> mouseLocationHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {   
                rotationIsValide = false;
                event.consume();
            }
        };
        return mouseLocationHandler;
    }
    
    /**
     * Moves the boat on the grid when the mouse moves over the squares.
     * @return mousePositionHandler The handler of the event (Mouse enters a grid position).
     */
    protected EventHandler<MouseEvent> drawBoatsNewPosition() {
        EventHandler<MouseEvent> mousePositionHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(activeBoat!=null){
                    Node source = (Node)event.getSource() ;
                    Integer colIndex = GridPane.getColumnIndex(source);
                    Integer rowIndex = GridPane.getRowIndex(source);
                    draw(activeBoat, colIndex, rowIndex);  
                }
                event.consume();
            }
        };
        return mousePositionHandler;
    }
    
    /**
     * Draws and updates the boat's position
     * @param boat The boat that will be drawn.
     * @param colIndex The grid column of its position.
     * @param rowIndex The grid row of tis position.
     */
    protected void draw(BoatDrawing boat, Integer colIndex, Integer rowIndex){
        // if boat have rotation, we must deplace boat 
        
        // offset is value to move rotation from center of boat to first case
        /*
             ___ ___ ___ ___ ___   
            |___|___|_*_|___|___| normal point of rotation
             ___ ___ ___ ___ ___   
            |_*_|___|___|___|___| our point of rotation
        
            (nb case-1)/2
        
        */
        float offset = boat.isRotation()? ((float) boat.getBoatSize()-1)/2:0;
            
        float positionX = GRID_X + SPACE + GRID_ELEMENT_SIZE*(colIndex - offset);
        float positionY = GRID_Y + SPACE + GRID_ELEMENT_SIZE*(rowIndex + offset);
            
        boat.getBoatRectangle().relocate(positionX, positionY);
        boat.setPosition(colIndex, rowIndex);
        if(positionCorrect(boat)) {
           boat.getBoatRectangle().setFill(boat.getActiveColor());            
           logMsg(EXPLAIN_PLACEMENT);
        } else {
           logMsg("invalid position");
           boat.getBoatRectangle().setFill(boat.getBadPlacementColor());
        }
    }
       
    /**
     * Rotates the active boat when the user press R and deletes the active boat
     * when the user presses delete.
     * @return keyEeventHandler The handler of the event (Users types any key).
     */  
    protected EventHandler<KeyEvent> playKeyEvent() {
        EventHandler<KeyEvent> keyEventHandler;
        keyEventHandler = new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                // Rotates the boat.
                if (keyEvent.getCode() == KeyCode.R) {
                    if(rotationIsValide){
                        if(activeBoat!=null){
                            drawRotation(activeBoat);                            
                        }
                    }   
                }
                // Relocates the boat to origin.
                if (keyEvent.getCode() == KeyCode.DELETE) {
                   if(activeBoat!=null) {
                       reinitBoat(activeBoat);
                       // enable validate button if all boats are well placed
                       valider.setDisable(!allBoatsArePlaced());
                   }
                   // enable validate button if all boats are well placed
                   valider.setDisable(!allBoatsArePlaced());
                }
                keyEvent.consume();
            }
        };
        return keyEventHandler;
    }
                  
    /**
     * Rotates the boat with 90° and updates the view.
     * @param boat The boat that will be rotated
     */
    protected void drawRotation(BoatDrawing boat){
        boat.setRotation(!boat.isRotation());
        Rectangle rectangleBoat = boat.getBoatRectangle();
        rectangleBoat.setRotate(rectangleBoat.getRotate()+90);
        draw(boat,boat.getGridCol(),boat.getGridRow());
    }
    
    /**
     * Relocates the boat in the initial position and deactives it.
     * @param boat The boat that will be relocated and deactivated.
     */
    protected void reinitBoat(BoatDrawing boat) {
        boat.reinit();
        Rectangle myRectangle = boat.getBoatRectangle();
        myRectangle.setRotate(0);
        myRectangle.relocate(boat.getInitialLayoutX(), boat.getInitialLayoutY());
        desactiveBoat();
    }        
    
    /**
     * Checks if the user cans put the boat at the selected position.
     * @param activeBoat The active boat that will have its positions checked.
     * @return True if the position is correct.
     */
    protected boolean positionCorrect(BoatDrawing activeBoat) {
        // Gets the active rectangle's bound
        Rectangle activeRectangle = activeBoat.getBoatRectangle();
        Bounds boundR1 = activeRectangle.getBoundsInParent();

        // Tries to find any intersection with the other boats.
        for (Rectangle myRectangle : boatMap.keySet()) {
            if(activeRectangle.equals(myRectangle)) {} else {
                Bounds boundR2 = myRectangle.getBoundsInParent();
                if (boundR1.intersects(boundR2)) {
                    return false ;
                }
            }
        } 
        // Checks the boat's position in relation to the grid.
        int index ;
        if(activeBoat.isRotation()) {
            index= activeBoat.getGridRow();
        } else {
            index= activeBoat.getGridCol();
        }

        return activeBoat.getBoatSize()+index <= NB_CASES_GRID ;        
    }
   
    /**
     * Unactivates the boat when it is placed over the grid.
     * @return mousePressGridHandler The handler of the event (Click over the grid).
     */    
    protected EventHandler<MouseEvent> MousePlaceBoat() {
        EventHandler<MouseEvent> mousePressGridHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    if(activeBoat!=null){
                        placeBoat(activeBoat);
                    }
                }
                event.consume();
            }
        };
        return mousePressGridHandler;
    }
    
    protected void placeBoat(BoatDrawing myBoat) {
        if(positionCorrect(myBoat)) {
            myBoat.setPlaced(true);
            desactiveBoat(); 
            // enable validate button if all boats are well placed
            valider.setDisable(!allBoatsArePlaced());
        }
    }
           
    /**
     * Desactivates the active boat.
     */
    protected void desactiveBoat() {
        if (activeBoat!=null) {
            activeBoat.setActive(false);
            activeBoat.getBoatRectangle().setMouseTransparent(false);
            activeBoat.getBoatRectangle().setFill(activeBoat.getDisactiveColor());
            activeBoat=null;
            closeMsg();
        } 
    }
    
    public void setPlacementTime(Integer placementTime){
        if (placementTime != null) {
            this.timePlacement = LocalTime.MIN.plusSeconds(placementTime);
            this.time = timePlacement ;
            // update timerLabel
            timerLabel.setText(time.toString().substring(3));
            timeline = new Timeline();
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler() {
                // KeyFrame event handler
                @Override
                public void handle(Event event) {
                    // update timerLabel
                    time = time.minusSeconds(1);
                    timerLabel.setText(time.toString().substring(3));
                    if (time.isBefore(LocalTime.MIN.plusSeconds(10))) {
                        timerLabel.setTextFill(Color.RED);
                    }
                    if (time.isBefore(LocalTime.MIN.plusSeconds(1)) ) {
                        timeline.stop();
                        timeIsOver();
                }
                }
            }));
            timeline.playFromStart();
        }
    }

    /**
     * Places the boats randomly if time's over and there are boats to place
     */
    protected void timeIsOver(){
        for(BoatDrawing myBoat : boatMap.values()){
            while(!myBoat.isPlaced()){
                activeBoat=myBoat;
                Random rn = new Random(); 
                draw(activeBoat, rn.nextInt(NB_CASES_GRID), rn.nextInt(NB_CASES_GRID));
                if(rn.nextInt(RANDOM_ROTATION)==1){
                    drawRotation(activeBoat);
                }                
                if(positionCorrect(myBoat)){
                    this.placeBoat(myBoat);
                }
           }
        }
        onValidate();
    }

    /**
     * @return the isValidate
     */
    public boolean isIsValidate() {
        return isValidate;
    }

    /**
     * @param isValidate the isValidate to set
     */
    public void setIsValidate(boolean isValidate) {
        this.isValidate = isValidate;
    }

    public AnchorPane getChatPane() {
        return this.chatPane;
    }
}

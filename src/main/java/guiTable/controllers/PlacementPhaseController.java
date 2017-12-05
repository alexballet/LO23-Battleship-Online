/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package guiTable.controllers;


import guiTable.BoatDrawing;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import packageStructDonnées.Boat;

/**
 *
 * @author caioz
 */
public abstract class PlacementPhaseController {
       
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private GridPane table;
    @FXML
    private Button valider;
    @FXML
    private AnchorPane chatPane;
    @FXML
    private AnchorPane profilePane;
        
    protected static final int GRID_X = 100;
    protected static final int GRID_Y = 100;
    protected static final int SPACE = 3;
    protected static final int GRID_ELEMENT_SIZE = 35;
    protected static final int NB_CASES_GRID = 10;
    
    protected boolean rotationIsValide;
    protected BoatDrawing activeBoat;
    
    protected HashMap<Rectangle, BoatDrawing> boatMap;
    
    
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
     * @throws Exception
     */
    public void initialize(URL location, ResourceBundle resources){
        
        FXMLLoader loader;
//        loader = fillElement(chatPane, "/fxml/IhmTable/chat.fxml" );
//        ChatController chatController = loader.getController();
//        chatController.init();

        loader = fillElement(profilePane, "/fxml/IhmTable/profile.fxml" );
        ProfileController profileController = loader.getController();
        profileController.init();
        
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
        table.setOnMousePressed(placeBoat());
        table.setOnMouseEntered(enableRotation());
        table.setOnMouseExited(disableRotation());
        // This probably will change after the addition of the chat.
        anchorPane.addEventHandler(KeyEvent.KEY_PRESSED, playKeyEvent());
        
        rotationIsValide = false;
    }
    
    
    /**
    * fillChatSlot() allows external class to fill the chatPane and get the ChatController
    * @param chatFxmlUrl
    * @return chatController
    */        
    public ChatController fillChatSlot(String chatFxmlUrl){
        FXMLLoader loader;
        loader = fillElement(chatPane, chatFxmlUrl );
        ChatController chatController = loader.getController();
        chatController.init();
        return chatController;
    }
        
    /**
     * Trigger validation of placement phase
     */
    @FXML
    protected void onValidate() {
        List<Boat> boats = this.getBoats();
        // TODO: Call the coordinateShips(boats) function in Data interface, once the arguement is changed by Data team
    }
    
    /**
     * Convert the boatMap of type HashMap<Rectangle, BoatDrawing> to List<Boat>
     * @return List<Boat> list of boats
     */
    protected List<Boat> getBoats() {
        List<Boat> boats = new ArrayList(this.boatMap.size());
        for(BoatDrawing boatDraw : boatMap.values()) {
            boats.add(boatDraw.getBoat());
        }
        
        return boats;
    }
    
        
    /**
     * Check if all boats are placed
     * (ie. every boats are on the gird)
     * @return boolean true if all boats are placed, false else
     */
    protected boolean allBoatsArePlaced() {
        boolean allBoatsArePlaced = true;
        for(BoatDrawing boat : boatMap.values()) {
            if (!boat.isPlaced()) {
                allBoatsArePlaced = false;
            }
        }
        
        return allBoatsArePlaced;
    }
    
    
     /**
     * Allows to replace pane by another one
     * @param paneToFill
     * @param contentAdress
     * @return FXMLLoader
     * @throws Exception 
     */
    private FXMLLoader fillElement(AnchorPane paneToFill, String contentAddress) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(contentAddress));
        try{
            AnchorPane contentPane = loader.load();
            paneToFill.getChildren().add(contentPane);
            
            //System.out.println(contentAddress + " " + paneToFill);
        }
        catch(Exception e){
            System.err.println(contentAddress + " " + paneToFill + " " + e.getMessage());
        }
        return loader;
    }
    
    
    
     /**
     * Actives the boat when the user clicks on it.
     * @return mousePressHandler The handler of the event (Click over the boat).
     */   
    protected EventHandler<MouseEvent> activateBoat() {
        EventHandler<MouseEvent> mousePressHandler = new EventHandler<MouseEvent>() {        
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    //If the user has clicked in the window and no other boat is already selected
                    if (activeBoat == null) {
                        Rectangle myRectangle =(Rectangle) event.getSource();
                        BoatDrawing myboat  = boatMap.get(myRectangle);
                        activeBoat = myboat.setActiveBoat(boatMap);
                        activeBoat.setPlaced(false);
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
        boat.setGridCol(colIndex);
        boat.setGridRow(rowIndex);
        if(positionCorrect(boat)) {
           boat.getBoatRectangle().setFill(boat.getActiveColor());            
        } else {
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
    protected EventHandler<MouseEvent> placeBoat() {
        EventHandler<MouseEvent> mousePressGridHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    if(activeBoat!=null){
                        if(positionCorrect(activeBoat)) {
                            activeBoat.setPlaced(true);
                            desactiveBoat(); 
                            // enable validate button if all boats are well placed
                            valider.setDisable(!allBoatsArePlaced());
                        }
                    }
                }
                event.consume();
            }
        };
        return mousePressGridHandler;
    }
           
    /**
     * Deactivates the active boat.
     */
    protected void desactiveBoat() {
        if (activeBoat!=null) {
            activeBoat.setActive(false);
            activeBoat.getBoatRectangle().setMouseTransparent(false);
            activeBoat.getBoatRectangle().setFill(activeBoat.getDisactiveColor());
            activeBoat=null;
        } 
    }
}

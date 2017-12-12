package guiTable;

import data.CDataTable;
import javafx.stage.Stage;
import structData.Boat;
import structData.ChatMessage;
import structData.Shot;


/**
 * Interface for the Ihm Table team
 * @author corentinhembise
 */
public interface GuiTableInterface {
    
    /**
     * Displays the window where the player will place their ships.
     * @param currentStage
     * @param classic 
     * @param placementTime : Time limitation for placement phase, if null, no time limtation
     * @throws Exception 
     */
    public void displayPlacementPhase(Stage currentStage, Boolean classic, Integer placementTime) throws Exception;
    
    /**
     * Notifies the IHM Table that the opponent is ready so that it can adapt its views.
     * @param myTurn
     * @param roundTime : Time limitation for each round game, if null, no time round limitation
     */
    public void opponentReady(Boolean myTurn, Integer roundTime);

    /**
     * Notifies the IHM Table that the opponent is ready so that it can adapt its views.
     * Without round time limitation
     * This method exist to avoid BC compatibilities
     * @param myTurn
     */
    public void opponentReady(Boolean myTurn);
    
    /**
     * Displays view where the user can watch a game.
     */
    public void displayObserverPhase();

    /**
     * Displays a victory message, and allow player to return to IHM Main.
     */
    public void displayVictory();

    /**
     * Displays a defeat message, and allow player to return to IHM Main.
     */
    public void displayDefeat();
    
    /**
     * Displays the result of the opponent shot on the board.
     * @param opponentShot : The shot sent by the opponent.
     * @param boat : If filled, indicates this 'boat' is sunk.
     */
    public void displayOpponentShot(Shot opponentShot, Boat boat);

    /**
     * Displays the result of the user shot on the board.
     * @param myShotResult : The result of the shot sent by the user.
     * @param boat : If filled, indicates this 'boat' is sunk.
     */
    public void displayMyShotResult(Shot myShotResult, Boat boat);

    /**
     * Displays a new incoming message on chat window.
     * @param message : New incoming message to display.
     */
    public void addChatMessage(ChatMessage message);

    /**
     * Displays an error or notification message in main window.
     * @param messageType : Error type to show
     * @param message : Error message to show.
     */
    //public void displayMessage(MessageType messageType, String message);
    public void displayMessage(String message);
    
    public void setDataController(CDataTable d);

}

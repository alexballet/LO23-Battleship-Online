package guiTable;

import data.CDataTable;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
     * @param currentStage current stage to be replaced
     * @param classic specify if it is a classic or belgian game
     * @param timePerShot : Time limitation for placement phase, if null, no time limtation
     * @throws Exception 
     */
    public void displayPlacementPhase(Stage currentStage, Boolean classic, int timePerShot) throws Exception;
    
    /**
     * Notifies the IHM Table that the opponent is ready so that it can adapt its views.
     * Without round time limitation
     * This method exist to avoid BC compatibilities
     * @param myTurn specify if it is the local player turn
     */
    public void opponentReady(Boolean myTurn, int time);
    
    /**
     * Displays view where the user can watch a game.

     * @param currentStage : The stage used by the main window
     * @param turn : Turn of the players. True 
     * @param shotsDoneByPlayer1 : All shots done by the player 1 before the entry of the observer.
     * @param shotsDoneByPlayer2 : All shots done by the player 2 before the entry of the observer.
     */
    public void displayObserverPhase(Stage currentStage, final boolean turn, LinkedHashMap<Shot,Boat> shotsDoneByPlayer1,LinkedHashMap<Shot,Boat> shotsDoneByPlayer2);
    
    /**
     * Displays the result of the player 1's shot on the player 2's board.
     * @param shot : The shot sent by the player 1.
     * @param boat : If filled, indicates this 'boat' is sunk.
     */
    public void displayPlayer1Shot(Shot shot, Boat boat);

    /**
     * Displays the result of the player 2's shot on the player 1's board.
     * @param shot : The shot sent by the player 2.
     * @param boat : If filled, indicates this 'boat' is sunk.
     */
    public void displayPlayer2Shot(Shot shot, Boat boat);
    
    /**
     * Displays the victory of one of the players in the observer phase
     * @param winner : Boolean that represents the winner. True if it is the player 1, False if it is the player 2
     */     
    public void displayObserverPhaseVictory(boolean winner);   

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
     * @param message : Error message to show.
     */
    //public void displayMessage(MessageType messageType, String message);
    public void displayMessage(String message);
    
    public void setDataController(CDataTable d);

}

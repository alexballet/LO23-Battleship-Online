package guiTable;

import data.CDataTable;
import javafx.stage.Stage;
import structData.Boat;
import structData.ChatMessage;
import structData.Game;
import structData.Shot;


/**
 * Interface for the Ihm Table team
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
     * @param time the time
     */
    public void opponentReady(Boolean myTurn, long time);
    
    /**
     * Displays view where the user can watch a game.

     * @param currentStage : The stage used by the main window
     * @param game
     */
    public void displayObserverPhase(Stage currentStage, Game game);
    
        /**
     * Displays the result of the shot send by player number player.
     * @param shot : The shot sent by the player 1.
     * @param player : the player who send the shot
     */
    public void displayObserverShot(Shot shot, int player);

    /**
     * Displays the victory of one of the players in the observer phase
     * @param winner the position of the winner
     */
    public void displayObserverPhaseVictory(int winner);

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
     * Set the data controller
     * @param d the data controller
     */
    public void setDataController(CDataTable d);

    /**
     * Display a rage quit message
     */
    public void displayRageQuit();

    /**
     * Update the spectator game
     * @param g the game tu update
     */
    public void updateSpectatorGame(Game g);

    /**
     * Sunk the player boat
     * @param i
     * @param boat
     */
    public void sunkPlayerBoat(int i, Boat boat);

}

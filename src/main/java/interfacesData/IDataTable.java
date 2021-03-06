package interfacesData;
import structData.Position;
import structData.Boat;
import java.util.List;
import structData.Game;

/**
 * Data's interface for IHM-Table
 */
public interface IDataTable {
    /**
    * Function to exit.
    * @return : 1 if the game was successfully closed and return 0 if not.
    */
    public Boolean  exit();

    /**
    * Add the message to the chat in the current game.
    * @param message : The main part of message that the player wants to send.
    */
    public void textMessage(String message);

    /**
     * Point out the position of shot.
     * @param pos : The position of shot. 
     */
    public void coordinate(Position pos);

    /**
     * Point out the the boats that players 
     * place at the beginning of game.
     * @param listBoat : List of boats. 
     */
    public void coordinateShips(List<Boat> listBoat);

    /* Not used because the robot user was abandonned.
    /**
     * IA that choose a shot. 
     * @return the chosen Shot
    
    public Shot iaShot();
    */
    
    public void timerOver();
    
    /**
     * Transmit to every user that the game is beign played, allow them to spectate
     */
    public void changeStatusGameStarted();
    
    /**
     *  Accessor for the observed game
     * @return the game observed
     */
    public Game getObserverGame();
    
    /**
     * To end a game
     */
    public void gameEnded();
    
    /**
     * 
     * @return the local game
     */
    public Game getLocalGame();
}
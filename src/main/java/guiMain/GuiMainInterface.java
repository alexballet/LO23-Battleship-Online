package guiMain;
import structData.*;

/**
 * GUI Main Interface
 * @author aureliedigeon
 */
public interface GuiMainInterface {
	 /**
	  * Adds the user passed as a parameter to the list of users.
	  * @param user : user to add to the list.
	  */
	public void addUser(User user);
	
	/**
	 * Adds the game passed as a parameter in the list of games displayed.
	 * @param createdGame : game to be added to the list of games.
	 */
	public void addGame(Game createdGame);

	/**
	 * Displays the statistics of a player
	 * @param profil : profile of the player.
	 */
	public void sendStatistics(Profile profil);

	/**
	 * Updates the status of the game.
	 * @param game : Game is updated status.
	 */
	public void transmitNewStatus(Game game);

	/**
	 * Updates according to the response to the request to join the game.
	 * @param isOk : true : the request is accepted, false : the request is reject.
	 */
	public void setGameJoinResponse(boolean isOk);
	
}

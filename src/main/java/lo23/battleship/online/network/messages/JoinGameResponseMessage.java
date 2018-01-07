package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Game;
import structData.Profile;

import java.net.InetAddress;


/**
 * This class implements the message which is sent when
 * when a creator of a game adds a second player to the game.
 *
 * The creator of the game sends this message to the user who was added as second player.
 *
 * This class extends the abstract Message and implements the two abstract methods:
 * <code>getType</code> and <code>process</code>
 *
 * @see Message
 * @author COM Module
 */
public class JoinGameResponseMessage extends Message{

    private Game game;
    private Profile sender;
    private Boolean isOk;

    /**
     * Allocates a new {@code JoinGameResponseMessage} object.
     * @param isOk is the answer  depending on whether the opponent slot is available for the distant player willing to
     *             join (true : the slot is available, false : the slot isn't available).
     * @param sender is the User class of the local player.
     * @param game is the game which is willed to be joined by the distant player.
     */
    public JoinGameResponseMessage(Boolean isOk, Profile sender, Game game){
        this.game = game;
        this.isOk = isOk;
        this.sender = sender;
        this.type = "JoinGameResponseMessage";
    }

    /**
     * Returns the type of the message.
     * Implementation of an abstract method.
     * @return type : {@code String}
     */
    public String getType() {
        return type;
    }

    /**
     * Notifies the user receiving this message that he has (or has not) joined the game.
     * @param IData : {@code IDataCom}
     *              instance of IDataCom interface.
     * @param senderAddress : {@code InetAddress}
     *                      sender's IP address
     */
    public void process(IDataCom IData, InetAddress senderAddress){
        if (isOk) {
            IData.setLocalGame(game);
        } else {
            IData.setGameJoinResponse(false);
        }
    }
}

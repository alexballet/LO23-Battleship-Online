package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Game;

import java.net.InetAddress;

/**
 * This class implements the message sent when a game is removed from the network
 * (and the list of games) by his creator(user) or when the game is over.
 *
 * This class extends the abstract Message and implements the two abstract methods:
 * <code>getType</code> and <code>process</code>
 *
 * @see Message
 * @author COM Module
 */
public class GameQuitMessage extends Message{
    private Game game;
    public GameQuitMessage(Game game) {
        this.game = game;
        this.type = "GameQuitMessage";
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
     * Remove the game <code>game</code> from the list of games using data package interface <code>IData</code>
     * and the NetworkController : removes the disconnected user from those 2 collections
     * @param IData : {@code IDataCom}
     *              instance of IDataCom interface.
     * @param senderAddress : {@code InetAddress}
     *                      sender's IP address
     */
    public void process(IDataCom IData, InetAddress senderAddress) {
        IData.removeGame(game);
    }
}

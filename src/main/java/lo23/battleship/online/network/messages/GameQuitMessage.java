package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Game;

import java.net.InetAddress;

/**
 * Network class message, extends from class Message.
 * Message send when the user quit the game
 * @author xzirva
 */
public class GameQuitMessage extends Message{
    private Game game;

    /**
     * Class constructor.
     * @param game is the game quit.
     */
    public GameQuitMessage(Game game) {
        this.game = game;
        this.type = "GameQuitMessage";
    }

    /**
     * Message type getter. Implementation of an abstract method.
     * @return type, this is the message type.
     */
    public String getType() {
        return type;
    }

    /**
     * Method calling method from data in order to remove the game
     * @param IData interface with Data.
     * @param senderAddress sender IP address.
     */
    public void process(IDataCom IData, InetAddress senderAddress) {
        IData.removeGame(game);
    }
}

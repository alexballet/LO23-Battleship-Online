package lo23.battleship.online.network.messages;

import structData.Game;
import interfacesData.IDataCom;
import java.net.InetAddress;

/**
 * This class implements the message sent by the local user to other users
 * when a game (namely the status of the game) has changed.
 *
 * @see Message
 * @author COM Module
 */

public class UpdateGameMessage extends Message{

    private Game gameUpdate;

    /**
     * Allocates a new {@code UpdateGameMessage} object
     * @param game : {@code Message}
     *             game which has been updated.
     */
    public UpdateGameMessage(Game game){
        this.gameUpdate = game;
        this.type = "UpdateGameMessage";
    }

    /**
     * Message type getter. Implementation of an abstract method.
     * @return type, this is the message type.
     */
    public String getType() {
        return type;
    }

    /**
     * Provides the updated game to Data.
     * @param IData : {@code IDataCom}
     *              instance of IDataCom interface.
     * @param senderAddress : {@code InetAddress}
     *                      sender's IP address
     */
    public void process(IDataCom IData, InetAddress senderAddress) {
        IData.changeStatusGame(gameUpdate);
    }

}

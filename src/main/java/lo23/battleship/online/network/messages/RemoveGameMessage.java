package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.ChatMessage;
import structData.Game;

import java.net.InetAddress;

/**
 * SendTextMessage, descendant Message, send (and receive message also because it's the same attribute) chat message to (from) away ap.
 * @author Lejeune Lola
 */

public class RemoveGameMessage extends Message{

    private Game game;

    /**
     * Class constructor.
     * @param game is the game to remove
     */
    public RemoveGameMessage(Game game){
        this.type = "RemoveGameMessage";
        this.game = game;

    }

    /**
     * Message type getter. Implementation of an abstract method.
     * @return type, this is the message type.
     */
    public String getType() {
        return type;
    }

    /**
     * Method calling method from data in order to remove a game
     * @param IData interface with Data.
     * @param senderAddress sender IP address.
     */
    public void process(IDataCom IData, InetAddress senderAddress){
        // TODO décommenter quand on aura methode de data
        //IData.removeGameFromList(game);
    }
}

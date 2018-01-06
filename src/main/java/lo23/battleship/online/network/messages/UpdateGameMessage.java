package lo23.battleship.online.network.messages;

import structData.Game;
import interfacesData.IDataCom;
import java.net.InetAddress;

/**
 * Network message class, extends Message class.
 * Message sent to Data when an game is updated.
 * @author COM Module
 */

public class UpdateGameMessage extends Message{

    Game gameUpdate;

    /**
     * Class constructor.
     * @param game is the Game which has been updated.
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
     * Method providing the updated game to Data.
     * @param IData interface with Data.
     */
    public void process(IDataCom IData, InetAddress senderAddress) {
        System.out.println("game: "+ gameUpdate);
        IData.changeStatusGame(gameUpdate);
    }

}

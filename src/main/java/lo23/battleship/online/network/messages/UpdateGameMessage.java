package lo23.battleship.online.network.messages;

import structData.Game;
import interfacesData.IDataCom;
import java.net.InetAddress;

/**
 * UpdateGame,descendant class Message, send a update game to Data in order to register the modification
 * @author Lejeune Lola ==DESCRIPTION NEEDED==
 */

public class UpdateGameMessage extends Message{

    Game gameUpdate;

    /**
     * Class constructor.
     * @param game ==DESCRIPTION NEEDED==
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
     * ==DESCRIPTION NEEDED==
     * @param IData interface with Data.
     */
    public void process(IDataCom IData){
        System.out.println("game: "+ gameUpdate);;
        IData.changeStatusGame(gameUpdate);
       }

    /**
     * Unused method for this class.
     * @param IData interface with Data.
     * @param senderAddress sender IP address.
     */
    public void process(IDataCom IData, InetAddress senderAddress){}
}

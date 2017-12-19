package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Game;
import structData.User;

import java.net.InetAddress;

/**
 * SendInfoGameForSpectatorMessage,descendant class Message, localuser send the game to the spectator.
 * @author Lejeune Lola
 */

public class SendInfoGameForSpectatorMessage extends Message{

    Game game;
    User spec;

    /**
     * Class constructor.
     * @param g is the game send to the spectator
     * @param s is the spectator who are going to receive the game
     */
    public SendInfoGameForSpectatorMessage(Game g, User s){
        this.type = "SendInfoGameForSpectatorMessage";
        this.game = g;
        this.spec = s;
    }

    /**
     * Message type getter. Implementation of an abstract method.
     * @return type, this is the message type.
     */
    public String getType() {
        return type;
    }

    /**
     * Method calling method from data in order to send the game to the new spectator
     * @param IData interface with Data.
     * @param senderAddress sender IP address.
     */
    public void process(IDataCom IData, InetAddress senderAddress){
        // TODO decommenter quand dans interface data
        //IData.joinGameSpectator(game)
    }
}

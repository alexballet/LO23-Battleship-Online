package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Game;
import structData.User;

import java.net.InetAddress;

/**
 * This class implements the message sent when the local user
 * has been accepted as spectator.
 * The local user receives this message with the current state(shots and sunk boats etc. ...)
 * of the game.
 *
 * @see Message
 * @author COM Module
 */

public class SendInfoGameForSpectatorMessage extends Message{

    Game game;
    User spec;
    public SendInfoGameForSpectatorMessage(Game g, User s){
        this.type = "SendInfoGameForSpectatorMessage";
        this.game = g;
        this.spec = s;
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
     * Sets the initial state of the game the spectator(local user) is watching
     * using the data package interface <code>IData</code>

     * @param IData : {@code IDataCom}
     *              instance of IDataCom interface.
     * @param senderAddress : {@code InetAddress}
     *                      sender's IP address
     */
    public void process(IDataCom IData, InetAddress senderAddress){
        IData.joinGameSpectator(game);
    }
}

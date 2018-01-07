package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Game;
import structData.Player;
import structData.User;

import java.net.InetAddress;
import java.util.HashSet;

/**
 * This class implements the message sent when a user
 * has been accepted as spectator by the creator of the game.
 * The message is sent to all the current spectators and the other player to notify them of
 * the new spectator's arrival.
 *
 * @see Message
 * @author COM Module
 */

public class SendNewSpectatorMessage extends Message {

    private User spec;
    /**
     * Allocates a new {@code SendNewSpectatorMessage} object
     * @param s : {@code User}:
     *          new spectator
     * */
    public SendNewSpectatorMessage(User s) {
        this.type = "SendNewSpectatorMessage";
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
     * Adds the spectator to the list of spectators of the game
     * using the data package interface <code>IData</code>

     * @param IData : {@code IDataCom}
     *              instance of IDataCom interface.
     * @param senderAddress : {@code InetAddress}
     *                      sender's IP address
     */
    public void process(IDataCom IData, InetAddress senderAddress){
        IData.notifyToSpecGame(spec);
    }
}

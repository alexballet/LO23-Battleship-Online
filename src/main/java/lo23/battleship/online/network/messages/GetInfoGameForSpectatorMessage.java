package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Player;
import structData.User;

import java.net.InetAddress;

/**
 * This class implements the message which is sent when
 * when a user wants to watch a game
 *
 * The user sends this message to the creator of the game
 *
 * This class extends the abstract Message and implements the two abstract methods:
 * <code>getType</code> and <code>process</code>
 *
 * @see Message
 * @author COM Module
 */

public class GetInfoGameForSpectatorMessage extends Message{

    Player player;
    User spec;

    public GetInfoGameForSpectatorMessage(Player p, User s){
        this.type = "GetInfoGameForSpectatorMessage";
        this.player = p;
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
     * Adds the user as spectator to the game (if possible)
     * using the data package interface <code>IData</code>

     * @param IData : {@code IDataCom}
     *              instance of IDataCom interface.
     * @param senderAddress : {@code InetAddress}
     *                      sender's IP address
     */
    public void process(IDataCom IData, InetAddress senderAddress){
        IData.newRequestSpectator(spec);
    }
}

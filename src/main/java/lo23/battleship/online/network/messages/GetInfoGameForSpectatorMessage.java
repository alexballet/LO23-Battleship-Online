package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Player;
import structData.User;

import java.net.InetAddress;
import java.util.HashSet;

/**
 * GetInfoGameForSpectatorMessage,descendant class Message, spectator ask the game.
 * @author Lejeune Lola
 */

public class GetInfoGameForSpectatorMessage extends Message{

    Player player;
    User spec;

    /**
     * Class constructor.
     * @param p is the player who send the information to the spectator
     * @param s spectator
     */
    public GetInfoGameForSpectatorMessage(Player p, User s){
        this.type = "GetInfoGameForSpectatorMessage";
        this.player = p;
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
     * Method calling method from data for a spectator who ask to join a game
     * @param IData interface with Data.
     * @param senderAddress sender IP address.
     */
    public void process(IDataCom IData, InetAddress senderAddress){
       // TODO decommenter quand dans interface data
        //IData.newRequestSpectator(spec)
    }
}

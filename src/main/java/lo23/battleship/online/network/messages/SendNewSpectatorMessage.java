package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Game;
import structData.Player;
import structData.User;

import java.net.InetAddress;
import java.util.HashSet;

/**
 * SendNewSpectatorMessage,descendant class Message, notify everybody there is a new spectator.
 * @author Lejeune Lola
 */

public class SendNewSpectatorMessage extends Message {

    Player player;
    User spec;
    HashSet<User> listS;

    /**
     * Class constructor.
     * @param s is the new spectator
     */
    public SendNewSpectatorMessage(User s) {
        this.type = "SendNewSpectatorMessage";
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
     * Method calling method from data in order to add a spectator in listSpectator and notif
     * @param IData interface with Data.
     * @param senderAddress sender IP address.
     */
    public void process(IDataCom IData, InetAddress senderAddress){
        // TODO decommenter quand dans interface data
        //IData.notifyToSpecGame(User spec)
    }
}

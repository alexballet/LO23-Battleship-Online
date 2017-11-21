package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.User;

import java.net.InetAddress;

/**
 * Disconnection, descendant class Message, disconnection message.
 * When the user click on the disconnection button or on the cross
 * @author Lejeune Lola
 */


/* ajouter les méthodes une fois qu'elles seront faites*/

public class DisconnectionMessage extends Message{

    User user;

    public DisconnectionMessage(User userWhoDisconnect){
        this.user = userWhoDisconnect;
        this.type = "DisconnectionMessage";
    }

    public String getType() {
        return type;
    }

    public void process(IDataCom IData){}

    public void process(IDataCom IData, InetAddress senderAddress){}

}

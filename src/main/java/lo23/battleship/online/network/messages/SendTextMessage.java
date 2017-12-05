package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.ChatMessage;
import java.net.InetAddress;

/**
 * SendTextMessage, descendant Message, send (and receive message also because it's the same attribute) chat message to (from) away ap.
 * @author Lejeune Lola ==DESCRIPTION NEEDED==
 */

public class SendTextMessage extends Message{

    ChatMessage message;

    /**
     * Class constructor.
     * @param m ==DESCRIPTION NEEDED==
     */
    public SendTextMessage(ChatMessage m){
        this.type = "SendTextMessage";
        this.message = m;

    }

    /**
     * Message type getter. Implementation of an abstract method.
     * @return type, this is the message type.
     */
    public String getType() {
        return type;
    }

    /**
     * Unused method for this class.
     * @param IData interface with Data.
     */
    public void process(IDataCom IData){}

    /**
     * ==DESCRIPTION NEEDED==
     * @param IData interface with Data.
     * @param senderAddress sender IP address.
     */
    public void process(IDataCom IData, InetAddress senderAddress){
        IData.receiveMessage(message);

    }
}

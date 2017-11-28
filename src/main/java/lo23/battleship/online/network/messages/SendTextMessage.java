package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.ChatMessage;
import java.net.InetAddress;

/**
 * SendTextMessage, descendant Message, send (and receive message also because it's the same attribute) chat message to (from) away ap.
 * @author Lejeune Lola
 */

public class SendTextMessage extends Message{

    ChatMessage message;

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

    public void process(IDataCom IData){}

    public void process(IDataCom IData, InetAddress senderAddress){
        IData.receiveMessage(message);

    }
}

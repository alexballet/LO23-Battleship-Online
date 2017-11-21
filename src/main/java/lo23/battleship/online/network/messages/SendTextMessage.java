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

    public String getType() {
        return type;
    }

    public void process(IDataCom IData){}

    public void process(IDataCom IData, InetAddress senderAddress){
        IData.receiveMessage(message);

    }
}

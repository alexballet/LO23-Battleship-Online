package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.ChatMessage;
import java.net.InetAddress;

/**
 * Network message class, extends Message class.
 * Textual message sent/received by the local user to/from a distant user.
 * Note: sent and received because this is the same attribute.
 * @author COM Module
 */

public class SendTextMessage extends Message{

    ChatMessage message;

    /**
     * Class constructor.
     * @param m textual message needed to be sent.
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
     * Method providing the textual message to Data.
     * @param IData interface with Data.
     * @param senderAddress sender IP address.
     */
    public void process(IDataCom IData, InetAddress senderAddress){
        IData.receiveMessage(message);

    }
}

package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.ChatMessage;
import java.net.InetAddress;

/**
 * This class implements textual messages sent/received by
 * the local user to/from a distant user.
 *
 * @see Message
 * @author COM Module
 */
public class SendTextMessage extends Message{

    ChatMessage message;

    /**
     * Allocates a new {@code SendTextMessage} object
     * @param m : {@code ChatMessage}:
     *          textual message to send.
     */
    public SendTextMessage(ChatMessage m){
        this.type = "SendTextMessage";
        this.message = m;
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
     * Method providing the textual message to data package interface.
     * @param IData : {@code IDataCom}
     *              instance of IDataCom interface.
     * @param senderAddress : {@code InetAddress}
     *                      sender's IP address
     */
    public void process(IDataCom IData, InetAddress senderAddress){
        IData.receiveMessage(message);

    }
}

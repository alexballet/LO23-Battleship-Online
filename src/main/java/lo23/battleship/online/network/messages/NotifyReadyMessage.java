package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.User;

import java.net.InetAddress;

/**
 * This class implements the message sent by the local user (player) to the other player
 * when he is ready to play.
 *
 * @see Message
 * @author COM Module
 */


public class NotifyReadyMessage extends Message {

    private User sender;
    private User destUser;

    /**
     * Allocates a new {@code NotifyReadyMessage} object
     * @param sender: {@code User}
     *                sender of the message
     * @param destUser: {@code User}
     *                receiver of the message
     * */
    public NotifyReadyMessage(User sender, User destUser){
        this.type = "NotifyReadyMessage";
        this.destUser = destUser;
        this.sender = sender;
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
     * Notifies data package interface that the other player (sender of this message)
     * is ready
     * @param IData : {@code IDataCom}
     *              instance of IDataCom interface.
     * @param senderAddress : {@code InetAddress}
     *                      sender's IP address
     */
    public void process(IDataCom IData, InetAddress senderAddress) {
        IData.receiveReady();
    }
}

package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.User;

import java.net.InetAddress;

/**
 * NotifyReadyMessage,descendant class Message, the away user is ready to play. Send the information to Data.
 * @author Lejeune Lola
 */


public class NotifyReadyMessage extends Message {

    private User sender;
    private User destUser;

    /**
     * Class constructor.
     * @param sender is the user who send the notification
     * @param destUser is the user who is going to receive the notification
     */
    public NotifyReadyMessage(User sender, User destUser){
        this.type = "NotifyReadyMessage";
        this.destUser = destUser;
        this.sender = sender;
    }

    /**
     * Message type getter. Implementation of an abstract method.
     * @return type, this is the message type.
     */
    public String getType() {
        return type;
    }

    /**
     * Method calling method from data in order to inform that a player is ready to play
     * @param IData interface with Data.
     * @param senderAddress sender IP address.
     */
    public void process(IDataCom IData, InetAddress senderAddress) {
        System.out.println(getType() + ": Player " + sender.getUsername() + " is ready." );
        IData.receiveReady();
    }
}

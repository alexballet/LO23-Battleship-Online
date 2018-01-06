package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.User;

import java.net.InetAddress;

/**
 * NotifyReadyMessage,descendant class Message, the away user is ready to play. Send the information to Data.
 * @author COM Module
 */


public class NotifyReadyMessage extends Message {

    private User sender;
    private User destUser;

    public NotifyReadyMessage(User sender, User destUser){
        this.type = "NotifyReadyMessage";
        this.destUser = destUser;
        this.sender = sender;
    }


    public String getType() {
        return type;
    }

    public void process(IDataCom IData, InetAddress senderAddress) {
        System.out.println(getType() + ": Player " + sender.getUsername() + " is ready." );
        IData.receiveReady();
    }
}

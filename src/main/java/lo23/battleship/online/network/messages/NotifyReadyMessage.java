package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;

import java.net.InetAddress;

/**
 * NotifyReadyMessage,descendant class Message, the user is ready to play. Send the information to Data.
 * @author Lejeune Lola
 */


public class NotifyReadyMessage extends Message {

    public NotifyReadyMessage(){
        this.type = "NotifyReadyMessage";
    }


    public String getType() {
        return type;
    }

    public void process(IDataCom IData) {
        IData.receiveReady();
    }

    public void process(IDataCom IData, InetAddress senderAddress) {}
}

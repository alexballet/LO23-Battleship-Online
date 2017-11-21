package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import java.net.InetAddress;

/**
 * Disconnection, descendant class Message, disconnection message.
 * When the user click on the disconnection button or on the cross
 * @author Lejeune Lola
 */


/* ajouter les méthodes une fois qu'elles seront faites*/

public class Disconnection extends Message{

    public Disconnection(){
        this.type = "Disconnection";
    }

    public String getType() {
        return type;
    }

    public void process(IDataCom IData){}

    public void process(IDataCom IData, InetAddress senderAddress){}

}

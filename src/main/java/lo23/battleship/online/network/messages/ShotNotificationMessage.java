package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Shot;


import java.net.InetAddress;

/**
 * ShotNotificationMessage,descendant class Message, send shot.
 * @author Lejeune Lola
 */

public class ShotNotificationMessage extends Message{
        Shot shot;

    public ShotNotificationMessage(Shot s) {
        this.shot = s;
        this.type = "ShotNotificationMessage";}

    public String getType() {
        return type;
    }

    public void process(IDataCom IData){}

    public void process(IDataCom IData, InetAddress senderAddress){
        IData.coordinates(shot);
    }
}

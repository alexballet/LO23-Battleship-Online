package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Boat;
import structData.Shot;


import java.net.InetAddress;

/**
 * ShotNotificationMessage,descendant class Message, receive shot with result on boat.
 * @author Lejeune Lola
 */


public class ShotNotificationResultMessage extends Message{
    Shot shot;
    Boat boat;

    public ShotNotificationResultMessage(Shot s, Boat b) {
        this.shot = s;
        this.boat = b;
        this.type = "ShotNotificationResultMessage";}

    public String getType() {
        return type;
    }

    public void process(IDataCom IData, InetAddress senderAddress){
        
        IData.coordinates(shot, boat);
    }



}

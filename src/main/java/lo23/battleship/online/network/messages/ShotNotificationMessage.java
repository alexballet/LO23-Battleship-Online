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

    /**
     * Class constructor.
     * @param s is the shot send.
     */
    public ShotNotificationMessage(Shot s) {
        this.shot = s;
        this.type = "ShotNotificationMessage";}

    /**
     * Message type getter. Implementation of an abstract method.
     * @return type, this is the message type.
     */
    public String getType() {
        return type;
    }

    /**
     * Method calling method from data in order to send a shot
     * @param IData interface with Data.
     * @param senderAddress sender IP address.
     */
    public void process(IDataCom IData, InetAddress senderAddress){
        IData.coordinates(shot);
        System.out.println( "send shot: " + shot.getX() + ";"+ shot.getY());
    }



}

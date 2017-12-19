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

    /**
     * Class constructor.
     * @param s is the shot receive
     * @param b is the boat shot (if there is a bot shot)
     */
    public ShotNotificationResultMessage(Shot s, Boat b) {
        this.shot = s;
        this.boat = b;
        this.type = "ShotNotificationResultMessage";}

    /**
     * Message type getter. Implementation of an abstract method.
     * @return type, this is the message type.
     */
    public String getType() {
        return type;
    }

    /**
     * Method calling method from data in order to receive the traitement after a shot was send
     * @param IData interface with Data.
     * @param senderAddress sender IP address.
     */
    public void process(IDataCom IData, InetAddress senderAddress){
        
        IData.coordinates(shot, boat);
    }



}

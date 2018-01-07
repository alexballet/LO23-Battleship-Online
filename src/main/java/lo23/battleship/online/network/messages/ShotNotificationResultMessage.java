package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Boat;
import structData.Shot;


import java.net.InetAddress;

/**
 * This class implements the message sent by the local user (player) to the other player
 * with the result of the other player's shot (missed, touched or sunk boat).
 *
 * @see Message
 * @author COM Module
 */

public class ShotNotificationResultMessage extends Message{
    private Shot shot;
    private Boat boat;

    /**
     * Allocates a new {@code ShotNotificationResultMessage} message
     * @param s : {@code Shot}
     *          the shot made by the other player made
     *
     * @param b : {@code Boat}
     *          the boat sunk by the other player
     *          (null if no boat has been sunk)
     * */
    public ShotNotificationResultMessage(Shot s, Boat b) {
        this.shot = s;
        this.boat = b;
        this.type = "ShotNotificationResultMessage";
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
     * Provides the shot result and the boat to data package interface
     * to process it (display it eventually).
     * @param IData : {@code IDataCom}
     *              instance of IDataCom interface.
     * @param senderAddress : {@code InetAddress}
     *                      sender's IP address
     */
    public void process(IDataCom IData, InetAddress senderAddress){
        IData.coordinates(shot, boat);
    }



}

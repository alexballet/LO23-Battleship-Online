package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Shot;


import java.net.InetAddress;

/**
 * This class implements the message sent by the local user (player) when he shots.
 *
 * @see Message
 * @author COM Module
 */

public class ShotNotificationMessage extends Message{
    private Shot shot;

    /**
     * Allocates a new {@code ShotNotificationMessage} object
     * @param s : {@code Shot}
     *          the shot of the local user(player)
     * */
    public ShotNotificationMessage(Shot s) {
        this.shot = s;
        this.type = "ShotNotificationMessage";
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
     * Method providing the shot to data package interface
     * to process the shot(missed, touched or sunk boat)
     * @param IData : {@code IDataCom}
     *              instance of IDataCom interface.
     * @param senderAddress : {@code InetAddress}
     *                      sender's IP address
     */
    public void process(IDataCom IData, InetAddress senderAddress){
        IData.coordinates(shot);
    }
}

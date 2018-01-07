package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Boat;
import structData.Player;
import structData.Shot;

import java.net.InetAddress;

/**
 * This class implements the message sent by the local user (player) to the spectators
 * with the result of the other player shot (missed, touched or sunk boat).
 *
 * @see Message
 * @author COM Module
 */


public class ShotNotificationResultForSpectatorMessage extends Message{
    private Shot shot;
    private Boat boat;
    private Player player;

    /**
     * Allocates a new {@code ShotNotificationResultForSpectatorMessage} message
     * @param p : {@code Player}
     *          Player who shots
     * @param s : {@code Shot}
     *          the shot made by the <code>player</code>
     *
     * @param b : {@code Boat}
     *          the boat sunk by the <code>player</code>
     *          (null if no boat has been sunk)
     * */
    public ShotNotificationResultForSpectatorMessage(Player p, Shot s, Boat b) {
        this.shot = s;
        this.boat = b;
        this.player = p;
        this.type = "ShotNotificationResultForSpectatorMessage";
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
     * Provides the shot result to data package interface
     * to process it (display it eventually).
     * @param IData : {@code IDataCom}
     *              instance of IDataCom interface.
     * @param senderAddress : {@code InetAddress}
     *                      sender's IP address
     */
    public void process(IDataCom IData, InetAddress senderAddress){
            IData.updateAttendedGame(player, shot, boat);
    }



}

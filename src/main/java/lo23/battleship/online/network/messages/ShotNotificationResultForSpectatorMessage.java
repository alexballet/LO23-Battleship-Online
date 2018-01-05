package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Boat;
import structData.Player;
import structData.Shot;

import java.net.InetAddress;

/**
 * ShotNotificationMessage,descendant class Message, receive shot with result on boat.
 * @author Lejeune Lola
 */


public class ShotNotificationResultForSpectatorMessage extends Message{
    Shot shot;
    Boat boat;
    Player player;
    boolean gameOver;

    public ShotNotificationResultForSpectatorMessage(Player p, Shot s, Boat b) {
        this.shot = s;
        this.boat = b;
        this.player = p;
        this.type = "ShotNotificationResultForSpectatorMessage";}

    public String getType() {
        return type;
    }

    public void process(IDataCom IData, InetAddress senderAddress){
            IData.updateAttendedGame(player, shot, boat);
    }



}

package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Boat;
import structData.Player;
import structData.Profile;
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

    public ShotNotificationResultForSpectatorMessage(Player p, Shot s, Boat b, boolean gameOver) {
        this.shot = s;
        this.boat = b;
        this.player = p;
        this.gameOver = gameOver;
        this.type = "ShotNotificationResultForSpectatorMessage";}

    public String getType() {
        return type;
    }

    public void process(IDataCom IData, InetAddress senderAddress){
        Profile playerProfile = player.getProfile();
        Profile localProfile = IData.getUserProfile();
        if(localProfile.getIdUser().equals(playerProfile)) {
            IData.updateAttendedGame(player, shot, boat, gameOver);
        }
    }



}

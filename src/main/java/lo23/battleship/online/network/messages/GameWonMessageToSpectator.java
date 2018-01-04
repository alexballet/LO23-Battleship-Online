package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Player;

import java.net.InetAddress;

/**
 * GameWonMessage,descendant class Message, notify the player if he win.
 * @author Lejeune Lola
 */

public class GameWonMessageToSpectator extends Message{

    Player player;

    public GameWonMessageToSpectator(Player p){
        this.type = "GameWonMessageToSpectator";
        this.player = p;
    }

    public String getType() {
        return type;
    }


    public void process(IDataCom IData, InetAddress senderAddress){
        IData.notifyAttendedGameWon(player);
    }



}

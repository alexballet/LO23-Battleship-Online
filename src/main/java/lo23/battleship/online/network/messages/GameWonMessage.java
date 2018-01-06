package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Player;
import java.net.InetAddress;

/**
 * GameWonMessage,descendant class Message, notify the player if he win.
 * @author COM Module
 */

public class GameWonMessage extends Message{

    Player player;

    public GameWonMessage(Player p){
        this.type = "GameWonMessage";
        this.player = p;
    }

    public String getType() {
        return type;
    }


    public void process(IDataCom IData, InetAddress senderAddress){
        IData.notifiedGameWon();
    }



}

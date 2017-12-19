package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Player;
import structData.User;

import java.net.InetAddress;

/**
 * GetInfoGameForSpectatorMessage,descendant class Message, spectator ask the game.
 * @author Lejeune Lola
 */

public class GetInfoGameForSpectatorMessage extends Message{

    Player player;
    User spec;
    public GetInfoGameForSpectatorMessage(Player p, User s){
        this.type = "GetInfoGameForSpectatorMessage";
        this.player = p;
        this.spec = s;
    }

    public String getType() {
        return type;
    }


    public void process(IDataCom IData, InetAddress senderAddress){
       // TODO decommenter quand dans interface data
        //IData.newRequestSpectator(spec)
    }
}
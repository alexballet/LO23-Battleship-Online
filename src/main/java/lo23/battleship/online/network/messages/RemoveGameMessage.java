package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.ChatMessage;
import structData.Game;

import java.net.InetAddress;

/**
 * SendTextMessage, descendant Message, send (and receive message also because it's the same attribute) chat message to (from) away ap.
 * @author Lejeune Lola
 */

public class RemoveGameMessage extends Message{

    private Game game;

    public RemoveGameMessage(Game game){
        this.type = "RemoveGameMessage";
        this.game = game;

    }

    public String getType() {
        return type;
    }

    public void process(IDataCom IData){
    }

    public void process(IDataCom IData, InetAddress senderAddress){
        //IData.removeGameFromList(game);
    }
}

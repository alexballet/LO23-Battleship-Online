package lo23.battleship.online.network.messages;

import structData.Game;
import interfacesData.IDataCom;
import java.net.InetAddress;

/**
 * UpdateGame,descendant class Message, send a update game to Data in order to register the modification
 * @author Lejeune Lola
 */

public class UpdateGameMessage extends Message{

    Game gameUpdate;

    public UpdateGameMessage(Game game){
        this.gameUpdate = game;
        this.type = "UpdateGameMessage";
    }

    public String getType() {
        return type;
    }

    public void process(IDataCom IData, InetAddress senderAddress) {
        System.out.println("game: "+ gameUpdate);
        IData.changeStatusGame(gameUpdate);
    }

}

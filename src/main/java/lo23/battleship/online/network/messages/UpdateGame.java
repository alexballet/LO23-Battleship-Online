package lo23.battleship.online.network.messages;

import structData.Game;
import interfacesData.IDataCom;
import java.net.InetAddress;

/**
 * UpdateGame,descendant class Message, send a update game to Data in order to register the modification
 * @author Lejeune Lola
 */

public class UpdateGame extends Message{

    Game gameUpdate;

    public UpdateGame(Game game){
        this.gameUpdate = game;
        this.type = "UpdateGame";
    }

    public String getType() {
        return type;
    }

    public void process(IDataCom IData){
        IData.changeStatusGame(gameUpdate);
       }

    public void process(IDataCom IData, InetAddress senderAddress){}
}

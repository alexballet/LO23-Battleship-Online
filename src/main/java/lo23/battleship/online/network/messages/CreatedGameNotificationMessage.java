package lo23.battleship.online.network.messages;

import structData.Game;
import interfacesData.IDataCom;
import java.net.InetAddress;

/**
 * CreatedGameNotification,descendant class Message, create a game notification and send the new game to data
 * @author Lejeune Lola
 */

public class CreatedGameNotificationMessage extends Message{

    Game game;

    public CreatedGameNotificationMessage(Game gameCreated) {
        this.game = gameCreated;
        this.type = "CreatedGameNotificationMessage";}

    public String getType() {
        return type;
    }

    public void process(IDataCom IData){
        IData.addNewGameList(game);
    }

    public void process(IDataCom IData, InetAddress senderAddress){}

}


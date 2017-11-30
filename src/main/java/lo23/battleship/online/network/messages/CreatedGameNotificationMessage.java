package lo23.battleship.online.network.messages;

import com.sun.org.apache.xpath.internal.SourceTree;
import structData.Game;
import interfacesData.IDataCom;
import structData.User;

import java.net.InetAddress;

/**
 * CreatedGameNotification,descendant class Message, create a game notification and send the new game to data
 * @author Lejeune Lola
 */

public class CreatedGameNotificationMessage extends Message{
    Game game;

    public CreatedGameNotificationMessage(Game gameCreated) {
        this.game = gameCreated;
        this.type = "CreatedGameNotification";}

    public String getType() {
        return type;
    }

    public void process(IDataCom IData, InetAddress senderAddress){
        System.out.println("Game received" + game.getName() + " from " + game.getPlayer1().getProfile().getLogin());
        IData.addNewGameList(game);
    }


}


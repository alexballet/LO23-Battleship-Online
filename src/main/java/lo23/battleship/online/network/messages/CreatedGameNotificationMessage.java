package lo23.battleship.online.network.messages;

import com.sun.org.apache.xpath.internal.SourceTree;
import structData.Game;
import interfacesData.IDataCom;
import structData.User;

import java.net.InetAddress;

/**
 * Network message class, extends Message class.
 * Message notifying Data a new game has been created by the sender.
 */
public class CreatedGameNotificationMessage extends Message{
    User user;
    Game game;

    /**
     * Class constructor.
     * @param sender is the User class of the network message sender.
     * @param gameCreated game created by the sender. The receiver will be able to fill its displayed game list.
     */
    public CreatedGameNotificationMessage(User sender, Game gameCreated) {
        this.user = sender;
        this.game = gameCreated;
        this.type = "CreatedGameNotificationMessage";
    }

    /**
     * Message type getter. Implementation of an abstract method.
     * @return type, this is the message type.
     */
    public String getType() {
        return type;
    }

    /**
     * Method updating Data with the new created game.
     * @param IData interface with Data.
     */
    public void process(IDataCom IData){
        System.out.println("Game received" + game.getName() + " from " + user.getLogin());
        IData.addNewGameList(game);
    }

    /**
     * Unused method for this class.
     * @param IData interface with Data.
     * @param senderAddress sender IP address.
     */
    public void process(IDataCom IData, InetAddress senderAddress){ }

}


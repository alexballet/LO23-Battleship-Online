package lo23.battleship.online.network.messages;

import structData.Game;
import interfacesData.IDataCom;

import java.net.InetAddress;

/**
 * Network message class, extends Message class.
 * Message notifying Data a new game has been created by the sender.
 */
public class CreatedGameNotificationMessage extends Message{
    Game game;

    /**
     * Class constructor.
     * @param gameCreated game created by the sender. The receiver will be able to fill its displayed game list.
     */
    public CreatedGameNotificationMessage(Game gameCreated) {

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
     * Unused method for this class.
     * @param IData interface with Data.
     * @param senderAddress sender IP address.
     */
    public void process(IDataCom IData, InetAddress senderAddress){
        System.out.println("Game received " + game.getName() + " from " + game.getPlayer1().getProfile().getLogin());
        IData.addNewGameList(game);
    }


}


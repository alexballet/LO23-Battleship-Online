package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Game;
import structData.Player;
import structData.User;
import java.net.InetAddress;

/**
 * GameWonMessage,descendant class Message, notify the player if he win.
 * @author Lejeune Lola
 */

public class GameWonMessage extends Message{

    Player player;

    /**
     * Class constructor.
     * @param p is the player who won.
     */
    public GameWonMessage(Player p){
        this.type = "GameWonMessage";
        this.player = p;
    }

    /**
     * Message type getter. Implementation of an abstract method.
     * @return type, this is the message type.
     */
    public String getType() {
        return type;
    }

    /**
     * Method calling method from data in order to notify the player who won the game
     * @param IData interface with Data.
     * @param senderAddress sender IP address.
     */
    public void process(IDataCom IData, InetAddress senderAddress){
        IData.notifiedGameWon();
    }



}

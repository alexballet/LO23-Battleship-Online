package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Player;
import java.net.InetAddress;

/**
 * This class implements the message which is sent when
 * a game is over with a victory of one of the players.
 *
 * The loser sends this message to the winner.
 *
 * This class extends the abstract Message and implements the two abstract methods:
 * <code>getType</code> and <code>process</code>
 *
 * @see Message
 * @author COM Module
 */

public class GameWonMessage extends Message{

    Player player;

    public GameWonMessage(Player p){
        this.type = "GameWonMessage";
        this.player = p;
    }

    /**
     * Returns the type of the message.
     * Implementation of an abstract method.
     * @return type : {@code String}
     */
    public String getType() {
        return type;
    }


    /**
     * Notifies the other player that he has won and update the data corresponding
     * the game using the data package interface <code>IData</code>

     * @param IData : {@code IDataCom}
     *              instance of IDataCom interface.
     * @param senderAddress : {@code InetAddress}
     *                      sender's IP address
     */
    public void process(IDataCom IData, InetAddress senderAddress){
        IData.notifiedGameWon();
    }



}

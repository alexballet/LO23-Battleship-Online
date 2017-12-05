package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Game;
import structData.User;
import java.net.InetAddress;

/**
 * JoinGameRequest,descendant class Message, received a game request and send info to data.
 * @author Lejeune Lola ==DESCRIPTION NEEDED==
 */

public class JoinGameRequestMessage extends Message{

    User sender;
    Game game;

    /**
     * Class construtor.
     * @param senderGame ==DESCRIPTION NEEDED==
     * @param gameSend ==DESCRIPTION NEEDED==
     */
    public JoinGameRequestMessage(User senderGame, Game gameSend){
        this.sender = senderGame;
        this.game = gameSend;
        this.type = "JoinGameRequestMessage";}

    /**
     * Message type getter. Implementation of an abstract method.
     * @return type, this is the message type.
     */
    public String getType() {
        return type;
    }

    /**
     * ==DESCRIPTION NEEDED==
     * @param IData interface with Data.
     */
    public void process(IDataCom IData){
        IData.notifToJoinGame(sender, game);
    }

    /**
     * Unused method for this class.
     * @param IData interface with Data.
     * @param senderAddress sender IP address.
     */
    public void process(IDataCom IData, InetAddress senderAddress){ }



}




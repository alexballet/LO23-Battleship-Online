package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Game;
import structData.Profile;
import java.net.InetAddress;

/**
 * This class implements the message which is sent when
 * when a user wants to join a game created by another user.
 *
 * This class extends the abstract Message and implements the two abstract methods:
 * <code>getType</code> and <code>process</code>
 *
 * @see Message
 * @author COM Module
 */
public class JoinGameRequestMessage extends Message{

    Profile sender;
    Game game;

    /**
     * Allocates a new {@code JoinGameRequestMessage} object.
     * @param senderGame :
     *                   the User who wants to join the game.
     * @param gameSend :
     *                 the game that the user <code>senderGame</code> wants to join.
     */
    public JoinGameRequestMessage(Profile senderGame, Game gameSend){
        this.sender = senderGame;
        this.game = gameSend;
        this.type = "JoinGameRequestMessage";}

    /**
     * Returns the type of the message.
     * Implementation of an abstract method.
     * @return type : {@code String}
     */
    public String getType() {
        return type;
    }

    /**
     * Add the user <code>sender</code> to the game if possible using IDataCom interface
     * @param IData : {@code IDataCom}
     *              instance of IDataCom interface.
     * @param senderAddress : {@code InetAddress}
     *                      sender's IP address
     */
    public void process(IDataCom IData, InetAddress senderAddress){
        IData.notifToJoinGame(sender, game);
    }




}




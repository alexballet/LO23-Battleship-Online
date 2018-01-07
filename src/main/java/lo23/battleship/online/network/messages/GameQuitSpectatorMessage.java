package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Game;
import structData.User;

import java.net.InetAddress;

/**
 * This class implements the message which is sent when
 * a spectator quit a game he is watching.
 *
 * This class extends the abstract Message and implements the two abstract methods:
 * <code>getType</code> and <code>process</code>
 *
 * @see Message
 * @author COM Module
 */
public class GameQuitSpectatorMessage extends Message{
    private Game game;
    private User spec;

    public GameQuitSpectatorMessage(Game g, User s){
        this.type = "GameQuitSpectatorMessage";
        this.spec = s;
        this.game =g;
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
     * execute the process associated to the GameQuitSpectator message
     * It notifies the <code>data</code> interface of users who received this message that the spectator
     * <code>spec</code> has quit watching the game <code>game</code>
     * @param IData : {@code IDataCom}
     *              instance of IDataCom interface
     * @param senderAddress : {@code InetAddress}
     *                      IP address of the sender
     * */
    public void process(IDataCom IData, InetAddress senderAddress){
        IData.notifyQuitSpectator(spec);
    }
}
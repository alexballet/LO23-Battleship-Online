package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Game;
import structData.User;

import java.net.InetAddress;

/**
 * GameQuitSpectatorMessage,descendant class Message, spectator quit a game.
 * @author Lejeune Lola
 */

public class GameQuitSpectatorMessage extends Message{
    Game game;
    User spec;

    /**
     * Class constructor.
     * @param g is the game quit by the spectator
     * @param s is the spectator who quit the game
     */
    public GameQuitSpectatorMessage(Game g, User s){
        this.type = "GameQuitSpectatorMessage";
        this.spec = s;
        this.game =g;
    }

    /**
     * Message type getter. Implementation of an abstract method.
     * @return type, this is the message type.
     */
    public String getType() {
        return type;
    }

    /**
     * Method calling method from data in order to notify when a spectator quit the game
     * @param IData interface with Data.
     * @param senderAddress sender IP address.
     */
    public void process(IDataCom IData, InetAddress senderAddress){
        // TODO avoir la méthode de data à appeler
        // methode de data à appeler
        //IData.notifyQuitSpectator(User spec);
    }
}
package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Game;
import structData.User;

import java.net.InetAddress;

/**
 * JoinGameSpectatorMessage,descendant class Message, spectator join a game.
 * @author Lejeune Lola
 */

public class JoinGameSpectatorMessage extends Message{

    Game game;
    User spec;
    public JoinGameSpectatorMessage(Game g, User s){
        this.type = "JoinGameSpectatorMessage";
        this.game = g;
        this.spec = s;
    }

    public String getType() {
        return type;
    }


    public void process(IDataCom IData, InetAddress senderAddress){
        // TODO avoir la méthode de data à appeler
        // methode de data à appeler
        //IData.truc()
    }
}

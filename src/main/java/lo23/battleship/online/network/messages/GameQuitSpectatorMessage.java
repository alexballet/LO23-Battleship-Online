package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Game;
import structData.User;

import java.net.InetAddress;

/**
 * GameQuitSpectatorMessage,descendant class Message, spectator quit a game.
 * @author COM Module
 */

public class GameQuitSpectatorMessage extends Message{
    Game game;
    User spec;
    public GameQuitSpectatorMessage(Game g, User s){
        this.type = "GameQuitSpectatorMessage";
        this.spec = s;
        this.game =g;
    }

    public String getType() {
        return type;
    }


    public void process(IDataCom IData, InetAddress senderAddress){
        IData.notifyQuitSpectator(spec);
    }
}
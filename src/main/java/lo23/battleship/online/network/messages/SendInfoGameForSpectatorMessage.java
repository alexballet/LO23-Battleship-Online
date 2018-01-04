package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Game;
import structData.User;

import java.net.InetAddress;

/**
 * SendInfoGameForSpectatorMessage,descendant class Message, localuser send the game to the spectator.
 * @author Lejeune Lola
 */

public class SendInfoGameForSpectatorMessage extends Message{

    Game game;
    User spec;
    public SendInfoGameForSpectatorMessage(Game g, User s){
        this.type = "SendInfoGameForSpectatorMessage";
        this.game = g;
        this.spec = s;
    }

    public String getType() {
        return type;
    }


    public void process(IDataCom IData, InetAddress senderAddress){
        System.out.println("game : "+ game);
        System.out.println("player 1 : "+ game.getPlayer1());
        System.out.println("player 2 : "+ game.getPlayer2());
        
        System.out.println("listshot player 1 : "+ game.getPlayer1().getListShots());
        
        IData.joinGameSpectator(game);
    }
}

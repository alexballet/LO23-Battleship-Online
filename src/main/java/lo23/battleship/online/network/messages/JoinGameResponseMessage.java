package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Game;
import structData.Profile;
import structData.User;

import java.net.InetAddress;

/**
 * JoinGameRequest,descendant class Message, received a game request and send to data.
 * @author Lejeune Lola
 */

public class JoinGameResponseMessage extends Message{

    Game game;
    Profile sender;
    Boolean isOk;

    public JoinGameResponseMessage(Boolean isOk, Profile sender, Game game){
        this.game = game;
        this.isOk = isOk;
        this.sender = sender;
        this.type = "JoinGameResponseMessage";
    }

    public String getType() {
        return type;
    }

    public void process(IDataCom IData, InetAddress senderAddress) {
        System.out.println("Response received for game " + game.getName() + " from " + senderAddress.toString());

        if (isOk) {
            IData.setLocalGame(g);
        }
    }
}

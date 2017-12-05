package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Game;
import structData.Profile;
import structData.User;
import java.net.InetAddress;

/**
 * JoinGameRequest,descendant class Message, received a game request and send info to data.
 * @author Lejeune Lola
 */

public class JoinGameRequestMessage extends Message{

    Profile sender;
    Game game;

    public JoinGameRequestMessage(Profile senderGame, Game gameSend){
        this.sender = senderGame;
        this.game = gameSend;
        this.type = "JoinGameRequestMessage";}

    public String getType() {
        return type;
    }

    public void process(IDataCom IData, InetAddress senderAddress){
        IData.notifToJoinGame(sender, game);
    }



}




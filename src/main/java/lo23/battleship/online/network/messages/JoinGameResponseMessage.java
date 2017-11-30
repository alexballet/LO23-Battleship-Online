package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Game;
import structData.User;

import java.net.InetAddress;

/**
 * JoinGameRequest,descendant class Message, received a game request and send to data.
 * @author Lejeune Lola
 */

public class JoinGameResponseMessage extends Message{

    User sender;
    Game game;

    public JoinGameResponseMessage(User senderGame, Game gameSend){
        this.sender = senderGame;
        this.game = gameSend;
        this.type = "JoinGameRequest";}

    public String getType() {
        return type;
    }

    public void process(IDataCom IData, InetAddress senderAddress){
        System.out.println("Response received for game " + game.getName() + " from " + senderAddress.toString());
        IData.notifToJoinGame(sender, game);
    }



}




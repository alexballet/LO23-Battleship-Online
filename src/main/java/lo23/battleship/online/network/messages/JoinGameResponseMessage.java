package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Game;
import structData.User;
import java.net.InetAddress;

/**
 * JoinGameRequest,descendant class Message, respond to a game request and send to into to data.
 * @author Lejeune Lola
 */


public class JoinGameResponseMessage extends Message{

    User receiver;
    Game game;

    public JoinGameResponseMessage(User receiverGame, Game gameReceived){
        this.receiver = receiverGame;
        this.game = gameReceived;
        this.type = "JoinGameResponseMessage";

    }

    public void process(IDataCom IData){


    }

    public void process(IDataCom IData, InetAddress senderAddress){}


}

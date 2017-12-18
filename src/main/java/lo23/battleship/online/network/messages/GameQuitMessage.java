package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Game;

import java.net.InetAddress;

/**
 * Created by xzirva on 04/12/17.
 */
public class GameQuitMessage extends Message{
    private Game game;
    public GameQuitMessage(Game game) {
        this.game = game;
        this.type = "GameQuitMessage";
    }

    public String getType() {
        return type;
    }
    public void process(IDataCom IData, InetAddress senderAddress) {
        IData.removeGame(game);
    }
}

package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Game;

import java.net.InetAddress;

/**
 * @author COM Module
 */
public class RageQuitMessage extends Message{
    private Game game;
    public RageQuitMessage(Game game) {
        this.game = game;
        this.type = "RageQuitMessage";
    }

    public String getType() {
        return type;
    }
    public void process(IDataCom IData, InetAddress senderAddress) {
        // TODO : uncomment this when ready integV5
        //IData.quitGame(game);
    }
}

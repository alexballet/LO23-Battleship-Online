package lo23.battleship.online.network.messages;

import structData.Game;
import interfacesData.IDataCom;
import java.net.InetAddress;

public class CreatedGameNotification extends Message{

    Game game;

    public CreatedGameNotification(Game gameCreated) {
        this.game = gameCreated;
        this.type = "CreatedGameNotification";}

    public String getType() {
        return type;
    }

    public void process(IDataCom IData){
        IData.addNewGameList(game);
    }

    public void process(IDataCom IData, InetAddress senderAddress){}

}


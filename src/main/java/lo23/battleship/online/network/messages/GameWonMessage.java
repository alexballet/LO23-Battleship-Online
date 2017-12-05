package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Game;
import structData.User;
import java.net.InetAddress;

/**
 * GameWonMessage,descendant class Message, notify the player if he win.
 * @author Lejeune Lola
 */

public class GameWonMessage extends Message{

    public GameWonMessage(User senderGame, Game gameSend){
        this.type = "GameWonMessage";}

    public String getType() {
        return type;
    }

    public void process(IDataCom IData){
        // TO DO decommenter quand méthode sera ajoutée par data
        //IData.notifiedGameWon()
    }

    public void process(IDataCom IData, InetAddress senderAddress){
    }



}

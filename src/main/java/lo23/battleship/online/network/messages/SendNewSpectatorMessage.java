package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Game;
import structData.Player;
import structData.User;

import java.net.InetAddress;
import java.util.HashSet;

/**
 * SendNewSpectatorMessage,descendant class Message, notify everybody there is a new spectator.
 * @author Lejeune Lola
 */

public class SendNewSpectatorMessage extends Message {

    Player player;
    User spec;
    HashSet<User> listS;
    public SendNewSpectatorMessage(User s) {
        this.type = "SendNewSpectatorMessage";
        this.spec = s;
    }

    public String getType() {
        return type;
    }


    public void process(IDataCom IData, InetAddress senderAddress){
        // TODO decommenter quand dans interface data
        IData.notifyToSpecGame(spec);
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("SHHHHIIIIITTT! : " + IData.getCreatedGame().getListSpectators().size());
        System.out.println("NEWWWWWWWW: SPEC: " + spec.getUsername());
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("############################################");
    }
}

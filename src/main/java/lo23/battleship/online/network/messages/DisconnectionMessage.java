package lo23.battleship.online.network.messages;

import com.sun.org.apache.bcel.internal.generic.NEW;
import interfacesData.IDataCom;
import lo23.battleship.online.network.NetworkController;
import structData.User;

import java.net.InetAddress;
import java.util.Arrays;

/**
 * Disconnection, descendant class Message, disconnection message.
 * When the user click on the disconnection button or on the cross
 * @author Lejeune Lola
 */


/* ajouter les méthodes une fois qu'elles seront faites*/

public class DisconnectionMessage extends Message{

    User user;

    public DisconnectionMessage(User userWhoDisconnect){
        this.user = userWhoDisconnect;
        this.type = "DisconnectionMessage";
    }

    public String getType() {
        return type;
    }

    public void process(IDataCom IData, InetAddress senderAddress){
        System.out.println("User: " + user + " has disconnected");
        NetworkController controller = NetworkController.getInstance();
        controller.removeFromNetwork(user);
        System.out.println("Remaining Users:");
        System.out.println(Arrays.toString(controller.getConnectedUsers().toArray()));
        System.out.println(Arrays.toString(controller.getIPTable().toArray()));
    }

}

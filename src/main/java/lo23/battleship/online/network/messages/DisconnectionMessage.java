package lo23.battleship.online.network.messages;

import com.sun.org.apache.bcel.internal.generic.NEW;
import interfacesData.IDataCom;
import lo23.battleship.online.network.NetworkController;
import structData.User;

import java.net.InetAddress;
import java.util.Arrays;

/**
 * Network message class, extends from Message class.
 * Disconnection message.
 * Sent when the user click on one of the disconnection buttons or HMI elements.
 */


/* ajouter les méthodes une fois qu'elles seront faites*/

public class DisconnectionMessage extends Message{

    User user;

    /**
     * Class constructor.
     * @param userWhoDisconnect is the User class of the player who is disconnecting from the app.
     */
    public DisconnectionMessage(User userWhoDisconnect){
        this.user = userWhoDisconnect;
        this.type = "DisconnectionMessage";
    }

    /**
     * Message type getter. Implementation of an abstract method.
     * @return type, this is the message type.
     */
    public String getType() {
        return type;
    }

    /**
     * Unused method for this class.
     * @param IData interface with Data.
     */
    public void process(IDataCom IData){ }

    /**
     *
     * @param IData interface with Data.
     * @param senderAddress sender IP address.
     */
    public void process(IDataCom IData, InetAddress senderAddress){
        System.out.println("User: " + user + " has disconnected");
        NetworkController controller = NetworkController.getInstance();
        controller.removeFromNetwork(user);
        System.out.println("Remaining Users:");
        System.out.println(Arrays.toString(controller.getConnectedUsers().toArray()));
        System.out.println(Arrays.toString(controller.getIPTable().toArray()));
    }

}

package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import lo23.battleship.online.network.NetworkController;
import structData.Game;
import structData.User;

import java.net.InetAddress;
import java.util.Arrays;

/**
 * This class implements the message which is sent when
 * when a user disconnects from the application.
 *
 * The user who wants to disconnect sends this message to all connected users.
 *
 * This class extends the abstract Message and implements the two abstract methods:
 * <code>getType</code> and <code>process</code>
 *
 * @see Message
 * @author COM Module
 */

public class DisconnectionMessage extends Message{

    User user;
    Game game;

    /**
     * Allocates a new {@code DisconnectionMessage} object.
     * @param userWhoDisconnect {@code User}:
     *                                     the user is disconnecting from the application.
     */
    public DisconnectionMessage(User userWhoDisconnect, Game game){
        this.user = userWhoDisconnect;
        this.type = "DisconnectionMessage";
        this.game = game;
    }

    /**
     * Returns the type of the message.
     * Implementation of an abstract method.
     * @return type : {@code String}
     */
    public String getType() {
        return type;
    }


    /**
     * Updates the network state and the list of connected users using data package interface
     * and the NetworkController : removes the disconnected user from those 2 collections
     * @param IData : {@code IDataCom}
     *              instance of IDataCom interface.
     * @param senderAddress : {@code InetAddress}
     *                      sender's IP address
     */
    public void process(IDataCom IData, InetAddress senderAddress){
        System.out.println("User: " + user + " has disconnected");
        NetworkController controller = NetworkController.getInstance();
        controller.removeFromNetwork(user, game);
        System.out.println("Remaining Users:");
        System.out.println(Arrays.toString(controller.getConnectedUsers().toArray()));
        System.out.println(Arrays.toString(controller.getIPTable().toArray()));
    }

}

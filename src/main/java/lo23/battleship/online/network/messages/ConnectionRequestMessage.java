package lo23.battleship.online.network.messages;

import com.sun.xml.internal.bind.v2.model.core.ID;
import interfacesData.IDataCom;
import lo23.battleship.online.network.NetworkController;
import structData.User;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.List;

/** !Doc OK
 * Network message class, extends Message class.
 * Message notifying the sender wants to join a network of P2P users on the application.
 * The sender let the other users know he wants to join the network.
 */
public class ConnectionRequestMessage extends Message {
    private User sender;
    private List<InetAddress> ipAdressesTable; /**< Known IP addresses list */

    /**
     * Class constructor.
     * @param user is the User class of the network message sender.
     * @param ipAdressesTable is the IP addresses list known by the sender.
     */
    public ConnectionRequestMessage(User user, List<InetAddress> ipAdressesTable) {
        this.sender = user;
        this.ipAdressesTable = ipAdressesTable;
        type = "ConnectionRequestMessage";
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
    public void process(IDataCom IData) { }

    /**
     * Method updating the NetworkController and class members accordingly to the message sent.
     * @param IData interface with Data.
     * @param senderAddress sender IP address.
     */
    public void process(IDataCom IData, InetAddress senderAddress) {

        //dataInterface.getIPTableAdresses();
        System.out.println("New message received from: " + senderAddress);
        System.out.println("Message Type: " + type);
        // TODO getCurrentUserGame

        NetworkController controller = NetworkController.getInstance();

        controller.addToNetwork(sender, senderAddress, null);
        System.out.println("1 - " + IData);
        System.out.println("2 - " + controller);

        ConnectionEstablishedMessage connectionEstablishedMessage =
                new ConnectionEstablishedMessage(IData.getLocalUser(), controller.getIPTable(), null);

        controller.sendMessage(connectionEstablishedMessage, senderAddress);
    }
}

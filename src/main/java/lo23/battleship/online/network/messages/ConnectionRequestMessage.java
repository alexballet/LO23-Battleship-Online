package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import lo23.battleship.online.network.NetworkController;
import structData.Game;
import structData.User;

import java.net.InetAddress;
import java.util.List;

/**
 * Network message class, extends Message class.
 * Message sent when the local user wants to join a network of P2P users on the application.
 * The local user (the sender) let the other users know he wants to join the network.
 */
public class ConnectionRequestMessage extends Message {
    private User sender;
    private List<InetAddress> ipAdressesTable; /**< Known IP addresses list */
    private Game game;
    /**
     * Class constructor.
     * @param user is the User class of the network message sender.
     * @param ipAdressesTable is the IP addresses list known by the sender.
     */
    public ConnectionRequestMessage(User user, List<InetAddress> ipAdressesTable, Game game) {
        this.sender = user;
        this.ipAdressesTable = ipAdressesTable;
        this.game = game;
        type = "ConnectionRequestMessage";
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
     * Method updating the NetworkController and class members accordingly to the message sent.
     * @param IData interface with Data.
     * @param senderAddress sender IP address.
     */
    public void process(IDataCom IData, InetAddress senderAddress) {
        NetworkController controller = NetworkController.getInstance();
        controller.addToNetwork(sender, senderAddress, game);

        ConnectionEstablishedMessage connectionEstablishedMessage =
                new ConnectionEstablishedMessage(IData.getLocalUser(), controller.getIPTable(), IData.getCreatedGame());

        controller.sendMessage(connectionEstablishedMessage, senderAddress);
    }
}

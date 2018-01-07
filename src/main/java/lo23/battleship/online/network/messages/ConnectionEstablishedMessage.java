package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import lo23.battleship.online.network.NetworkController;
import structData.Game;
import structData.User;

import java.net.InetAddress;
import java.util.List;

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

public class ConnectionEstablishedMessage extends Message {
    private User sender;
    private List<InetAddress> ipAdressesTable;
    private Game createdGame;

    /**
     * Class constructor
     * @param sender is the User class of the network message sender.
     * @param ipTable is the IP addresses list known by the sender.
     * @param game is the game the sender has created, if he has created a game. Thus, the receiver can fill its
     *             displayed game list once connected to the P2P network.
     */
    ConnectionEstablishedMessage(User sender, List<InetAddress> ipTable, Game game) {
        ipAdressesTable = ipTable;
        createdGame = game;
        this.sender = sender;
        type = "ConnectionEstablishedMessage";
    }

    /**
     * Message type getter. Implementation of an abstract method.
     * @return type, this is the message type.
     */
    public String getType() {
        return type;
    }

    /**
     * Updates the network state using the NetworkController
     * and the list of connected users using the IDataCom interface.
     * Then sends a <code>ConnectionRequestMessage</code> to the IP Addresses that the local user
     * does not know and a {@code ConnectionEstablishedMessage} to the users the sender of the
     * {@code ConnectionEstablishedMessage} does not know
     * @param IData interface with Data.
     * @param senderAddress sender IP address.
     */
    public void process(IDataCom IData, InetAddress senderAddress) {
        NetworkController controller = NetworkController.getInstance();
        controller.addToNetwork(sender, senderAddress, createdGame);

        User user = IData.getLocalUser();
        List<InetAddress> filteredAddresses = controller.filterUnknownIPAddresses(ipAdressesTable);

        for(InetAddress ipAddress : filteredAddresses) {
            ConnectionRequestMessage connectionRequestMessage =
                    new ConnectionRequestMessage(user, controller.getIPTable(), IData.getCreatedGame());
            controller.sendMessage(connectionRequestMessage, ipAddress);
        }

        ipAdressesTable.add(senderAddress); // add the sender address to the P2P network list
        // sender is now a KNOWN address

        filteredAddresses.add(senderAddress); //
        // sender is part of the unknown addresses for users in my network

        List<InetAddress> ipAddressesToNotify = controller.filterKnownIPAddressesToNotify(ipAdressesTable);

        for(InetAddress ipAddress : ipAddressesToNotify) {
            ConnectionEstablishedMessage connectionEstablishedMessage =
                    new ConnectionEstablishedMessage(user, controller.getIPTable(), null);
            controller.sendMessage(connectionEstablishedMessage, ipAddress);
        }
    }
}

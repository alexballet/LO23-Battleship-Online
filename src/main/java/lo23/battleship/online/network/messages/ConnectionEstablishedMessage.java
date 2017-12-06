package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import lo23.battleship.online.network.NetworkController;
import structData.Game;
import structData.User;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;

/**
 * Network message class, extends from Message class.
 * Message sent to provide the list of known IP addresses after receiving a ConnectionRequestMessage from a distant user.
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
    public ConnectionEstablishedMessage(User sender, List<InetAddress> ipTable, Game game) {
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
        System.out.println("New message received from: " + senderAddress);
        System.out.println("Message Type: " + type);

        NetworkController controller = NetworkController.getInstance();
        controller.addToNetwork(sender, senderAddress, createdGame);

        User user = IData.getLocalUser();
        List<InetAddress> filteredAddresses = controller.filterUnknownIPAddresses(ipAdressesTable);

        System.out.println("Network State: " + Arrays.toString(controller.getIPTable().toArray()));
        System.out.println("(Unknown) Filtered addresses: " + Arrays.toString(filteredAddresses.toArray())
         + "\n to " + senderAddress);

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
        System.out.println("(Known) Filtered addresses: " + Arrays.toString(ipAddressesToNotify.toArray())
                + "\n to " + senderAddress);
        for(InetAddress ipAddress : ipAddressesToNotify) {
            ConnectionEstablishedMessage connectionEstablishedMessage =
                    new ConnectionEstablishedMessage(user, controller.getIPTable(), null);
            controller.sendMessage(connectionEstablishedMessage, ipAddress);
        }
    }
}

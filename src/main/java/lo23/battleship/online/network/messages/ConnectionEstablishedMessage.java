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
 * Created by xzirva on 14/11/17.
 */
public class ConnectionEstablishedMessage extends Message {
    private User sender;
    private Inet4Address destinationIPAddress;
    private List<InetAddress> ipAdressesTable;
    private Game createdGame;

    public ConnectionEstablishedMessage(User sender, List<InetAddress> ipTable, Game game) {
        ipAdressesTable = ipTable;
        createdGame = game;
        this.sender = sender;
        type = "ConnectionEstablishedMessage";

    }
    public String getType() {
        return type;
    }
    public void process(IDataCom dataInterface) {
    }

    public void process(IDataCom IData, InetAddress senderAddress) {
        System.out.println("New message received from: " + senderAddress);
        System.out.println("Message Type: " + type);
        NetworkController controller = NetworkController.getInstance();
        controller.updateNetwork(sender, senderAddress, createdGame);
        User user = IData.getLocalUser();
        List<InetAddress> filteredAddresses = controller.filterUnknownIPAddresses(ipAdressesTable);
        for(InetAddress ipAddress : filteredAddresses) {
            ConnectionRequestMessage connectionRequestMessage =
                    new ConnectionRequestMessage(user, controller.getIPTable());
            controller.sendMessage(connectionRequestMessage, ipAddress);
        }

        ipAdressesTable.add(senderAddress);
        List<InetAddress> ipAddressesToNotify = controller.filterKnownIPAddressesToNotify(ipAdressesTable);
        for(InetAddress ipAddress : ipAddressesToNotify) {
            ConnectionEstablishedMessage connectionEstablishedMessage =
                    new ConnectionEstablishedMessage(user, filteredAddresses, null);
            controller.sendMessage(connectionEstablishedMessage, ipAddress);
        }
        System.out.println(Arrays.toString(controller.getIPTable().toArray()));
    }
}

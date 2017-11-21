package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import lo23.battleship.online.network.NetworkController;
import structData.Game;
import structData.User;

import java.net.Inet4Address;
import java.net.InetAddress;
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
        NetworkController controller = NetworkController.getInstance();
        List<InetAddress> filteredAddresses = controller.filterUnknownIPAddresses(ipAdressesTable);
        controller.updateNetwork(sender, senderAddress, createdGame);
        for(InetAddress ipAddress : filteredAddresses) {
            User user = new User();
            user.setUsername("TryingToConnect");
            ConnectionEstablishedMessage connectionEstablishedMessage = new ConnectionEstablishedMessage(user, controller.getIPTable(), null);
            controller.sendMessage(connectionEstablishedMessage, ipAddress);
        }
    }
}

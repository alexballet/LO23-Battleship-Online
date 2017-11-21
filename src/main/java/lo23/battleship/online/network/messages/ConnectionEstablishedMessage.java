package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import lo23.battleship.online.network.NetworkController;
import lo23.battleship.online.network.NetworkModuleInterface;
import structData.DataGame;
import structData.User;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xzirva on 14/11/17.
 */
public class ConnectionEstablishedMessage extends Message {
    private User sender;
    private Inet4Address destinationIPAddress;
    private List<InetAddress> ipAdressesTable;
    private DataGame createdGame;

    public ConnectionEstablishedMessage(User sender, List<InetAddress> ipTable, DataGame game) {
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
            User user = IData.getLocalUser();
            user.setUsername("");
            ConnectionEstablishedMessage connectionEstablishedMessage = new ConnectionEstablishedMessage(user, controller.getIPTable(), null);
            controller.sendMessage(connectionEstablishedMessage, ipAddress);
        }
    }
}

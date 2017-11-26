package lo23.battleship.online.network.messages;

import com.sun.xml.internal.bind.v2.model.core.ID;
import interfacesData.IDataCom;
import lo23.battleship.online.network.NetworkController;
import structData.User;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.List;

/**
 * Created by xzirva on 14/11/17.
 */
public class ConnectionRequestMessage extends Message {
    private User sender;
    private List<InetAddress> ipAdressesTable;

    public ConnectionRequestMessage(User user, List<InetAddress> ipAdressesTable) {
        this.sender = user;
        this.ipAdressesTable = ipAdressesTable;
        type = "ConnectionRequestMessage";
    }
    public String getType() {
        return type;
    }
    public void process(IDataCom dataInterface) {


    }

    public void process(IDataCom IData, InetAddress senderAddress) {

        //dataInterface.getIPTableAdresses();
        System.out.println("New message received from: " + senderAddress);
        System.out.println("Message Type: " + type);
        // TODO getCurrentUserGame

        NetworkController controller = NetworkController.getInstance();

        controller.updateNetwork(sender, senderAddress, null);
        System.out.println("1 - " + IData);
        System.out.println("2 - " + controller);

        ConnectionEstablishedMessage connectionEstablishedMessage =
                new ConnectionEstablishedMessage(IData.getLocalUser(), controller.getIPTable(), null);

        controller.sendMessage(connectionEstablishedMessage, senderAddress);
    }
}

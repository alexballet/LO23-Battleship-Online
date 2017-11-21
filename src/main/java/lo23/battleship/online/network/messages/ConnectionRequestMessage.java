package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import lo23.battleship.online.network.NetworkController;
import structData.User;

import java.net.Inet4Address;
import java.net.InetAddress;

/**
 * Created by xzirva on 14/11/17.
 */
public class ConnectionRequestMessage extends Message {
    private User sender;
    private Inet4Address destinationIPAddress;

    public ConnectionRequestMessage(User user) {
        this.sender = user;
        type = "ConnectionRequestMessage";
    }
    public String getType() {
        return type;
    }
    public void process(IDataCom dataInterface) {


    }

    public void process(IDataCom IData, InetAddress senderAddress) {

        //dataInterface.getIPTableAdresses();

        // TODO getCurrentUserGame

        NetworkController controller = NetworkController.getInstance();

        User user = new User();
        user.setUsername("testUsername");
        user.setLogin("loginTest");
        controller.updateNetwork(sender, senderAddress, null);

        ConnectionEstablishedMessage connectionEstablishedMessage = new ConnectionEstablishedMessage(user, controller.getIPTable(), null);

        controller.sendMessage(connectionEstablishedMessage, senderAddress);
    }
}

package lo23.battleship.online.network.messages;

import com.sun.xml.internal.bind.v2.model.core.ID;
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
public class ConnectionRequestMessage extends Message {
    private User sender;
    private List<InetAddress> ipAdressesTable;
    private Game game;
    public ConnectionRequestMessage(User user, List<InetAddress> ipAdressesTable, Game game) {
        this.sender = user;
        this.ipAdressesTable = ipAdressesTable;
        this.game = game;
        type = "ConnectionRequestMessage";
    }
    public String getType() {
        return type;
    }
    public void process(IDataCom dataInterface) {


    }

    public void process(IDataCom IData, InetAddress senderAddress) {

        if(IData.getLocalUser() == null) // if user's machine is connected but user is not!
            return;
        //dataInterface.getIPTableAdresses();
        System.out.println("New message received from: " + senderAddress);
        System.out.println("Message Type: " + type);
        // TODO getCurrentUserGame

        NetworkController controller = NetworkController.getInstance();

        controller.addToNetwork(sender, senderAddress, game);
        System.out.println("1 - " + IData);
        System.out.println("2 - " + controller);

        ConnectionEstablishedMessage connectionEstablishedMessage =
                new ConnectionEstablishedMessage(IData.getLocalUser(), controller.getIPTable(), IData.getCreatedGame());

        controller.sendMessage(connectionEstablishedMessage, senderAddress);
    }
}

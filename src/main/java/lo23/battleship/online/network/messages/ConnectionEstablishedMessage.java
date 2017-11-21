package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import lo23.battleship.online.network.NetworkController;
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
    private User destination;
    private Inet4Address destinationIPAddress;
    private List<InetAddress> ipAdressesTable;
    private DataGame createdGame;

    public ConnectionEstablishedMessage(User user, List<InetAddress> ipTable, DataGame game) {
        destination = user;
        ipAdressesTable = ipTable;
        createdGame = game;
        type = "ConnectionEstablishedMessage";

    }
    public String getType() {
        return type;
    }
    public void process(IDataCom dataInterface) {
        

    }

    public void process(IDataCom IData, InetAddress senderAddress) {


    }
}

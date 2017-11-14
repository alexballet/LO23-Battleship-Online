package lo23.battleship.online.network;

import java.net.Inet4Address;

/**
 * Created by xzirva on 17/10/17.
 */
public class NetworkController {
    private NetworkModuleInterface networkInterface;
    // TODO: uncomment when User class is defined
    //private HashMap<User, Inet4Address> networkState;
    private NetworkServer networkServer;

    public NetworkController() {

        this.networkServer = new NetworkServer(this);

        this.networkServer.open();
    }

    public static void main(String[] args) {

        new NetworkController();
    }
}

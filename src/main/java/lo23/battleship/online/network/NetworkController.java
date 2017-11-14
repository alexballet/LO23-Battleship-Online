package lo23.battleship.online.network;

import java.io.IOException;
import java.net.Inet4Address;

/**
 * Created by xzirva on 17/10/17.
 * NetworkController
 */
public class NetworkController {
    private NetworkModuleInterface networkInterface;
    private static NetworkController instance;
    // TODO: uncomment when User class is defined
    //private HashMap<User, Inet4Address> networkState;
    private NetworkServer networkServer;
    public static NetworkController getInstance() {
        if(instance == null)
            instance = new NetworkController();

        return instance;

    }
    private NetworkController() {

        this.networkServer = new NetworkServer(this);

        try {
            this.networkServer.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

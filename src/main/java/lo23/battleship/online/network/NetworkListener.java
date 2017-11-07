package lo23.battleship.online.network;

import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by xzirva on 17/10/17.
 * TODO: To be completed
 */
public class NetworkListener {
    private NetworkController networkController;
    private ArrayList<NetworkSubListener> subListeners;

    public NetworkSubListener createSubListener(Socket socket){
        return null;
    }
}

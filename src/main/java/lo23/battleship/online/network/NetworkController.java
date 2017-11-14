package lo23.battleship.online.network;

import interfacesData.IDataCom;

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
    IDataCom dataInterface;
    private NetworkServer networkServer;
    public static NetworkController getInstance() {
        if(instance == null)
            instance = new NetworkController();

        return instance;

    }
    private NetworkController() {
        networkInterface = new NetworkModuleInterface();
        networkInterface.setDataInterface(dataInterface);
    }

    public void launchServer() {
        this.networkServer = new NetworkServer(this);
        networkServer.setDataInterface(dataInterface);

        try {
            this.networkServer.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //
    public void setDataInterface(IDataCom IData) {
        this.dataInterface = IData;
    }

    public COMInterface getCOMInterface() {
        return networkInterface;
    }
}

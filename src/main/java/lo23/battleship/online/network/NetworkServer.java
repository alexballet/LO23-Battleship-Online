package lo23.battleship.online.network;


import interfacesData.IDataCom;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class implements the network server. The network server is an encapsulation of
 * the network listener. It is responsible of opening the network listener with the right IP address
 * and closing the network listener when needed.
 *
 * @see NetworkListener
 * @see IDataCom
 * */
public class NetworkServer {

    private int port;
    private InetAddress address;
    private static int backlog = 100;
    private NetworkListener listener = null;
    private NetworkController networkController;
    private IDataCom dataInterface;

    /**
     * Allocates a new {@code NetworkServer} object.
     * Finds the right IP address to use as host of the listener
     *
     * @param networkController : {@code NetworkController}
     *                         instance of NetworkController class
     * @param port : {@code int}
     *             port on which the server (and the listener) is (are) opened
     * */
    NetworkServer(NetworkController networkController, int port)  {
        System.out.println("-----------Initialize Server(Listener)---------");
        this.networkController = networkController;
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();

                // filters out 127.0.0.1 and inactive interfaces
                if (iface.isLoopback() || !iface.isUp())
                    continue;

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while(addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();

                    //avoid IPv6 local address
                    if (addr instanceof Inet6Address) continue;

                    //get the first non-default address (different than loopback address)
                    if(!addr.getHostAddress().equals(Inet4Address.getLocalHost().toString())) {
                        address = addr;
                        break;
                    }
                }
                if(address != null) break;
            }
        } catch (UnknownHostException e) {
            Logger.getLogger("mainLogger").log(Level.SEVERE, "an exception was thrown", e.getMessage());
        } catch (IOException e) {
            Logger.getLogger("mainLogger").log(Level.WARNING ,"Server already opened!");
        }
        this.port = port;

    }

    /**
     * Sets IDataCom interface with the right IDataCom interface instance
     * @param IData : {@code IDataCom}
     *              instance of IDataCom interface
     * */
    void setDataInterface(IDataCom IData) {
        dataInterface = IData;
    }

    /**
     * Opens listener on <strong>address</strong> and the <strong>port</strong>
     * */
    public void open() throws IOException {
        //A different thread to run the server
        Logger.getLogger("mainLogger").log(Level.INFO, "Opening Server(and Listener accordingly)");
        listener = new NetworkListener(this, new ServerSocket(port, backlog, address));
        listener.setDataInterface(dataInterface);
        Logger.getLogger("mainLogger").log(Level.INFO, "Starting Listener on " + listener.getServerSocketIPAddress().toString());
        listener.start();

    }

    /**
     * Returns server (and thus listener) host IP address
     * @return {@code InetAddress}
     * */
    InetAddress getIpAddress() {
        return listener.getServerSocketIPAddress();
    }

    /**
     * Returns a boolean indicating if the server (and thus the listener) is opened (listening)
     * @return {@code boolean}
     * */
    boolean isOpened() {
        if(listener == null) return false;
        return listener.getIsRunning();
    }

    /**
     * Close server (and listener accordingly)
     * */
    public void close() {
        listener.closeSocket();
    }
}
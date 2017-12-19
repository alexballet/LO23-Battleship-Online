package lo23.battleship.online.network;


import interfacesData.IDataCom;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NetworkServer {

    //Configuration
    private int port = 2345;
    private InetAddress address;
    private int backlog = 100;
    private NetworkListener listener = null;
    private NetworkController networkController;
    private IDataCom dataInterface;
    public NetworkServer(NetworkController networkController) {
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

                    // *EDIT*
                    if (addr instanceof Inet6Address) continue;
                    if(!addr.getHostAddress().equals(Inet4Address.getLocalHost().toString())) {
                        address = addr;
                        break;
                    }
                }
                if(address != null) break;
            }
            //listener = new NetworkListener(this, new ServerSocket(port, backlog, address));
        } catch (UnknownHostException e) {
            Logger.getLogger("mainLogger").log(Level.SEVERE, "an exception was thrown", e);
        } catch (IOException e) {
            System.out.println("------!! Server already opened !!------");
        }

    }

    public void setDataInterface(IDataCom IData) {
        dataInterface = IData;
    }

    //Open and run server
    public void open() throws IOException {

        //A different thread to run the server
        System.out.println("-----------Open Server(Listener)---------");
        listener = new NetworkListener(this, new ServerSocket(port, backlog, address));
        listener.setDataInterface(dataInterface);
        System.out.println(listener.getServerSocketIPAddress().toString());
        listener.start();

    }

    public InetAddress getIpAddress() {
        return listener.getServerSocketIPAddress();
    }

    /**
     * Close server
     * Turn isRunning into false
     * */

    public boolean isOpened() {
        if(listener == null) return false;
        return listener.isRunning;
    }
    public void close() {

        System.out.println("-----------Close Server(Listener)---------");
        try {
            listener.closeSocket();
        } catch (IOException e) {
            Logger.getLogger("mainLogger").log(Level.SEVERE, "an exception was thrown", e);
        }
    }
}
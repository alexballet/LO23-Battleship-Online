package lo23.battleship.online.network;


import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

public class NetworkServer {

    //Configuration
    private int port = 2345;
    private String host = "172.25.35.108";
    private InetAddress address;
    private int backlog = 100;
    private ServerSocket server = null;
    private NetworkListener listener = null;
    private NetworkController networkController;

    public NetworkServer(NetworkController networkController) {
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
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("------!! Server already opened !!------");
        }

    }


    //Open and run server
    public void open() throws IOException {

        //A different thread to run the server
        listener = new NetworkListener(this, new ServerSocket(port, backlog, address));
        System.out.println(listener.getServerSocketIPAddress().toString());
        listener.start();

    }

    /**
     * Close server
     * Turn isRunning into false
     * */
    public void close() {

        listener.setIsRunning(false);

        try {
            listener.closeSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
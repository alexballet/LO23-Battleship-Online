package lo23.battleship.online.network;


import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetworkServer {

    //Configuration
    private int port = 2345;
    private String host = "172.25.35.108";
    private ServerSocket server = null;
    private NetworkListener listener = null;
    private NetworkController networkController;

    public NetworkServer(NetworkController networkController) {
        this.networkController = networkController;
        try {
            server = new ServerSocket(port, 100, InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("------!! Server already opened !!------");
        }

    }


    //Open and run server
    public void open(){

        //A different thread to run the server
        listener = new NetworkListener(server, this.networkController);

        listener.start();

    }

    /**
     * Close server
     * Turn isRunning into false
     * */
    public void close() {

        listener.setIsRunning(false);

        try {
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
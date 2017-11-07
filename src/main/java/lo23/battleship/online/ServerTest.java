package lo23.battleship.online;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerTest {

    //Configuration
    private int port = 2345;
    private String host = "127.0.0.1";
    private ServerSocket server = null;
    private ServerListener listener = null;

    public ServerTest(){
        try {
            server = new ServerSocket(port, 100, InetAddress.getByName(host));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerTest(String host, int port) {
        this.host = host;
        this.port = port;
        try {
            server = new ServerSocket(port, 100, InetAddress.getByName(host));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("------!! Server already opened !!------");
        }

    }


    //Open and run server
    public void open(){

        //A different thread to run the server
        listener = new ServerListener(server);

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
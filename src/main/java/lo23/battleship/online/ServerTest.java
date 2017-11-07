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
    private boolean isRunning = true;

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
        Thread t = new Thread(new Runnable(){
            public void run(){
                while(isRunning){

                    try {
                        //Waiting for client request --> Accept
                        Socket client = server.accept();

                        //request accepted --> New thread to process it
                        System.out.println("NEW MESSAGE RECEIVED");
                        Thread t = new Thread(new ClientProcessor(client));
                        t.start();//Run the client request process thread

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    server = null;
                }
            }
        });

        t.start(); // Run the server thread
    }

    /**
     * Close server
     * Turn isRunning into false
     * */
    public void close(){
        isRunning = false;
    }
}
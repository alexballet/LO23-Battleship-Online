package lo23.battleship.online.network;

import lo23.battleship.online.ClientProcessor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by xzirva on 17/10/17.
 * TODO: To be completed
 */
public class NetworkListener extends Thread {

    private ServerSocket serverSocket = null;
    boolean isRunning ;
    private NetworkController networkController;

    NetworkListener(ServerSocket serverSocket, NetworkController networkController) {

        this.serverSocket = serverSocket;
        this.networkController = networkController;
    }

    public void setIsRunning (boolean newValue) {
        isRunning = newValue;
    }

    @Override
    public void run() {
        isRunning = true;
        while (isRunning) {

            try {
                //Waiting for client request --> Accept
                Socket client = serverSocket.accept();

                //request accepted --> New thread to process it
                System.out.println("NEW MESSAGE RECEIVED");
                Thread t = new Thread(new ClientProcessor(client));
                t.start();//Run the client request process thread

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

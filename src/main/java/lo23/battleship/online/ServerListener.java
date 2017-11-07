package lo23.battleship.online;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener extends Thread {

    private ServerSocket serverSocket = null;
boolean isRunning ;

    ServerListener(ServerSocket serverSocket) {

        this.serverSocket = serverSocket;
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
package lo23.battleship.online.network;

import lo23.battleship.online.network.messages.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.Date;

//  {"Connect", "Ready", "Disconnect", "Chat", "RageQuit"};

public class NetworkSender extends Thread{

    private Socket sock;
    private ObjectOutputStream writer = null;
    private Message message;

    public NetworkSender(Socket socket, Message message) {

        sock = socket;
        this.message = message;
    }
    public NetworkSender(String host, int port, Message message) {

        try {
            sock = new Socket(host, port);
        } catch (IOException e) {
            System.out.println("Warning: Unable to reach host: " + host + "on port: " + port);
        }
        this.message = message;
    }

    public NetworkSender(InetAddress host, int port, Message message) {

        try {
            sock = new Socket(host, port);
        } catch (IOException e) {
            System.out.println("Warning: Unable to reach host: " + host + "on port: " + port);
        }
        this.message = message;
    }

    //New thread to process request
    public void run() {

        try {
            writer = new ObjectOutputStream(sock.getOutputStream());
            String timeStamps = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM).format(new Date());
            System.out.println("Message " + message.getType() + " sent to server at " + timeStamps);
            writer.writeObject(message);
            sock.close();

        }catch(SocketException e){
            System.err.println(" / ! \\ Interrupted: Something went wrong / ! \\");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
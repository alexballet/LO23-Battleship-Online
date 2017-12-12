package lo23.battleship.online.network;

import lo23.battleship.online.network.messages.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Date;

//  {"Connect", "Ready", "Disconnect", "Chat", "RageQuit"};

public class NetworkSender extends Thread{

    private InetAddress host;
    private int port;
    private Socket sock;
    private ObjectOutputStream writer = null;
    private Message message;

    public NetworkSender(InetAddress host, int port, Message message) {

        this.host = host;
        this.port = port;
        this.message = message;
    }

    //New thread to process request
    public void run() {

        try {
            sock = new Socket(host, port);
            writer = new ObjectOutputStream(sock.getOutputStream());
            String timeStamps = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM).format(new Date());
            System.out.println("Message " + message.getType() + " sent to " + sock.getInetAddress() + " at " + timeStamps);
            writer.writeObject(message);
            sock.close();
        } catch (IOException e) {
            System.out.println("Warning: Unable to reach host: " + host + ":" + port);
        }
    }
}
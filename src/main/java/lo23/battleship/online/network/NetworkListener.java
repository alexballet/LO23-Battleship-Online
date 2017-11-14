package lo23.battleship.online.network;

import com.sun.security.ntlm.Server;
import lo23.battleship.online.network.NetworkSender;
import lo23.battleship.online.network.messages.CustomMessage;
import lo23.battleship.online.network.messages.Message;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by xzirva on 17/10/17.
 * TODO: To be completed
 */
public class NetworkListener extends Thread {

    private ServerSocket serverSocket = null;
    boolean isRunning ;
    private ObjectInputStream reader;
    private NetworkServer server;
    private NetworkController networkController;
    private static String name = "Client-";
    private static int count = 0;
    NetworkListener(NetworkServer server, ServerSocket socket) {
        this.server = server;
        this.serverSocket = socket;
    }

    public void setIsRunning (boolean newValue) {
        isRunning = newValue;
    }

    @Override
    public void run() {
        setIsRunning(true);
        while (isRunning) {

            try {
                //Waiting for client request --> Accept
                Socket client = serverSocket.accept();

                //request accepted --> New thread to process it
                System.out.println("NEW MESSAGE RECEIVED");
                reader = new ObjectInputStream(client.getInputStream());

                //Waiting for client request
                Message request = read();
                InetSocketAddress remote = (InetSocketAddress)client.getRemoteSocketAddress();

                //Displaying info about request
                String debug = "";
                debug = "Thread : " + Thread.currentThread().getName() + ". ";
                debug += "Sender : " + remote.getAddress().getHostAddress() +".";
                debug += "Port : " + remote.getPort() + ".\n";
                debug += "\t -> Request Content : " + request + "\n";
                String timeStamps = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM).format(new Date());
                debug += "\n Request Received at " + timeStamps;
                System.err.println("\n" + debug);

                Message responseToSend = request.process();

                Thread t = new Thread(new NetworkSender(client, responseToSend));
                t.start();//Run the client request process thread
                //Waiting for server response
//                Message responseFromServer = read();
//                System.out.println("\t * " + name + " : Server response: " + responseFromServer);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public InetAddress getServerSocketIPAddress() {
        return serverSocket.getInetAddress();
    }

    public void closeSocket() throws IOException {
        serverSocket.close();
    }

    //Read data:  byte --> string
    private Message read() throws IOException{
        try {
            Message message = (Message) reader.readObject();
            return message;
        } catch(ClassNotFoundException e) {
            return new CustomMessage("UNKNOWN");
        }
    }
}

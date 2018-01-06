package lo23.battleship.online.network;

import interfacesData.IDataCom;
import lo23.battleship.online.network.messages.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class implements network listeners. Network listeners use and encapsulate
 * server sockets to receive messages transferred on the network between players,
 * object input streams to read the messages and then run the process associated
 * with each of those message. The process of each message sometimes requires
 * the IDataCom interface instance.
 *
 * @author COM Module
 * @see java.lang.Thread
 * @see Message
 * @see java.io.ObjectInputStream
 * Plus it extends Thread class so that it is run asynchronously from the
 * thread of the main application.
 */

public class NetworkListener extends Thread {

    private ServerSocket serverSocket = null;
    boolean isRunning;
    private IDataCom dataInterface;
    private ObjectInputStream reader;
    private NetworkServer server;
    NetworkListener(NetworkServer server, ServerSocket socket) {
        this.server = server;
        this.serverSocket = socket;
    }

    void setIsRunning (boolean newValue) {
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
                InetSocketAddress remote = (InetSocketAddress) client.getRemoteSocketAddress();

                //Displaying info about request
                String debug = "Thread : " + Thread.currentThread().getName() + ". ";
                debug += "Sender : " + remote.getAddress().getHostAddress() + ".";
                debug += "Port : " + remote.getPort() + ".\n";
                debug += "\t -> Request Content : " + request + "\n";
                String timeStamps = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM).format(new Date());
                debug += "\n Request Received at " + timeStamps;
                System.err.println("\n" + debug);

                if (request != null) {
                    request.process(dataInterface, remote.getAddress());
                }
            } catch(SocketException e) {
                Logger.getLogger("mainLogger").log(Level.WARNING, "Still closing. Revert accept", e.getMessage());
            } catch (IOException e) {
                Logger.getLogger("mainLogger").log(Level.SEVERE, "an exception was thrown", e.getMessage());
            }
        }
    }


    /**
     * @return server socket IP address as InetAddress Object
     * */
    InetAddress getServerSocketIPAddress() {
        return serverSocket.getInetAddress();
    }

    void closeSocket() throws IOException {
        try {
            this.isRunning = false;
            Logger.getLogger("mainLogger").log(Level.WARNING, "Closing Listener serverSocket");
            serverSocket.close();
        } catch(IOException e) {
            Logger.getLogger("mainLogger").log(Level.WARNING, "Still closing. Revert accept", e.getMessage());
        }
    }

    /**
     * Read and deserialize object of type Message transferred through the network
     * @return object of class Message
     */
    private Message read() throws IOException{
        Message message = null;
        try {
            message = (Message) reader.readObject();
        } catch(ClassNotFoundException e) {
            Logger.getLogger("mainLogger").log(Level.SEVERE, "an exception was thrown", e);
        }
        return message;
    }

    public void setDataInterface(IDataCom IData) {
        dataInterface = IData;
    }
}

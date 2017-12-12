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

/** TODO: To be completed
 * Extends Thread class.
 * Unique class instance being in charge of network message reception. Allows the data transmissions between users via
 * server socket, the local user is receiving network messages sent by a distant user.
 */
public class NetworkListener extends Thread {

    private ServerSocket serverSocket = null;
    boolean isRunning ;
    private IDataCom dataInterface;
    private ObjectInputStream reader;
    private NetworkServer server;
    private NetworkController networkController;
    private static String name = "Client-";
    private static int count = 0;

    /** TODO:
     * Class constructor.
     * @param server
     * @param socket
     */
    NetworkListener(NetworkServer server, ServerSocket socket) {
        this.server = server;
        this.serverSocket = socket;
    }

    /**
     * Method to set
     * @param newValue
     */
    public void setIsRunning (boolean newValue) {
        isRunning = newValue;
    }

    /**
     * Method to open a new thread/server socket with a distant user in order to transmit message from the former to
     * the local user.
     */
    @Override
    public void run() {
        setIsRunning(true);
        while (isRunning) {

            try {
                //Waiting for client request --> Accept
                System.out.println("IsRunning: " + isRunning);
                Socket client = serverSocket.accept();

                //request accepted --> New thread to process it
                System.out.println("NEW MESSAGE RECEIVED");
                reader = new ObjectInputStream(client.getInputStream());

                //Waiting for client request
                Message request = read();
                InetSocketAddress remote = (InetSocketAddress) client.getRemoteSocketAddress();

                //Displaying info about request
                String debug = "";
                debug = "Thread : " + Thread.currentThread().getName() + ". ";
                debug += "Sender : " + remote.getAddress().getHostAddress() + ".";
                debug += "Port : " + remote.getPort() + ".\n";
                debug += "\t -> Request Content : " + request + "\n";
                String timeStamps = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM).format(new Date());
                debug += "\n Request Received at " + timeStamps;
                System.err.println("\n" + debug);

                request.process(dataInterface, remote.getAddress());

//                Thread t = new Thread(new NetworkSender(client, responseToSend));
//                t.start();//Run the client request process thread
                //Waiting for server response
//                Message responseFromServer = read();
//                System.out.println("\t * " + name + " : Server response: " + responseFromServer);
            } catch(SocketException e) {
                System.out.println("Still closing. Revert accept");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /** TODO:
     * Getter.
     * @return the IP address of ...
     */
    public InetAddress getServerSocketIPAddress() {
        return serverSocket.getInetAddress();
    }

    /** TODO:
     * Method to close a server socket previously created.
     * @throws IOException
     */
    public void closeSocket() throws IOException {
        try {
            this.isRunning = false;
            System.out.println("IsRunning: close " + isRunning);
            serverSocket.close();
        } catch(IOException e) {
            System.out.println("Still closing. Revert accept");
        }
    }

    /** TODO:
     * Method to read a network message received by the local user.
     * @return the read Message.
     * @throws IOException
     */
    //Read data:  byte --> string
    private Message read() throws IOException{
        try {
            Message message = (Message) reader.readObject();
            return message;
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method to set the dataInterface member, which is the interface with Data.
     * @param IData interface with Data to be set.
     */
    public void setDataInterface(IDataCom IData) {
        dataInterface = IData;
    }
}

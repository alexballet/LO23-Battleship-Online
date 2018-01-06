package lo23.battleship.online.network;

import lo23.battleship.online.network.messages.Message;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class implements network senders. Network senders are threads (Thread) responsible
 * of sending message though the network. They use socket and output streams to connect to the
 * destination host and send the serialized message.
 * @see Socket
 * @see java.lang.Thread
 * @see ObjectOutputStream
 * @see Message
 * */
public class NetworkSender extends Thread{

    private InetAddress host;
    private int port;
    private Socket sock;
    private ObjectOutputStream writer = null;
    private Message message;
    private NetworkController controller = NetworkController.getInstance();

    /**
     * Allocates a new {@code NetworkSender} object.
     * @param host : {@code InetAddress}
     *             destination host of the message
     * @param port : {@code int}
     *             host's port on which the message sent
     * @param message : {@code Message}
     *                message to be sent
     * */
    NetworkSender(InetAddress host, int port, Message message) {
        this.host = host;
        this.port = port;
        this.message = message;
    }

    /**
     * <p>
     * Overriding java.lang.Thread run method
     * Connect to <strong>host</strong> on port <strong>port</strong> and
     * send <strong>message</strong> through an output stream.
     * </p>
     * */
    @Override
    public void run() {
        try {
            if(host == null) {
                System.out.println("Host null");
            } else {

                String debug = "\n" + "Connecting to " + host + ":" + port;
                Logger.getLogger("mainLogger").log(Level.INFO, debug);
                sock = new Socket(host, port);
                writer = new ObjectOutputStream(sock.getOutputStream());
                String timeStamps = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM).format(new Date());
                Logger.getLogger("mainLogger").log(Level.FINE,"Message " + message.getType() + " sent to " + sock.getInetAddress() + " at " + timeStamps);
                writer.writeObject(message);
                sock.close();
            }
        } catch (IOException e) {
            Logger.getLogger("mainLogger").log(Level.SEVERE, "Warning: Unable to reach host: " + host + ":" + port, e.getMessage());
            InetAddress removed = controller.removeUnreachableHost(host);
            if(removed != null)
                Logger.getLogger("mainLogger").log(Level.WARNING,"Removed unreachable host <" + removed + "> from NetworkState");
        }
    }
}
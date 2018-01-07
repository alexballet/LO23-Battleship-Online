package lo23.battleship.online.network.messages;


import java.io.Serializable;
import java.net.InetAddress;
import interfacesData.IDataCom;


/**
 * This abstract class implements the core structure of a message sent on the network.
 * Every single message sent on the network has to extend this class.
 * This class declares the two abstract methods:
 * <code>getType</code> and <code>process</code>
 *
 * @author COM Module
 */
public abstract class Message implements Serializable {
    String type;

    /**
     * Returns the type of the message.
     * Abstract method.
     * @return type : {@code String}
     */
    public abstract String getType();

    /**
     * Abstract method, interacts with data package interface (IDataCom) once implemented.
     * @param IData : {@code IDataCom}
     *              instance of IDataCom interface.
     * @param senderAddress : {@code InetAddress}
     *                      sender's IP address
     */
    public abstract void process(IDataCom IData, InetAddress senderAddress);
}


package lo23.battleship.online.network.messages;


import java.io.Serializable;
import java.net.InetAddress;
import interfacesData.IDataCom;


/**
 * Abstract network class, implements Serializable.
 */
public abstract class Message implements Serializable {

    String type;

    /**
     * Abstract getter method, once implemented it returns the message type.
     */
    public abstract String getType();

    /**
     * Abstract method, interacts with Data once implemented.
     * @param IData interface with Data.
     * @param senderAddress sender IP address.
     */
    public abstract void process(IDataCom IData, InetAddress senderAddress);
}


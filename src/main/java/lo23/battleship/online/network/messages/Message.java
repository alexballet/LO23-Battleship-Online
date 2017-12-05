package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;

import java.io.Serializable;
import java.net.InetAddress;
import interfacesData.IDataCom;


/** /!Doc NON-OK
 * ==DESCRIPTION NEEDED==
 */
public abstract class Message implements Serializable {

    String type;

    /**
     * ==DESCRIPTION NEEDED==
     * @return ==DESCRIPTION NEEDED==
     */
    public abstract String getType();

    /**
     * ==DESCRIPTION NEEDED==
     * @param IData ==DESCRIPTION NEEDED==
     */
    public abstract void process(IDataCom IData);

    /**
     * Abstract method.
     * @param IData ==DESCRIPTION NEEDED==
     * @param senderAddress ==DESCRIPTION NEEDED==
     */
    public abstract void process(IDataCom IData, InetAddress senderAddress);
}


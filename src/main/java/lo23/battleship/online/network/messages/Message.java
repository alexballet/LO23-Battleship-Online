package lo23.battleship.online.network.messages;


import java.io.Serializable;
import java.net.InetAddress;
import interfacesData.IDataCom;


/**
 * Created by xzirva on 31/10/17.
 */
public abstract class Message implements Serializable {

    String type;
    public abstract String getType();
    public abstract void process(IDataCom IData, InetAddress senderAddress);
}


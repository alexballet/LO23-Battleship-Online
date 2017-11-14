package lo23.battleship.online.network.messages;

import java.io.Serializable;

/**
 * Created by xzirva on 31/10/17.
 */
public abstract class Message implements Serializable {

    String type;
    public abstract String getType();
    public abstract Message process();
}


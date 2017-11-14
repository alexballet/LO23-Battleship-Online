package lo23.battleship.online.network.messages;

/**
 * Created by xzirva on 14/11/17.
 */
public class ConnectionRequest extends Message {
    public String getType() {
        return type;
    }
    public Message process() {
        return null;
    }
}

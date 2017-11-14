package lo23.battleship.online.network.messages;

import structData.User;

import java.net.Inet4Address;

/**
 * Created by xzirva on 14/11/17.
 */
public class ConnectionRequestMessage extends Message {
    private User destination;
    private Inet4Address destinationIPAddress;

    public ConnectionRequestMessage(User user) {
        destination = user;
    }
    public String getType() {
        return type;
    }
    public Message process() {
        return null;
    }
}

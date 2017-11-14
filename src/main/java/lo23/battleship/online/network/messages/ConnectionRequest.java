package lo23.battleship.online.network.messages;

import structData.User;

import java.net.Inet4Address;

/**
 * Created by xzirva on 14/11/17.
 */
public class ConnectionRequest extends Message {
    private User destination;
    private Inet4Address destinationIPAddress;
    public ConnectionRequest(User user, Inet4Address ipAddress) {
        destination = user;
        destinationIPAddress = ipAddress;
    }
    public String getType() {
        return type;
    }
    public Message process() {
        return null;
    }
}

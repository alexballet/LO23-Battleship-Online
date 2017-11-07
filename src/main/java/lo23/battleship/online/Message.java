package lo23.battleship.online;

import java.io.Serializable;

/**
 * Created by xzirva on 31/10/17.
 */
public class Message implements Serializable {
    String type;
    Message(String test) {
        this.type = test;
    }

    String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Message type: " + getType();
    }
}

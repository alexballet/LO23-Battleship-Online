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

    public Message process() {
        //Request processing
        Message response = new Message("");
        switch(this.getType().toUpperCase()){
            case "CLOSE":
                response.type = "Communication over";
                break;
            case "RAGEQUIT":
                response.type = "You suck! Get lost you faggot!";
                break;
            case "UNKNOWN":
                response.type = "!!!!!!Unknown Message!!!!!!";
            default :
                response.type = this.type + " Message Received!";
                break;
        }
        return response;
    }
}

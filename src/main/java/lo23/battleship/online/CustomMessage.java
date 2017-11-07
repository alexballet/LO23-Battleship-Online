package lo23.battleship.online;

public class CustomMessage extends Message {

    CustomMessage(String test) {
        this.type = test;
    }

    String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Message type: " + getType();
    }

    public CustomMessage process() {
        //Request processing
        CustomMessage response = new CustomMessage("");
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

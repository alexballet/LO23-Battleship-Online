package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.User;

public class CustomMessage extends Message {
    private User user;
    public CustomMessage(String test) {
        this.type = test;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Message type: " + getType();
    }

    public void setUser(User u) {
        this.user = u;
    }

    public void process(IDataCom IData) {
        IData.addUserToUserList(user);
        //Request processing
//        CustomMessage response = new CustomMessage("");
//        switch(this.getType().toUpperCase()){
//            case "CLOSE":
//                response.type = "Communication over";
//                break;
//            case "RAGEQUIT":
//                response.type = "You suck! Get lost you faggot!";
//                break;
//            case "UNKNOWN":
//                response.type = "!!!!!!Unknown Message!!!!!!";
//                break;
//            default :
//                response.type = this.type + " Message Received!";
//                break;
//        }
        //return response;
    }
}

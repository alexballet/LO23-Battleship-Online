package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Profile;
import structData.User;

import java.net.InetAddress;

public class ProfileRequestAnswerMessage extends Message {

    User user;
    Profile profile;

    public ProfileRequestAnswerMessage(Profile profile) {

        this.profile = profile;
        this.type = "ProfileRequestAnswerMessage";}

    /**
     * Message type getter. Implementation of an abstract method.
     * @return type, this is the message type.
     */
    public String getType() {
        return type;
    }

    public void process(IDataCom IData){

        IData.sendStatistics(profile);
    }

    public void process(IDataCom IData, InetAddress senderAddress){
    }


}
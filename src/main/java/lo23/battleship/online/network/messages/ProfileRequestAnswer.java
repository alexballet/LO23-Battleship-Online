package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Profile;
import structData.User;

import java.net.InetAddress;

public class ProfileRequestAnswer extends Message {

    User user;
    Profile profile;

    public ProfileRequestAnswer(Profile profile) {

        this.profile = profile;
        this.type = "ProfileRequestAnswer";}

    public String getType() {
        return type;
    }

    public void process(IDataCom IData){

        IData.sendStatistics(profile);
    }

    public void process(IDataCom IData, InetAddress senderAddress){
    }


}
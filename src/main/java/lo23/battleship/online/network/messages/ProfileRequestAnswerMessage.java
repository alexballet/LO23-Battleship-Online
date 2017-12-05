package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Profile;
import structData.User;

import java.net.InetAddress;

/**
 * ==DESCRIPTION NEEDED==
 */
public class ProfileRequestAnswerMessage extends Message {

    User user;
    Profile profile;

    /**
     * Class constructor.
     * @param profile ==DESCRIPTION NEEDED==
     */
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

    /**
     * ==DESCRIPTION NEEDED==
     * @param IData interface with Data.
     */
    public void process(IDataCom IData){

        IData.sendStatistics(profile);
    }

    /**
     * Unused method for this class.
     * @param IData interface with Data.
     * @param senderAddress sender IP address.
     */
    public void process(IDataCom IData, InetAddress senderAddress){
    }


}
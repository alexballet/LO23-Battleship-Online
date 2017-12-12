package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Profile;
import structData.User;

import java.net.InetAddress;

/**
 * Network message class, extends Message class.
 * Message sent to provide the Profile requested by the network.
 */
public class ProfileRequestAnswerMessage extends Message {

    User user; //! whut?
    Profile profile;

    /**
     * Class constructor.
     * @param profile the Profile class which needs to be sent.
     */
    public ProfileRequestAnswerMessage(Profile profile) {
        this.profile = profile;
        this.type = "ProfileRequestAnswerMessage";
    }

    /**
     * Message type getter. Implementation of an abstract method.
     * @return type, this is the message type.
     */
    public String getType() {
        return type;
    }
    

    /**
     * Unused method for this class.
     * @param IData interface with Data.
     * @param senderAddress sender IP address.
     */
    public void process(IDataCom IData, InetAddress senderAddress){
        IData.sendStatistics(profile);
    }

}
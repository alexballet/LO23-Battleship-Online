package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Profile;
import structData.User;

import java.net.InetAddress;

/**
 * Network message class, extends Message class.
 * Message sent to provide the Profile requested by the network.
 * @author COM Module
 */
public class ProfileRequestAnswerMessage extends Message {

    private User user;
    private Profile profile;

    /**
     * Allocates a new {@code ProfileRequestAnswerMessage} object
     * @param profile : {@code Profile} the Profile class which needs to be sent.
     */
    ProfileRequestAnswerMessage(Profile profile) {
        this.profile = profile;
        this.type = "ProfileRequestAnswerMessage";
    }

    /**
     * Returns the type of the message.
     * Implementation of an abstract method.
     * @return type : {@code String}
     */
    public String getType() {
        return type;
    }
    

    /**
     * Notifies the data package interface that profile which the local user asked for,
     * has been received.
     * @param IData : {@code IDataCom}
     *              instance of IDataCom interface.
     * @param senderAddress : {@code InetAddress}
     *                      sender's IP address
     */
    public void process(IDataCom IData, InetAddress senderAddress){
        IData.sendStatistics(profile);
    }

}
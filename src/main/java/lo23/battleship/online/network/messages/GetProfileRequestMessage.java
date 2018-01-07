package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import lo23.battleship.online.network.NetworkController;
import structData.User;

import java.net.InetAddress;

/**
 * This class implements the message which is sent when
 * when a user wants to see the profile(including statistics) of an other user.
 *
 * This class extends the abstract Message and implements the two abstract methods:
 * <code>getType</code> and <code>process</code>
 *
 * @see Message
 * @author COM Module
 */
public class GetProfileRequestMessage extends Message {
    User userRequester;

    /**
     * Allocates a new {@code GetProfileRequestMessage} object.
     * @param requester : {@code User} :
     *                 the user who requested to see the profile.
     */
    public GetProfileRequestMessage(User requester){
        this.userRequester = requester;
        this.type = "JoinGameRequest";
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
     * Sends a response to the user asking for the local user profile(user receiving this message)
     * using IDataCom interface
     * @param IData : {@code IDataCom}
     *              instance of IDataCom interface.
     * @param senderAddress : {@code InetAddress}
     *                      sender's IP address
     */
    public void process(IDataCom IData, InetAddress senderAddress){
        ProfileRequestAnswerMessage profileRequestAnswer = new ProfileRequestAnswerMessage(IData.getUserProfile());
        NetworkController.getInstance().sendMessage(profileRequestAnswer, senderAddress);
    }
}

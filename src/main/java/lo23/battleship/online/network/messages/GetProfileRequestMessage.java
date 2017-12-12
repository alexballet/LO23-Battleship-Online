package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import lo23.battleship.online.network.NetworkController;
import structData.User;

import java.net.InetAddress;

/**
 * Network message class, extends Message class.
 * Message sent when the local player wants to see the profile page of another player.
 */
public class GetProfileRequestMessage extends Message {

    User userRequester;

    /**
     * Class constructor.
     * @param requester is the User class who requested the profile which need to be seen.
     */
    public GetProfileRequestMessage(User requester){
        this.userRequester = requester;
        this.type = "JoinGameRequest";}

    /**
     * Message type getter. Implementation of an abstract method.
     * @return type, this is the message type.
     */
    public String getType() {
        return type;
    }

    /**
     * Method requesting the profile from Data.
     * @param IData interface with Data.
     * @param senderAddress sender IP address.
     */
    public void process(IDataCom IData, InetAddress senderAddress){

        ProfileRequestAnswerMessage profileRequestAnswer = new ProfileRequestAnswerMessage(IData.getUserProfile());

        NetworkController.getInstance().sendMessage(profileRequestAnswer, senderAddress);
    }



}

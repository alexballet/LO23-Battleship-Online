package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import lo23.battleship.online.network.NetworkController;
import structData.User;

import java.net.InetAddress;

/**
 * ==DESCRIPTION NEEDED==
 */

public class GetProfileRequestMessage extends Message {

    User userRequester;

    /**
     * Class constructor.
     * @param requester ==DESCRIPTION NEEDED==
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
     * Unused method for this class.
     * @param IData interface with Data.
     */
    public void process(IDataCom IData){ }

    /**
     * ==DESCRIPTION NEEDED==
     * @param IData interface with Data.
     * @param senderAddress sender IP address.
     */
    public void process(IDataCom IData, InetAddress senderAddress){

        ProfileRequestAnswerMessage profileRequestAnswer = new ProfileRequestAnswerMessage(IData.getUserProfile());

        NetworkController.getInstance().sendMessage(profileRequestAnswer, senderAddress);
    }



}

package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import lo23.battleship.online.network.NetworkController;
import structData.User;

import java.net.InetAddress;

public class GetProfileRequestMessage extends Message {

    User userRequester;

    public GetProfileRequestMessage(User requester){
        this.userRequester = requester;
        this.type = "JoinGameRequest";}

    public String getType() {
        return type;
    }

    public void process(IDataCom IData){
    }

    public void process(IDataCom IData, InetAddress senderAddress){

        ProfileRequestAnswerMessage profileRequestAnswer = new ProfileRequestAnswerMessage(IData.getUserProfile());

        NetworkController.getInstance().sendMessage(profileRequestAnswer, senderAddress);
    }



}

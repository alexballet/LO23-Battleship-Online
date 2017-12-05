package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Game;
import structData.User;

import java.net.InetAddress;


/**
 * ==DESCRIPTION NEEDED==
 */
public class JoinGameResponseMessage extends Message{

    Game game;
    User sender;
    Boolean isOk;

    /**
     * Class constructor.
     * @param isOk ==DESCRIPTION NEEDED==
     * @param sender ==DESCRIPTION NEEDED==
     * @param game ==DESCRIPTION NEEDED==
     */
    public JoinGameResponseMessage(Boolean isOk, User sender, Game game){
        this.game = game;
        this.isOk = isOk;
        this.sender = sender;
        this.type = "JoinGameResponseMessage";}

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

        if (isOk) {

            IData.setGameJoinResponse(true, sender, IData.getLocalUser());

        } else {

            IData.setGameJoinResponse(false);
        }
    }

    /**
     * Unused method for this class.
     * @param IData interface with Data.
     * @param senderAddress sender IP address.
     */
    public void process(IDataCom IData, InetAddress senderAddress){ }



}

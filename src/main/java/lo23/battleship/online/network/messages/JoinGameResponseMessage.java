package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Game;
import structData.User;

import java.net.InetAddress;


/**
 * Network message class, extends Message class.
 * Message sent when the local player notify the distant player whether the local game is available for the distant
 * player willing to join it, or not.
 */
public class JoinGameResponseMessage extends Message{

    Game game;
    User sender;
    Boolean isOk;

    /**
     * Class constructor.
     * @param isOk is the answer  depending on whether the opponent slot is available for the distant player willing to
     *             join (true : the slot is available, false : the slot isn't available).
     * @param sender is the User class of the local player.
     * @param game is the game which is willed to be joined by the distant player.
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
     * Method updating the game status in Data whether the opponent slot is available or not.
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

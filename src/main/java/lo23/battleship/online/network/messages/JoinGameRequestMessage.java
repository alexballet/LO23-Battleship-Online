package lo23.battleship.online.network.messages;

import interfacesData.IDataCom;
import structData.Game;
import structData.Profile;
import java.net.InetAddress;

/**
 * Network message class, extends Message class.
 * Message sent when the local player wants to join a distant game displayed in the HMI games list.
 */
public class JoinGameRequestMessage extends Message{

    Profile sender;
    Game game;

    /**
     * Class constructor.
     * @param senderGame is the User class of the local player who wants to join the game.
     * @param gameSend is the Game class that the local player wants to join.
     */
    public JoinGameRequestMessage(Profile senderGame, Game gameSend){
        this.sender = senderGame;
        this.game = gameSend;
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("SHHHHIIIIITTT! - JOIN : " + game.getListSpectators().size());
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("############################################");
        this.type = "JoinGameRequestMessage";}

    /**
     * Message type getter. Implementation of an abstract method.
     * @return type, this is the message type.
     */
    public String getType() {
        return type;
    }

    /**
     * Method notifying Data the local player wants to join the game.
     * @param IData interface with Data.
     */
    public void process(IDataCom IData, InetAddress senderAddress){
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("SHHHHIIIIITTT! - JOIN PROCESS: " + game.getListSpectators().size());
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("############################################");
        IData.notifToJoinGame(sender, game);
    }




}




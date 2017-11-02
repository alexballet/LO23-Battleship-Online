/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesData;
import packageStructDonnees.Position;
import packageStructDonnees.Message;
import java.util.Date;
import java.util.List;

/**
 * Data's interface for IHM-Table
 */
public interface IDataTable {
    /**
    * Function to exit.
    * @return : 1 if the game was successfully closed and return 0 if not.
    */
    public Boolean  exit();

    /**
    * Add the message to the chat in the current game.
    * @param message : The main part of message that the player wants to send.
    */
    public void textMessage(Message message);

    /**
    * Returns a message after the creation of chat in the game.
    * @param content : The string (some characters) of message before 
    * players send a main part.
    * @param time : The moment when the player sends the message.
    */
    public void textMessage(String content, Date time);

    /**
     * Point out the position of shot.
     * @param pos : The position of shot. 
     */
    public void coordinate(Position pos);

    /**
     * Point out the list of positions of all the boats that players 
     * place at the beginning of game.
     * @param listposition : List of positions of the boats. 
     */
    public void coordinateShips(List<Position> listposition);

}







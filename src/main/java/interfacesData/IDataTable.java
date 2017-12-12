/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacesData;
import structData.Position;
import structData.Boat;
import structData.ChatMessage;
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
    public void textMessage(String message);



    /**
     * Point out the position of shot.
     * @param pos : The position of shot. 
     */
    public void coordinate(Position pos);

    /**
     * Point out the the boats that players 
     * place at the beginning of game.
     * @param listBoat : List of boats. 
     */
    public void coordinateShips(List<Boat> listBoat);

}







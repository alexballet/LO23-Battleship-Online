/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageStructDonnées;


/**
 * Boat is the class which represents a ship of a player.
 * @author lola
 */
public class Boat {
    private BoatType type;
    private Boolean status;
    private Position[] listCases;
    
    public Boat(){
        type = BoatType.PORTEAVIONS;
        status = false;
        listCases[0] = new Position();
    }
}

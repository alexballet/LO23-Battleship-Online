/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageStructDonnees;

/**
 * Position is a class of a position in a table.
 */
public class Position {
    protected Byte x;
    protected Byte y;
    protected Boolean touched;
    
    public Position(){
        x = 0;
        y = 0;
        touched = false;
    }
}

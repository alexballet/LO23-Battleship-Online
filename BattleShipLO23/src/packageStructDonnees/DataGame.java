/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageStructDonnees;
import java.util.HashSet;
import java.util.ArrayList;
/**
 *
 * @author Huiling
 */
public class DataGame extends Game{
    private HashSet listSpectators;
    private Player player1;
    private Player player2;
    private Boolean player1Start;
    private ArrayList listMessages;
    
    public DataGame(){
        super();
        listSpectators = new HashSet();
        player1 = new Player();
        player2 = new Player();
        player1Start = false;
        listMessages = new ArrayList();
    }
    
}

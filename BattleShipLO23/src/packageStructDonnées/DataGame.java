/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageStructDonnées;

/**
 *
 * @author Huiling
 */
public class DataGame extends Game{
    private User[] listSpectators;
    private Player player1;
    private Player player2;
    private Boolean player1Start;
    private Message[] listMessages;
    
    public DataGame(){
        listSpectators[0] = new User();
        player1 = new Player();
        player2 = new Player();
        player1Start = false;
        listMessages[0] = new Message();
    }
    
}

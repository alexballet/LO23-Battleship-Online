/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lo23.battleship.data.packageStructDonnées;
import java.util.UUID;
/**
 *
 * @author Huiling
 */
public class Game {
    private UUID idGame;
    private StatusGame status;
    private Boolean classicType;
    private String name;
    private Boolean humanOpponent;
    private int timePerShot;
    private Boolean spectator;
    private Boolean spectatorChat;
    
    
    public Game(){
        idGame = UUID.randomUUID();
        status = StatusGame.WAITINGPLAYER;
        classicType = false;
        name = "";
        humanOpponent = false;
        timePerShot = 0;
        spectator = false;
        spectatorChat = false;
    }

    
}

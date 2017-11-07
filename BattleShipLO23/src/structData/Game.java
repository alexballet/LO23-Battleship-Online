/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structData;
import java.util.UUID;
/**
 * Game is a class for the user's game
 * @author Huiling
 */
public class Game {
    protected UUID idGame;
    protected StatusGame status;
    protected Boolean classicType;
    protected String name;
    protected Boolean humanOpponent;
    protected int timePerShot;
    protected Boolean spectator;
    protected Boolean spectatorChat;
    
    /**
     * Default constructor
     */
    public Game(){
        idGame = UUID.randomUUID();
        status = StatusGame.WAITINGPLAYER;
        classicType = false;
        name = new String("");
        humanOpponent = false;
        timePerShot = 0;
        spectator = false;
        spectatorChat = false;
    }
    
    /**
     * Constructor with all parameters
     * @param newClassicType the game type
     * @param newName the game's name
     * @param newHumanOpponent a boolean equal to 1 if 
     * the game is between two players and 0 if it is against a bot
     * @param newTimePerShot time per shot
     * @param newSpectator a boolean equal to 1 if spectators are allowed
     * @param newSpectatorChat  a boolean equal to 1 if chat is allowed
     */
    public Game(Boolean newClassicType, String newName, 
            Boolean newHumanOpponent, int newTimePerShot, 
            Boolean newSpectator, Boolean newSpectatorChat){
        idGame = UUID.randomUUID();
        classicType = newClassicType;
        name = new String(newName);
        humanOpponent = newHumanOpponent;
        if (humanOpponent){
            status = StatusGame.WAITINGPLAYER;
        }else{
            status = StatusGame.WAITINGBOT;
        }
        timePerShot = newTimePerShot;
        spectator = newSpectator;
        spectatorChat = newSpectatorChat;
    }

    /**
     * Constructor with a Game
     * @param g a Game
     */
    public Game(Game g){
        idGame = g.idGame;
        classicType = g.classicType;
        name = new String(g.name);
        humanOpponent = g.humanOpponent;
        status = g.status;
        timePerShot = g.timePerShot;
        spectator = g.spectator;
        spectatorChat = g.spectatorChat;
    }
    
    /**
     * Method to copy a Gamr
     * @param g a Game to copy
     * @return a copied Game
     */
    public Game clone(Game g){
        
        idGame = g.idGame;
        classicType = g.classicType;
        name = new String(g.name);
        humanOpponent = g.humanOpponent;
        status = g.status;
        timePerShot = g.timePerShot;
        spectator = g.spectator;
        spectatorChat = g.spectatorChat;
        
        return this;
    }
    
    /**
     * Accessor for idGame
     * @return the Game's id
     */
    public UUID getidGame(){
        return this.idGame;
    }
    
    /**
     * Mutator for status
     * @param status the game's new status
     */
    public void setStatus(StatusGame status){
        this.status = status;
    }
    /**
     * Accessor for status
     * @return the game's status as a StatusGame
     */
    public StatusGame getStatus(){
        return this.status;
    }
    
    /**
     * Accessor for classicType
     * @return the game's classicType as a boolean
     */
    public Boolean getClassicType(){
        return this.classicType;
    }       
    
    /**
     * Accessor for name
     * @return the game's name as a string
     */
    public String getName(){
        return this.name;
    }          
    
    /**
     * Accessor for humanOpponent
     * @return the game's humanOpponent as a boolean
     */
    public Boolean getHumanOpponent(){
        return this.humanOpponent;
    }             
    
    /**
     * Accessor for timePerShot
     * @return the game's timePerShot as an integer
     */
    public int getTimePerShot(){
        return this.timePerShot;
    }

    /**
     * Accessor for spectator
     * @return the game's spectator as a boolean
     */
    public Boolean getSpectator(){
        return this.spectator;
    }
  
    /**
     * Accessor for spectatorChat
     * @return the game's spectatorChat as a boolean
     */
    public Boolean getSpectatorChat(){
        return this.spectatorChat;
    }

}

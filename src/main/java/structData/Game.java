/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structData;
import java.util.UUID;
import java.util.HashSet;
import java.util.ArrayList;
import java.io.Serializable;
/**
 * Game is a class for the user's game
 */
public class Game implements Serializable{
    static final long serialVersionUID = 4L;
    private UUID idGame;
    private StatusGame status;
    private Boolean classicType;
    private String name;
    private Boolean humanOpponent;
    private Integer timePerShot;
    private Integer timeToPlaceBoats;
    private Boolean spectator;
    private Boolean spectatorChat;
    private HashSet<User> listSpectators;
    private Player player1;
    private Player player2;
    private Boolean player1Start;
    private ArrayList listMessages;
    
    /**
     * Default constructor
     * @param p : Profile of the user creating the Game
     */
    public Game(Profile p){
        idGame = UUID.randomUUID();
        status = StatusGame.WAITINGPLAYER;
        classicType = false;
        name = "";
        humanOpponent = false;
        timePerShot = 0;
        timeToPlaceBoats = 300;
        spectator = false;
        spectatorChat = false;
        listSpectators = new HashSet();
        player1 = new Player(p);
        player2 = null;
        player1Start = false;
        listMessages = new ArrayList();
    }
    
    /**
     * Constructor with all parameters
     * @param newClassicType the game type
     * @param newName the game's name
     * @param newHumanOpponent a boolean equal to 1 if 
     * the game is between two players and 0 if it is against a bot
     * @param newTimePerShot time per shot
     * @param newTimeToPlaceBoats
     * @param newSpectator a boolean equal to 1 if spectators are allowed
     * @param newSpectatorChat  a boolean equal to 1 if chat is allowed
     * @param p a Profile
     */
    public Game(Boolean newClassicType, String newName, 
            Boolean newHumanOpponent, Integer newTimePerShot, Integer newTimeToPlaceBoats,
            Boolean newSpectator, Boolean newSpectatorChat,
            Profile p){
        idGame = UUID.randomUUID();
        classicType = newClassicType;
        name = newName;
        humanOpponent = newHumanOpponent;
        if (humanOpponent){
            status = StatusGame.WAITINGPLAYER;
        }else{
            status = StatusGame.WAITINGBOT;
        }
        timePerShot = newTimePerShot;
        timeToPlaceBoats = newTimeToPlaceBoats;
        spectator = newSpectator;
        spectatorChat = newSpectatorChat;
        listSpectators = new HashSet();
            
        player1 = new Player(p);
        player1Start = true;
        listMessages = new ArrayList<>();
    }
    
    /**
     * Constructor with all parameters
     * @param newClassicType the game type
     * @param newName the game's name
     * @param newHumanOpponent a boolean equal to 1 if 
     * the game is between two players and 0 if it is against a bot
     * @param newTimePerShot time per shot
     * @param newTimeToPlaceBoats
     * @param newSpectator a boolean equal to 1 if spectators are allowed
     * @param newSpectatorChat  a boolean equal to 1 if chat is allowed
     * @param p a Profile
     * @param lS a Hashset
     * @param p1 a Player
     * @param p2 a Player
     * @param p1Start a Boolean
     * @param lMsg an ArrayList
     */
    public Game(Boolean newClassicType, String newName, 
            Boolean newHumanOpponent, Integer newTimePerShot,  Integer newTimeToPlaceBoats,
            Boolean newSpectator, Boolean newSpectatorChat,
            Profile p, HashSet lS, Player p1, Player p2,
            Boolean p1Start, ArrayList lMsg){
        idGame = UUID.randomUUID();
        classicType = newClassicType;
        name = newName;
        humanOpponent = newHumanOpponent;
        if (humanOpponent){
            status = StatusGame.WAITINGPLAYER;
            player2 = p2;
        }else{
            status = StatusGame.WAITINGBOT;
            User u = new User("Bot", "Bot");
            DataUser dU= new DataUser(u);
            Player pBot = new Player(dU);
            player2 = pBot;
        }
        timePerShot = newTimePerShot;
        timeToPlaceBoats = newTimeToPlaceBoats;
        spectator = newSpectator;
        spectatorChat = newSpectatorChat;
        if (spectator == true) {
            listSpectators = lS;
        }
        else {
            listSpectators = new HashSet();
        }
            
        player1 = p1;
        player1Start = p1Start;
        listMessages = lMsg;
    }

    
    /**
     * Constructor with a Game
     * @param g a Game
     */
    public Game(Game g){
        idGame = g.idGame;
        classicType = g.classicType;
        name = g.name;
        humanOpponent = g.humanOpponent;
        status = g.status;
        timePerShot = g.timePerShot;
        spectator = g.spectator;
        spectatorChat = g.spectatorChat;
        listSpectators = g.listSpectators;
        player1 = g.player1;
        player2 = g.player2;
        player1Start = g.player1Start;
        listMessages = g.listMessages;
    }
    
    /**
     * Method to copy a Game
     * @param g a Game to copy
     * @return a copied Game
     */
    public Game clone(Game g){
        
        idGame = g.idGame;
        classicType = g.classicType;
        name = g.name;
        humanOpponent = g.humanOpponent;
        status = g.status;
        timePerShot = g.timePerShot;
        spectator = g.spectator;
        spectatorChat = g.spectatorChat;
        listSpectators = g.listSpectators;
        player1 = g.player1;
        player2 = g.player2;
        player1Start = g.player1Start;
        listMessages = g.listMessages;
        
        return this;
    }
    
    /**
     * Accessor for idGame
     * @return the Game's id
     */
    public UUID getIdGame(){
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
   /**
     * Mutator for the list of messages
     * @param lMsg a HashSet
     */
    public void setListMessages(ArrayList lMsg){
        this.listMessages = lMsg;    
    }
    
    /**
     * Accessor for the list of messages
     * @return a list of message as an ArrayList
     */
    public ArrayList getListMessages(){
        return this.listMessages;
    }
    
    /**
     * Add a message to the list of message
     * @param msg the message to add
     */
    public void addMessage(ChatMessage msg){
        listMessages.add(msg);
    }
 
    /**
     * Mutator for the list of spectator
     * @param lS a HashSet
     */
    public void setListSpectators(HashSet<User> lS){
        if (this.spectator == true) {
            this.listSpectators = lS;
        }       
    }
    
    /**
     * Accessor for the list of spectator
     * @return a list of spectator as an HashSet
     */
    public HashSet getListSpectators(){
        return this.listSpectators;
    }
    
    /**
     * Add a spectator to the list of spectator
     * @param spectator the user to add
     */
    public void addSpectators(User spectator){
        listSpectators.add(spectator);
    }
    
     /**
     * Accessor for the player 1
     * @return player 1 as a Player
     */
    public Player getPlayer1(){
        return this.player1;
    }
    
    /**
     * Accessor for the player 2
     * @return player 2 as a Player
     */
    public Player getPlayer2(){
        return this.player2;
    }
    
     /**
     * Accessor for player1Start
     * @return player1Start as a Boolean
     */
    public Boolean getPlayer1Start(){
        return this.player1Start;
    }
    
    /**
     * Mutator for player 1
     * @param p1 the new player 1
     */
     public void setPlayer1(Player p1){
        player1 = p1;
    }

    /**
     * Mutator for player 2
     * @param p2 : the new player 2
     */    
    public void setPlayer2(Player p2){
        player2 = p2;
    }
    
    /**
     * Check if a profile belongs to a Game
     * @param p : a profile
     * @return a boolean if the profile belongs to one of the Game's Players
     */
    public Boolean doesProfileBelongToGame(Profile p){
        return ((this.player1 != null && this.player1.compareProfileToPlayer(p)) || (this.player2 != null && this.player2.compareProfileToPlayer(p)));
    }
    
    public int getTimeToPlaceBoats(){
        return timeToPlaceBoats;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structData;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.UUID;
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
    
    /**
     * Default Constructor
     * @param g
     * @param p 
     */
    public DataGame(Game g, Profile p){
        super(g);
        listSpectators = new HashSet();
        player1 = new Player(p);
        player2 = new Player(p);
        player1Start = false;
        listMessages = new ArrayList();
    }
    
    /**
     * Constructor with all parameters
     * @param g a Game
     * @param p a Profile
     * @param lS a Hashset
     * @param p1 a Player
     * @param p2 a Player
     * @param p1Start a Boolean
     * @param lMsg an ArrayList
     */
    public DataGame(Game g, Profile p, HashSet lS, Player p1, Player p2,
            Boolean p1Start, ArrayList lMsg){
        super(g);
        
        if (spectator == true) {
            listSpectators = lS;
        }
        else {
            listSpectators = new HashSet();
        }
            
        player1 = p1;
        player2 = p2;
        player1Start = p1Start;
        listMessages = lMsg;
    }
    
    /**
     * Constructor without list of spectator
     * @param g a Game
     * @param p a Profile
     * @param p1 a Player
     * @param p2 a Player
     * @param p1Start a Boolean
     * @param lMsg an ArrayList
     */
    public DataGame(Game g, Profile p, Player p1, Player p2,
            Boolean p1Start, ArrayList lMsg){
        super(g);
        listSpectators = new HashSet();  
        player1 = p1;
        player2 = p2;
        player1Start = p1Start;
        listMessages = lMsg;
    }
    
    /**
     * Method to copy a DataGame
     * @param dG the dataGame to copy
     * @return the copy of dG
     */
    public DataGame clone(DataGame dG){
        idGame = dG.idGame;
        status = dG.status;
        classicType = dG.classicType;
        name = dG.name;
        humanOpponent = dG.humanOpponent;
        timePerShot = dG.timePerShot;
        spectator = dG.spectator;
        spectatorChat = dG.spectatorChat;
        
        listSpectators = dG.listSpectators;
        player1 = dG.player1;
        player2 = dG.player2;
        player1Start = dG.player1Start;
        listMessages = dG.listMessages;
        
        return this;
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
    public void addMessage(Message msg){
        listMessages.add(msg);
    }
 
    /**
     * Mutator for the list of spectator
     * @param lS a HashSet
     */
    public void setListSpectators(HashSet lS){
        if (spectator == true) {
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
}


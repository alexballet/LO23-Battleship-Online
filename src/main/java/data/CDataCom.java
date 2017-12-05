/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import guiMain.GuiMainInterface;
import guiTable.GuiTableInterface;
import interfacesData.IDataCom;
import java.util.List;
import java.util.Set;
import lo23.battleship.online.network.COMInterface;
import structData.Boat;
import structData.ChatMessage;
import structData.Game;
import structData.Profile;
import structData.Shot;
import structData.StatusGame;
import structData.User;
import structData.Player;
import java.util.Iterator;

/**
 *
 * @author Irvin
 */
public class CDataCom implements IDataCom {
    
    private DataController controller;
    
    private GuiMainInterface interfaceMain;
    private GuiTableInterface interfaceTable;
    private COMInterface interfaceCom;
    
    public CDataCom(DataController dc){
        super();
        controller = dc;
    }
    
    public void setInterfaceMain(GuiMainInterface i){
        interfaceMain = i;
    }
    
    public void setInterfaceTable(GuiTableInterface i){
        interfaceTable = i;
    }
    
    public void setInterfaceCom(COMInterface c){
        interfaceCom = c;
    }

    //@Override
    public void getIPTableAdresses(Boolean withGame, Set iPs, Game dataGame) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
    * Returns the current Game
    * @return the current Game
    */    
    @Override
    public Game getCreatedGame() {
        Game g = controller.getLocalGame();
        return g;
    }

 
    @Override
    public void setGameJoinResponse(Boolean ok, Player player1, Player player2) {
       interfaceMain.setGameJoinResponse(true);
       controller.updateGameData(true, player1, player2);
    }

    /**
     * The distance user has refused the request to join the game 
     * @param no : Refuse of the request to join the game
     */
    @Override
    public void setGameJoinResponse(Boolean no){
        interfaceMain.setGameJoinResponse(false);
    }

    /**
    * After an user has connected, this user will be added to the list of user
    * @param u : The new user
    */
    @Override
    public void addUserToUserList(User u) {
        controller.addUserToList(u);
        interfaceMain.addUser(u);
    }

    /**
     * Sends the profile of a distant user to the local user 
     * @param profile : the profile of distant user
     */
    @Override
    public void sendStatistics(Profile profile) {
        interfaceMain.sendStatistics(profile);
    }

    /**
    * Add the player to the game if it is available.
    * @param sender : The player who sends this request
    * @param g : The game that the player wants to join
    * @return 1 if the parameter game is an avaiable game and add the player 
    * to this game, 0 if not
    */
    @Override
    public void notifToJoinGame(User sender, Game g) {
        Boolean isOk = false;
        for (Game ga: controller.getListGames()) {
            if (ga.getIdGame() == g.getIdGame()) {
                if (ga.getStatus() == StatusGame.WAITINGPLAYER){
                    isOk = true;
                }else{
                    isOk = false;           
                }
            }
        }
        interfaceCom.notifyJoinGameResponse(isOk, sender, g);
    }

    /**
    * Adds the game given as a parameter to the list of games.
    * @param g : The new game
    */
    @Override
    public void addNewGameList(Game g) {
        controller.addGameToList(g);
        interfaceMain.addGame(g);
    }
    
    @Override
    public void removeGameFromList(Game g){
        controller.removeGameFromList(g);
        
    }

    @Override
    public void errorPrint(String error) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void receiveMessage(ChatMessage message) {
        controller.getLocalGame().addMessage(message);
        interfaceTable.addChatMessage(message);
    }

    @Override
    public void receiveReady() {
                Boolean myTurn;
        Boolean p1Start = controller.getLocalGame().getPlayer1Start();
        Player localPlayer = controller.getLocalPlayer();
        Player p1 = controller.getLocalGame().getPlayer1();
        
        if ( p1Start == true && p1 == localPlayer ){
            myTurn = true;
        }
        else if ( p1Start == true && p1 != localPlayer ){
            myTurn = false;
        }
        else if ( p1Start == false && p1 != localPlayer ){
            myTurn = true;
        }
        else /*if ( p1Start == false && p1 == localPlayer )*/{
            myTurn = false;
        }
        
        interfaceTable.opponentReady(myTurn);
    }

    @Override
    public void coordinate(Shot s) {
        Boat b = controller.testShot(s);
        interfaceTable.displayOpponentShot(s, b);
        //interfaceCom.coordinates(s,b); TODO : décommenter quand la fonction sera crée chez COM
        if (b != null){
            boolean gameOver = true;            
            for(int i=0;i<controller.getLocalPlayer().getListBoats().size();i++) {
                if (!controller.getLocalPlayer().getListBoats().get(i).getStatus()){
                    gameOver = false;
                    break;
                }
                i++;
            }
            if (gameOver){
                //arreter la partie localPlayer a perdu
                interfaceTable.displayDefeat();
                //interfaceCom.notifyGameWon(); COM doit notifier à l'autre joueur qu'il a gagné
                controller.getLocalProfile().setGamesLost(controller.getLocalProfile().getGamesLost()+1);
                controller.getLocalProfile().setGamesPlayed(controller.getLocalProfile().getGamesPlayed()+1);
                controller.removeGameFromList(controller.getLocalGame()); // Verifier comment est géré la notification que la partie n'existe plus
                //interfaceMain.removeGame(controller.getLocalGame());
                //interfaceCom.removeGame(controller.getLocalGame());
            }              
            
        }
    }

    @Override
    public void coordinates(Shot s, Boat b) {
        interfaceTable.displayMyShotResult(s, b);
    }
    
    /**
     * Returns the local user's profile
     * @return the local user's profile
     */
    @Override
    public Profile getUserProfile() {
        Profile localProfile = controller.getLocalProfile();
        return localProfile;
    }
    
    /**
     * Takes a game given as a parameter and updates his status
     * @param g : the game which status has been modified
     */
    @Override
    public void changeStatusGame(Game g) {
        Game localGame  = controller.getLocalGame();
        controller.removeGameFromList(localGame);
        controller.updateGameStatus(g);
        interfaceMain.transmitNewStatus(g);
    }
    public User getLocalUser(){
        return controller.getLocalUser();
    }
    
    /**
      * Notification that you won, update stats and display win
      */
    @Override
     public void notifiedGameWon(){
         controller.getLocalProfile().setGamesWon(controller.getLocalProfile().getGamesWon()+1);
         controller.getLocalProfile().setGamesPlayed(controller.getLocalProfile().getGamesPlayed()+1);
         interfaceTable.displayVictory();
     }
     
     @Override
     public void notifyToSpecGame(Game g, User spec){
         
         controller.updateSpecList(g,spec);
     }
    
}

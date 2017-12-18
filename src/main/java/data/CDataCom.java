/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import guiMain.GuiMainInterface;
import guiTable.GuiTableInterface;
import interfacesData.IDataCom;

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
    
    private final DataController controller;
    
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

    
    public void getIPTableAdresses(Boolean withGame, Set iPs, Game dataGame) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
    * Returns the current Game
    * @return the current Game
    */    
    
    public Game getCreatedGame() {
        Game g = controller.getLocalGame();
        return g;
    }

 
    
    public void setGameJoinResponse(Boolean ok, Player player1, Player player2) {
       interfaceMain.setGameJoinResponse(true);
       controller.updateGameData(true, player1, player2);
    }

    /**
     * The distance user has refused the request to join the game 
     * @param no : Refuse of the request to join the game
     */
    
    public void setGameJoinResponse(Boolean no){
        interfaceMain.setGameJoinResponse(false);
    }

    /**
    * After an user has connected, this user will be added to the list of user
    * @param u : The new user
    */
    
    public void addUserToUserList(User u) {
        controller.addUserToList(u);
        interfaceMain.addUser(u);
    }

    /**
     * Sends the profile of a distant user to the local user 
     * @param profile : the profile of distant user
     */
    
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
    
    public void notifToJoinGame(Profile sender, Game g) {
        Boolean isOk = false;
        for (Game ga: controller.getListGames()) {
            if (ga.getIdGame().equals(g.getIdGame())) {
                if (ga.getStatus().equals(StatusGame.WAITINGPLAYER)){
                    isOk = true;
                    ga.setStatus(StatusGame.BOATPHASE);
                    Player p = new Player(sender);
                    ga.setPlayer2(p);
                    interfaceCom.changeStatusGame(ga);
                    g = ga;
                }else{
                    isOk = false;           
                }
            }
        }
        System.out.println("CDataCom isok " + isOk);
        interfaceCom.notifyJoinGameResponse(isOk, sender, g);
        interfaceMain.openPlacementPhase(g);
    }

    /**
    * Adds the game given as a parameter to the list of games.
    * @param g : The new game
    */
    
    public void addNewGameList(Game g) {
        controller.addGameToList(g);
        interfaceMain.addGame(g);
    }
    
    @Override
    public void removeGameFromList(Game g){
        controller.removeGameFromList(g);
        
    }

    
    public void errorPrint(String error) {
        //wait the method errorPrint in GuiTableInterface.java and GuiMainInterface.java
        //Interfacetable.errorPrint(error);
        //InterfaceMain.errorPrint(error);
    }

    
    public void receiveMessage(ChatMessage message) {
        controller.getLocalGame().addMessage(message);
        System.out.println("Message: " + message);
        System.out.println("interfaceTable: " + interfaceTable);
        interfaceTable.addChatMessage(message);
    }

    
    public void receiveReady() {
        //TODO decrasser le code !!! les conditions sont toujours les mêmes donc faire ça proprement
        Boolean myTurn;
        Boolean p1Start = controller.getLocalGame().getPlayer1Start();
        Player localPlayer = controller.getLocalPlayer();
        Player p1 = controller.getLocalGame().getPlayer1();

        if ( p1Start && p1.getProfile().getIdUser().equals(localPlayer.getProfile().getIdUser()) ) {
            controller.getLocalGame().getPlayer2().setReady(true);
        } else {
            controller.getLocalGame().getPlayer1().setReady(true);
        }

        
        
        
        if(controller.getLocalGame().getPlayer1().isReady() &&
                controller.getLocalGame().getPlayer2().isReady())
        {
            System.out.println("2 player are ready");
            if (p1Start && p1.getProfile().getIdUser().equals(localPlayer.getProfile().getIdUser())) {
                myTurn = true;
            } else if (p1Start && !p1.getProfile().getIdUser().equals(localPlayer.getProfile().getIdUser())) {
                myTurn = false;
            } else if (p1Start && !p1.getProfile().getIdUser().equals(localPlayer.getProfile().getIdUser())) {
                myTurn = true;
            } else /*if ( p1Start == false && p1 == localPlayer )*/ {
                myTurn = false;
            }
            //System.out.println("myturn"+ myTurn);

            interfaceTable.opponentReady(myTurn);
        }
    }



    public void coordinates(Shot s) {
        Boat b = controller.testShot(s);
        interfaceTable.displayOpponentShot(s, b);
        interfaceCom.coordinates(controller.getOtherPLayer() , s, controller.getLocalGame());
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
                
                //notify the other player that he has won
                Player pl; // to know to which player we send the notification : it's the player who is not ourself
                if (controller.getLocalGame().getPlayer1() == controller.getLocalPlayer())
                    pl = controller.getLocalGame().getPlayer2();
                else
                    pl = controller.getLocalGame().getPlayer1();
                //interfaceCom.notifyGameWon(pl);
                
                controller.getLocalProfile().setGamesLost(controller.getLocalProfile().getGamesLost()+1);
                controller.getLocalProfile().setGamesPlayed(controller.getLocalProfile().getGamesPlayed()+1);
                controller.removeGameFromList(controller.getLocalGame()); // Verifier comment est géré la notification que la partie n'existe plus
                //interfaceMain.removeGame(controller.getLocalGame());
                //interfaceCom.removeGame(controller.getLocalGame());
            }              
            
        }
    }

    
    public void coordinates(Shot s, Boat b) {
        controller.getLocalPlayer().addShot(s);
        interfaceTable.displayMyShotResult(s, b);
    }
    
    /**
     * Returns the local user's profile
     * @return the local user's profile
     */
    
    public Profile getUserProfile() {
        Profile localProfile = controller.getLocalProfile();
        return localProfile;
    }
    
    /**
     * Takes a game given as a parameter and updates his status
     * @param g : the game which status has been modified
     */
    
    public void changeStatusGame(Game g) {
       /* Game localGame  = controller.getLocalGame();
        if (localGame != null) controller.removeGameFromList(localGame);
        controller.updateGameStatus(g);
        interfaceMain.transmitNewStatus(g);*/
        controller.updateGameStatus(g);
        interfaceMain.transmitNewStatus(g);
    }
    
    public User getLocalUser(){
        return controller.getLocalUser();
    }
    
    
    public void setLocalGame(Game g){
        controller.setLocalGame(g);
        interfaceMain.openPlacementPhase(g);
    }
    
    
    public void removeUser(User u){
        controller.removeUserFromList(u);
        interfaceMain.removeUser(u);
    }
    
    
    public void removeGame(Game g){
        controller.removeGameFromList(g);
        interfaceMain.removeGame(g);
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

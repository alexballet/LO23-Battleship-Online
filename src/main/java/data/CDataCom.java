/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import guiMain.GuiMainInterface;
import interfacesData.IDataCom;
import java.util.List;
import java.util.Set;
import lo23.battleship.online.network.COMInterface;
import structData.Boat;
import structData.ChatMessage;
import structData.Game;
import structData.Position;
import structData.Profile;
import structData.Shot;
import structData.StatusGame;
import structData.User;
import structData.Player;

/**
 *
 * @author Irvin
 */
public class CDataCom implements IDataCom {
    
    private DataController controller;
    
    private GuiMainInterface interfaceMain;
    private COMInterface interfaceCom;
    
    public CDataCom(DataController dc){
        super();
        controller = dc;
    }
    
    public void setInterfaceCom(COMInterface i){
        interfaceCom = i;
    }
    
    public void setInterfaceMain(GuiMainInterface i){
        interfaceMain = i;
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
    public void errorPrint(String error) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void receiveMessage(ChatMessage message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void receiveReady() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void coordinate(Position p, Shot s, Boat b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void coordinates(Shot s, Boat b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
    public void setLocalGame(Game g){
        controller.setLocalGame(g);
    }
    
    public void removeUser(User u){
        controller.removeUserFromList(u);
        interfaceMain.removeUser(u);
    }
}

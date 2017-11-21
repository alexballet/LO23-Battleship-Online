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
import java.util.HashSet;
import structData.Boat;
import structData.ChatMessage;
import structData.Game;
import structData.Position;
import structData.Profile;
import structData.Shot;
import structData.User;
import structData.Player;

/**
 *
 * @author Irvin
 */
public class CDataCom implements IDataCom {
    
    private DataController controller;
    
    private GuiMainInterface interfaceMain;
    
    public CDataCom(DataController dc){
        super();
        controller = dc;
    }
    
    public void setInterfaceMain(GuiMainInterface i){
        interfaceMain = i;
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
    public void addGame(List<Game> createdGames) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



 /**
    * 
    * @param ok : true if game is accepted, false if game is refused
    * @param player1 : local player
    * @param player2 : distant player
    */
    @Override
    public void setGameJoinResponse(Boolean ok, Player player1, Player player2) {
        
       interfaceMain.setGameJoinResponse(ok);
       
       if (ok == true){
           controller.updateGameData(ok, player1, player2); 
       }

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

    @Override
    public Boolean notifToJoinGame(User sender, Game g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import guiMain.GuiMainInterface;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import lo23.battleship.online.network.COMInterface;
import structData.Game;
import structData.User;
import structData.DataUser;
import structData.Profile;
import structData.StatusGame;
import structData.Player;

/**
 *
 * @author Irvin
 */
public class DataController {
    
    private CDataCom interfaceDataCom;
    private CDataMain interfaceDataMain;
    private CDataTable interfaceDataTable;
    
    private GuiMainInterface interfaceMain;
    private COMInterface interfaceCom;
   
    //private
        
    private User localUser;
    private DataUser localDataUser;
    private Game localGame;
    private List<User> listUsers;
    private Profile localProfile;
    private List<Game> listGames;
    
    /**
     * DataController
     */
    public DataController(){
        //localUser = new User(); //test
        interfaceDataCom = new CDataCom(this);
        interfaceDataMain = new CDataMain(this);
        interfaceDataTable = new CDataTable(this);
        
        listUsers = new ArrayList<User>();
        listGames = new ArrayList<Game>();
        //localDataUser = new DataUser(localUser);
        //localProfile = new Profile(localDataUser);
    }
    
    /**
     * Mutator for interfaceMain
     * @param i : new interfaceMain
     */
    public void setInterfaceMain(GuiMainInterface i){
        interfaceMain = i;
        interfaceDataCom.setInterfaceMain(i);
    }
    
    /**
     * Mutator for interfaceCom
     * @param i : new interfaceCom
     */
    public void setInterfaceCom(COMInterface i){
        interfaceCom = i;
        interfaceDataCom.setInterfaceCom(i);
        interfaceDataMain.setInterfaceCom(i);
    }
    
    /**
     * Accessor for interfaceDataCom
     * @return interfaceDataCom
     */
    public CDataCom getInterfaceDataCom(){
        return interfaceDataCom;
    }
    
    /**
     * Accessor for interfaceDataMain
     * @return interfaceDataMain
     */
    public CDataMain getInterfaceDataMain(){
        return interfaceDataMain;
    }
    
    /**
     * Accessor for interfaceDataTable
     * @return interfaceDataTable
     */
    public CDataTable getInterfaceDataTable(){
        return interfaceDataTable;
    }
    
    /**
     * Accessor for the local user 
     * @return local user
     */
    public User getLocalUser(){
        return localUser;
    }
    
    /**
     * Accessor for local DataUser
     * @return the local DataUser
     */
    public DataUser getLocalDataUser(){
        return localDataUser;
    }
    
    /**
     * Accessor for local Profile
     * @return the local Profile
     */
    public Profile getLocalProfile(){
        return localProfile;
    }

    /**
     * Mutator for User
     * @param u : new User
     */
    public void setLocalUser(User u){
        localUser = u;
    }
    
    /**
     * Mutator for local DataUser
     * @param du : new DataUser
     */
    public void setLocalDataUser (DataUser du){
        localDataUser = du;
    }
    
    /**
     * Mutator for local Profile
     * @param p : new local Profile
     */
    public void setLocalProfile (Profile p){
        localProfile = p;
    }

    /**
     * Add User to local list
     * @param u : User to add
     */
    public void addUserToList(User u){
        listUsers.add(u);
    }
    
    /**
     * Remove a user form local list
     * @param u : User to remove
     */
    public void removeUserFromList(User u){
        //comparer les UUID de u et des objets de listUser et enlever l'user si présent
        listUsers.remove(u);
    }
    

    /**
    * Accessor local Game
    * @return the local Game
    */
    public Game getLocalGame(){
        return localGame;
    }

    /**
     * Mutator local Game
     * @param g : new local Game
     */
    public void setLocalGame(Game g){
        localGame = g;
    }
    
    /**
     * Add a Game to the local list
     * @param g : game to add to the local list
     */
    public void addGameToList(Game g){
        if(g != null)
            listGames.add(g);
    }
    
    /**
     * Update the status of game and add it into the list of Game
     * @param g : the game which status has been modified
     */
    public void updateGameStatus(Game g){
        localGame = g;
        listGames.add(localGame);
    }
    /**
     * Remove the game
     * @param g : game has to be remove
     */
    public void removeGameFromList(Game g){
    	for (Game game : listGames) {
			if (game.getIdGame().equals(g.getIdGame())) {
				listGames.remove(game); 
				return;
			}
		}
        
    }
    
     /**
     * used by the method setGameJoinResponse of CDataCom
     * @param ok 
     * @param player1
     * @param player2
     */
    public void updateGameData(Boolean ok, Player player1, Player player2){
        if (ok == true){
            localGame.setStatus(StatusGame.BOATPHASE);
            localGame.setPlayer1(player1);
            localGame.setPlayer2(player2);
        }
        else{
            localGame.setStatus(StatusGame.WAITINGPLAYER);
        }
        
    }

    
    /**
     * Get list of Games
     * @return the list of games
     */
    public List<Game> getListGames(){
        return listGames;
    }
    
    /**
     * Reload local profile previously saved
     */
    public void reloadSavedProfile(){
        try {
         FileInputStream fis = new FileInputStream("profile.ser");
         ObjectInputStream ois = new ObjectInputStream(fis);
         localProfile = (Profile) ois.readObject(); 
         localUser = new User(localProfile);
         localDataUser = new DataUser(localProfile);
         ois.close();
         
      } catch (Exception e) { 
         e.printStackTrace(); 
      }
    }
}

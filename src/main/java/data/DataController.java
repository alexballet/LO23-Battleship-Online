/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import guiMain.GuiMainInterface;
import java.util.HashSet;
import java.util.Set;
import lo23.battleship.online.network.COMInterface;
import structData.Game;
import structData.User;
import structData.DataUser;
import structData.Profile;

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
    private HashSet<User> listUsers;
    private Profile localProfile;
    private HashSet<Game> listGames;
    
    
    public DataController(){
        User user = new User("truc", "machin"); // for test
           
        interfaceDataCom = new CDataCom(this);
        interfaceDataMain = new CDataMain(this);
        interfaceDataTable = new CDataTable(this);
        
        listUsers = new HashSet<User>();
        localDataUser = new DataUser(user);
        localProfile = new Profile(localDataUser);
    }
    
    public void setInterfaceMain(GuiMainInterface i){
        interfaceMain = i;
        interfaceDataCom.setInterfaceMain(i);
    }
    
    public void setInterfaceCom(COMInterface i){
        interfaceCom = i;
        interfaceDataMain.setInterfaceCom(i);
    }
    
    
    public CDataCom getInterfaceDataCom(){
        return interfaceDataCom;
    }
    
    public CDataMain getInterfaceDataMain(){
        return interfaceDataMain;
    }
    
    public CDataTable getInterfaceDataTable(){
        return interfaceDataTable;
    }
    
    public User getLocalUser(){
        return localUser;
    }
    
    public DataUser getLocalDataUser(){
        return localDataUser;
    }
    
    public Profile getLocalProfile(){
        return localProfile;
    }

    public void setLocalUser(User u){
        localUser = u;
    }
    
    public void addUserToList(User u){
        listUsers.add(u);
    }
    
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
     * Add a Game to the local list
     * @param g : game to add to the local list
     */
    public void addGameToList(Game g){
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
        listGames.remove(g);
    }
}

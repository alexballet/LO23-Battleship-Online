/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import guiMain.GuiMainInterface;
import java.util.HashSet;
import java.util.Set;
import structData.Game;
import structData.User;

/**
 *
 * @author Irvin
 */
public class DataController {
    
    private CDataCom interfaceDataCom;
    private CDataMain interfaceDataMain;
    private CDataTable interfaceDataTable;
    
    private GuiMainInterface interfaceMain;
    private NetworkModuleInterface interfaceCom;
    
    
    
    //private
        
    private User localUser;
    private Game localGame;
    private HashSet<User> listUsers;
    private HashSet<Game> listGames;
    
    
    public DataController(){
        interfaceDataCom = new CDataCom(this);
        interfaceDataMain = new CDataMain(this);
        interfaceDataTable = new CDataTable(this);
        
        listUsers = new HashSet<User>();
    }
    
    public void setInterfaceMain(GuiMainInterface i){
        interfaceMain = i;
        interfaceDataMain.setInterfaceMain(i);
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
}

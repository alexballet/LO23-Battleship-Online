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
import structData.*;

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
    private DataGame localDataGame;
    private HashSet<User> listUsers;
    private Profile localProfile;
    private HashSet<DataGame> listDataGames;
    
    
    public DataController(){
        interfaceDataCom = new CDataCom(this);
        interfaceDataMain = new CDataMain(this);
        interfaceDataTable = new CDataTable(this);
        
        listUsers = new HashSet<User>();
        //localDataUser = new DataUser(localUser);
        //localProfile = new Profile(localDataUser);
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
    * Accessor local DataGame
    * @return the local DataGame
    */
    public DataGame getLocalDataGame(){
        return localDataGame;
    }

    public void eraseNetwork() {

    }
}

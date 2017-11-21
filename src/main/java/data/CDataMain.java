/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import guiMain.GuiMainInterface;
import interfacesData.IDataMain;
import java.util.Date;
import lo23.battleship.online.network.COMInterface;
import structData.ContactGroup;
import structData.Game;
import structData.User;
import structData.DataUser;
import structData.Profile;
import java.awt.Image;
import java.util.HashSet;


/**
 *
 * @author Irvin
 */
public class CDataMain implements IDataMain {
    
    private DataController controller;
    
    private COMInterface interfaceCom;
    
    
    public CDataMain(DataController dc){
        super();
        controller = dc;
    }
    
    public void setInterfaceCom(COMInterface i){
        interfaceCom = i;
    }

    @Override
    public void editProfile(String username, String password, Image avatar, String lastName, String firstName, Date birthDate) {
  
        controller.getLocalUser().setUsername(username);
        controller.getLocalDataUser().setPassword(password);
        controller.getLocalProfile().setAvatar(avatar);
        controller.getLocalProfile().setLastname(lastName);
        controller.getLocalProfile().setName(firstName);
        controller.getLocalProfile().setBirthdate(birthDate);
        
    }

    @Override
    public void createAccount(String login, String username, HashSet ips, String password, String contactList, Image avatar, String lastname, String firstname, Date birthDate) {
        User newUser = new User(login,username);
        newUser.setIPs(ips);
        
        DataUser newDataUser = new DataUser(newUser,password,contactList);
        
        Profile newProfile = new Profile(newDataUser,avatar,lastname,firstname,birthDate);
     
        controller.setLocalUser(newUser);
        controller.addUserToList(newUser);
    }

    @Override
    public void addUser(User u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getStatistics(User u, int nbGamePlayed, int nbGameWon, int nbGameLost, int nbGameAbandoned) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifGameChosen(Game g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void askDisconnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void connection() {
        User u = new User("login", "username");
        controller.setLocalUser(u);
        interfaceCom.searchForPlayers(u);
    }

    @Override
    public void newGame(Game g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

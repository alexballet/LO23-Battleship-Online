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
import java.awt.Image;


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
    public void createAccount(String idUser, String login, String username, Long[] ips, String password, ContactGroup[] contactList, int avatar, String lastname, String firstname, Date birthDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        //interfaceCom.searchForPlayers()
    }

    @Override
    public void newGame(Game g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

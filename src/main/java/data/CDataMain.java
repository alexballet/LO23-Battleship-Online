/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import interfacesData.IDataMain;
import java.util.Date;
import structData.ContactGroup;
import structData.Game;
import structData.Profile;
import structData.User;

/**
 *
 * @author Irvin
 */
public class CDataMain implements IDataMain {
    
    private DataController controller;
    
    public CDataMain(DataController dc){
        super();
        controller = dc;
    }

    @Override
    public void editProfile(String username, String password, int avatar, String lastName, String firstName, Date borthDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public Profile getStatistics() {
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
        //searchForPlayers(controller.getUser().getiPs());
    }

    @Override
    public void newGame(Game g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import interfacesData.IDataMain;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import lo23.battleship.online.network.COMInterface;
import structData.ContactGroup;
import structData.Game;
import structData.Player;
import structData.User;
import structData.DataUser;
import structData.Profile;
import javax.swing.ImageIcon;
import java.util.HashSet;
import java.util.List;


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
    public void editProfile(String username, String password, String avatar, String lastName, String firstName, Date birthDate) {
  
        controller.getLocalUser().setUsername(username);
        controller.getLocalDataUser().setPassword(password);
        controller.getLocalProfile().setAvatar(avatar);
        controller.getLocalProfile().setLastname(lastName);
        controller.getLocalProfile().setName(firstName);
        controller.getLocalProfile().setBirthdate(birthDate);
        
    }

    @Override
    public void createAccount(String login, String username, HashSet ips, String password, List<ContactGroup> contactList, String avatar, String lastname, String firstname, Date birthDate) {
        User newUser = new User(login,username);
        newUser.setIPs(ips);
        
        DataUser newDataUser = new DataUser(newUser,password,contactList);
        
        Profile newProfile = new Profile(newDataUser,avatar,lastname,firstname,birthDate);
        newProfile.saveProfile();
        
        // controller.setLocalUser(newUser);
        // controller.setLocalDataUser(newDataUser);
        // controller.setLocalProfile(newProfile);
    }

    @Override
    public Profile getStatistics() {
        Profile p = controller.getLocalProfile();
        return p;
    }
    
    /**
     * Notifies the away application that an user wants to join the game given as parameter
     * @param g : the game the user wants to join
     */
    @Override
    public void notifGameChosen(Game g) {
        User u = controller.getLocalUser();
        interfaceCom.joinGame(g);
    }

    @Override
    public void askDisconnection() {
        interfaceCom.askDisconnection();
    }

    @Override
    public Boolean connection() throws UnknownHostException {
        Boolean result = false;
        HashSet<InetAddress> IPs = new HashSet<>();
        controller.reloadSavedProfile();
        if(controller.getLocalProfile() != null){
            result = true;
        }
        interfaceCom.searchForPlayers();
        return result;
    }

    /**
     * Adds a new game to the list of games
     */
    @Override
    public Game newGame(Boolean newClassicType, String newName, 
            Boolean newHumanOpponent, int newTimePerShot, 
            Boolean newSpectator, Boolean newSpectatorChat) {
    		
    		Game g = new Game(newClassicType, newName, newHumanOpponent, newTimePerShot, newSpectator, newSpectatorChat, controller.getLocalProfile());
        controller.addGameToList(g);
        interfaceCom.notifyNewGame(g);
        
        return g;
    }
}
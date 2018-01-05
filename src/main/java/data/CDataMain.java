/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import interfacesData.IDataMain;
import java.net.UnknownHostException;
import java.util.Date;
import lo23.battleship.online.network.COMInterface;
import structData.ContactGroup;
import structData.Game;
import structData.User;
import structData.DataUser;
import structData.Profile;
import structData.Player;
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
        if (password != null && !password.isEmpty()) {
        		controller.getLocalDataUser().setPassword(password);
        }
        controller.getLocalProfile().setAvatar(avatar);
        controller.getLocalProfile().setLastname(lastName);
        controller.getLocalProfile().setName(firstName);
        controller.getLocalProfile().setBirthdate(birthDate);
        controller.getLocalProfile().saveeditedProfile();
    }

    @Override
    public void createAccount(String login, String username, HashSet ips, int port, String password, List<ContactGroup> contactList, String avatar, String lastname, String firstname, Date birthDate) {
        User newUser = new User(login,username);
        newUser.setIPs(ips);
        	
        newUser.setPort(port);
        
        DataUser newDataUser = new DataUser(newUser,password,contactList);
        
        Profile newProfile = new Profile(newDataUser,avatar,lastname,firstname,birthDate);
        newProfile.saveProfile();
        
        // controller.setLocalUser(newUser);
        // controller.setLocalDataUser(newDataUser);
        // controller.setLocalProfile(newProfile);
    }

    @Override
    public void getStatistics(Profile p) {
        //demander à l'interface de la com WHAT THE FUCK ????
    }
    
    /*
     * 
     */
    @Override
    public Profile getLocalProfile() {
        return controller.getLocalProfile();
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
        controller = new DataController();
    }

    @Override
    public Boolean connection(String login, String password) throws UnknownHostException {
        Boolean result = false;
        controller.reloadSavedProfile(login, password);
        if(controller.getLocalProfile() != null){
            Player p = new Player(controller.getLocalProfile());
            controller.setLocalPlayer(p);
            interfaceCom.searchForPlayers();
            result = true;
        }
        return result;
    }

    @Override
    public Game newGame(Boolean newClassicType, String newName, 
            Boolean newHumanOpponent, Integer newTimePerShot, Integer newTimeToPlaceBoats,
            Boolean newSpectator, Boolean newSpectatorChat) {
    		
        Game g = new Game(newClassicType, newName, newHumanOpponent, newTimePerShot, newTimeToPlaceBoats, newSpectator, newSpectatorChat, controller.getLocalProfile());
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("JOIN NEW GAME LOCAL BEFORE SET: " + g.getListSpectators().size());
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("############################################");
        controller.addGameToList(g);
        interfaceCom.notifyNewGame(g);
        controller.setLocalGame(g);
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("JOIN NEW GAME LOCAL: " + controller.getLocalGame().getListSpectators().size());
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("############################################");
        System.out.println("############################################");

        return g;
    }
    
    @Override
    public void removeGame(Game g){
        interfaceCom.removeGame(g);
    }

    @Override
    public List<Game> getGames() {
        return controller.getListGames();
    }

    @Override
    public void setLocalGame(Game g){
        controller.setLocalGame(g);
    }
    
    @Override
     public void getProfile(User u){
         interfaceCom.getProfile(u);
     }
     
    @Override
     public void setListIps(HashSet Ips){
         controller.getLocalProfile().setIPs(Ips);
         interfaceCom.searchForPlayers();
         controller.getLocalProfile().saveProfile();
     }

    
    /**
     * Add a spectator in the game
     * @param g : game that the spectator wants to join
     */
    @Override
    public void gameToSpec(Game g){
        interfaceCom.getInfoGameForSpectator(g.getPlayer1(), controller.getLocalUser());
    }

    @Override
    public void setPort(int p) {
        controller.getLocalProfile().setPort(p);
        controller.getLocalProfile().saveProfile();
    }
    
    /**
     * Get the list of connected users
     * @return the list of connected users
     */
    @Override
    public List<User> getListUsers() {
        return controller.getListUsers();
    }
    public void clear() {
        controller.clearData();
    }
}
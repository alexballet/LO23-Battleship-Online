
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import com.sun.org.apache.xpath.internal.SourceTree;
import guiMain.GuiMainInterface;
import java.io.File;
import guiTable.GuiTableInterface;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.application.Platform;
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
    private GuiTableInterface interfaceTable;
    private COMInterface interfaceCom;
   
    //private
        
    private User localUser;
    // private DataUser localDataUser;
    private Game localGame;
    private Game attendedGame;
    private List<User> listUsers;
    private Profile localProfile;
    private List<Game> listGames;
    private Player localPlayer;
    
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
        interfaceDataTable.setInterfaceMain(i);
    }
        
    public void setInterfaceTable(GuiTableInterface i){
        interfaceTable = i;
        interfaceDataCom.setInterfaceTable(i);
        /* ajout ihm-plateau débug   */
        interfaceDataTable.setInterfaceTable(i);
        /* ajout ihm-plateau débug   */
    }
    
    public GuiTableInterface getTableInterface() {
        return interfaceTable;
    }
    
    /**
     * Mutator for interfaceCom
     * @param i : new interfaceCom
     */
    public void setInterfaceCom(COMInterface i){
        interfaceCom = i;
        interfaceDataCom.setInterfaceCom(i);
        interfaceDataMain.setInterfaceCom(i);
        interfaceDataCom.setInterfaceCom(i);
        interfaceDataTable.setInterfaceCom(i);
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
        return (DataUser) localUser;
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
        localUser = du;
    }
    
    /**
     * Mutator for local Profile
     * @param p : new local Profile
     */
    public void setLocalProfile (Profile p){
        localProfile = p;
    }
    
    public void setLocalPlayer(Player p){
        localPlayer = p;
    }
    
    public Player getLocalPlayer(){
        return localPlayer;
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
     * Accessor local Game
     * @return the local Game
     */
    public Game getAttendedGame(){
        return attendedGame;
    }
    
    public void setAttendedGame(Game g) {
        this.attendedGame = g;
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
        if(g != null) {
        		boolean isOk = true;
        		for (Game game : listGames) {
        			if (game.getIdGame().equals(g.getIdGame())) isOk = false;
        		}
        		if (isOk) listGames.add(g);
        }
    }
    
    
    /**
     * Update the status of game and add it into the list of Game
     * @param g : the game which status has been modified
     */
    public void updateGameStatus(Game g){
        for (int i = 0; i < listGames.size(); i++) {
            if (listGames.get(i).getIdGame().equals(g.getIdGame())){
                listGames.set(i, g);
                break;
            }
        }
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
        if (ok){
            localGame.setStatus(StatusGame.BOATPHASE);
            localGame.setPlayer1(player1);
            localGame.setPlayer2(player2);
        }
        else{
            localGame.setStatus(StatusGame.WAITINGPLAYER);
        }
        
    }

    /**
     * used by the method setGameJoinResponse of CDataCom
     */

    private boolean isPlayer1() {
        return getLocalUser().getIdUser().equals(
                getLocalGame().getPlayer1().getProfile().getIdUser());
    }

    public void updateGameDataPlaying(Shot s, Boat b, boolean forLocalPlayer) {
        Player otherPlayer;
        Player PlayerWhoMadeShot;
        if(forLocalPlayer) {
            System.out.println("Update GameData Playing for Me");
            otherPlayer = getOtherPLayer();
            PlayerWhoMadeShot = getLocalPlayerInGame();
            localPlayer.addShot(s);
        }
        else {
            System.out.println("Update GameData Playing for Opponent");
            PlayerWhoMadeShot = getOtherPLayer();
            otherPlayer = getLocalPlayerInGame();
        }

        if(b != null)
            otherPlayer.addBoat(b);

        PlayerWhoMadeShot.addShot(s);
    }

    
    /**
     * Get list of Games
     * @return the list of games
     */
    public List<Game> getListGames(){
        return listGames;
    }
    
    /**
     * Get list of Users
     * @return the list of users
     */
    public List<User> getListUsers(){
        return listUsers;
    }
    
    /**
     * Reload local profile previously saved given the login and password given
     * @param login : login written by User 
     * @param mdp : password written by User
     */
    public void reloadSavedProfile(String login, String mdp){
        
        File dir =new File(".");
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
          for (File child : directoryListing) {
            if (child.getName().contains(".ser")){
                try {
                    FileInputStream fis = new FileInputStream(child.getName());
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    Profile p = (Profile) ois.readObject();
                    ois.close();
                    fis.close();
                    if ((p.getLogin().equals(login)) && (p.getPassword().equals(mdp))){
                        localProfile = p; 
                        localUser = localProfile;
                        // localDataUser = localProfile;
                        break;
                    }            
                } catch (Exception e) { 
                    e.printStackTrace(); 
                }
            }
          }
          if (localProfile != null && localProfile.getName().equals(""))
              System.out.print("No profile found with this login and password");
        } else {
            System.out.print("no file found on this computer");
        }
    }
    
    /**
     * Test if a boat is touched or sunk by a shot
     * @param s : shot of the opponent to test
     * @return a boat if a boat has been sunk
     */
    public Boat testShot(Shot s){
        Boat b ;
        List<Boat> listBoat = localPlayer.getListBoats();
        for (Boat boat : listBoat) {
            if(boat.getSunk())
                continue;
            b = boat.updateShot(s);
            if(b.getSunk()) {
                return b;
            }
        }
        return null;
    }
    
    public void setListUser(List<User> u){
        listUsers = u;
    }
    
    public void setListGame(List<Game> g){
        listGames = g;
    }
    
    public Player getOtherPLayer() {
        if (isPlayer1()) {
            return getLocalGame().getPlayer2();
        } else {
            return getLocalGame().getPlayer1();
        }
    }

    public Player getLocalPlayerInGame() {
        if (isPlayer1()) {
            return getLocalGame().getPlayer1();
        } else {
            return getLocalGame().getPlayer2();
        }
    }

    public boolean isPlayerOf(Game game) {
        Profile localProfile = getLocalProfile();
        Profile profilePlayer1 = game.getPlayer1().getProfile();
        Profile profilePlayer2 = game.getPlayer1().getProfile();
        return localProfile.getIdUser().equals(profilePlayer1.getIdUser()) ||
                localProfile.getIdUser().equals(profilePlayer2.getIdUser());
    }
    
    public void gameOver() {
        System.out.println("GAME OVER");
        //arreter la partie localPlayer a perdu
                Game game = getLocalGame();
                try {
                game.setStatus(StatusGame.FINISHED);
                interfaceCom.changeStatusGame(game);
                 setLocalGame(game);
                } catch (NullPointerException e) {
                    // le jeu est déjà supprimé
                }

        
         Runnable command = new Runnable() {
			@Override
			public void run() {
                            interfaceTable.displayDefeat();
                            }
        		};
		Platform.runLater(command);

        
        //notify the other player that he has won
        Player pl = getOtherPLayer(); // to know to which player we send the notification : it's the player who is not ourself
        interfaceCom.notifyGameWon();
        interfaceCom.removeGame(getLocalGame());

        immediateDefeat();
        //interfaceMain.removeGame(controller.getLocalGame());
        //interfaceCom.removeGame(controller.getLocalGame());
    }

    public void immediateDefeat() {
        Profile localProfile = getLocalProfile();
        localProfile.setGamesLost(localProfile.getGamesLost()+1);
        localProfile.setGamesPlayed(localProfile.getGamesPlayed()+1);
        removeGameFromList(getLocalGame()); // Verifier comment est géré la notification que la partie n'existe plus
        localProfile.saveeditedProfile();
    }

    public void recordVictory() {
        Profile localProfile = getLocalProfile();
        localProfile.setGamesWon(localProfile.getGamesWon()+1);
        localProfile.setGamesPlayed(localProfile.getGamesPlayed()+1);
        localProfile.saveeditedProfile();
    }

    void endGame() {
        Game game = getLocalGame();
        if (attendedGame != null) { // si on est seulement spectateurs
            interfaceCom.gameQuitSpectator(localUser, attendedGame);
            User u = getLocalUser();
            String msg = " a quitté la partie en tant que spectateur";
            interfaceCom.sendInfoGameForSpectator(game, u);
            ChatMessage m = new ChatMessage(u,
                    msg, new Date());
            interfaceCom.sendChatMessage(m, getAttendedGame());
            setAttendedGame(null); // on retire le attended game
        } else if(game != null){
            System.out.println("GAME : " + game.getStatus() );
        switch (game.getStatus()) {
            case PLAYING :
                User u = getLocalUser();
                String msg = " a quitté la partie en tant que joueur";
                interfaceCom.sendInfoGameForSpectator(game, u);
                ChatMessage m = new ChatMessage(u,
                        msg, new Date());
                interfaceCom.sendChatMessage(m, getLocalGame());
                interfaceCom.notifyGameWon();
                immediateDefeat();
                break;
            case PLAYER1READY :
            case PLAYER2READY : 
            case BOATPHASE :
              //  interfaceCom.quitMessage();
        } 
        removeGameFromList(game);
        interfaceCom.removeGame(game);
        setLocalGame(null);
        }
    }

    int getPlayerPosition(Player p, Game g) {
        Profile p1 = g.getPlayer1().getProfile();
        if(p1.getIdUser().equals(p.getProfile().getIdUser())) {
            return 1;
        }
        else {
            return 2;
        }
    }

    void clearData() {
        localUser = null;
        // private DataUser localDataUser;
        localGame = null;
        attendedGame = null;
        listUsers = null;
        localProfile = null;
        listGames = null;
        localPlayer = null;
    }
}

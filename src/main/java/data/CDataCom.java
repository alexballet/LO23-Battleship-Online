/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import guiMain.GuiMainInterface;
import guiTable.GuiTableInterface;
import interfacesData.IDataCom;
import java.util.Date;

import lo23.battleship.online.network.COMInterface;
import structData.Boat;
import structData.ChatMessage;
import structData.Game;
import structData.Profile;
import structData.Shot;
import structData.StatusGame;
import structData.User;
import structData.Player;
import java.util.List;

/**
 *
 * @author Irvin
 */
public class CDataCom implements IDataCom {
    
    private final DataController controller;
    
    private GuiMainInterface interfaceMain;
    private GuiTableInterface interfaceTable;
    private COMInterface interfaceCom;
    
    public CDataCom(DataController dc){
        super();
        controller = dc;
    }
    
    public void setInterfaceMain(GuiMainInterface i){
        interfaceMain = i;
    }
    
    public void setInterfaceTable(GuiTableInterface i){
        interfaceTable = i;
    }
    
    public void setInterfaceCom(COMInterface c){
        interfaceCom = c;
    }
   
    public Game getCreatedGame() {
        Game g = controller.getLocalGame();
        return g;
    }
 
    
    @Override
    public void setGameJoinResponse(Boolean ok, Player player1, Player player2) {
       interfaceMain.setGameJoinResponse(true);
       controller.updateGameData(true, player1, player2);
    }

    /**
     * The distance user has refused the request to join the game 
     * @param no : Refuse of the request to join the game
     */
    
    @Override
    public void setGameJoinResponse(Boolean no){
        interfaceMain.setGameJoinResponse(false);
    }

    /**
    * After an user has connected, this user will be added to the list of user
    * @param u : The new user
    */
    
    @Override
    public void addUserToUserList(User u) {
        controller.addUserToList(u);
        interfaceMain.addUser(u);
    }

    /**
     * Sends the profile of a distant user to the local user 
     * @param profile : the profile of distant user
     */
    
    @Override
    public void sendStatistics(Profile profile) {
        interfaceMain.sendStatistics(profile);
    }

    /**
    * Add the player to the game if it is available.
    * @param sender : The player who sends this request
    * @param g : The game that the player wants to join
    */
    
    @Override
    public void notifToJoinGame(Profile sender, Game g) {
        Boolean isOk = false;
        for (Game ga: controller.getListGames()) {
            if (ga.getIdGame().equals(g.getIdGame())) {
                if (ga.getStatus().equals(StatusGame.WAITINGPLAYER)){
                    isOk = true;
                    ga.setStatus(StatusGame.BOATPHASE);
                    Player p = new Player(sender);
                    ga.setPlayer2(p);
                    interfaceCom.changeStatusGame(ga);
                    g = ga;
                }else{
                    isOk = false;           
                }
            }
        }
        System.out.println("CDataCom isok " + isOk);
        interfaceCom.notifyJoinGameResponse(isOk, sender, g);
        interfaceMain.openPlacementPhase(g);
    }

    /**
    * Adds the game given as a parameter to the list of games.
    * @param g : The new game
    */
    
    @Override
    public void addNewGameList(Game g) {
    		System.out.println("[DATA] add new game to list - ID : " + g.getIdGame());
        controller.addGameToList(g);
        interfaceMain.addGame(g);
    }
    
    @Override
    public void removeGameFromList(Game g){
        controller.removeGameFromList(g);
        interfaceMain.removeGame(g);
    }

    
    @Override
    public void errorPrint(String error) {
        //wait the method errorPrint in GuiTableInterface.java and GuiMainInterface.java
        //Interfacetable.errorPrint(error);
        //InterfaceMain.errorPrint(error);
    }

    
    @Override
    public void receiveMessage(ChatMessage message) {
        controller.getLocalGame().addMessage(message);
        System.out.println("Message: " + message);
        interfaceTable.addChatMessage(message);
    }

    
    public void receiveReady() {
        //TODO decrasser le code !!! les conditions sont toujours les mêmes donc faire ça proprement
        Boolean myTurn;
        Boolean p1Start = controller.getLocalGame().getPlayer1Start();
        Player localPlayer = controller.getLocalPlayer();
        Player p1 = controller.getLocalGame().getPlayer1();

        if ( p1Start && p1.getProfile().getIdUser().equals(localPlayer.getProfile().getIdUser()) ) {
            controller.getLocalGame().getPlayer2().setReady(true);
        } else {
            controller.getLocalGame().getPlayer1().setReady(true);
        }
        
        //TODO REFACTOR
        if(controller.getLocalGame().getPlayer1().isReady() &&
                controller.getLocalGame().getPlayer2().isReady())
        {
            System.out.println("2 player are ready");
            if (p1Start && p1.getProfile().getIdUser().equals(localPlayer.getProfile().getIdUser())) {
                myTurn = true;
            } else if (p1Start && !p1.getProfile().getIdUser().equals(localPlayer.getProfile().getIdUser())) {
                myTurn = false;
            } else if (p1Start && !p1.getProfile().getIdUser().equals(localPlayer.getProfile().getIdUser())) {
                myTurn = true;
            } else /*if ( p1Start == false && p1 == localPlayer )*/ {
                myTurn = false;
            }

            interfaceTable.opponentReady(myTurn, controller.getLocalGame().getTimePerShot());
            Game g = controller.getLocalGame();
            g.setStatus(StatusGame.PLAYING);
            interfaceCom.changeStatusGame(g);
        }
    }



    public void coordinates(Shot s) {
        Boat b = controller.testShot(s);
        interfaceTable.displayOpponentShot(s, b);
        controller.updateGameDataPlaying(s, b, false);
        interfaceCom.coordinates(controller.getOtherPLayer() , s, controller.getLocalGame(), b);
        if (b != null){
            boolean gameOver = true;
            Player localPlayer = controller.getLocalPlayer();
            List<Boat> listboat = localPlayer.getListBoats();
            for (Boat boat : listboat) {
                if(!boat.getSunk()) {
                    gameOver = false;
                    break;
                }
            }
            if (gameOver){
                controller.gameOver();
            }              
            
        }
    }
    
    
    public void coordinates(Shot s, Boat b) {
        controller.updateGameDataPlaying(s, b, true);
        
        interfaceTable.displayMyShotResult(s, b);
        System.out.println(controller.getLocalPlayerInGame().getListShots().size()
                + "/" + controller.getOtherPLayer().getListBoats().size());

    }
    public void notifyAttendedGameWon(Player p) {
        Game attendedGame = controller.getAttendedGame();
        int playerPositionInGame = getPlayerPosition(p, attendedGame);
        interfaceTable.displayObserverPhaseVictory(playerPositionInGame);
    }

    public void updateAttendedGame(Player p, Shot s, Boat b) {
        System.out.println("################################################");
        System.out.println("################################################");
        System.out.println(" shot recu : " + s);
        System.out.println("################################################");
        System.out.println("################################################");
        Game attendedGame = controller.getAttendedGame();
        int playerPositionInGame = getPlayerPosition(p, attendedGame);
        System.out.println(" player position " + playerPositionInGame);
        if(b != null) {// it means it sank
            interfaceTable.sunkPlayerBoat(playerPositionInGame, b);
            System.out.println(" sunk " + s);
        } else {
            interfaceTable.displayObserverShot(s, playerPositionInGame);
        }
    }
    
    /**
     * Returns the local user's profile
     * @return the local user's profile
     */
    
    public Profile getUserProfile() {
        Profile localProfile = controller.getLocalProfile();
        return localProfile;
    }
    
    /**
     * Takes a game given as a parameter and updates its status
     * @param g : the game which status has been modified
     */
    
    public void changeStatusGame(Game g) {
       /* Game localGame  = controller.getLocalGame();
        if (localGame != null) controller.removeGameFromList(localGame);
        controller.updateGameStatus(g);
        interfaceMain.transmitNewStatus(g);*/
        controller.updateGameStatus(g);
        interfaceMain.transmitNewStatus(g);
    }
    
    public User getLocalUser(){
        return controller.getLocalUser();
    }
    
    
    public void setLocalGame(Game g){
        controller.setLocalGame(g);
        interfaceMain.openPlacementPhase(g);
    }
    
    
    public void removeUser(User u){
        controller.removeUserFromList(u);
        interfaceMain.removeUser(u);
    }
    
    
    public void removeGame(Game g){
        controller.removeGameFromList(g);
        interfaceMain.removeGame(g);
    }
    /**
      * Notification that you won, update stats and display win
      */
    @Override
     public void notifiedGameWon(){
        Game game = controller.getLocalGame();

        game.setStatus(StatusGame.FINISHED);
        interfaceCom.changeStatusGame(game);
        controller.setLocalGame(game);

        interfaceTable.displayVictory();
        controller.recordVictory();
     }


     
     @Override
     public void notifyToSpecGame(User spec){
         controller.getLocalGame().addSpectators(spec);
         System.out.println("ADD NEW SPECTATOR " + spec.getUsername());
     }

     public void newRequestSpectator(User u){
         Game game = controller.getLocalGame();
         interfaceCom.sendInfoGameForSpectator(game, u);
         ChatMessage m = new ChatMessage(controller.getLocalUser(),
                 "Le joueur " + u.getUsername() + " a rejoint en spectateur.", new Date());
         game.addMessage(m);

         interfaceCom.sendChatMessage(m, game);

         //TODO : décommenter à l'integ

         interfaceCom.sendNewSpectator(u, controller.getOtherPLayer(), game.getListSpectators());
         notifyToSpecGame(u);
     }

    @Override
    public void joinGameSpectator(Game g) {
        controller.setAttendedGame(g);
        interfaceTable.updateSpectatorGame(g); //le main doit nous swap dans la partie, on vient de récupérer les données de la partie via le player1
    }
    
    @Override
    public void notifyQuitSpectator(User spec){
        //interfaceCom.notifyQuitSpectator(spec); A decommenter pdt l'integ
    }

    public Player getOtherPlayer(){
        return controller.getOtherPLayer();
    }

    public int getPlayerPosition(Player p, Game g) {
        return controller.getPlayerPosition(p, g);
    }
}

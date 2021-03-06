package data;

import interfacesData.IDataTable;
import java.util.Date;
import java.util.List;
import structData.Boat;
import structData.ChatMessage;
import structData.Position;
import structData.Game;
import lo23.battleship.online.network.COMInterface;
import guiMain.GuiMainInterface;
import guiTable.GuiTableInterface;
import java.util.UUID;
import structData.Player;
import structData.Shot;
import structData.StatusGame;

/**
 * Data's interface for Table
 */
public class CDataTable implements IDataTable {
    
    final private DataController controller;
   
    private GuiTableInterface interfaceTable;
    private GuiMainInterface interfaceMain;
    private COMInterface interfaceCom;
    
     protected static final int NB_CASES_GRID = 10;
    
    public CDataTable(DataController dc){
        super();
        controller = dc;
    }
    
    /**
     * Set the table's interface
     * @param t Table interface
     */
    public void setInterfaceTable(GuiTableInterface t) {
        interfaceTable = t;
    }
    
    /**
     * Set the com's interface
     * @param c interface com
     */
    public void setInterfaceCom(COMInterface c){
        interfaceCom = c;
    }

    /**
     * Set the main's interface
     * @param m interface main
     */
    public void setInterfaceMain(GuiMainInterface m) {
        interfaceMain = m;
    }
    
    @Override
    public Boolean exit() {
        return true;
    }

    @Override
    public void textMessage(String message) {
        ChatMessage cm = new ChatMessage(controller.getLocalUser(),message,new Date());
        Game g = controller.getLocalGame();
        if(g == null) {
            g = controller.getAttendedGame();
        }
        interfaceCom.sendChatMessage(cm, g); 
    }

    @Override
    public void coordinate(Position pos) {
        Shot s = new Shot(pos);
        interfaceCom.sendShot(controller.getLocalPlayer(), controller.getLocalGame(), s);
    }

    @Override
    public void coordinateShips(List<Boat> listBoat) {
        Boolean myTurn;
        Boolean p1Start = controller.getLocalGame().getPlayer1Start();
        Player localPlayer = controller.getLocalPlayer();
        localPlayer.setListBoats(listBoat);
        UUID idUser = controller.getLocalUser().getIdUser();
        boolean localPlayerIsPlayer1 = idUser.equals(
                controller.getLocalGame().getPlayer1().getProfile().getIdUser());
        Game myGame = controller.getLocalGame();

            if (localPlayerIsPlayer1) {
                controller.getLocalGame().getPlayer1().setReady(true);
                interfaceCom.notifyReady(controller.getLocalUser(), controller.getLocalGame().getPlayer2());
            } else {
                controller.getLocalGame().getPlayer2().setReady(true);
                interfaceCom.notifyReady(controller.getLocalUser(), controller.getLocalGame().getPlayer1());
            }
        

        if(controller.getLocalGame().getPlayer1().isReady() &&
                controller.getLocalGame().getPlayer2().isReady())
        {
            controller.getLocalGame().setStatus(StatusGame.PLAYING);
            myTurn = !(p1Start ^ localPlayerIsPlayer1); //retourne false si l'un des deux est faux et l'autre vrai

            interfaceTable.opponentReady(myTurn, myGame.getTimePerShot());
        }
    }
    

    /* Not used because the robot user was dropped
    @Override
    public Shot iaShot(){
        
        // Three random values
        Random rn1;
        Random rn2;
        
        // Two values for the shot
        Byte x;
        Byte y;
        Byte tampX;
        Byte tampY;    
        
        Integer shootNear;
        
        Boolean lastShotTouchedSomething = false;
        Boolean randomShot = false;
        
        // Get list of shots
        HashSet<Shot> listShot = controller.getLocalPlayer().getListShots();
        
        // Create and set shotAreadyPlayed to false
        Boolean shotAlreadyPlayed = false;
        
        do { 
            // Takes two new Randoms
            rn1 = new Random();
            rn2 = new Random();
            
            Iterator<Shot> itr;
            itr = listShot.iterator();    
            
            // Set x and y with integers using randoms
            x = (byte) (rn1.nextInt(NB_CASES_GRID/2)*2);
            y = (byte) (rn2.nextInt(NB_CASES_GRID/2)*2);       
            

                // Check if the last position played touched a boat
                while(!randomShot && itr.hasNext()){
                    // If is the last position played and if touched a boat
                    if (! itr.hasNext() && itr.next().getTouched()) {
                            lastShotTouchedSomething = true;
                            tampX = (byte) itr.next().getX();
                            tampY = (byte) itr.next().getY();
                            shootNear = rn1.nextInt(4);
                            switch (shootNear)
                            {
                              case 1:
                                tampX = (byte)(tampX+1);
                                break;
                              case 2:
                                tampX = (byte)(tampX-1);
                                break;
                              case 3:
                                tampY = (byte)(tampY+1);
                                break;
                              default:
                                tampY = (byte)(tampY-1);
                            }
                            // If out of grid
                            if(tampX<=NB_CASES_GRID && tampX>=1 && tampY<=NB_CASES_GRID && tampY>=1){
                                x = tampX;
                                y = tampY;
                            }
                         
                }
            }
            
            // Check if a position was already played
            while(itr.hasNext() && shotAlreadyPlayed == false) {
                
                if(itr.next().getX() == x && itr.next().getY() == y){
                    shotAlreadyPlayed = true;
                    if(lastShotTouchedSomething){
                        randomShot = true;
                    }
                } else {
                    shotAlreadyPlayed = false;
                }  
            }
            
        } while (shotAlreadyPlayed == true);
        // Create a Position and the corresponding Shot with x and y
        Position p = new Position(x, y, false);
        Shot s = new Shot(p);
        
        return s;
    }*/
    
    @Override
    public Game getLocalGame() {
        return controller.getLocalGame();
    }
    
    @Override
    public void changeStatusGameStarted(){
        Game g = controller.getLocalGame();
        g.setStatus(StatusGame.PLAYING);
        interfaceCom.changeStatusGame(g);
    }
    
    @Override
    public void gameEnded() {
        controller.endGame();
        interfaceMain.openMenuWindow();
    }

    @Override
    public void timerOver() {
        controller.gameOver();
    }
    
    @Override
    public Game getObserverGame(){
        return controller.getAttendedGame();
    }
}

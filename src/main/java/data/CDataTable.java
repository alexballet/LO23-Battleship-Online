/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Random;
import guiTable.GuiTableInterface;
import structData.Shot;

/**
 *
 * @author Irvin
 */
public class CDataTable implements IDataTable {
    
    private DataController controller;
   
    /* ajout ihm-plateau débug   */
    private GuiTableInterface interfaceTable;
    /* ajout ihm-plateau débug   */
    private GuiMainInterface interfaceMain;
    private COMInterface interfaceCom;
    
     protected static final int NB_CASES_GRID = 10;
    
    public CDataTable(DataController dc){
        super();
        controller = dc;
    }
    
    /* ajout ihm-plateau débug   */
    public void setInterfaceTable(GuiTableInterface t) {
        interfaceTable = t;
    }
    /* ajout ihm-plateau débug   */
    public void setInterfaceCom(COMInterface c){
        interfaceCom = c;
    }

    @Override
    public Boolean exit() {
        //Boolean b = interfaceCom.exit(); Com doit s'occuper la fonction exit
        //return b;
        return true; //delete, si Com a fini exit
    }

    @Override
    public void textMessage(String message) {
        ChatMessage cm = new ChatMessage(controller.getLocalUser(),message,new Date());
        Game g = controller.getLocalGame();
        interfaceCom.sendChatMessage(cm, g); //décommenter à l'integ
    }

    @Override
    public void coordinate(Position pos) {
        Shot s = new Shot(pos);
        interfaceCom.sendShot(controller.getLocalPlayer(), controller.getLocalGame(), s);
    }

    @Override

    // TODO: Mettre les instructions dans le bon sens
    public void coordinateShips(List<Boat> listBoat) {
        Boolean myTurn;
        Boolean p1Start = controller.getLocalGame().getPlayer1Start();
        controller.getLocalPlayer().setListBoats(listBoat);
        boolean localPlayerIsPlayer1 = controller.getLocalUser().getIdUser().equals(
                controller.getLocalGame().getPlayer1().getProfile().getIdUser());
        boolean localPlayerIsPlayer2 = controller.getLocalUser().getIdUser().equals(
                controller.getLocalGame().getPlayer2().getProfile().getIdUser());
        //TODO : uncomment when integV3 done
        Game myGame = controller.getLocalGame();
       /* ajout ihm-plateau débug   */
        if(!myGame.getHumanOpponent())  {
            interfaceTable.opponentReady(myGame.getPlayer1Start());
        }else {
            /* ajout ihm-plateau débug   */
            if (localPlayerIsPlayer1) {
                controller.getLocalGame().getPlayer1().setReady(true);
                interfaceCom.notifyReady(controller.getLocalUser(), controller.getLocalGame().getPlayer2());
            } else {
                controller.getLocalGame().getPlayer2().setReady(true);
                interfaceCom.notifyReady(controller.getLocalUser(), controller.getLocalGame().getPlayer1());
            }
        }

        // TODO: Simplifier les conditions (myTurn = p1Start && localPlayerIsPlayer1)
        if(controller.getLocalGame().getPlayer1().isReady() &&
                controller.getLocalGame().getPlayer2().isReady())
        {
            if (p1Start && localPlayerIsPlayer1) {
                myTurn = true;
            } else if (p1Start && localPlayerIsPlayer2) {
                myTurn = false;
            } else if (!p1Start && localPlayerIsPlayer2) {
                myTurn = true;
            } else /*if ( p1Start == false && p1 == localPlayer )*/ {
                myTurn = false;
            }

            interfaceTable.opponentReady(myTurn);
        }
    }
    
    @Override
    public Shot iaShot(){
        
        Random rn1 = new Random();
        Random rn2 = new Random();
        
        int x;
        int y;
        
        HashSet<Shot> listShot = controller.getLocalPlayer().getListShots();
                
        Boolean shotAlreadyPlayed = false;
        
        do { 
            
            x =  rn1.nextInt(NB_CASES_GRID);
            y =  rn2.nextInt(NB_CASES_GRID);
            
            Iterator<Shot> itr;
            itr = listShot.iterator();
            while(itr.hasNext() && shotAlreadyPlayed == false) {
                
                if (itr.next().getX()==x && itr.next().getY()==y) {
                    shotAlreadyPlayed = true;
                } else {
                    shotAlreadyPlayed = false;
                }  
            }
            
        } while (shotAlreadyPlayed == true);
        
        Position p = new Position(x, y, false);
        Shot s = new Shot(p);

        return s;
    }
    
}

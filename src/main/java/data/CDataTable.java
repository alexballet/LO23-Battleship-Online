/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import guiTable.GuiTableInterface;
import interfacesData.IDataTable;
import java.util.Date;
import java.util.List;
import lo23.battleship.online.network.COMInterface;
import structData.Boat;
import structData.ChatMessage;
import structData.Position;
import structData.Game;
import lo23.battleship.online.network.COMInterface;
import guiMain.GuiMainInterface;
import java.util.Random;
import structData.Shot;

/**
 *
 * @author Irvin
 */
public class CDataTable implements IDataTable {
    
    private DataController controller;
    
    private GuiMainInterface interfaceMain;
    private COMInterface interfaceCom;
    
     protected static final int NB_CASES_GRID = 10;
    
    public CDataTable(DataController dc){
        super();
        controller = dc;
    }
    
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
        //interfaceCom.sendChatMessage(cm, g); décommenter à l'integ
    }

    @Override
    public void coordinate(Position pos) {
        Shot s = new Shot(pos);
        interfaceCom.sendShot(controller.getLocalPlayer(), controller.getLocalGame(), s);
    }

    @Override
    public void coordinateShips(List<Boat> listBoat) {
        controller.getLocalPlayer().setListBoats(listBoat);
        // notifyready was modified by Com : it will be OK once integrated
        if (controller.getLocalGame().getPlayer1().equals(controller.getLocalPlayer())){
            interfaceCom.notifyReady(controller.getLocalUser(), controller.getLocalGame().getPlayer1());
        }else{ 
            interfaceCom.notifyReady(controller.getLocalUser(), controller.getLocalGame().getPlayer2());
        };
        
    }
    
    public Shot iaShot(){
        Position p = new Position();
        Random rn1 = new Random();
        Random rn2 = new Random();
        
        rn1.nextInt(NB_CASES_GRID);
        rn2.nextInt(NB_CASES_GRID);
        
        Shot s = new Shot(p);

        return s;
    }
    
}

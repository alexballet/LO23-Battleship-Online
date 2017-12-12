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
        interfaceCom.sendChatMessage(cm, g); //décommenter à l'integ
    }

    @Override
    public void coordinate(Position pos) {
        Shot s = new Shot(pos);
        interfaceCom.sendShot(controller.getLocalPlayer(), controller.getLocalGame(), s);
    }

    @Override
    public void coordinateShips(List<Boat> listBoat) {
        controller.getLocalPlayer().setListBoats(listBoat);
        //TODO : uncomment when integV3 done
        if(controller.getLocalUser().getIdUser().equals(
                controller.getLocalGame().getPlayer1().getProfile().getIdUser()))
                    interfaceCom.notifyReady(controller.getLocalUser(), controller.getLocalGame().getPlayer2());
        else
            interfaceCom.notifyReady(controller.getLocalUser(), controller.getLocalGame().getPlayer1());

    }
    
    @Override
    public Shot iaShot(){
        
        Random rn1 = new Random();
        Random rn2 = new Random();
        
        Byte x;
        Byte y;
        
        HashSet<Shot> listShot = controller.getLocalPlayer().getListShots();
                
        Boolean shotAlreadyPlayed = false;
        
        do { 
            
            x = (byte) rn1.nextInt(NB_CASES_GRID);
            y = (byte) rn2.nextInt(NB_CASES_GRID);
            
            Iterator<Shot> itr;
            itr = listShot.iterator();
            while(itr.hasNext() && shotAlreadyPlayed == false) {
                
                if (itr.next().getX().equals(x) && itr.next().getY().equals(y)) {
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

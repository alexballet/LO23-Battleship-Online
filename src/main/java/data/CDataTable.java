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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        Game myGame = controller.getLocalGame();
       /* ajout ihm-plateau débug   */
        if(!myGame.getHumanOpponent())  {
            interfaceTable.opponentReady(myGame.getPlayer1Start());
        }else {
            /* ajout ihm-plateau débug   */
        if(controller.getLocalUser().getIdUser().equals(
                controller.getLocalGame().getPlayer1().getProfile().getIdUser()))
            interfaceCom.notifyReady(controller.getLocalUser(), controller.getLocalGame().getPlayer2());
        else
            interfaceCom.notifyReady(controller.getLocalUser(), controller.getLocalGame().getPlayer1());
        }

    }
    
}

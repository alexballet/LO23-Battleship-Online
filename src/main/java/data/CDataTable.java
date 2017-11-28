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

/**
 *
 * @author Irvin
 */
public class CDataTable implements IDataTable {
    
    private DataController controller;
    
    private GuiMainInterface interfaceMain;
    private COMInterface interfaceCom;
    
    public CDataTable(DataController dc){
        super();
        controller = dc;
    }

    @Override
    public Boolean exit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void textMessage(ChatMessage message) {
        String s = message.getContent();
        Game g = controller.getLocalGame();
        interfaceCom.sendChatMessage(s, g);
    }

    @Override
    public void coordinate(Position pos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void coordinateShips(List<Boat> listBoat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

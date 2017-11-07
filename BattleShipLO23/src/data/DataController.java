/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;
import structData.Game;
import structData.User;

/**
 *
 * @author Irvin
 */
public class DataController {
    
    private CDataCom InterfaceDataCom;
    private CDataMain InterfaceDataMain;
    private CDataTable InterfaceDataTable;
        
    private User localUser;
    private Game localGame;
    private ArrayList<User> listUsers;
    private ArrayList<Game> listGames;
    
    
    
    
    public CDataCom getInterfaceDataCom(){
        return InterfaceDataCom;
    }
    
    public CDataMain getInterfaceDataMain(){
        return InterfaceDataMain;
    }
    
    public CDataTable getInterfaceDataTable(){
        return InterfaceDataTable;
    }
    
    public User getUser(){
        return localUser;
    }
}

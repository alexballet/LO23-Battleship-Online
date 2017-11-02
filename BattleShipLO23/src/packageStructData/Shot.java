/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageStructData;

import java.util.Date;

/**
 * Shot is a class of a shot of a player.
 */
public class Shot extends Position{
    private Date time;
    
    public Shot(){
        super();
        time = new Date();
  } 
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageStructData;

import java.util.Date;
import java.awt.Image;
/**
 *
 * @author loulou
 */
public class Profile extends DataUser {
    private Image avatar;
    private String lastname;
    private String name;
    private Date birthdate;
    private int gamesPlayed;
    private int gamesWon;
    private int gamesLost;
    private int gamesAborted;
    
    public Profile(){
        super();
        avatar = null;
        lastname = "";
        name = "";
        birthdate = new Date();
        gamesPlayed = 0;
        gamesWon = 0;
        gamesLost = 0;
        gamesAborted = 0;
    }
    
}

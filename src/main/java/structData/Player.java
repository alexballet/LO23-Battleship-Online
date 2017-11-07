/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structData;
import java.util.HashSet;
/**
 *
 * @author loulou
 */
public class Player {
    private Profile profile;
    private HashSet listBoats;
    private HashSet listShots;
    
    public Player(Profile p) {
        profile = new Profile(p);
        listBoats = new HashSet();
        listShots = new HashSet();
    }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lo23.battleship.data.packageStructDonnées;
import java.util.HashSet;
/**
 *
 * @author loulou
 */
public class ContactGroup {
    private HashSet listUsers;
    private int rights;
    private String name;
    
    public ContactGroup() {
        listUsers = new HashSet();
        rights = 0;
        name = "";
    }
}


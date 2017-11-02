/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageStructData;
import java.util.HashSet;
import java.util.UUID;

public class User {
    private UUID idUser;
    private String login;
    private String username;
    private HashSet iPs;
    
    public User(){
        idUser = UUID.randomUUID();
        login = "";
        username = "";
        iPs = new HashSet();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageStructDonnées;

import java.util.UUID;

public class User {
    private UUID idUser;
    private String login;
    private String username;
    private long[] IPs;
    
    public User(){
        idUser = UUID.randomUUID();
        login = "";
        username = "";
        IPs[0] = 0;
    }
}

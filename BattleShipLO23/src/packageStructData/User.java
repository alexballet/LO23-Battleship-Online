/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageStructData;
import java.util.HashSet;
import java.util.UUID;

public class User {
    protected UUID idUser;
    protected String login;
    protected String username;
    protected HashSet iPs;
    
    /*constructor by default*/
    public User(){
        idUser = UUID.randomUUID();
        login = new String("");
        username = new String("");
        iPs = new HashSet();
    }
    
     /*constructor with parameters*/
    public User(String logindata, String usernamedata){
        idUser = UUID.randomUUID();
        login = logindata;
        username = usernamedata;
        iPs = new HashSet();
    }
    
     /*constructor for the class DataUser who is "son" of the class User*/
    public User(User u){
        idUser = u.idUser;
        login = u.login;
        username = u.username;
        iPs = u.iPs;
    }
    
    /*accessors*/
    public UUID getidUser(){
        return idUser;
    }
    
    public String getlogin(){
        return login;
    } 
    
    public String getusername(){
        return username;
    }
    
    public HashSet getiPs(){
        return iPs;
    }
    
    /*mutator*/
    public void setidUser(UUID idUserdata){
        this.idUser = idUserdata;
    }
    public void setlogin(String logindata){
        this.login = logindata;
    }
    public void setusername(String usernamedata){
        this.username = usernamedata;
    }
    public void setiPs(HashSet iPsdata){
        this.iPs = iPsdata;
    }
    
    
    /*clone*/
    
   public User cloneUser(User userclone){
        idUser = userclone.idUser;
        login = userclone.login;
        username = userclone.username;
        iPs = userclone.iPs;
        return this;
   }
    
}

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
    /**
     * 
     * @param logindata : the login of the new user
     * @param usernamedata : the username of the new user
     */
    public User(String logindata, String usernamedata){
        idUser = UUID.randomUUID();
        login = logindata;
        username = usernamedata;
        iPs = new HashSet();
    }
    
    /*constructor for the class DataUser who is "son" of the class User*/
    /**
     * 
     * @param u : a new object of the class User
     */
    public User(User u){
        idUser = u.idUser;
        login = u.login;
        username = u.username;
        iPs = u.iPs;
    }
    
    /*accessors*/
    /**
     * 
     * @return the id of the user
     */
    public UUID getidUser(){
        return idUser;
    }
    /**
     * 
     * @return the login of the user
     */
    public String getlogin(){
        return login;
    } 
    /**
     * 
     * @return the username of the user
     */
    public String getusername(){
        return username;
    }
    /**
     * 
     * @return iPs of the user
     */
    public HashSet getiPs(){
        return iPs;
    }
    
    /*mutator*/
    /**
     * 
     * @param idUserdata : the new value of the id of the user
     */
    public void setidUser(UUID idUserdata){
        this.idUser = idUserdata;
    }
    /**
     * 
     * @param logindata : the new value of the login of the user
     */
    public void setlogin(String logindata){
        this.login = logindata;
    }
    /**
     * 
     * @param usernamedata : the new value of the username of the user
     */
    public void setusername(String usernamedata){
        this.username = usernamedata;
    }
    /**
     * 
     * @param iPsdata : the new value of the iPs of the user
     */
    public void setiPs(HashSet iPsdata){
        this.iPs = iPsdata;
    }
    
    
    /*clone a present user*/
    /**
     * 
     * @param userclone : the user to be cloned
     * @return the user who has called this method to clone all the 
     *         information of the userclone
     */
    
   public User cloneUser(User userclone){
        idUser = userclone.idUser;
        login = userclone.login;
        username = userclone.username;
        iPs = userclone.iPs;
        return this;
   }
    
}

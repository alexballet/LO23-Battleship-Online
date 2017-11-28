/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structData;
import java.io.Serializable;
import java.util.HashSet;
import java.util.UUID;
import java.io.Serializable;

public class User implements Serializable {

    protected UUID idUser;
    protected String login;
    protected String username;
    protected HashSet<Long> iPs;
    
    /**
     * Constructor by default
     */
    public User(){
        idUser = UUID.randomUUID();
        login = new String("");
        username = new String("");
        iPs = new HashSet();
    }
    
    /**
     * Constructor with parameters
     * @param loginData : the login of the new user
     * @param usernameData : the username of the new user
     */
    public User(String loginData, String usernameData){
        idUser = UUID.randomUUID();
        login = new String(loginData);
        username = new String(usernameData);
        iPs = new HashSet();
    }
    
    /**
     * Constructor for the class DataUser who is "son" of the class User
     * @param u : a new object of the class User
     */
    public User(User u){
        idUser = u.idUser;
        login = u.login;
        username = u.username;
        iPs = u.iPs;
    }
    
    /**
     * Accessors for idUser
     * @return the id of the user
     */
    public UUID getIdUser(){
        return idUser;
    }
    /**
     * Accessors for login
     * @return the login of the user
     */
    public String getLogin(){
        return login;
    } 
    /**
     * Accessors for Username
     * @return the username of the user
     */
    public String getUsername(){
        return username;
    }
    /**
     * Accessors for iPs
     * @return iPs of the user
     */
    public HashSet getIPs(){
        return iPs;
    }
    
    /**
     * Mutator for idUser
     * @param idUserData : the new value of the id of the user
     */
    public void setIdUser(UUID idUserData){
        this.idUser = idUserData;
    }
    /**
     * Mutator for login
     * @param loginData : the new value of the login of the user
     */
    public void setLogin(String loginData){
        this.login = loginData;
    }
    /**
     * Mutator for userName
     * @param usernameData : the new value of the username of the user
     */
    public void setUsername(String usernameData){
        this.username = usernameData;
    }
    /**
     * Mutator for iPs
     * @param iPsData : the new value of the iPs of the user
     */
    public void setIPs(HashSet iPsData){
        this.iPs = iPsData;
    }
    
    
    /*clone a present user*/
    /**
     * Clone function to copy a User
     * @param userClone : the user to be cloned
     * @return the user who has called this method to clone all the 
     *         information of the userClone
     */
    
   public User cloneUser(User userClone){
        idUser = userClone.idUser;
        login = userClone.login;
        username = userClone.username;
        iPs = userClone.iPs;
        return this;
   }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packageStructData;

/**
 *
 * @author loulou
 */
public class DataUser extends User {
    protected String password;
    protected String listContacts;
    
    /*constructor by default*/
    public DataUser(User u){
        super(u);
        password = new String("");
        listContacts = new String("");
    }
    
     /*constructor with parameters*/
    public DataUser(User u, String passworddata, String listContactsdata){
        super(u);
        password = passworddata;
        listContacts = listContactsdata;
    }
    
    /*constructor for the class Profile who is "son" of the class User*/
    public DataUser (DataUser du){
        /*properties of class User*/
        idUser = du.idUser;
        login = du.login;
        username = du.username;
        iPs = du.iPs;
        
         /*properties of class DataUser*/
        password = du.password;
        listContacts = du.listContacts;
    }
    
     /*accessors*/
    public String getpassword(){
        return password;
    }
    
    public String getlistContacts(){
        return listContacts;
    } 
    
        /*mutator*/
    public void setpassword(String passworddata){
        this.password = passworddata;
    }
    public void setlistContacts(String listContactsdata){
        this.listContacts = listContactsdata;
    }
    
    /*clone*/
    
   public DataUser cloneDataUser(DataUser duserclone){
        idUser = duserclone.idUser;
        login = duserclone.login;
        username = duserclone.username;
        iPs = duserclone.iPs;
        password = duserclone.password;
        listContacts = duserclone.listContacts;
        return this;
   }
    
}

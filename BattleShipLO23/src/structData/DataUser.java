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
    /**
     * 
     * @param u : the user of the parent class User. This user has the heritage
     *            relationship with the object of the class DataUser
     */
    public DataUser(User u){
        super(u);
        password = new String("");
        listContacts = new String("");
    }
    
     /*constructor with parameters*/
    /**
     * 
     * @param u : the parent user of the new object of class DataUser
     * @param passworddata : the password of the new object of class DataUser
     * @param listContactsdata : the list of contacts of the new object of class
     *                           DataUser
     */
    public DataUser(User u, String passworddata, String listContactsdata){
        super(u);
        password = passworddata;
        listContacts = listContactsdata;
    }
    
    /*constructor for the class Profile who is "son" of the class User*/
    /**
     * 
     * @param du : a new object of the class DataUser
     */
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
    /**
     * 
     * @return the password of the object of the class DataUser
     */
    public String getpassword(){
        return password;
    }
    /**
     * 
     * @return the list of contacts of the object of the class DataUser
     */
    public String getlistContacts(){
        return listContacts;
    } 
    
        /*mutator*/
    /**
     * 
     * @param passworddata : the new value of password of the object of the
     *                       class DataUser
     */
    public void setpassword(String passworddata){
        this.password = passworddata;
    }
    /**
     * 
     * @param listContactsdata : the new value of list of contacts of the object
     *                           of the class DataUser
     */
    public void setlistContacts(String listContactsdata){
        this.listContacts = listContactsdata;
    }
    
    /*clone*/
    /**
     *
     * @param duserclone : the object of the datauser to be cloned
     * @return the datauser who has called this method to clone all the 
     *         information of the duserclone
     */
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

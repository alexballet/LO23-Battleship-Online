/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structData;

/**
 *
 * @author loulou
 */
public class DataUser extends User {
    protected String password;
    protected String listContacts;
    
    /**
     * Constructor by default
     * @param u : the user of the parent class User. This user has the heritage
     *            relationship with the object of the class DataUser
     */
    public DataUser(User u){
        super(u);
        password = new String("");
        listContacts = new String("");
    }
    
    /**
     * Constructor with parameters
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
    
    /**
     * Constructor for the class Profile who is "son" of the class User
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
    

    /**
     * Accessor for Password
     * @return the password of the object of the class DataUser
     */
    public String getPassword(){
        return this.password;
    }
    /**
     * Accessor for List of contacts
     * @return the list of contacts of the object of the class DataUser
     */
    public String getListContacts(){
        return this.listContacts;
    } 
    
    /**
     * Mutator for password
     * @param passworddata : the new value of password of the object of the
     *                       class DataUser
     */
    public void setPassword(String passworddata){
        this.password = passworddata;
    }
    /**
     * Mutator for List of contacts
     * @param listContactsdata : the new value of list of contacts of the object
     *                           of the class DataUser
     */
    public void setListContacts(String listContactsdata){
        this.listContacts = listContactsdata;
    }
    
    /**
     * Clone function to copy a DataUser
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

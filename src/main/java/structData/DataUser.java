package structData;

import java.util.ArrayList;
import java.util.List;

/**
 * DataUser class
 */
public class DataUser extends User {
    protected String password;
    protected List<ContactGroup> listContacts;
    
    /**
     * Constructor by default
     * @param u : the user of the parent class User. This user has the heritage
     *            relationship with the object of the class DataUser
     */
    public DataUser(User u){
        super(u);
        password = new String("");
        listContacts = new ArrayList();
    }
    
    /**
     * Constructor with parameters
     * @param u : the parent user of the new object of class DataUser
     * @param passworddata : the password of the new object of class DataUser
     * @param listContactsdata : the list of contacts of the new object of class
     *                           DataUser
     */
    public DataUser(User u, String passworddata, List<ContactGroup> listContactsdata){
        super(u);
        password = passworddata;
        listContacts = listContactsdata;
    }
    
    /**
     * Constructor for the class Profile who is "son" of the class User
     * @param du : a new object of the class DataUser
     */
    public DataUser(DataUser du){
    		super(du);
        /*properties of class User*/
        idUser = du.idUser;
        login = du.login;
        username = du.username;
        iPs = du.iPs;
        port = du.port;
        
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
    public List<ContactGroup> getListContacts(){
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
    public void setListContacts(List<ContactGroup> listContactsdata){
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

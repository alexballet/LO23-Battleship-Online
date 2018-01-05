package structData;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * ContactGroup is a class a contact groups 
 */
public class ContactGroup implements Serializable{
    static final long serialVersionUID = 3L;
    private List<User> listUsers;
    private int rights;
    private String name;
    
    /**
     * Constructor by default
     */
    public ContactGroup() {
        listUsers = new ArrayList();
        rights = 0;
        name = "";
    }
}


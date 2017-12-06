/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structData;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author loulou
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


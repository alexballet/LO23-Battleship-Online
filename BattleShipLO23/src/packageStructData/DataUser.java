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
    private String password;
    private String listContacts;
    
    public DataUser(){
        super();
        password = "";
        listContacts = "";
    }
    
}
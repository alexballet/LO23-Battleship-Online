/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lo23.battleship.data.packageStructDonnées;
import java.util.HashSet;
import java.util.UUID;

public class User {
    private UUID idUser;
    private String login;
    private String username;
    private HashSet IPs;
    
    public User(){
        idUser = UUID.randomUUID();
        login = "";
        username = "";
        IPs = new HashSet();
    }

	public UUID getIdUser() {
		return idUser;
	}

	public void setIdUser(UUID idUser) {
		this.idUser = idUser;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}

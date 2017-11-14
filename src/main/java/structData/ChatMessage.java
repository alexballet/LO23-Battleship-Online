/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package structData;
import java.util.Date;
/**
 *
 * @author loulou
 */
public class ChatMessage {
    private User profile;
    private String content;
    private Date time;
    
    public ChatMessage() {
        profile = new User();
        content = "";
        time = new Date();
    }

}


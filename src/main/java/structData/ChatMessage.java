package structData;
import java.util.Date;
import java.io.Serializable;
/**
 * ChatMessage is a class for chat messages
 */
public class ChatMessage implements Serializable{
    static final long serialVersionUID = 2L;
    private User profile;
    private String content;
    private Date time;
    
    /**
     * Constructor with all parameters
     * @param newSender : person who send this message
     * @param newMessage :the message has to be send
     * @param newBirthdate : the birthdate of this message
     */
    public ChatMessage(User newSender, String newMessage, Date newBirthdate){
        profile = newSender;
        content = new String(newMessage);
        time = newBirthdate;
    }
    
    /**
     * Accessors for profile(user)
     * @return the user who send the message
     */
    public User getProfile(){
        return this.profile;
    }
    
    /**
     * Accessors for content
     * @return the content of message
     */
    public String getContent(){
        return this.content;
    }
    
    /**
     * Accessors for time
     * @return the time that the message has been sent
     */
    public Date getTime(){
        return this.time;
    }
    
    /**
     * Mutator for profile(user)
     * @param u : the sender
     */
    public void setProfile(User u){
        this.profile = u;
    }
    
    /**
     * Mutator for content
     * @param m : message
     */
    public void setContent(String m){
        this.content = m;
    }
    
    /**
     * Mutator for time
     * @param t : time that message has been sent
     */
    public void setTime(Date t){
        this.time = t;
    }
    
    /**
     * Clone function to copy a chatMessage
     * @param chatMessageClone : message to be cloned
     * @return a copied chatMessage
     */
    public ChatMessage cloneChatMessage(ChatMessage chatMessageClone){
        profile = chatMessageClone.profile;
        content = chatMessageClone.content;
        time = chatMessageClone.time;
        return this;
    }
}


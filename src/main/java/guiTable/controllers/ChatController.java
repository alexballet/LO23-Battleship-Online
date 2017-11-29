package guiTable.controllers;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ChatController {

    @FXML
    //area dedicated to the conversation
    private TextArea conversation;
    
    @FXML
    //text that we want to send
    private TextField field;
    
    public void init() {
        //initialisation of the chat controller
    }

    public void sendMyMessage() {
        //send a message by clicking on the button
        
        //variables
        String message;
        String currentConversation;
        
        //get the message to send
        message = field.getText();
        
        //send the message to other users
        // TO DO
        
        //add this to the conversation
        currentConversation = conversation.getText();
        conversation.setText("Moi: " + message + "\n" + currentConversation);
        
        //System.out.println(temp);
        
    } 
}

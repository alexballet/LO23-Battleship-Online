package guiTable.controllers;
import data.CDataTable;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import structData.ChatMessage;

public class ChatController {

    @FXML
    //area dedicated to the conversation
    private TextArea conversation;
    
    @FXML
    //text that we want to send
    private TextField field;
    
    private CDataTable dataController;
    
    public void init() {
        //initialisation of the chat controller
        conversation.setEditable(false);
    }

    public void sendMyMessage() {
        //send a message by clicking on the button
        
        //variables
        String message;
        String currentConversation;
        
        //get the message to send
        message = field.getText();
        
        //add this to the conversation
        if(!message.isEmpty())
        {
            
            //send the message to other users
            //dataController.textMessage(message);
            
            //add this to the conversation
            currentConversation = conversation.getText();
            conversation.setText("Moi: " + message + "\n" + currentConversation);
        }
        
        //erase the textfield
        field.setText("");
    }
    public void receiveAMessage(ChatMessage message) {
        //receive a message
        
        //variables
        String currentConversation;
        
        //add this to the conversation
        currentConversation = conversation.getText();
        conversation.setText(message.getProfile().getUsername()+ ": " + message.getContent() + "\n" + currentConversation);
    }
    
    public void setDataController(CDataTable d) {
        this.dataController = d;
    }
    
}

package guiTable.controllers;
import guiTable.GuiTableInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
//import interfacesData.IDataTable;
import structData.ChatMessage;

public class ChatController {

    @FXML
    //area dedicated to the conversation
    private TextArea conversation;
    
    @FXML
    //text that we want to send
    private TextField field;
    
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
            
            
            //System.out.println("message : "+message);
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
        conversation.setText(message.getProfile().getIdUser() + ": " + message.getContent() + "\n" + currentConversation);
    }
    
}

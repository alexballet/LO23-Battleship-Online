package guiTable.controllers;
import data.CDataTable;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import structData.ChatMessage;

public class ChatController {

    @FXML
    //area dedicated to the conversation
    private TextArea conversationArea;
    
    @FXML
    //text that we want to send
    private TextField field;
    
    private CDataTable dataController;
    private String conversation;
    
    
    public void init() {
        //initialisation of the chat controller
        conversationArea.setEditable(false);
        conversation = "Vous êtes connecté.";
        conversationArea.setText(conversation);
        System.out.println("\n FONCTION INIT \n");
    }

    public void sendMyMessage() {
        //send a message by clicking on the button
        
        //variables
        String message;
        
        //get the message to send
        message = field.getText();
        
        //System.out.println("\n"+ "voici mon message : " + message + "\n");
        //System.out.println( "voici ma conversation : " + conversation + "\n");
        
        //add this to the conversation
        if(!message.isEmpty())
        {
            //send the message to other users
            //dataController.textMessage(message);
            
            //add this to the conversation
            conversationArea.appendText("Moi: " + message + "\n");
            conversation = conversationArea.getText();
        }
        
        //erase the textfield
        field.setText("");
    }
    public void receiveAMessage(ChatMessage message) {
        //receive a message
        
        //add this to the conversation
        conversationArea.setText(message.getProfile().getUsername()+ ": " + message.getContent() + "\n" + conversation);
    }
    
    public void reloadConversation(){
        conversationArea.setText("");
        conversationArea.appendText(conversation);
        System.out.println("on reload \n conv : " + conversation);
    }
    
    public void setDataController(CDataTable d) {
        this.dataController = d;
    }
    
}

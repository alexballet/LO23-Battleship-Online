package guiTable.controllers;
import data.CDataTable;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import structData.ChatMessage;
import structData.Game;
import structData.Player;
import structData.Profile;

public class ChatController {

    @FXML
    //area dedicated to the conversation
    private TextArea conversationArea;
    
    @FXML
    //text that we want to send
    private TextField field;
    
    @FXML
    //profile area
    private Label profils;
    
    private CDataTable dataController;
    private String conversation;

    /**
     *
     * @param conv the previous conversation
     */
    public void init(String conv) {
        //initialisation of the chat controller
        this.conversation = conv;
        conversationArea.setEditable(false);
        conversationArea.setText(this.conversation);
    }
    
    /**
     * créer la conversation à partir de la liste des messages
     * @param list
     */
    public void createConversation(List<ChatMessage> list) {
        list.forEach(this::receiveAMessage);
    }

    public void sendMyMessage() {
        //send a message by clicking on the button
        
        //variables
        String message;
        
        //get the message to send
        message = field.getText();

        //add this to the conversation
        if(!message.isEmpty())
        {
            //send the message to other users
            dataController.textMessage(message);
            
            //add this to the conversation
            conversationArea.setText("Moi: " + message + "\n" + conversation);
            conversation = conversationArea.getText();
        }
        
        //erase the textfield
        field.setText("");
    }

    /**
     * Receive a message
     * @param message
     */
    public void receiveAMessage(ChatMessage message) {
        //add this to the conversation
        conversationArea.setText(message.getProfile().getUsername()+ ": " + message.getContent() + "\n" + conversation);
        conversation = conversationArea.getText();
    }

    /**
     * Reload the conversation
     */
    public void reloadConversation(){
        conversationArea.setText("");
        conversationArea.appendText(conversation);
    }

    /**
     * Set the data controller
     * @param d
     */
    public void setDataController(CDataTable d) {
        this.dataController = d;
    }

    /**
     *
     * @return get the current conversation
     */
    public String getConversation() {
        return this.conversation;
    }

    /**
     * Paint the design area
     */
    public void doProfileArea(){
        Game partie;
        partie = dataController.getLocalGame(); 
        if (partie == null) {
            partie = dataController.getObserverGame();
        }
        
        Player player1 = partie.getPlayer1();
        Player player2 = partie.getPlayer2();
        
        Profile profilePlayer1 = player1.getProfile();
        Profile profilePlayer2 = player2.getProfile();

        String namePlayer1 = profilePlayer1.getUsername();
        String namePlayer2 = profilePlayer2.getUsername();
        
        //chaine qui comprend les 2 adversaires
        String full = "   " + namePlayer1 + " VS " + namePlayer2;
        
        profils.setText(full);
    }
    
}

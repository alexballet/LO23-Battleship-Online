package guiTable.controllers;
import data.CDataTable;
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
    
    
    public void init(String conv) {
        //initialisation of the chat controller
        this.conversation = conv;
        conversationArea.setEditable(false);
        conversationArea.setText(this.conversation);
        
        //set profile zone
        doProfileArea();
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
            dataController.textMessage(message);
            
            //add this to the conversation
            conversationArea.setText("Moi: " + message + "\n" + conversation);
            conversation = conversationArea.getText();
        }
        
        //erase the textfield
        field.setText("");
        //field.home();
    }
    public void receiveAMessage(ChatMessage message) {
        //receive a message
        
        //add this to the conversation
        conversationArea.setText(message.getProfile().getUsername()+ ": " + message.getContent() + "\n" + conversation);
        conversation = conversationArea.getText();
    }
    
    public void reloadConversation(){
        conversationArea.setText("");
        conversationArea.appendText(conversation);
        //System.out.println("on reload \n conv : " + conversation);
    }
    
    public void setDataController(CDataTable d) {
        this.dataController = d;
    }

    public String getConversation() {
        return this.conversation;
    }
    
    public void doProfileArea(){   
        
        String namePlayer1 = "Player 1";
        String namePlayer2 = "Player 2";
        String full = "";
        
        Game partie;
        partie = dataController.getLocalGame(); //INTEGV5
        
        Player player1 = partie.getPlayer1();
        Player player2 = partie.getPlayer2();
        
        Profile profilePlayer1 = player1.getProfile();
        Profile profilePlayer2 = player2.getProfile();
        
        namePlayer1 = profilePlayer1.getUsername();
        namePlayer2 = profilePlayer2.getUsername();
        
        //chaine qui comprend les 2 adversaires
        full = "   " + namePlayer1 + " VS " + namePlayer2;
        
        profils.setText(full);
    }
    
}

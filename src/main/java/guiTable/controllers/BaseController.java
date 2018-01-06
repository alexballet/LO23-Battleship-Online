package guiTable.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 * every class controller will herit from BaseController
 */
public class BaseController {
    
    
    public static final int GRID_X = 100;
    public static final int GRID_Y = 100;
    public static final int SPACE = 3;
    public static final int GRID_ELEMENT_SIZE = 35;
    public static final int NB_CASES_GRID = 10;
    
    /**
    * Allows to replace pane by another one
    * @param paneToFill
    * @param contentAdress
    * @return FXMLLoader
    */
    protected FXMLLoader fillElement(AnchorPane paneToFill, String contentAdress) {
       FXMLLoader loader = new FXMLLoader();
       loader.setLocation(getClass().getResource(contentAdress));
       try{
           AnchorPane contentPane = loader.load();
           paneToFill.getChildren().add(contentPane);
       }
       catch(Exception e){
           System.err.println(e.getMessage());
       }
       return loader;
    }
    
        
    /**
    * fillChatSlot() allows external class to fill the chatPane and get the ChatController
     * @param pane
    * @param chatFxmlUrl
     * @param conversation
    * @return chatController
    */        
    public ChatController fillChatSlot(AnchorPane pane, String chatFxmlUrl, String conversation){
        FXMLLoader loader;
        loader = fillElement(pane, chatFxmlUrl );
        ChatController chatController = loader.getController();
        chatController.init(conversation);
        return chatController;
    }
    
}



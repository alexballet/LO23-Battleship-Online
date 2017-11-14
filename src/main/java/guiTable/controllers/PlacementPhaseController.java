package guiTable.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class PlacementPhaseController {

    @FXML
    private AnchorPane chatPane;

    @FXML
    private AnchorPane boardPane;

    @FXML
    private AnchorPane availableBoatsPane;


    /**
     * Assign to each pane the associated module
     * @throws Exception
     */
    public void init() throws Exception {

        FXMLLoader loader;

        loader = fillElement(chatPane, "/fxml/Ihm-plateau/chat.fxml" );
        ChatController chatController = loader.getController();
        chatController.init();

        loader = fillElement(boardPane, "/fxml/Ihm-plateau/board.fxml");
        BoardController boardController = loader.getController();
        boardController.init();

        loader = fillElement(availableBoatsPane, "/fxml/Ihm-plateau/availableBoats.fxml");
        AvailableBoatsController availableBoatsController = loader.getController();
        availableBoatsController.init();
    }

    /**
     * Allows to replace pane by another one
     * @param paneToFill
     * @param contentAdress
     * @return FXMLLoader
     * @throws Exception 
     */
    private FXMLLoader fillElement(AnchorPane paneToFill, String contentAdress) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(contentAdress));
        AnchorPane contentPane = loader.load();
        paneToFill.getChildren().add(contentPane);
        return loader;
    }
}

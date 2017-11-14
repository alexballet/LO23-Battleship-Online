package lo23.battleship.main;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class FXMLController implements Initializable {
    
    @FXML
    private TextArea chat;
    @FXML
    private Rectangle boat;
    
    private double m_nX = 0;
    private double m_nY = 0;
    private double m_nMouseX = 0;
    private double m_nMouseY = 0;
    
    @FXML
    private void addMessage(ActionEvent event) throws InterruptedException {
        if (boat.getRotate()==0){
            boat.setRotate(90);
        } else if (boat.getRotate()==90){
            boat.setRotate(0);
        }

        
    }

    private EventHandler<MouseEvent> pressMouse() {
		EventHandler<MouseEvent> mousePressHandler = new EventHandler<MouseEvent>() {

			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.PRIMARY) {
					// get the current mouse coordinates according to the scene.
					m_nMouseX = event.getSceneX();
					m_nMouseY = event.getSceneY();

					// get the current coordinates of the draggable node.
					m_nX = boat.getLayoutX();
					m_nY = boat.getLayoutY();
				}
			}
		};

		return mousePressHandler;
	}

	/**
	 * Creates an event handler that handles a mouse drag on the node.
	 * 
	 * @return the event handler.
	 */
	private EventHandler<KeyEvent> rotacioner() {
            // handler for enter key press / release events, other keys are
            // handled by the parent (keyboard) node handler
            EventHandler<KeyEvent> keyEventHandler = new EventHandler<KeyEvent>() {
                    public void handle(KeyEvent keyEvent) {
                        
                        if (keyEvent.getCode() == KeyCode.R) {
                            
                            if (boat.getRotate()==0){
                                boat.setRotate(90);
                               // System.out.println("X: " + boat.getLayoutX() +" Y: " + boat.getLayoutY() + "/n");
                            } else if (boat.getRotate()==90){
                                boat.setRotate(0);
                                //System.out.println("X: " + boat.getLayoutX() +" Y: " + boat.getLayoutY() + "/n");
                                //boat.get
                            }

                            //keyEvent.consume();
                        }
                    }
                };
            return keyEventHandler;
        }
        
        private EventHandler<MouseEvent> dragMouse() {
		EventHandler<MouseEvent> dragHandler = new EventHandler<MouseEvent>() {

			public void handle(MouseEvent event) {
				if (event.getButton() == MouseButton.PRIMARY) {
					// find the delta coordinates by subtracting the new mouse
					// coordinates with the old.
					double deltaX = event.getSceneX() - m_nMouseX;
					double deltaY = event.getSceneY() - m_nMouseY;

					// add the delta coordinates to the node coordinates.
					m_nX += deltaX;
					m_nY += deltaY;

					// set the layout for the draggable node.
					boat.setLayoutX(m_nX);
					boat.setLayoutY(m_nY);

					// get the latest mouse coordinate.
					m_nMouseX = event.getSceneX();
					m_nMouseY = event.getSceneY();

				}
			}
		};
		return dragHandler;
	}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        boat.setOnMousePressed(pressMouse());
        boat.setOnMouseDragged(dragMouse());
        chat.setOnKeyPressed(rotacioner());
    }    
}

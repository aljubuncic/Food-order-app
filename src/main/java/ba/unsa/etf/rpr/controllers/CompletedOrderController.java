package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

/**
 * JavaFX Controller for completed order window
 */
public class CompletedOrderController extends AbstractController{
    public Label orderID;
    private final int orderId;
    private final User user;
    public CompletedOrderController(int orderId, User user){
        this.orderId=orderId;
        this.user = user;
    }

    /**
     * Sets the label for the newly placed order ID
     */
    @FXML
    public void initialize(){
        orderID.setText(String.valueOf(orderId));
    }

    /**
     * Exits the window and opens a login window
     * @param actionEvent
     * @throws IOException
     */

    public void logoutClick(ActionEvent actionEvent) throws IOException {
        closeWindow(actionEvent);
        openLoginWindow();
    }

    /**
     * Exits the window and opens a home window
     * @param actionEvent
     * @throws IOException
     */
    public void homeClick(ActionEvent actionEvent) throws IOException {
        closeWindow(actionEvent);
        openHomeWindow(user,null);
    }
}

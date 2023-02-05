package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CompletedOrderController {
    public Label orderID;
    private int orderId;
    public CompletedOrderController(int orderId){
        this.orderId=orderId;
    }
    @FXML
    public void initialize(){
        orderID.setText(String.valueOf(orderId));
    }

    public void logoutClick(ActionEvent actionEvent) {
    }

    public void homeClick(ActionEvent actionEvent) {
    }
}

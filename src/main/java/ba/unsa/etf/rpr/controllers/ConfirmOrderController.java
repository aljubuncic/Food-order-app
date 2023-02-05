package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Meal;
import ba.unsa.etf.rpr.domain.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

import java.util.List;

public class ConfirmOrderController {
    private double priceOfOrder = 0;
    public RadioButton deliveryRadioButton;
    public RadioButton sendEmailRadioButton;
    public TextField addressField;
    public TextField emailField;
    public ListView cartList;
    public Label priceOfOrderLabel;
    public Label addressLabel;
    public Label emailLabel;
    public Label checkEmail;
    public Label checkAddress;
    private List<Meal> orderList;
    private User user;
    private void addListenerToRadioButton(RadioButton radioButton, TextField correspondingTextField,Label correspondingLabel){
        radioButton.selectedProperty().addListener((observable ->{
            if(radioButton.selectedProperty().getValue()) {
                correspondingTextField.setEditable(true);
                correspondingTextField.setDisable(false);
                correspondingLabel.setDisable(false);
            }
            else {
                correspondingTextField.setEditable(false);
                correspondingTextField.setDisable(true);
                correspondingLabel.setDisable(true);
            }
        }));
    }
    public ConfirmOrderController(List<Meal> selectedMeals, User user){
        this.orderList = selectedMeals;
        this.user = user;
    }
    @FXML
    public void initialize(){
        if(user.getAddress()!=null)
            addressField.setText(user.getAddress());
        if(user.getEmail()!=null)
            emailField.setText(user.getEmail());
        addressField.setDisable(true);
        addressLabel.setDisable(true);
        emailField.setDisable(true);
        emailLabel.setDisable(true);
        for(Meal meal : orderList){
            priceOfOrder+= meal.getPrice();
            StringBuilder builder = new StringBuilder();
            builder.append(meal.getName()).append(" ").append(meal.getQuantity()).append("gr ").append(meal.getPrice()).append(" KM");
            cartList.getItems().add(builder.toString());
        }
        priceOfOrderLabel.setText(priceOfOrder + " KM");

        addListenerToRadioButton(deliveryRadioButton,addressField,addressLabel);
        addListenerToRadioButton(sendEmailRadioButton,emailField,emailLabel);
    }
}

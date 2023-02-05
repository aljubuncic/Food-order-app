package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.OrderManager;
import ba.unsa.etf.rpr.domain.Meal;
import ba.unsa.etf.rpr.domain.Order;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.OrderException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class ConfirmOrderController extends AbstractController{
    private final OrderManager orderManager = new OrderManager();
    private double priceOfOrder = 0;
    public RadioButton deliveryRadioButton;
    public RadioButton sendEmailRadioButton;
    public TextField addressField;
    public TextField emailField;
    public ListView cartListView;
    public Label priceOfOrderLabel;
    public Label addressLabel;
    public Label emailLabel;
    public Label checkEmail;
    public Label checkAddress;
    private List<Meal> orderList;
    private User user;

    /**
     * Adds listeners to radio buttons which sets the corresponding label and text field to disabled
     * @param radioButton
     * @param correspondingTextField
     * @param correspondingLabel
     */
    private void addListenerToRadioButton(RadioButton radioButton, TextField correspondingTextField,Label correspondingLabel,Label correspondingCheckLabel){
        radioButton.selectedProperty().addListener((observable ->{
            if(radioButton.selectedProperty().getValue()) {
                correspondingTextField.setEditable(true);
                correspondingTextField.setDisable(false);
                correspondingLabel.setDisable(false);
                correspondingCheckLabel.setDisable(false);
            }
            else {
                correspondingTextField.setEditable(false);
                correspondingTextField.setDisable(true);
                correspondingLabel.setDisable(true);
                correspondingCheckLabel.setDisable(true);
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
            addMealToCartListView(cartListView,meal);
        }
        priceOfOrderLabel.setText(priceOfOrder + " KM");

        addListenerToRadioButton(deliveryRadioButton,addressField,addressLabel,checkAddress);
        addListenerToRadioButton(sendEmailRadioButton,emailField,emailLabel,checkEmail);
        addListenerToField(addressField,checkAddress,"Invalid address","Address","\\S+");
        addListenerToField(emailField,checkEmail,"Invalid email","Email","^[A-Za-z0-9+_.-]+@(.+)$");
    }
    public void goBackClick(ActionEvent actionEvent) throws Exception {
        closeWindow(actionEvent);
        openHomeWindow(user);
    }
    public void orderClick(ActionEvent actionEvent) throws IOException {
        Order order;
        try {
            order = orderManager.add(user,orderList,priceOfOrder,emailField.getText(),addressField.getText());
        } catch (OrderException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage(),ButtonType.CLOSE).showAndWait();
            return;
        }
        Stage newStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/completedOrder.fxml"));
        CompletedOrderController controller = new CompletedOrderController(order.getId(),user);
        loader.setController(controller);
        newStage.setTitle("Order success");
        newStage.setScene(new Scene(loader.load(), 400, 250));
        newStage.setResizable(false);
        newStage.getIcons().add(new Image("img/iconOnWindow.png"));
        newStage.show();
    }
}

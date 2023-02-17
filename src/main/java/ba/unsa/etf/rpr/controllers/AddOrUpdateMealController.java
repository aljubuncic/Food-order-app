package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.MealManager;
import ba.unsa.etf.rpr.domain.Meal;
import ba.unsa.etf.rpr.exceptions.OrderException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * JavaFX controller for creation and alteration of Meal object
 */
public class AddOrUpdateMealController extends AbstractController{
    private final MealManager mealManager = new MealManager();
    public TextField nameField;
    public TextField priceField;
    public TextField quantityField;
    public ChoiceBox<String> typeField;
    public Button defaultButton;
    private Meal mealToUpdate;
    public AddOrUpdateMealController(){}
    public AddOrUpdateMealController(Meal meal){
        mealToUpdate = meal;
    }

    /**
     * Sets the fields to be already populated with meal info (if meal is to be updated)
     * Sets the text of the button to "Add" or "Update" depending on the situation
     */
    @FXML
    public void initialize() {
        String typesOfMeals[] ={"Appetizer","Main dish", "Desert", "Drink"};
        typeField.setItems(FXCollections.observableList(new ArrayList(Arrays.asList(typesOfMeals))));
        typeField.setValue("Appetizer");
        if(mealToUpdate==null) {
            defaultButton.setText("Add");
            return;
        }
        defaultButton.setText("Update");
        nameField.setText(mealToUpdate.getName());
        priceField.setText(String.valueOf(mealToUpdate.getPrice()));
        quantityField.setText(String.valueOf(mealToUpdate.getQuantity()));
        typeField.setValue(mealToUpdate.getType());
    }

    /**
     * Event handler for add or update button
     * @param actionEvent
     */
    public void addOrUpdateClick(ActionEvent actionEvent) {
        if(nameField.getText().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Name is empty", ButtonType.OK).showAndWait();
            return;
        }
        if(!priceField.getText().matches("[0-9]{1,13}(\\.[0-9]*)?")) {
            new Alert(Alert.AlertType.WARNING, "Price can only contain number", ButtonType.OK).showAndWait();
            return;
        }
        if(!quantityField.getText().matches("^[1-9]\\d*$")){
            new Alert(Alert.AlertType.WARNING, "Quantity is integer", ButtonType.OK).showAndWait();
            return;
        }
        if(mealToUpdate!=null) {
            try {
                mealToUpdate.setName(nameField.getText());
                mealToUpdate.setPrice(Double.parseDouble(priceField.getText()));
                mealToUpdate.setQuantity(Integer.parseInt(quantityField.getText()));
                mealToUpdate.setType(typeField.getValue());
                mealManager.update(mealToUpdate);
            } catch (OrderException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage(),ButtonType.CLOSE).showAndWait();
                return;
            }
            new Alert(Alert.AlertType.INFORMATION,mealToUpdate.getName()+" successfully updated!",ButtonType.OK);
        }
        else {
            Meal meal = new Meal();
            meal.setName(nameField.getText());
            meal.setPrice(Double.parseDouble(priceField.getText()));
            meal.setQuantity(Integer.parseInt(quantityField.getText()));
            meal.setType(typeField.getValue());
            try {
                mealManager.add(meal);
            } catch (OrderException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.CLOSE).showAndWait();
                return;
            }
            new Alert(Alert.AlertType.INFORMATION, meal.getName() + " successfully added!", ButtonType.OK);
        }
        closeWindow(actionEvent);
    }

    /**
     * Cancels all changes and closes the window
     * @param actionEvent
     */
    public void cancelClick(ActionEvent actionEvent) {
        closeWindow(actionEvent);
    }
}

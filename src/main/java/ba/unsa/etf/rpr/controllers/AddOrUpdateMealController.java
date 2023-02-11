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
}

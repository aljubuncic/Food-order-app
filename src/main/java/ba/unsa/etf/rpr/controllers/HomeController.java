package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.MealManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.Meal;
import ba.unsa.etf.rpr.domain.TypeOfMeal;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.OrderException;
import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class HomeController {
    private final UserManager userManager= new UserManager();
    private final MealManager mealManager = new MealManager();
    public Label usernameLabel;
    public Label nameLabel;
    public Label surnameLabel;
    private String username;
    @FXML
    private TableView<Meal> mealsTable;
    @FXML
    private TableColumn<Meal,String> nameColumn;
    @FXML
    private TableColumn<Meal, Double> priceColumn;
    @FXML
    private TableColumn<Meal,Integer> quantityColumn;
    @FXML
    private TableColumn<Meal, String> typeColumn;

    public HomeController(String username) {
        this.username = username;
    }

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType().toString()));
        refreshMeals();

        usernameLabel.setText(username);
        try {
            User user = userManager.getByUsername(username);
            nameLabel.setText(user.getName());
            surnameLabel.setText(user.getSurname());
        } catch (OrderException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * fetches meals from database
     */
    private void refreshMeals(){
        try{
            mealsTable.setItems(FXCollections.observableList(mealManager.getAll()));
            mealsTable.refresh();
        }
        catch(OrderException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage(), ButtonType.CLOSE).showAndWait();
        }
    }
    public void clickAbout(ActionEvent actionEvent) throws Exception{
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/about.fxml"));
        newStage.setTitle("About");
        newStage.setScene(new Scene(root, 300, 100));
        newStage.setResizable(false);
        newStage.getIcons().add(new Image("img/iconOnWindow.png"));
        newStage.show();
    }
}

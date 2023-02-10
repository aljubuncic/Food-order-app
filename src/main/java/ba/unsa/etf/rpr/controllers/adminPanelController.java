package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.MealManager;
import ba.unsa.etf.rpr.business.OrderManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Meal;
import ba.unsa.etf.rpr.domain.Order;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.OrderException;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class adminPanelController {
    private final OrderManager orderManager = new OrderManager();
    private final MealManager mealManager = new MealManager();
    private final UserManager userManager = new UserManager();

    @FXML
    public TableView<Order> ordersTable;
    @FXML
    public TableColumn<Order,String> userColumn;
    @FXML
    public TableColumn<Order,String> dateOfOrderColumn;
    @FXML
    public TableColumn<Order,Double> orderPriceColumn;
    @FXML
    public TableColumn<Order,String> orderAddressColumn;
    @FXML
    public TableColumn<Order,String> confirmationEmailColumn;

    @FXML
    public TableView<Meal> mealsTable;
    @FXML
    public TableColumn<Meal,String> mealNameColumn;
    @FXML
    public TableColumn<Meal,Double> mealPriceColumn;
    @FXML
    public TableColumn<Meal,Integer> quantityColumn;
    @FXML
    public TableColumn<Meal,String> typeColumn;

    @FXML
    public TableView<User> usersTable;
    @FXML
    public TableColumn<User,String> userNameColumn;
    @FXML
    public TableColumn<User,String> surnameColumn;
    @FXML
    public TableColumn<User,String> usernameColumn;
    @FXML
    public TableColumn<User,String> emailColumn;
    @FXML
    public TableColumn<User,String> userAddressColumn;
    @FXML
    public TableColumn<User,String> telephoneNumberColumn;

    private void refreshOrders(){
        try{
            ordersTable.setItems(FXCollections.observableList(orderManager.getAll()));
            ordersTable.refresh();
        }
        catch (OrderException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage(), ButtonType.CLOSE);
        }
    }

    private void refreshMeals(){
        try{
            mealsTable.setItems(FXCollections.observableList(mealManager.getAll()));
            mealsTable.refresh();
        }
        catch (OrderException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage(), ButtonType.CLOSE);
        }
    }

    private void refreshUsers(){
        try{
            usersTable.setItems(FXCollections.observableList(userManager.getAll()));
            usersTable.refresh();
        }
        catch (OrderException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage(), ButtonType.CLOSE);
        }
    }

    @FXML
    public void initialize(){
        userColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser().getUsername()));
        dateOfOrderColumn.setCellValueFactory(cellData -> new SimpleStringProperty((cellData.getValue().getDateOfOrder().toString())));
        orderPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        confirmationEmailColumn.setCellValueFactory(new PropertyValueFactory<>("confirmationEmail"));
        orderAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        mealNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        mealPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType().toString()));

        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        userAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        telephoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("telephoneNumber"));

        refreshOrders();
        refreshMeals();
        refreshUsers();
    }



}

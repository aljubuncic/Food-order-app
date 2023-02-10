package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.MealManager;
import ba.unsa.etf.rpr.business.OrderManager;
import ba.unsa.etf.rpr.business.Order_MealManager;
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
import java.util.Optional;

public class adminPanelController {
    private final OrderManager orderManager = new OrderManager();
    private final MealManager mealManager = new MealManager();
    private final UserManager userManager = new UserManager();
    private final Order_MealManager orderMealManager = new Order_MealManager();

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

    /**
     * fetches orders from database
     */

    private void refreshOrders(){
        try{
            ordersTable.setItems(FXCollections.observableList(orderManager.getAll()));
            ordersTable.refresh();
        }
        catch (OrderException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage(), ButtonType.CLOSE);
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
        catch (OrderException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage(), ButtonType.CLOSE);
        }
    }

    /**
     * fetches users from database
     */

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

    public void viewMealsClick(ActionEvent actionEvent) {
        if(ordersTable.getSelectionModel().getSelectedIndex()==-1) {
            new Alert(Alert.AlertType.WARNING, "No order selected", ButtonType.OK);
            return;
        }
        Order order = ordersTable.getSelectionModel().getSelectedItem();
        List<Meal> mealList;
        try {
            mealList = DaoFactory.order_MealDao().getMealsFromOrder(order);
        } catch (OrderException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage(),ButtonType.CLOSE);
            return;
        }
    }

    /**
     * Deletes an order from tableview and database (with a confirmation alert)
     * @param actionEvent
     */
    public void deleteOrderClick(ActionEvent actionEvent) {
        if(ordersTable.getSelectionModel().getSelectedIndex()==-1) {
            new Alert(Alert.AlertType.WARNING, "Please select an order", ButtonType.CLOSE);
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to delete this order?",ButtonType.YES,ButtonType.CANCEL);
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get()!=ButtonType.YES)
            return;
        Order order = ordersTable.getSelectionModel().getSelectedItem();
        try{
            orderMealManager.deleteOrder(order);
            orderManager.delete(order);
        }
        catch(OrderException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage(),ButtonType.CLOSE);
        }
        refreshOrders();
    }
}

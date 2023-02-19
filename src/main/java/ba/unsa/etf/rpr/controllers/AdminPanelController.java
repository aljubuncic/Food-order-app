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
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * JavaFX Controller for admin panel - user, meal and order management
 */
public class AdminPanelController extends AbstractController{
    private final OrderManager orderManager = new OrderManager();
    private final MealManager mealManager = new MealManager();
    private final UserManager userManager = new UserManager();
    private final Order_MealManager orderMealManager = new Order_MealManager();

    @FXML
    public TableView<Order> ordersTable;
    @FXML
    public TableColumn<Order,Integer> orderIDColumn;
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
    public TabPane tabPane;
    public MenuBar menuBar;

    /**
     * fetches orders from database
     */

    private void refreshOrders(){
        try{
            ordersTable.setItems(FXCollections.observableList(orderManager.getAll()));
            ordersTable.refresh();
        }
        catch (OrderException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage(), ButtonType.CLOSE).showAndWait();
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
            new Alert(Alert.AlertType.ERROR,e.getMessage(), ButtonType.CLOSE).showAndWait();
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
            new Alert(Alert.AlertType.ERROR,e.getMessage(), ButtonType.CLOSE).showAndWait();
        }
    }
    /**
     * Creates a confirmation alert and returns true if user has confirmed deletion of object
     * @param text text to be displayed on confirmation alert
     * @return
     */
    private boolean confirmationOfDelete(String text){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you sure you want to delete this " + text + "?",ButtonType.YES,ButtonType.CANCEL);
        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.YES;
    }
    /**
     * Opens a window for adding or updating a meal (if mealToUpdate is not null) and refreshes a table of meals afterwards
     * @param mealToUpdate to be updated
     * @throws IOException
     */
    private void openEditMeal(Meal mealToUpdate) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/adminPanelWindows/addOrUpdateMeal.fxml"));
        AddOrUpdateMealController controller;
        if(mealToUpdate!=null) {
            controller = new AddOrUpdateMealController(mealToUpdate);
            stage.setTitle("Update a meal");
        }
        else {
            stage.setTitle("Add a meal");
            controller=new AddOrUpdateMealController();
        }
        loader.setController(controller);
        stage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.getIcons().add(new Image("img/iconOnWindow.png"));
        stage.show();
        stage.setOnHiding(event -> {
            ((Stage)tabPane.getScene().getWindow()).show();
            refreshMeals();
        });
    }

    /**
     * Populates the tableviews for orders, users and meals from database
     */
    @FXML
    public void initialize(){
        orderIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        userColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUser().getUsername()));
        dateOfOrderColumn.setCellValueFactory(cellData -> new SimpleStringProperty((cellData.getValue().getDateOfOrder().toString())));
        orderPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        confirmationEmailColumn.setCellValueFactory(new PropertyValueFactory<>("confirmationEmail"));
        orderAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        mealNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        mealPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));

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
    /**
     * Opens a new window which displays meals in a selected order
     * @param actionEvent
     * @throws IOException
     */
    public void viewMealsClick(ActionEvent actionEvent) throws IOException {
        if(ordersTable.getSelectionModel().getSelectedIndex()==-1) {
            new Alert(Alert.AlertType.WARNING, "No order selected", ButtonType.OK).showAndWait();
            return;
        }
        Order order = ordersTable.getSelectionModel().getSelectedItem();
        List<Meal> orderList;
        try {
            orderList = DaoFactory.order_MealDao().getMealsFromOrder(order);
        } catch (OrderException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage(),ButtonType.CLOSE);
            return;
        }
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/adminPanelWindows/orderMealList.fxml"));
        stage.setTitle("Order by " + order.getUser().getUsername() + " " + " on " + order.getDateOfOrder());
        OrderMealListController controller = new OrderMealListController(orderList);
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.getIcons().add(new Image("img/iconOnWindow.png"));
        stage.show();
    }

    /**
     * Deletes the selected order from tableview and database (with a confirmation alert)
     * @param actionEvent
     */
    public void deleteOrderClick(ActionEvent actionEvent) {
        if(!isAnyItemSelected(ordersTable.getSelectionModel().getSelectedIndex(),"No order selected"))
            return;
        if(!confirmationOfDelete("order"))
            return;
        Order order = ordersTable.getSelectionModel().getSelectedItem();
        try{
            orderManager.delete(order);
            refreshOrders();
        }
        catch(OrderException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage(),ButtonType.CLOSE).showAndWait();
            return;
        }
        new Alert(Alert.AlertType.INFORMATION,"Order with ID " + order.getId()+ " successfully deleted",ButtonType.OK).showAndWait();
    }

    /**
     * Switches to users tab and selects and focuses a selected user in a users table
     * @param actionEvent
     */
    public void viewUserClick(ActionEvent actionEvent) {
        if(!isAnyItemSelected(ordersTable.getSelectionModel().getSelectedIndex(),"No order selected"))
            return;
        tabPane.getSelectionModel().select(2);
        User user = ordersTable.getSelectionModel().getSelectedItem().getUser();
        usersTable.getSelectionModel().select(user);
        int index = usersTable.getSelectionModel().getSelectedIndex();
        usersTable.getFocusModel().focus(index);
    }

    /**
     * Event handler for menu item add
     * @param actionEvent
     * @throws IOException
     */
    public void addMealClick(ActionEvent actionEvent) throws IOException {
        openEditMeal(null);
    }

    /**
     * Event handler for menu item update
     * @param actionEvent
     * @throws IOException
     */
    public void updateMealClick(ActionEvent actionEvent) throws IOException {
        if(!isAnyItemSelected(mealsTable.getSelectionModel().getSelectedIndex(),"No meal selected"))
            return;
        openEditMeal(mealsTable.getSelectionModel().getSelectedItem());
    }
    /**
     * Deletes the selected meal from tableview and database (with a confirmation alert)
     * @param actionEvent
     */
    public void deleteMealClick(ActionEvent actionEvent) {
        if(!isAnyItemSelected(mealsTable.getSelectionModel().getSelectedIndex(),"No meal selected"))
            return;
        if (!confirmationOfDelete("meal"))
            return;
        Meal meal = mealsTable.getSelectionModel().getSelectedItem();
        try {
            mealManager.delete(meal);
            refreshMeals();
        }
        catch (OrderException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage(),ButtonType.CLOSE).showAndWait();
            return;
        }
        new Alert(Alert.AlertType.INFORMATION,meal.getName() + " successfully deleted",ButtonType.OK).showAndWait();
    }

    /**
     * Deletes the selected user from tableview and database (with a confirmation alert)
     * WARNING: This also removes every order made by user in the past
     * @param actionEvent
     */
    public void deleteUserClick(ActionEvent actionEvent) throws IOException {
        if(!isAnyItemSelected(usersTable.getSelectionModel().getSelectedIndex(),"No user selected"))
            return;
        if(!confirmationOfDelete("user"))
            return;
        User user = usersTable.getSelectionModel().getSelectedItem();
        try{
            Properties adminCredentials = new Properties();
            adminCredentials.load(new FileReader("src/main/resources/admin.properties"));
            if(user.getUsername().equals(adminCredentials.getProperty("username"))) {
                new Alert(Alert.AlertType.WARNING, "Admin cannot be deleted!", ButtonType.OK).showAndWait();
                return;
            }
            userManager.delete(user);
            refreshUsers();
            refreshOrders();
        }
        catch (OrderException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage(),ButtonType.CLOSE);
            return;
        }
        new Alert(Alert.AlertType.INFORMATION,user.getUsername() + " successfully deleted",ButtonType.OK).showAndWait();
    }

    /**
     * Exits the admin panel and opens a login window
     * @param actionEvent
     * @throws IOException
     */
    public void exitClick(ActionEvent actionEvent) throws IOException {
        ((Stage) menuBar.getScene().getWindow()).close();
        openLoginWindow();
    }
}

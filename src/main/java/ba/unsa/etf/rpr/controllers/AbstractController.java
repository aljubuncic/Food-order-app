package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.Meal;
import ba.unsa.etf.rpr.domain.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * Abstract class which implements usual methods for controller classes
 */
public abstract class AbstractController {
    /**
     * Sets the field invalid (adds red border on the text field and warning text beneath)
     * @param textField
     * @param checkLabel
     * @param errorMessage
     */
    protected void setFieldInvalid(TextField textField, Label checkLabel, String errorMessage) {
        textField.getStyleClass().add("fieldIsInvalid");
        checkLabel.setText(errorMessage);
    }
    /**
     * Sets the field valid (removes red border on the text field and warning text beneath)
     * @param textField
     * @param checkLabel
     */
    protected void setFieldValid(TextField textField, Label checkLabel) {
        textField.getStyleClass().removeAll("fieldIsInvalid");
        checkLabel.setText("");
    }
    /**
     * Closes the window corresponding to actionEvent
     * @param actionEvent
     */
    protected void closeWindow(ActionEvent actionEvent){
        Node node = (Node) actionEvent.getSource();
        Stage oldStage = (Stage) node.getScene().getWindow();
        oldStage.close();
    }
    /**
     * Checks if the string n matches regex and sets the responding field valid or invalid
     * @param n
     * @param regex
     * @param textField
     * @param checkLabel
     * @param fieldName
     * @param errorMessage
     * @return
     */
    protected boolean matchesRegex(String n,String regex,TextField textField, Label checkLabel,String fieldName,String errorMessage){
        if(!n.matches(regex)) {
            if (n.isEmpty()) {
                setFieldInvalid(textField, checkLabel,  fieldName + " is empty");
            } else {
                setFieldInvalid(textField, checkLabel, errorMessage);
            }
            return false;
        }
        return true;
    }
    /**
     * Adds listener to the specified text field and checks its validity in real time
     * @param textField
     * @param checkLabel
     * @param errorMessage
     * @param fieldName
     * @param regex
     */
    protected void addListenerToField(TextField textField, Label checkLabel, String errorMessage,String fieldName,String regex){
        textField.textProperty().addListener(((observableValue, o, n) -> {
            if(matchesRegex(n,regex,textField,checkLabel,fieldName,errorMessage))
                setFieldValid(textField,checkLabel);
        }));
    }

    /**
     * Adds a meal in string format (name, quantity in grams, price in KM) to cart (ListView)
     * @param cartListView
     * @param meal
     */
    protected void addMealToCartListView(ListView cartListView, Meal meal) {
        StringBuilder builder = new StringBuilder();
        builder.append(meal.getName()).append(" ").append(meal.getQuantity()).append("gr ").append(meal.getPrice()).append(" KM");
        cartListView.getItems().add(builder.toString());
    }
    /**
     * Opens a login window
     * @throws IOException
     */
    protected void openLoginWindow() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/login.fxml"));
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 450, 400));
        stage.setResizable(false);
        stage.getIcons().add(new Image("img/iconOnWindow.png"));
        stage.show();
    }
    /**
     * Opens a home window of specified user
     * @param user
     * @throws IOException
     */
    protected void openHomeWindow(User user) throws IOException {
        Stage newStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/home.fxml"));
        HomeController homeController = new HomeController(user);
        loader.setController(homeController);
        newStage.setTitle("Home");
        newStage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        newStage.getIcons().add(new Image("img/iconOnWindow.png"));
        newStage.show();
    }
}

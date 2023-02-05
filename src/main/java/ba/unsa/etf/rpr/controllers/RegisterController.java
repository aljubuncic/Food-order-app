package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.OrderException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class RegisterController extends AbstractController {

    private UserManager userManager = new UserManager();

    public Label checkName;
    public Label checkSurname;
    public Label checkUsername;
    public Label checkEmail;
    public Label checkTelephoneNumber;
    public Label checkPassword;
    public Label checkConfirmedPassword;
    public TextField nameField;
    public TextField surnameField;
    public TextField usernameField;
    public TextField emailField;
    public TextField telephoneNumberField;
    public TextField addressField;
    public PasswordField passwordField;
    public PasswordField confirmedPasswordField;
    public Button registerButton;
    @FXML
    public void initialize() {
        // does not accept empty string, numbers and special characters
        addListenerToField(nameField,checkName,"Name is invalid","Name","^[a-zA-Z\\s]+");
        // does not accept empty string, numbers and special characters
        addListenerToField(surnameField,checkSurname,"Surname is invalid","Surname","^[a-zA-Z\\s]+");
        // accepts characters a-z, A-Z, 0-9, dots, dashes and underscores
        addListenerToField(usernameField,checkUsername,"Username is invalid","Username","^[a-zA-Z0-9._-]+");
        // accepts numbers only
        addListenerToField(telephoneNumberField,checkTelephoneNumber,"Enter numbers only","Telephone Number","^[0-9]+$");
        // does not accept white-space character
        addListenerToField(passwordField,checkPassword,"Password cannot contain spaces","Password","\\S+");
        // accepts characters a-z, A-Z, 0-9, dots, dashes and underscores
        emailField.textProperty().addListener(((observableValue, o, n) -> {
            if(n.isEmpty())
                setFieldValid(emailField,checkEmail);
            else {
                if (!n.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                    setFieldInvalid(emailField, checkEmail, "Email has invalid format");
                } else
                    setFieldValid(emailField, checkEmail);
            }
        }));
        confirmedPasswordField.textProperty().addListener(((observableValue, o, n) -> {
            confirmedPasswordField.getStyleClass().removeAll("fieldIsEmpty");
            checkConfirmedPassword.setText("");
        }));
    }

    /**
     * Validates register credentials and opens a home window of a registered user
     * @param actionEvent
     * @throws IOException
     */
    public void registerClick(ActionEvent actionEvent) throws IOException {
        if(!matchesRegex(nameField.getText(),"^[a-zA-Z\\s]+",nameField,checkName,"Name","Name is invalid")) {
            nameField.requestFocus();
            return;
        }
        if(!matchesRegex(surnameField.getText(),"^[a-zA-Z\\s]+",surnameField,checkSurname,"Surname","Surname is invalid")) {
            surnameField.requestFocus();
            return;
        }
        if(!matchesRegex(usernameField.getText(),"^[a-zA-Z0-9._-]+",usernameField,checkUsername,"Username","Username is invalid")) {
            usernameField.requestFocus();
            return;
        }
        if(!emailField.getText().isEmpty() && !emailField.getText().matches("^[A-Za-z0-9+_.-]+@(.+)$")){
            emailField.requestFocus();
            emailField.getStyleClass().add("fieldIsEmpty");
            checkEmail.setText("Email has invalid format");
        }
        if(!matchesRegex(telephoneNumberField.getText(),"^[0-9]+$",telephoneNumberField,checkTelephoneNumber,"Telephone number","Enter numbers only")) {
            telephoneNumberField.requestFocus();
            return;
        }
        if(!matchesRegex(passwordField.getText(),"^\\S+",passwordField,checkPassword,"Password","Password cannot contain spaces")){
            passwordField.requestFocus();
            return;
        }
        if(!confirmedPasswordField.getText().equals(passwordField.getText())){
            confirmedPasswordField.requestFocus();
            confirmedPasswordField.getStyleClass().add("fieldIsEmpty");
            checkConfirmedPassword.setText("Passwords do not match");
            return;
        }
        User addedUser;
        try {
            User user = new User();
            user.setName(nameField.getText());
            user.setSurname(surnameField.getText());
            user.setUsername(usernameField.getText());
            if(!emailField.getText().isEmpty())
                user.setEmail(emailField.getText());
            user.setTelephoneNumber(telephoneNumberField.getText());
            if(!addressField.getText().isEmpty())
                user.setAddress(addressField.getText());
            user.setPassword(passwordField.getText());
            addedUser = userManager.add(user);
        }
        catch (OrderException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK).showAndWait();
            return;
        }
        new Alert(Alert.AlertType.CONFIRMATION,"You have successfully registered!",ButtonType.OK).showAndWait();
        closeWindow(actionEvent);
        Stage newStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/home.fxml"));
        HomeController homeController = new HomeController(addedUser);
        loader.setController(homeController);
        newStage.setTitle("Home");
        newStage.setScene(new Scene(loader.load(), USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        newStage.getIcons().add(new Image("img/iconOnWindow.png"));
        newStage.show();
    }

    /**
     * Switches from login window to register window
     * @param actionEvent
     * @throws Exception
     */
    public void switchToLoginWindow(ActionEvent actionEvent) throws Exception{
        closeWindow(actionEvent);
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/login.fxml"));
        newStage.setTitle("Login");
        newStage.setScene(new Scene(root, 450, 400));
        newStage.setResizable(false);
        newStage.getIcons().add(new Image("img/iconOnWindow.png"));
        newStage.show();
    }
}

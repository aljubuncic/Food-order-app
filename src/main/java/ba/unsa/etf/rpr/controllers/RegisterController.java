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

public class RegisterController {

    private UserManager userManager = new UserManager();

    private void closeRegisterWindow(ActionEvent actionEvent){
        Node node = (Node) actionEvent.getSource();
        Stage oldStage = (Stage) node.getScene().getWindow();
        oldStage.close();
    }
    private void setFieldInvalid(TextField textField, Label checkLabel, String errorMessage) {
            textField.getStyleClass().add("fieldIsEmpty");
            checkLabel.setText(errorMessage);
    }
    private void setFieldValid(TextField textField, Label checkLabel) {
        textField.getStyleClass().removeAll("fieldIsEmpty");
        checkLabel.setText("");
    }
    private boolean matchesRegex(String n,String regex,TextField textField, Label checkLabel,String fieldName,String errorMessage){
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
    private void addListenerToField(TextField textField, Label checkLabel, String errorMessage,String fieldName,String regex){
        textField.textProperty().addListener(((observableValue, o, n) -> {
            if(matchesRegex(n,regex,textField,checkLabel,fieldName,errorMessage))
                setFieldValid(textField,checkLabel);
        }));
    }
    private void setFieldInvalidAfterClick(TextField textField, Label checkLabel, String errorMessage){
        textField.requestFocus();
        setFieldInvalid(textField,checkLabel,errorMessage);
    }

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

    public void registerClick(ActionEvent actionEvent) {
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


    }

    public void switchToLoginWindow(ActionEvent actionEvent) throws Exception{
        closeRegisterWindow(actionEvent);
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/login.fxml"));
        newStage.setTitle("Login");
        newStage.setScene(new Scene(root, 450, 400));
        newStage.setResizable(false);
        newStage.getIcons().add(new Image("img/iconOnWindow.png"));
        newStage.show();
    }
}

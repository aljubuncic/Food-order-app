package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class RegisterController {

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

    public void registerClick(ActionEvent actionEvent) {
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

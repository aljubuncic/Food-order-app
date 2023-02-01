package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.exceptions.OrderException;
import com.mysql.cj.xdevapi.Schema;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class LoginController {

    private UserManager userManager = new UserManager();

    private void closeLoginWindow(ActionEvent actionEvent){
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

    public TextField usernameField;
    public PasswordField passwordField;
    public Button loginButton;

    public Label checkUsername;
    public Label checkPassword;
    @FXML
    public void initialize(){
        addListenerToField(usernameField,checkUsername,"Username is invalid","Username","^[a-zA-Z0-9._-]+");
        addListenerToField(passwordField,checkPassword,"Password cannot contain spaces","Password","^\\S+");
    }

    public void loginClick(ActionEvent actionEvent) throws IOException {
        if(!matchesRegex(usernameField.getText(),"^[a-zA-Z0-9._-]+",usernameField,checkUsername,"Username","Username is invalid")) {
            usernameField.requestFocus();
            return;
        }
        if(!matchesRegex(passwordField.getText(),"^\\S+",passwordField,checkPassword,"Password","Password cannot contain spaces")){
            passwordField.requestFocus();
            return;
        }

        try {
            userManager.validateLoginCredentials(usernameField.getText(),passwordField.getText());
        } catch (OrderException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login error");
            alert.setHeaderText("Login credentials are not valid");
            alert.setContentText(e.getMessage());
            if(e.getMessage().equals("Incorrect password"))
                passwordField.requestFocus();
            else
                usernameField.requestFocus();
            alert.showAndWait();
            return;
        }
    }

    public void switchToRegisterWindow(ActionEvent actionEvent) throws Exception{
        closeLoginWindow(actionEvent);
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/register.fxml"));
        newStage.setTitle("Register");
        newStage.setScene(new Scene(root, 500, 450));
        newStage.setResizable(false);
        newStage.getIcons().add(new Image("img/iconOnWindow.png"));
        newStage.show();
    }
}

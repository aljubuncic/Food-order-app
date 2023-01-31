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

    private void setUsernameInvalid(){
        usernameField.getStyleClass().add("fieldIsEmpty");
        checkUsername.setText("Username is empty");
    }
    private void setUsernameValid(){
        usernameField.getStyleClass().removeAll("fieldIsEmpty");
        checkUsername.setText("");
    }
    private void setPasswordInvalid(){
        passwordField.getStyleClass().add("fieldIsEmpty");
        checkPassword.setText("Password is empty");
    }
    private void setPasswordValid(){
        passwordField.getStyleClass().removeAll("fieldIsEmpty");
        checkPassword.setText("");
    }


    public TextField usernameField;
    public PasswordField passwordField;
    public Button loginButton;

    public Label checkUsername;
    public Label checkPassword;
    @FXML
    public void initialize(){
        usernameField.textProperty().addListener(((observableValue, oldUsername, newUsername) -> {
            if(newUsername.isEmpty()) {
                setUsernameInvalid();
            }
            else {
                setUsernameValid();
            }
        }));

        passwordField.textProperty().addListener(((obs, oldPassword, newPassword) -> {
            if(newPassword.isEmpty()) {
                setPasswordInvalid();
            }
            else {
                setPasswordValid();
            }
        }));
    }

    public void loginClick(ActionEvent actionEvent) {
        if(usernameField.getText().isEmpty()){
            usernameField.requestFocus();
            setUsernameInvalid();
            if(passwordField.getText().isEmpty())
                setPasswordInvalid();
            return;
        } else if (passwordField.getText().isEmpty()) {
            passwordField.requestFocus();
            setPasswordInvalid();
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

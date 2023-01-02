package ba.unsa.etf.rpr.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LoginController {

    private void closeLoginWindow(ActionEvent actionEvent){
        Node node = (Node) actionEvent.getSource();
        Stage oldStage = (Stage) node.getScene().getWindow();
        oldStage.close();
    }

    public TextField usernameField;
    public PasswordField passwordField;
    public Button loginButton;

    public void loginClick(ActionEvent actionEvent) {
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

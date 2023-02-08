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

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class LoginController extends AbstractController {

    private UserManager userManager = new UserManager();

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

    /**
     * Validates login credentials and opens a home window of a specified user (if exists in the database)
     * @param actionEvent
     * @throws IOException
     */
    public void loginClick(ActionEvent actionEvent) throws IOException {
        if(!matchesRegex(usernameField.getText(),"^[a-zA-Z0-9._-]+",usernameField,checkUsername,"Username","Username is invalid")) {
            usernameField.requestFocus();
            return;
        }
        if(!matchesRegex(passwordField.getText(),"^\\S+",passwordField,checkPassword,"Password","Password cannot contain spaces")){
            passwordField.requestFocus();
            return;
        }
        User user;
        try {
            user = userManager.validateLoginCredentials(usernameField.getText(),passwordField.getText());
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
        closeWindow(actionEvent);
        Properties adminCredentials = new Properties();
        FileReader fileReader = new FileReader("src/main/resources/admin.properties");
        adminCredentials.load(fileReader);
        if(user.getUsername().equals(adminCredentials.getProperty("username")) && user.getPassword().equals(adminCredentials.getProperty("password")))
            System.out.println("Code for opening an admin panel");
        else
            openHomeWindow(user,null);
    }

    /**
     * Switches from the login window to the register window
     * @param actionEvent
     * @throws Exception
     */
    public void switchToRegisterWindow(ActionEvent actionEvent) throws Exception{
        closeWindow(actionEvent);
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/register.fxml"));
        newStage.setTitle("Register");
        newStage.setScene(new Scene(root, 500, 500));
        newStage.setResizable(false);
        newStage.getIcons().add(new Image("img/iconOnWindow.png"));
        newStage.show();
    }
}

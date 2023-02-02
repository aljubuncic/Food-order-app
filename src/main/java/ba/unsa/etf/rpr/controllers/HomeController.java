package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.OrderException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class HomeController {
    private UserManager userManager= new UserManager();
    public Label usernameLabel;
    public Label nameLabel;
    public Label surnameLabel;
    private String username;

    public HomeController(String username) {
        this.username = username;
    }

    @FXML
    public void initialize() {
        usernameLabel.setText(username);
        try {
            User user = userManager.getByUsername(username);
            nameLabel.setText(user.getName());
            surnameLabel.setText(user.getSurname());
        } catch (OrderException e) {
            throw new RuntimeException(e);
        }
    }

}

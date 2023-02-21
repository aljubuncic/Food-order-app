package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

/**
 * Class for GUI (Graphical User Interface) implementation using JavaFX
 */
public class AppFX extends Application {
    /**
     * Starts the GUI and opens a login window
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 450, 400));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("img/iconOnWindow.png"));
        primaryStage.show();
    }
}

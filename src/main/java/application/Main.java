package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
        new SplashController();
        new CreateAccount1();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SplashScreen.fxml")));
        Scene scene = new Scene(root);
        stage.setTitle("MY YATRA");
        stage.setScene(scene);
        stage.show();

        MainController mainController = new MainController();
        if (mainController.a) {
            stage.close();
        }
    }
}
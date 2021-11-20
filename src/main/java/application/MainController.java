package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainController {
//    public void CreateAccount1(ActionEvent event) throws IOException {
//        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CreateAccount1")));
//        Scene scene = new Scene(root);
//        Stage stage= new Stage();
//        stage.setTitle("MY YATRA");
//        stage.setScene(scene);
//        stage.show();
//    }

    boolean a = false;

    @FXML
    private void handleButtonClicks(javafx.event.ActionEvent mouseEvent) {
        try {
            a = true;
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CreateAccount1.fxml")));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

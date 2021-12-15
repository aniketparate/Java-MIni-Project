package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SuccessfullyBooked extends MainController{
    @FXML
    private void returnBackBtn(ActionEvent mouseEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginPage.fxml")));
        stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.setTitle("MY YATRA");
        stage.setScene(new Scene(root));
        stage.show();
    }

}

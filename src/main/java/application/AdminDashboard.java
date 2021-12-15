package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class AdminDashboard extends MainController{

    @FXML
    private void userData(ActionEvent mouseEvent) throws Exception{
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AllUserData.fxml")));
            stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            stage.setTitle("MY YATRA");
            //stage.getIcons().add(new Image("icon.png"));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void stationData(ActionEvent mouseEvent) throws Exception{
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AllStationData.fxml")));
            stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            stage.setTitle("MY YATRA");
            //stage.getIcons().add(new Image("icon.png"));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void trainData(ActionEvent mouseEvent) throws Exception{
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TrainData.fxml")));
            stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            stage.setTitle("MY YATRA");
            //stage.getIcons().add(new Image("icon.png"));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void passengerData(ActionEvent mouseEvent) throws Exception{
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AllPassenger.fxml")));
            stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            stage.setTitle("MY YATRA");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void adminLogOutBtn(ActionEvent mouseEvent) {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminLogin.fxml")));
            stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            stage.setTitle("MY YATRA");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

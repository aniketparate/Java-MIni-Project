package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Objects;
import java.util.ResourceBundle;

public class Payment extends MainController {

    @FXML
    public void logOutBtn(ActionEvent mouseEvent) {
        if (emptyPassenger()) {
            try {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginPage.fxml")));
                stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                stage.setTitle("MY YATRA");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void payment(ActionEvent mouseEvent) {
        if (copyPassengers() && emptyPassenger()) {
            try {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SuccessfullyBooked.fxml")));
                stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                stage.setTitle("MY YATRA");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean emptyPassenger() {
        DatabaseConnector connector = new DatabaseConnector();
        Connection connectDB = connector.getDatabaseLink();
        PreparedStatement pst = null;

        String sql = "truncate table confirm_passenger";
        try {
            pst = connectDB.prepareStatement(sql);
            pst.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean copyPassengers() {
        DatabaseConnector connector = new DatabaseConnector();
        Connection connectDB = connector.getDatabaseLink();
        PreparedStatement pst = null;

        String sql = "INSERT INTO passengers(name,gender,age,train_no,train_name,source,scheduled_departure,destination,scheduled_arrival,username,booked_status) SELECT * FROM confirm_passenger";
        try {
            pst = connectDB.prepareStatement(sql);
            pst.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class LoginPage extends MainController {

    @FXML
    private TextField userNameText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Text loginMessage;

    @FXML
    private void LogIn(ActionEvent mouseEvent) throws SQLException, IOException {
        if (!userNameText.getText().isBlank() && !passwordText.getText().isBlank()) {
            if (loginValidate()) {
                AddPassenger.username = userNameText.getText();
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Dashboard.fxml")));
                stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                stage.setTitle("MY YATRA");
                stage.setScene(new Scene(root));
                stage.show();
            }
        } else {
            loginMessage.setText("Please Enter Username And Password.");
        }
    }

    private boolean loginValidate() throws SQLException {
        DatabaseConnector connector = new DatabaseConnector();
        Connection connectDB = connector.getDatabaseLink();

        String verifyLogin = "SELECT count(1) FROM user WHERE u_username='"+userNameText.getText()+"'AND u_password='"+passwordText.getText()+"'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    //loginMessage.setText("Welcome!");
                    return true;
                }
                else{
                    loginMessage.setText("Invalid Login! Please try again.");
                    return false;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return false;
    }

    @FXML
    private void CreateAccount(ActionEvent mouseEvent) {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("CreateAccount.fxml")));
            stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            stage.setTitle("MY YATRA");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void AdminLogin(ActionEvent mouseEvent) {
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
package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginPage extends MainController {

    @FXML
    private Button loginButton;

    @FXML
    private TextField userNameText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Text loginMessage;

    int count = 0;

    @FXML
    private void LogIn(ActionEvent mouseEvent) throws SQLException, IOException {
        loginMessage.setText("Invalid Login!");

        if (!userNameText.getText().isBlank() && !passwordText.getText().isBlank()) {
            loginValidate();
            if (count == 1) {
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

    public void loginValidate() throws SQLException {
        DatabaseConnector connector = new DatabaseConnector();
        Connection connectDB = connector.getDatabaseLink();

        String verifyLogin = "SELECT count(1) FROM user WHERE u_username='"+userNameText.getText()+"'AND u_password='"+passwordText.getText()+"'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    //loginMessage.setText("Welcome!");
                    count++;
                    //Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
                }
                else{
                    loginMessage.setText("Invalid Login! Please try again.");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
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
}
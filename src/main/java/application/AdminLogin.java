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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class AdminLogin extends MainController{

    @FXML
    private TextField adminNameText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Text loginMessage;

    @FXML
    private void LogIn(ActionEvent mouseEvent) throws SQLException, IOException {
        if (!adminNameText.getText().isBlank() && !passwordText.getText().isBlank()) {
            if (adminLoginValidate()) {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminDashboard.fxml")));
                stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                stage.setTitle("MY YATRA");
                stage.setScene(new Scene(root));
                stage.show();
            }
        } else {
            loginMessage.setText("Please Enter Username And Password.");
        }
    }

    private boolean adminLoginValidate() throws SQLException {
        DatabaseConnector connector = new DatabaseConnector();
        Connection connectDB = connector.getDatabaseLink();

        String verifyLogin = "SELECT count(1) FROM admin WHERE a_userName='"+adminNameText.getText()+"'AND a_password='"+passwordText.getText()+"'";

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
    public void backBtn(ActionEvent mouseEvent) {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginPage.fxml")));
            stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            stage.setTitle("MY YATRA");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
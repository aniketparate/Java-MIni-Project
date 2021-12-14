package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class CreateAccount extends MainController{

    @FXML
    private TextField userNameText;

    @FXML
    private TextField passwordText;

    @FXML
    private TextField firstNameText;

    @FXML
    private TextField lastNameText;

    @FXML
    private TextField phoneText;

    @FXML
    private TextField emailText;

    @FXML
    private TextField stateNameText;

    @FXML
    private TextField cityNameText;

    @FXML
    private TextField pincodeNumber;

    @FXML
    private Text createMessage;



    @FXML
    private void Register(ActionEvent mouseEvent) throws SQLException, IOException {
        if (!userNameText.getText().isBlank() && !passwordText.getText().isBlank() && !firstNameText.getText().isBlank() && !lastNameText.getText().isBlank() && !phoneText.getText().isBlank() && !emailText.getText().isBlank() && !stateNameText.getText().isBlank() && !cityNameText.getText().isBlank() && !pincodeNumber.getText().isBlank()) {
            if (createAccount()) {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginPage.fxml")));
                stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
                stage.setTitle("MY YATRA");
                stage.setScene(new Scene(root));
                stage.show();
            }
        } else {
            createMessage.setText("Please fill all the details.");
        }
    }

    private boolean createAccount() throws SQLException{
        DatabaseConnector connector = new DatabaseConnector();
        Connection connectDB = connector.getDatabaseLink();

        String insertDetails = "INSERT INTO user(u_username,u_password,u_firstname,u_lastname,u_email,u_phone,u_state,u_city,u_pincode) VALUES('"+userNameText.getText()+"','"+passwordText.getText()+"','"+firstNameText.getText()+"','"+lastNameText.getText()+"','"+emailText.getText()+"','"+phoneText.getText()+"','"+stateNameText.getText()+"','"+cityNameText.getText()+"',"+pincodeNumber.getText()+")";

        try {
            Statement statement = connectDB.createStatement();
            int a = statement.executeUpdate(insertDetails);
            if (a == 1) {
                return true;
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
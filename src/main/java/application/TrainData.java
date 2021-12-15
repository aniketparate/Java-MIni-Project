package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class TrainData extends MainController implements Initializable {

    @FXML
    private Button addButton;

    @FXML
    private TableView<alltrain> allTrain;

    @FXML
    private TableColumn<alltrain, String> colRoute;

    @FXML
    private TableColumn<alltrain, String> colTrainName;

    @FXML
    private TableColumn<alltrain, Integer> colTrainNo;

    @FXML
    private Button deleteButton;

    @FXML
    private Text messageText;

    @FXML
    private TextField removeTrainText;

    @FXML
    private TextField routeText;

    @FXML
    private TextField trainNameText;

    @FXML
    private TextField trainNoText;

    public static ObservableList<alltrain> getAllTrains(){
        DatabaseConnector connector = new DatabaseConnector();
        Connection connectDB = connector.getDatabaseLink();
        ObservableList<alltrain> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = connectDB.prepareStatement("select * from train");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new alltrain(Integer.parseInt(rs.getString("train_no")),rs.getString("train_name"), rs.getString("route")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    ObservableList<alltrain> listM;

    @FXML
    public void backBtn(ActionEvent mouseEvent) {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminDashboard.fxml")));
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

    @FXML
    public void addTrainBtn(ActionEvent mouseEvent) throws SQLException, IOException {
        if (!trainNoText.getText().isBlank() && !trainNameText.getText().isBlank() && !routeText.getText().isBlank()) {
            if (addTrain()) {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TrainData.fxml")));
                stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
                stage.setTitle("MY YATRA");
                stage.setScene(new Scene(root));
                stage.show();
            }
        } else {
            messageText.setText("Please fill all the details to add.");
        }
    }

    private boolean addTrain() throws SQLException {
        DatabaseConnector connector = new DatabaseConnector();
        Connection connectDB = connector.getDatabaseLink();

        String insertDetails = "INSERT INTO train(train_no,train_name,route) VALUES("+trainNoText.getText()+",'"+trainNameText.getText()+"','"+routeText.getText()+"')";

        try {
            Statement statement = connectDB.createStatement();
            int a = statement.executeUpdate(insertDetails);
            if (a == 1) {
                return true;
            }
        }catch (SQLIntegrityConstraintViolationException e){
            e.printStackTrace();
            messageText.setText("Train already exist. Please try again!");
        }
        return false;
    }

    @FXML
    public void deleteButton(ActionEvent mouseEvent) throws SQLException, IOException {
        if (!removeTrainText.getText().isBlank()) {
            if (deleteTrain()) {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TrainData.fxml")));
                stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
                stage.setTitle("MY YATRA");
                stage.setScene(new Scene(root));
                stage.show();
            }
        } else {
            messageText.setText("Please enter Train No to delete.");
        }
    }

    private boolean deleteTrain() throws SQLException {
        DatabaseConnector connector = new DatabaseConnector();
        Connection connectDB = connector.getDatabaseLink();

        String sql = "delete from train where train_no = "+removeTrainText.getText()+"";

        try {
            Statement statement = connectDB.createStatement();
            int a = statement.executeUpdate(sql);
            if (a == 1) {
                return true;
            }
        }catch (SQLIntegrityConstraintViolationException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colTrainNo.setCellValueFactory(new PropertyValueFactory<alltrain, Integer>("train_no"));
        colTrainName.setCellValueFactory(new PropertyValueFactory<alltrain, String>("train_name"));
        colRoute.setCellValueFactory(new PropertyValueFactory<alltrain, String>("route"));

        listM = getAllTrains();
        allTrain.setItems(listM);
    }
}

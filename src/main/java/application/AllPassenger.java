package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.Objects;
import java.util.ResourceBundle;

public class AllPassenger extends MainController implements Initializable {

    @FXML
    private TableColumn<passenger, Integer> colAge;

    @FXML
    private TableColumn<passenger, String> colDestination;

    @FXML
    private TableColumn<passenger, Time> colDestinationTime;

    @FXML
    private TableColumn<passenger, String> colGender;

    @FXML
    private TableColumn<passenger, String> colName;

    @FXML
    private TableColumn<passenger, String> colSource;

    @FXML
    private TableColumn<passenger, Time> colSourceTime;

    @FXML
    private TableColumn<passenger, String> colStatus;

    @FXML
    private TableColumn<passenger, String> colTrainName;

    @FXML
    private TableColumn<passenger, Integer> colTrainNo;

    @FXML
    private TableColumn<passenger, String> colUsername;

    @FXML
    private TableView<passenger> passengerTable;

    public static ObservableList<passenger> getAllDataPassenger(){
        DatabaseConnector connector = new DatabaseConnector();
        Connection connectDB = connector.getDatabaseLink();
        ObservableList<passenger> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = connectDB.prepareStatement("select * from passengers");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new passenger(rs.getString("name"), rs.getString("gender"), rs.getString("train_name"), rs.getString("source"), rs.getString("destination"), rs.getString("username"), rs.getString("booked_status"), Integer.parseInt(rs.getString("age")), Integer.parseInt(rs.getString("train_no")), Time.valueOf(rs.getString("scheduled_departure")),Time.valueOf(rs.getString("scheduled_arrival"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    ObservableList<passenger> listM;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colName.setCellValueFactory(new PropertyValueFactory<passenger, String>("name"));
        colGender.setCellValueFactory(new PropertyValueFactory<passenger, String>("gender"));
        colAge.setCellValueFactory(new PropertyValueFactory<passenger, Integer>("age"));
        colSource.setCellValueFactory(new PropertyValueFactory<passenger, String>("source"));
        colSourceTime.setCellValueFactory(new PropertyValueFactory<passenger, Time>("scheduled_departure"));
        colDestination.setCellValueFactory(new PropertyValueFactory<passenger, String>("destination"));
        colDestinationTime.setCellValueFactory(new PropertyValueFactory<passenger, Time>("scheduled_arrival"));
        colStatus.setCellValueFactory(new PropertyValueFactory<passenger, String>("booked_status"));

        listM = getAllDataPassenger();
        passengerTable.setItems(listM);
    }
}

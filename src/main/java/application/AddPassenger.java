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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class AddPassenger extends MainController implements Initializable {

    public static String TrainNo;
    public static String TrainName;
    public static String Source;
    public static String SourceTime;
    public static String Destination;
    public static String DestinationTime;

    @FXML
    private TextField ageText;

    @FXML
    private TableColumn<confirmPassenger, Integer> colAge;

    @FXML
    private TableColumn<confirmPassenger, String> colFrom;

    @FXML
    private TableColumn<confirmPassenger, String> colGender;

    @FXML
    private TableColumn<confirmPassenger, String> colPassengerName;

    @FXML
    private TableColumn<confirmPassenger, Time> colScheduledArrival;

    @FXML
    private TableColumn<confirmPassenger, Time> colScheduledDeparture;

    @FXML
    private TableColumn<confirmPassenger, String> colStatus;

    @FXML
    private TableColumn<confirmPassenger, String> colTo;

    @FXML
    private TableView<confirmPassenger> confirmPassengerTable;

    @FXML
    private Text passengerMessage;

    @FXML
    private TextField genderText;

    @FXML
    private TextField nameRemoveText;

    @FXML
    private TextField passengerNameText;

    @FXML
    private Text seatText;

    public String getSeatData() {
        DatabaseConnector connector = new DatabaseConnector();
        Connection connectDB = connector.getDatabaseLink();

        String seat = null;
        try {
            PreparedStatement ps = connectDB.prepareStatement("select no_of_seats from seats where train_no = " + TrainNo + "");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                seat = rs.getString("no_of_seats");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seat;
    }

    private void insertSeat() {
        seatText.setText(getSeatData());
    }

    int seat = Integer.parseInt(getSeatData());

    public static ObservableList<confirmPassenger> getDatapassenger(){
        DatabaseConnector connector = new DatabaseConnector();
        Connection connectDB = connector.getDatabaseLink();
        ObservableList<confirmPassenger> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = connectDB.prepareStatement("select * from confirm_passenger");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new confirmPassenger(rs.getString("name"), rs.getString("gender"),  rs.getString("source"), rs.getString("destination"), rs.getString("booking_status"), Integer.parseInt(rs.getString("age")), Time.valueOf(rs.getString("scheduled_departure")),Time.valueOf(rs.getString("scheduled_arrival"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    ObservableList<confirmPassenger> listM;

    @FXML
    void addPassenger(ActionEvent mouseEvent) throws SQLException, IOException {
        if (!passengerNameText.getText().isBlank() && !genderText.getText().isBlank() && !ageText.getText().isBlank()) {
            if (addPassengerInfo()) {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddPassenger.fxml")));
                stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                stage.setTitle("MY YATRA");
                stage.setScene(new Scene(root));
                stage.show();

            }
        } else {
            passengerMessage.setText("Please fill Passenger details");
        }
    }

    private void decreaseSeats() {
        seat = seat - 1;
        seatText.setText(String.valueOf(seat));
    }

    private boolean addPassengerInfo() throws SQLException {
        DatabaseConnector connector = new DatabaseConnector();
        Connection connectDB = connector.getDatabaseLink();

        String insertDetails = "INSERT INTO confirm_passenger(name,gender,age,source,scheduled_departure,destination,scheduled_arrival,booking_status) VALUES('"+passengerNameText.getText()+"','"+genderText.getText()+"',"+ageText.getText()+",'"+Source+"','"+SourceTime+"','"+Destination+"','"+DestinationTime+"','"+Status()+"')";

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

    private String Status() {
        int seat = Integer.parseInt(getSeatData());
        if (seat > 0) {
            return "Available";
        } else if (seat >= -50){
            return "Waiting";
        } else {
            return "Not Available";
        }
    }

    @FXML
    void continuePassengerBtn(ActionEvent event) {

    }

    @FXML
    void deletePassenger(ActionEvent mouseEvent) throws IOException {
        if (!nameRemoveText.getText().isBlank()) {
            if (delete()) {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddPassenger.fxml")));
                stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                stage.setTitle("MY YATRA");
                stage.setScene(new Scene(root));
                stage.show();
            }
        } else {
            passengerMessage.setText("Please enter Station Code to delete.");
        }
    }

    private boolean delete(){
        DatabaseConnector connector = new DatabaseConnector();
        Connection connectDB = connector.getDatabaseLink();
        PreparedStatement pst = null;

        String sql = "delete from confirm_passenger where name = '"+nameRemoveText.getText()+"'";
        try {
            pst = connectDB.prepareStatement(sql);
            pst.execute();
            passengerMessage.setText("Sucessfully deleted!");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    void backBtn(ActionEvent mouseEvent) {
        if (emptyPassenger()) {
            try {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TrainList.fxml")));
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        insertSeat();

        colPassengerName.setCellValueFactory(new PropertyValueFactory<confirmPassenger, String>("name"));
        colGender.setCellValueFactory(new PropertyValueFactory<confirmPassenger, String>("gender"));
        colAge.setCellValueFactory(new PropertyValueFactory<confirmPassenger, Integer>("age"));
        colFrom.setCellValueFactory(new PropertyValueFactory<confirmPassenger, String>("source"));
        colScheduledDeparture.setCellValueFactory(new PropertyValueFactory<confirmPassenger, Time>("scheduled_departure"));
        colTo.setCellValueFactory(new PropertyValueFactory<confirmPassenger, String>("destination"));
        colScheduledArrival.setCellValueFactory(new PropertyValueFactory<confirmPassenger, Time>("scheduled_arrival"));
        colStatus.setCellValueFactory(new PropertyValueFactory<confirmPassenger, String>("booking_status"));

        listM = getDatapassenger();
        confirmPassengerTable.setItems(listM);
    }
}

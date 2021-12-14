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
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class AllStationData extends MainController implements Initializable {

    @FXML
    private Button addButton;

    @FXML
    private TableColumn<station, String> colStationCode;

    @FXML
    private TableColumn<station, String> colStationName;

    @FXML
    private Button deleteButton;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField stationCode;

    @FXML
    private TextField stationCode1;

    @FXML
    private TextField stationName;

    @FXML
    private Text stationMessage;

    @FXML
    private TableView<station> stationTable;

    public static ObservableList<station> getDatastations(){
        DatabaseConnector connector = new DatabaseConnector();
        Connection connectDB = connector.getDatabaseLink();
        ObservableList<station> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = connectDB.prepareStatement("select * from station");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new station(rs.getString("station_code"), rs.getString("station_name")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    ObservableList<station> listM;

    @FXML
    void addStation(ActionEvent mouseEvent) throws Exception{
        if (!stationCode.getText().isBlank() && !stationName.getText().isBlank()) {
            if (addStation()) {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AllStationData.fxml")));
                stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
                stage.setTitle("MY YATRA");
                stage.setScene(new Scene(root));
                stage.show();
            }
        } else {
            stationMessage.setText("Please fill all the details to add.");
        }
    }

    private boolean addStation() throws SQLException {
        DatabaseConnector connector = new DatabaseConnector();
        Connection connectDB = connector.getDatabaseLink();

        String insertDetails = "INSERT INTO station(station_code,station_name) VALUES('"+stationCode.getText()+"','"+stationName.getText()+"')";

        try {
            Statement statement = connectDB.createStatement();
            int a = statement.executeUpdate(insertDetails);
            if (a == 1) {
                return true;
            }
        }catch (SQLIntegrityConstraintViolationException e){
            e.printStackTrace();
            stationMessage.setText("Entry already exist or Invalid entry. Please try again!");
        }
        return false;
    }

    @FXML
    void deleteButton(ActionEvent mouseEvent) throws Exception{
        if (!stationCode1.getText().isBlank()) {
            if (Delete()) {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AllStationData.fxml")));
                stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                stage.setTitle("MY YATRA");
                //stage.getIcons().add(new Image("icon.png"));
                stage.setScene(new Scene(root));
                stage.show();
            }
        } else {
            stationMessage.setText("Please enter Station Code to delete.");
        }
    }

    private boolean Delete(){
        DatabaseConnector connector = new DatabaseConnector();
        Connection connectDB = connector.getDatabaseLink();
        PreparedStatement pst = null;

        String sql = "delete from station where station_code = '"+stationCode1.getText()+"'";
        try {
            pst = connectDB.prepareStatement(sql);
            pst.execute();
            stationMessage.setText("Sucessfully deleted!");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

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
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colStationCode.setCellValueFactory(new PropertyValueFactory<station, String>("station_code"));
        colStationName.setCellValueFactory(new PropertyValueFactory<station, String>("station_name"));

        listM = getDatastations();
        stationTable.setItems(listM);
    }
}

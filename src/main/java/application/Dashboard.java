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
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.ResourceBundle;

public class Dashboard extends MainController implements Initializable {

    @FXML
    private TableColumn<station, String> colStationCode;

    @FXML
    private TableColumn<station, String> colStationName;

    @FXML
    TextField fromText;

    @FXML
    TextField toText;

    @FXML
    TextField routeText;

    @FXML
    private TableView<station> stationTable;

    @FXML
    private Text searchMessage;

    @FXML
    private WebView webView;

    private WebEngine engine;

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
    public void logOutBtn(ActionEvent mouseEvent) {
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

    @FXML
    public void trainSearch(ActionEvent mouseEvent) {
        if (!fromText.getText().isBlank() && !toText.getText().isBlank() && !routeText.getText().isBlank()) {
            TrainList.Source = fromText.getText();
            TrainList.Destination = toText.getText();
            TrainList.Route = routeText.getText();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("TrainList.fxml"));
                root = loader.load();
                //root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TrainList.fxml")));
                TrainList trainList = loader.getController();
                TrainList.getDatatrain(fromText.getText(), toText.getText(), routeText.getText());
                stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
                stage.setTitle("MY YATRA");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            searchMessage.setText("Please enter text to search");
        }
    }

    public void loadpage() {
        engine.load("https://www.scribblemaps.com/create#/id=RailwayTicketBookingSystem&lat=21.09026087&lng=80.42196279&z=6&t=custom_style");
    }

    public void refreshPage() {
        engine.reload();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colStationCode.setCellValueFactory(new PropertyValueFactory<station, String>("station_code"));
        colStationName.setCellValueFactory(new PropertyValueFactory<station, String>("station_name"));

        listM = getDatastations();
        stationTable.setItems(listM);

        engine = webView.getEngine();
        loadpage();
    }
}

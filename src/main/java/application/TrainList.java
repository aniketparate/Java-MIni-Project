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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class TrainList extends MainController implements Initializable{

    public static String Source;
    public static String Destination;
    public static String Route;

    @FXML
    private TableColumn<train, String> colDestination;

    @FXML
    private TableColumn<train, Time> colDestinationTime;

    @FXML
    private TableColumn<train, String> colSource;

    @FXML
    private TableColumn<train, Time> colSourceTime;

    @FXML
    private TableColumn<train, String> colTrainName;

    @FXML
    private TableColumn<train, Integer> colTrainNo;

    @FXML
    private Text continueText;

    @FXML
    private Text destinationText;

    @FXML
    private Text destinationTimeText;

    @FXML
    private Text sourceText;

    @FXML
    private Text sourceTimeText;

    @FXML
    private Text trainNameText;

    @FXML
    private Text trainNoText;

    @FXML
    private TableView<train> trainTable;

    public static ObservableList<train> getDatatrain(String source, String destination, String route) {
        DatabaseConnector connector = new DatabaseConnector();
        Connection connectDB = connector.getDatabaseLink();

        ObservableList<train> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = connectDB.prepareStatement("select t.train_no,t.train_name,s.source_name,s.source_time,d.destination_name,d.destination_time from train t,source s,destination d where t.train_no=s.train_no and s.source_name='"+source+"' and t.train_no=d.train_no and d.destination_name='"+destination+"' and t.route='"+route+"';");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new train(Integer.parseInt(rs.getString("train_no")), rs.getString("train_name"), rs.getString("source_name"), Time.valueOf(rs.getString("source_time")), rs.getString("destination_name"), Time.valueOf(rs.getString("destination_time"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    ObservableList<train> listM;

    int index = -1;

    @FXML
    void getSelected(MouseEvent event) {
        index = trainTable.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        trainNoText.setText(colTrainNo.getCellData(index).toString());
        trainNameText.setText(colTrainName.getCellData(index));
        sourceText.setText(colSource.getCellData(index));
        sourceTimeText.setText(colSourceTime.getCellData(index).toString());
        destinationText.setText(colDestination.getCellData(index));
        destinationTimeText.setText(colDestinationTime.getCellData(index).toString());
    }

    @FXML
    void continuebtn(ActionEvent mouseEvent) {
        if (!trainNoText.getText().isBlank() && !trainNameText.getText().isBlank() && !sourceText.getText().isBlank() && !destinationText.getText().isBlank()) {
            AddPassenger.TrainNo = trainNoText.getText();
            AddPassenger.TrainName = trainNameText.getText();
            AddPassenger.Source = sourceText.getText();
            AddPassenger.SourceTime = sourceTimeText.getText();
            AddPassenger.Destination = destinationText.getText();
            AddPassenger.DestinationTime = destinationTimeText.getText();
            AddPassenger.seatNo = getSeatData();

            try {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AddPassenger.fxml")));
                stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                stage.setTitle("MY YATRA");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            continueText.setText("Please select the train to continue");
        }
    }

    public String getSeatData() {
        DatabaseConnector connector = new DatabaseConnector();
        Connection connectDB = connector.getDatabaseLink();

        String seat = null;
        try {
            PreparedStatement ps = connectDB.prepareStatement("select no_of_seats from seats where train_no = " + trainNoText.getText() + "");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                seat = rs.getString("no_of_seats");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seat;
    }

    @FXML
    void backBtn(ActionEvent mouseEvent) {
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Dashboard.fxml")));
            stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            stage.setTitle("MY YATRA");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void logOutBtn(ActionEvent mouseEvent) {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colTrainNo.setCellValueFactory(new PropertyValueFactory<train, Integer>("train_no"));
        colTrainName.setCellValueFactory(new PropertyValueFactory<train, String>("train_name"));
        colSource.setCellValueFactory(new PropertyValueFactory<train, String>("source_name"));
        colSourceTime.setCellValueFactory(new PropertyValueFactory<train, Time>("source_time"));
        colDestination.setCellValueFactory(new PropertyValueFactory<train, String>("destination_name"));
        colDestinationTime.setCellValueFactory(new PropertyValueFactory<train, Time>("destination_time"));

        listM = getDatatrain(Source, Destination, Route);
        trainTable.setItems(listM);
    }
}

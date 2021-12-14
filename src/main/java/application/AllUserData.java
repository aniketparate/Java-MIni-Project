package application;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import java.util.ResourceBundle;

public class AllUserData extends MainController implements Initializable {

    @FXML
    private Button addButton;

    @FXML
    private TableColumn<user, String> colCity;

    @FXML
    private TableColumn<user, String> colEmail;

    @FXML
    private TableColumn<user, String> colFirstName;

    @FXML
    private TableColumn<user, String> colLastName;

    @FXML
    private TableColumn<user, String> colPassword;

    @FXML
    private TableColumn<user, String> colPhoneNo;

    @FXML
    private TableColumn<user, Integer> colPinCode;

    @FXML
    private TableColumn<user, String> colState;

    @FXML
    private TableColumn<user, String> colUserName;

    @FXML
    private Button deleteButton;

    @FXML
    private Text userMessage;

    @FXML
    private TextField userNameText;

    @FXML
    private TableView<user> userTable;

    public static ObservableList<user> getDatausers(){
        DatabaseConnector connector = new DatabaseConnector();
        Connection connectDB = connector.getDatabaseLink();
        ObservableList<user> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = connectDB.prepareStatement("select * from user");
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                list.add(new user(rs.getString("u_username"), rs.getString("u_password"), rs.getString("u_firstname"), rs.getString("u_lastname"), rs.getString("u_email"), rs.getString("u_phone"), rs.getString("u_state"), rs.getString("u_city"), Integer.parseInt(rs.getString("u_pincode"))));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    ObservableList<user> listM;
//    ObservableList<user> dataList;

    @FXML
    public void deleteButton(ActionEvent mouseEvent) throws Exception {
        if (!userNameText.getText().isBlank()) {
            if (Delete()) {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AllUserData.fxml")));
                stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                stage.setTitle("MY YATRA");
                //stage.getIcons().add(new Image("icon.png"));
                stage.setScene(new Scene(root));
                stage.show();
            }
        } else {
            userMessage.setText("Please enter UserName to Delete.");
        }
    }

    private boolean Delete(){
        DatabaseConnector connector = new DatabaseConnector();
        Connection connectDB = connector.getDatabaseLink();
        PreparedStatement pst = null;

        String sql = "delete from user where u_username = '"+userNameText.getText()+"'";
        try {
            pst = connectDB.prepareStatement(sql);
            //pst.setString(1, userNameText.getText());
            pst.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @FXML
    public void Add_users(ActionEvent mouseEvent){
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
        colUserName.setCellValueFactory(new PropertyValueFactory<user, String>("u_username"));
        colPassword.setCellValueFactory(new PropertyValueFactory<user, String>("u_password"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<user, String>("u_firstname"));
        colLastName.setCellValueFactory(new PropertyValueFactory<user, String>("u_lastname"));
        colEmail.setCellValueFactory(new PropertyValueFactory<user, String>("u_email"));
        colPhoneNo.setCellValueFactory(new PropertyValueFactory<user, String>("u_phone"));
        colState.setCellValueFactory(new PropertyValueFactory<user, String>("u_state"));
        colCity.setCellValueFactory(new PropertyValueFactory<user, String>("u_city"));
        colPinCode.setCellValueFactory(new PropertyValueFactory<user, Integer>("u_pincode"));

        listM = getDatausers();
        userTable.setItems(listM);
    }
}
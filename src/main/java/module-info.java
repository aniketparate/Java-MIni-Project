module com.example.javaminiproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens application to javafx.fxml;
    exports application;
}
module com.example.javaminiproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires javafx.web;
    requires javafx.base;

    opens application to javafx.fxml;
    exports application;
}
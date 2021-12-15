package application;

import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseConnector {
    public Connection databaseLink;

    public Connection getDatabaseLink() {
        //String databaseName = "RailwayTicketBookingSystem";
        String databaseName = "railwayticketbooking";
        //String databaseUser = "AniketParate";
        String databaseUser = "root";
        //String databasePassword = "MDJafzw26MBXkQB3";
        String databasePassword = "Darkflame@2001";

        String url = "jdbc:mysql://localhost/" + databaseName;
        //String url = "jdbc:mysql://jblminiproject-do-user-10209104-0.b.db.ondigitalocean.com:25060/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
            System.out.println("DB Connection done !");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("DB Connection Failures !");
        }
        return databaseLink;
    }
}
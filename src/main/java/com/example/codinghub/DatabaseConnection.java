package com.example.codinghub;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public Connection connection;

    public DatabaseConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.printf("%s", e.getMessage());
            return;
        }

        try {
            String dbURL = "jdbc:mysql://localhost:3306/codinghub";
            this.connection = DriverManager.getConnection(dbURL, "root", "root");
        } catch (SQLException e) {
            System.out.printf("Error connecting to DB: %s", e.getMessage());
            return;
        }
    }
}

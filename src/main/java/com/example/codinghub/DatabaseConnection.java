package com.example.codinghub;

import java.sql.*;

public class DatabaseConnection {

    public Connection connection;
    public Statement statement;

    public DatabaseConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            System.out.printf("%s", e.getMessage());
            return;
        }

        try {
            String dbURL = "jdbc:mysql://localhost:3306/codinghub";
            this.connection = DriverManager.getConnection(dbURL, "root", "");
            String checkTableQuery = "SHOW TABLES LIKE 'users'";
            String createTableQuery = """
                    CREATE TABLE users (
                        email VARCHAR(100) NOT NULL PRIMARY KEY,
                        password VARCHAR(100) NOT NULL,
                    )
                    """;

            this.statement = this.connection.createStatement();
            ResultSet resultSet = this.statement.executeQuery(checkTableQuery);
            if (!resultSet.next()) {
                this.statement.executeUpdate(createTableQuery);
            }
        } catch (SQLException e) {
            System.out.printf("error connecting to db: %s", e.getMessage());
            return;
        }
    }
}

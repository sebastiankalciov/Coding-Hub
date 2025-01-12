package com.example.codinghub.handlers;

import com.example.codinghub.DatabaseConnection;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;

public class AuthentificationHandler {

    private final String url = "jdbc:mysql://localhost:3306/codinghub";
    private final String user = "root";
    private final String password = "";

    public String register (String email, String unHashedPassword) throws SQLException {
        String hashedPassword = BCrypt.hashpw(unHashedPassword, BCrypt.gensalt());
        String sql = "INSERT INTO users (email, hashedPassword) VALUES (?, ?)";
        DatabaseConnection database = new DatabaseConnection();

        try (PreparedStatement preparedStatement = database.connection.prepareStatement(sql)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, hashedPassword);
            preparedStatement.executeUpdate();
            System.out.println("user " + email + " registered");
            return "register successfully";
        } catch (Exception e) {
            if(e.getMessage().contains("Duplicate entry")) {
                System.out.println("user " + email + " already registered");
                return "already registered";
            } else {
                e.printStackTrace();
            }
            return "register failed";
        }
    }

    public String login (String email, String unhashedPassword) throws SQLException {
        String sql = "SELECT hashedPassword FROM users WHERE email = ?";
        DatabaseConnection database = new DatabaseConnection();

        try (PreparedStatement preparedStatement = database.connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String storedHash = resultSet.getString("hashedPassword");
                if (BCrypt.checkpw(unhashedPassword, storedHash)) {
                    System.out.println("logged in successfully");
                    return "logged in successfully";
                } else {
                    return "wrong password";
                }
            } else {
                return "user not found";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "user not found";
    }

}

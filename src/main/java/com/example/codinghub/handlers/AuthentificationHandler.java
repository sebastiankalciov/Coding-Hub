package com.example.codinghub.handlers;

import org.mindrot.jbcrypt.BCrypt;
import java.sql.*;
import java.util.regex.Pattern;

public class AuthentificationHandler {

    private final String url = "jdbc:mysql://localhost:3306/codinghub";
    private final String user = "root";
    private final String password = "root";

    public boolean register (String email, String unHashedPassword) throws SQLException {
        String hashedPassword = BCrypt.hashpw(unHashedPassword, BCrypt.gensalt());
        String sql = "INSERT INTO users (email, hashedPassword) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, hashedPassword);
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, hashedPassword);
            preparedStatement.executeUpdate();
            System.out.println("user " + email + " registered");
            return true;
        } catch (Exception e) {
            if(e.getMessage().contains("Duplicate entry")) {
                System.out.println("user " + email + " already registered");
            } else {
                e.printStackTrace();
            }
            return false;

        }

    }

    public String login (String email, String hashedPassword) throws SQLException {
        String sql = "SELECT hashedPassword FROM users WHERE email = ?";
        try (Connection conn = DriverManager.getConnection(url, user, hashedPassword);
        PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String storedHash = resultSet.getString("hashedPassword");
                if (BCrypt.checkpw(storedHash, hashedPassword)) {
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

    
    public String validateLogin(String email, String password) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            return "Please provide your credentials";
        }
        if ()
    }

    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
}

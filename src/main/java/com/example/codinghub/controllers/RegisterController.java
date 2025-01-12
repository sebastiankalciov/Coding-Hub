package com.example.codinghub.controllers;

import com.example.codinghub.handlers.AuthentificationHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

public class RegisterController {
    AuthentificationHandler authentificationHandler = new AuthentificationHandler();
    @FXML
    public Label subtitle;

    @FXML
    private TextField emailField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label authMessage;

    @FXML
    private Button registerButton;

    @FXML
    public void onRegisterButtonClick(javafx.event.ActionEvent event) throws IOException, SQLException {

        String email = emailField.getText();
        String password = passwordField.getText();
        String credentialsValidation = validateCredentials(email, password);
        if (Objects.equals(credentialsValidation, "empty credentials")) {
            authMessage.setText("Please enter your credentials");
        } else if (Objects.equals(credentialsValidation, "email invalid")) {
            authMessage.setText("Invalid email address");
        } else {
            String response = authentificationHandler.register(email, password);
            if (Objects.equals(response, "already registered")) {
                authMessage.setText("There is already an account with the email address");
            } else if (Objects.equals(response, "register failed")) {
                authMessage.setText("There was an error registering the account");
            } else if (Objects.equals(response, "register successfully")) {
                login(event);
            }
        }

        System.out.println("Login button clicked");
    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void login(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/codinghub/home-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/com/example/codinghub/styles/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public String validateCredentials(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            return "empty credentials";
        } else {
            if (!isEmailValid(email)) {
                return "email invalid";
            }
            return "invalid credentials";
        }
    }

    public boolean isEmailValid(String email) {
        String regexPattern = "^(.+)@(\\S+)$";
        return patternMatches(email, regexPattern);
    }

    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

    public void onLoginButtonClick(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/codinghub/login-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/com/example/codinghub/styles/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}

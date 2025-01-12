package com.example.codinghub.controllers;

import com.example.codinghub.handlers.AuthentificationHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;


// save file
public class LoginController {

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
    private Button loginButton;

    @FXML
    public void onLoginButtonClick(javafx.event.ActionEvent event) throws IOException, SQLException {
        String dbEmail = "test";
        String dbPassword = "test";

        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            authMessage.setText("Please enter your credentials");
        } else {
            String response = authentificationHandler.login(email, password);
            if (Objects.equals(response, "wrong password")) {
                authMessage.setText("Password is incorrect");
            } else if (Objects.equals(response, "user not found")) {
                authMessage.setText("Email or password is incorrect");
            } else {
                login(event);
            }
        }

        System.out.println("Login button clicked");
    }
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void onRegisterButtonClick(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/codinghub/register-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/com/example/codinghub/styles/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void login(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/codinghub/home-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/com/example/codinghub/styles/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void logout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/codinghub/login-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

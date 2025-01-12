package com.example.codinghub.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ExerciseController {
    public void onReturnButtonClick(javafx.event.ActionEvent event) throws IOException {
        goToHomeViewScene(event);
        System.out.println("Login button clicked");
    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void goToHomeViewScene(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/codinghub/home-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/com/example/codinghub/styles/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}

package com.example.codinghub.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    public void onSolveButtonClick(javafx.event.ActionEvent event) throws IOException {
        goToExerciseScene(event);
        System.out.println("Login button clicked");
    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void goToExerciseScene(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/codinghub/exercise-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/com/example/codinghub/styles/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public void returnToHomeScene(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/codinghub/home-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/com/example/codinghub/styles/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}

package com.example.codinghub.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import java.io.IOException;
import java.util.Objects;


public class ExerciseController {

    Integer exerciseExpectedOutput = 420;
    String exerciseTestInput = "sum_numbers(" + "400," + "20" + ")";

    @FXML
    public Label exerciseDescription;

    @FXML
    public Label exerciseInstructions;

    @FXML
    public Label examples;

    @FXML
    public Label codeOutputMessage;

    @FXML
    public TextArea rawCode;

    @FXML
    public void initialize() {
        rawCode.setText("def sum_numbers(a, b):\n    # write down your solution");

        exerciseDescription.setText("You are tasked with creating a function called sum_numbers " +
                "that takes two integers\n" +
                "as input" +
                "and returns their sum.\n" +
                "This exercise will help you practice working with functions,\n" +
                "arguments, and return values in Python.");

        exerciseInstructions.setText("""
                Write your function sum_numbers(a, b) in the provided code box.
                The function should take two integers, a and b, and return their sum.
                Hit the "Run" button to test your code with the example inputs.
                """);

        examples.setText("""
                Examples
                
                Input: sum_numbers(3, 5)
                Output: 8
                
                Input: sum_numbers(4, 6)
                Output: 10
                
                Input: sum_numbers(0, 0)
                Output: 0
                """);
    }

    public void onRunButtonClick(ActionEvent actionEvent) {
        String code = rawCode.getText();

        try (PythonInterpreter interpreter = new PythonInterpreter()) {
            interpreter.exec(code);
            PyObject obj = interpreter.eval(exerciseTestInput);

            if (Objects.equals(obj.toString(), exerciseExpectedOutput.toString())) {
                codeOutputMessage.setText("Expected output: " + exerciseExpectedOutput+"\n" +
                        "Test passed.\nExercise solved correctly!");
                System.out.println(obj.toString());
            } else {
                codeOutputMessage.setText("Expected output: " + exerciseExpectedOutput+"\n" +
                        "Test not passed.\nPlease try again!");
            }
        } catch (Exception e) {
            codeOutputMessage.setText(e.getMessage());
        }
    }

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

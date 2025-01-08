package com.example.codinghub;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

// TO-DO:

// - add database [x]
// - add db connection [x]
// - implement auth logic (login, create account) [ ]
// - implement auth input validation (check email, pass) [ ]

// - create test case for each constructor [ ]
// - 3 test cases for functionality [ ]
// - 3 scenes - login, home, exercise [X]

// - write an exercise (instructions, sample input, sample output) [ ]
// - implement python interpretation [ ]
// - implement testing for solution code [ ]
// - implement output messaging (eg: Solution failed, error: '...') [ ]

// EXTRA
// - implement threads [ ]
// - create a better design [ ]

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 420, 340);
        scene.getStylesheets().add(getClass().getResource("/com/example/codinghub/styles/style.css").toExternalForm());
        stage.setTitle("Coding Hub");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
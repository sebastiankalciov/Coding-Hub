module com.example.codinghub {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.sql;
    requires jbcrypt;

    opens com.example.codinghub to javafx.fxml;
    exports com.example.codinghub;
    exports com.example.codinghub.controllers;
    opens com.example.codinghub.controllers to javafx.fxml;
}
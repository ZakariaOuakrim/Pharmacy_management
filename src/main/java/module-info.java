module com.example.test {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires TrayNotification;


    opens com.example.test to javafx.fxml;
    exports com.example.test;
    exports com.example.test.Controller;
    opens com.example.test.Controller to javafx.fxml;
    exports com.example.test.Model;
    opens com.example.test.Model to javafx.fxml;
}
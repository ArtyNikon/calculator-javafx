module dsdd {
    exports org.example.calculator;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;

    opens org.example.calculator to javafx.fxml, javafx.graphics;
    exports org.example.calculator.сontroller;
    opens org.example.calculator.сontroller to javafx.fxml, javafx.graphics;
    exports org.example.calculator.service;
    opens org.example.calculator.service to javafx.fxml, javafx.graphics;

}
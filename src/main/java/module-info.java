module dsdd {
    exports org.example.dsdd.Service;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;

    opens org.example.dsdd.Service to javafx.fxml, javafx.graphics;

}
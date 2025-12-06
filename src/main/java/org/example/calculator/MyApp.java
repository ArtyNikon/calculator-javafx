package org.example.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MyApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/Calculator.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Калькулятор");

        Image icon = new Image(getClass().getResourceAsStream("/images/icon.png"));
        stage.getIcons().add(icon);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
package ru.medvedev.pract16;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import ru.medvedev.pract16.background.*;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    public static Stage primaryStage;
    public TableOrdersManager tableOrderManager = new TableOrdersManager();
    public InternetOrdersManager internetOrdersManager = new InternetOrdersManager();

    public static void runStage(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/primaryStage.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();

        primaryStage.setScene(new Scene(root));
        runStage(primaryStage);
    }
}

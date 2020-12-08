package ru.medvedev.pract16;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ru.medvedev.pract16.background.InternetOrdersManager;
import ru.medvedev.pract16.background.Order;

public class ControllerPrimaryStage {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ScrollPane tableOrderScrollPane;

    @FXML
    private Button createTableOrderButton;

    @FXML
    private Button editTableOrderButton;

    @FXML
    private Label totalCostTableOrderLabel;

    @FXML
    private Button totalCostTableOrderButton;

    @FXML
    private Button createInternetOrderButton;

    @FXML
    private Button editInternetOrderButton;

    @FXML
    private Label totalCostInternetOrderLabel;

    @FXML
    private VBox tableOrderVBox;

    @FXML
    private VBox internetOrderVBox;

    @FXML
    private Button totalCostInternetOrderButton;

    private void createTableOrderButtonOnClick() throws IOException {
        createInternetOrderButton.getScene().getWindow().hide();

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/createTableOrder.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void createInternetOrderButtonOnClick() throws IOException {
        createInternetOrderButton.getScene().getWindow().hide();

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/createInternetOrder.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void updateVBoxes(){
        tableOrderVBox.getChildren().clear();
        internetOrderVBox.getChildren().clear();

        if(DataContainer.internetOrdersManager.getOrders().length != 0) {
            for (Order order : DataContainer.internetOrdersManager.getOrders())
                internetOrderVBox.getChildren().add(new Text(order.toString()));
            String cost = "Общая стоимость " + Integer.toString(DataContainer.internetOrdersManager.ordersCostSummary()) + " руб.";
            totalCostInternetOrderLabel.setText(cost);
        }

        if(DataContainer.tableOrdersManager.getOrders().length != 0) {
            for (Order order : DataContainer.tableOrdersManager.getOrders())
                tableOrderVBox.getChildren().add(new Text(order.toString()));
            String cost = "Общая стоимость " + Integer.toString(DataContainer.tableOrdersManager.ordersCostSummary()) + " руб.";
            totalCostTableOrderLabel.setText(cost);
        }
    }

    @FXML
    void initialize() {
        createInternetOrderButton.setOnAction(event -> {
            try {
                createInternetOrderButtonOnClick();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        createTableOrderButton.setOnAction(event -> {
            try {
                createTableOrderButtonOnClick();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });



        updateVBoxes();
    }
}

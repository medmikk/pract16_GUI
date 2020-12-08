package ru.medvedev.pract16;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ru.medvedev.pract16.background.*;
import ru.medvedev.pract16.background.MenuItem;

public class ControllerEditTableOrder {

    private Order order;

    private final ObservableList<String> typeItemList = FXCollections.observableArrayList("Блюдо", "Напиток");

    private ObservableList<MenuItem> itemsList;

    private MenuItem selectedItem;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox ordersListVBox;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button acceptButton;

    @FXML
    private ChoiceBox<MenuItem> chooseItemChoiceBox;

    @FXML
    private Label totalCostLabel;

    @FXML
    private ChoiceBox<Order> chooseOrderChoiceBox;

    @FXML
    private TextField nameItemField;

    @FXML
    private ChoiceBox<String> typeMenuItemChoiceBox;

    @FXML
    private TextField costItemFiedl;

    @FXML
    private TextArea descriptionItemArea;

    private ObservableList<Order> typeOrderList = FXCollections.observableArrayList(DataContainer.tableOrdersManager.getOrders());

    private void updateVBox() {
        ordersListVBox.getChildren().clear();

        for(MenuItem item : order.getItems()) {
            ordersListVBox.getChildren().add(new Text(item.toString()));
            String cost = "Общая стоимость " + Integer.toString(order.costTotal()) + " руб.";
            totalCostLabel.setText(cost);
        }
        itemsList = FXCollections.observableArrayList(order.getItems());
        chooseItemChoiceBox.setItems(itemsList);
        if(!itemsList.isEmpty()) {
            chooseItemChoiceBox.setValue(itemsList.get(0));
            selectedItem = chooseItemChoiceBox.getValue();
        } else {
            DataContainer.tableOrdersManager.remove(order);
            cancelButtonOnClick();
        }
    }

    private void resetItemFields(){
        nameItemField.setText("");
        descriptionItemArea.setText("");
        costItemFiedl.setText("");
    }

    private void cancelButtonOnClick() {
        cancelButton.getScene().getWindow().hide();
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            URL xmlUrl = getClass().getResource("/primaryStage.fxml");
            loader.setLocation(xmlUrl);
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private void addButtonOnClick(){
        if(!   (!nameItemField.getText().isEmpty() &&
                !descriptionItemArea.getText().isEmpty() &&
                !costItemFiedl.getText().isEmpty())){
            //show warning message TODO
        } else {
            MenuItem item;
            if(typeMenuItemChoiceBox.getValue().equals(typeItemList.get(0)))
                item = new Dish(
                        Integer.parseInt(costItemFiedl.getText()),
                        nameItemField.getText(),
                        descriptionItemArea.getText());
            else
                item = new Drink(
                        Integer.parseInt(costItemFiedl.getText()),
                        nameItemField.getText(),
                        descriptionItemArea.getText());
            order.add(item);
            updateVBox();
            resetItemFields();
        }
    }

    private void acceptButtonOnClick(){
        cancelButtonOnClick();
    }

    private void deleteButtonOnClick(){
        if(selectedItem != null)
            order.remove(selectedItem);
        updateVBox();
    }

    @FXML
    void initialize() {
        typeMenuItemChoiceBox.setItems(typeItemList);
        typeMenuItemChoiceBox.setValue(typeItemList.get(0));
        chooseOrderChoiceBox.setItems(typeOrderList);
        chooseOrderChoiceBox.setValue(typeOrderList.get(0));
        order = chooseOrderChoiceBox.getValue();
        updateVBox();
        chooseOrderChoiceBox.setOnAction(event -> {
            order = chooseOrderChoiceBox.getValue();
            updateVBox();
        });
        chooseItemChoiceBox.setOnAction(event -> selectedItem = chooseItemChoiceBox.getValue());
        cancelButton.setOnAction(event -> cancelButtonOnClick());
        addButton.setOnAction(event -> addButtonOnClick());
        acceptButton.setOnAction(event -> acceptButtonOnClick());
        deleteButton.setOnAction(event -> deleteButtonOnClick());
    }
}


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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import ru.medvedev.pract16.background.*;

public class ControllerCreateTableOrder {

    private TableOrder tableOrder = new TableOrder();

    private ObservableList<String> typeItemList = FXCollections.observableArrayList("Блюдо", "Напиток");

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label totalOrderCostLabel;

    @FXML
    private Button cancelButton;

    @FXML
    private Button acceptButton;

    @FXML
    private TextField nameItemField;

    @FXML
    private TextField costItemFiedl;

    @FXML
    private TextArea descriptionItemArea;

    @FXML
    private Button addButton;

    @FXML
    private ChoiceBox<String> typeMenuItemChoiceBox;

    @FXML
    private ScrollPane internetOrderListScrollPane;

    @FXML
    private VBox itemListVBox;

    @FXML
    private TextField nameFieldCustomer;

    @FXML
    private TextField surnameFieldCustomer;

    @FXML
    private TextField thirdnameFieldCustomer;

    @FXML
    private TextField ageFieldCustomer;

    @FXML
    private TextField tableNumberField;

    @FXML
    private Label vacantTable1Label;

    @FXML
    private Label vacantTable2Label;

    @FXML
    private Label vacantTable3Label;

    @FXML
    private Label vacantTable4Label;

    @FXML
    private Label vacantTable5Label;

    @FXML
    private Label vacantTable6Label;

    private void updateVacantTables(){
        Table[] tables = DataContainer.tableOrdersManager.getTables();
        if(tables[0].isFree()){vacantTable1Label.setText("1 Свободно");vacantTable1Label.setTextFill(Color.LIME);}
        else{                  vacantTable1Label.setText("1 Занято");vacantTable1Label.setTextFill(Color.RED);}
        if(tables[1].isFree()){vacantTable2Label.setText("2 Свободно");vacantTable2Label.setTextFill(Color.LIME);}
        else{                  vacantTable2Label.setText("2 Занято");vacantTable2Label.setTextFill(Color.RED);}
        if(tables[2].isFree()){vacantTable3Label.setText("3 Свободно");vacantTable3Label.setTextFill(Color.LIME);}
        else{                  vacantTable3Label.setText("3 Занято");vacantTable3Label.setTextFill(Color.RED);}
        if(tables[3].isFree()){vacantTable4Label.setText("4 Свободно");vacantTable4Label.setTextFill(Color.LIME);}
        else{                  vacantTable4Label.setText("4 Занято");vacantTable4Label.setTextFill(Color.RED);}
        if(tables[4].isFree()){vacantTable5Label.setText("5 Свободно");vacantTable5Label.setTextFill(Color.LIME);}
        else{                  vacantTable5Label.setText("5 Занято");vacantTable5Label.setTextFill(Color.RED);}
        if(tables[5].isFree()){vacantTable6Label.setText("6 Свободно");vacantTable6Label.setTextFill(Color.LIME);}
        else{                  vacantTable6Label.setText("6 Занято");vacantTable6Label.setTextFill(Color.RED);}
    }

    private void resetItemFields(){
        nameItemField.setText("");
        descriptionItemArea.setText("");
        costItemFiedl.setText("");
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
            tableOrder.add(item);
            itemListVBox.getChildren().add(new Text(item.toString()));
            totalOrderCostLabel.setText("Общая стоимость " + tableOrder.costTotal() + " руб");
            resetItemFields();
        }
    }

    private void cancelButtonOnClick() throws IOException {
        cancelButton.getScene().getWindow().hide();

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/primaryStage.fxml");
        loader.setLocation(xmlUrl);
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();

    }

    private void acceptButtonOnClick(){
        if(!   (!nameFieldCustomer.getText().isEmpty() &&
                !surnameFieldCustomer.getText().isEmpty() &&
                !thirdnameFieldCustomer.getText().isEmpty() &&
                !ageFieldCustomer.getText().isEmpty() &&
                !tableNumberField.getText().isEmpty())){
            //show warning message
        } else {
            Customer customer = new Customer(
                    nameFieldCustomer.getText(),
                    surnameFieldCustomer.getText(),
                    Integer.parseInt(ageFieldCustomer.getText()),
                    new Address());

            int tableNumber = Integer.parseInt(tableNumberField.getText());
            tableOrder.setCustomer(customer);
            DataContainer.tableOrdersManager.add(tableOrder, tableNumber);

            try {
                cancelButtonOnClick();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @FXML
    void initialize() {
        typeMenuItemChoiceBox.setItems(typeItemList);
        typeMenuItemChoiceBox.setValue(typeItemList.get(0));
        cancelButton.setOnAction(event -> {
            try {
                cancelButtonOnClick();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        addButton.setOnAction(event -> addButtonOnClick());
        acceptButton.setOnAction(event -> acceptButtonOnClick());
        updateVacantTables();
    }
}


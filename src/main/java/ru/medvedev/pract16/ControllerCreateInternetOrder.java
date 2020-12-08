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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ru.medvedev.pract16.background.*;
import ru.medvedev.pract16.background.MenuItem;

public class ControllerCreateInternetOrder {

    private InternetOrder internetOrder = new InternetOrder();

    private ObservableList<String> typeItemList = FXCollections.observableArrayList("Блюдо", "Напиток");

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nameFieldCustomer;

    @FXML
    private TextField surnameFieldCustomer;

    @FXML
    private TextField thirdnameFieldCustomer;

    @FXML
    private TextField ageFieldCustomer;

    @FXML
    private TextField cityFieldCustomer;

    @FXML
    private TextField streetnameFieldCustomer;

    @FXML
    private TextField houseFieldCustomer;

    @FXML
    private TextField flatFieldCustomer;

    @FXML
    private TextField zipcodeFieldCustomer;

    @FXML
    private TextField letterFieldCustomer;

    @FXML
    private Label totalOrderCostLabel;

    @FXML
    private Button cancelButton;

    @FXML
    private ChoiceBox<String> typeMenuItemChoiceBox;

    @FXML
    private Button addButton;

    @FXML
    private Button acceptButton;

    @FXML
    private TextField nameItemField;

    @FXML
    private TextField costItemFiedl;

    @FXML
    private TextArea descriptionItemArea;

    @FXML
    private VBox itemListVBox;

    private void resetItemFields(){
        nameItemField.setText("");
        descriptionItemArea.setText("");
        costItemFiedl.setText("");
    }

    private void acceptButtonOnClick(){
        if(!   (!nameFieldCustomer.getText().isEmpty() &&
                !surnameFieldCustomer.getText().isEmpty() &&
                !thirdnameFieldCustomer.getText().isEmpty() &&
                !ageFieldCustomer.getText().isEmpty() &&
                !cityFieldCustomer.getText().isEmpty() &&
                !streetnameFieldCustomer.getText().isEmpty() &&
                !houseFieldCustomer.getText().isEmpty() &&
                !flatFieldCustomer.getText().isEmpty() &&
                !zipcodeFieldCustomer.getText().isEmpty() &&
                !letterFieldCustomer.getText().isEmpty())){
            //show warning message
        } else {
            Address address = new Address(
                    cityFieldCustomer.getText(),
                    streetnameFieldCustomer.getText(),
                    Integer.parseInt(houseFieldCustomer.getText()),
                    Integer.parseInt(flatFieldCustomer.getText()),
                    Integer.parseInt(zipcodeFieldCustomer.getText()),
                    letterFieldCustomer.getText().charAt(0));

            Customer customer = new Customer(
                    nameFieldCustomer.getText(),
                    surnameFieldCustomer.getText(),
                    Integer.parseInt(ageFieldCustomer.getText()),
                    address);

            internetOrder.setCustomer(customer);

            DataContainer.internetOrdersManager.add(internetOrder, address);

            try {
                cancelButtonOnClick();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void addButtonOnClick(){
        if(!   (!nameItemField.getText().isEmpty() &&
                !descriptionItemArea.getText().isEmpty() &&
                !costItemFiedl.getText().isEmpty())){
            //show warning message
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
            internetOrder.add(item);
            itemListVBox.getChildren().add(new Text(item.toString()));
            totalOrderCostLabel.setText("Общая стоимость " + internetOrder.costTotal() + " руб");
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
    }
}

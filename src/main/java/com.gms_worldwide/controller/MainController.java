package com.gms_worldwide.controller;

import com.gms_worldwide.dto.Customer;
import com.gms_worldwide.service.CustomerService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainController {

    private CustomerService customerService;
    private FileChooser fileChooser = new FileChooser();


    @FXML
    TableView<Customer> table;
    @FXML
    TableColumn<Customer, String> name;
    @FXML
    TableColumn<Customer, String> connection_name;
    @FXML
    TableColumn<Customer, String> connection_type;
    @FXML
    TableColumn<Customer, String> platform;
    @FXML
    TableColumn<Customer, String> protocol;
    @FXML
    TableColumn<Customer, String> counterparty;
    @FXML
    TableColumn<Customer, String> area;
    @FXML
    TableColumn<Customer, String> contacts;
    @FXML
    TableColumn<Customer, String> manager;
    @FXML
    TextField search;


    @FXML
    public void initialize() {
        this.customerService = new CustomerService();
        setTable(customerService.getCustomers());
        search.textProperty().addListener((ov, oldV, newV) -> {
            if (!newV.trim().isEmpty()) {
                searchCustomer(newV);
            } else {
                ObservableList<Customer> observedCustomers = FXCollections.observableArrayList(customerService.getCustomers());
                table.setItems(observedCustomers);
            }
        });

    }

    private void setTable(List<Customer> customers) {
        ObservableList<Customer> observedCustomers = FXCollections.observableArrayList(customers);
        table.setEditable(true);
        table.setItems(observedCustomers);
        setCells();
        table.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.DELETE) {
                    System.out.println("here");
                    delete();
                }
            }
        });

        //setRows();


    }

    public void add() {
        ObservableList<Customer> customers = table.getItems();
        Customer customer = new Customer();
        customers.add(customer);
        customerService.add(customer);
        this.table.setItems(customers);
    }

    public void update(Customer customer) {
        customerService.update(customer);
    }

    public void delete() {
        int row = table.getSelectionModel().getFocusedIndex();
        Customer customer = table.getItems().get(row);
        customerService.delete(customer);
        customerService.getCustomers().remove(customer);
        ObservableList<Customer> customers = FXCollections.observableArrayList(customerService.getCustomers());
        table.setItems(customers);
    }

    private void setCells() {
        name.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        name.setCellFactory(TextFieldTableCell.<Customer>forTableColumn());
        name.setOnEditCommit((TableColumn.CellEditEvent<Customer, String> event) -> {
            TablePosition<Customer, String> pos = event.getTablePosition();
            String newValue = event.getNewValue();
            int row = pos.getRow();
            Customer customer = event.getTableView().getItems().get(row);
            customer.setName(newValue);
            update(customer);
        });
        connection_name.setCellValueFactory(new PropertyValueFactory<Customer, String>("connectionName"));
        connection_name.setCellFactory(TextFieldTableCell.<Customer>forTableColumn());
        connection_name.setOnEditCommit((TableColumn.CellEditEvent<Customer, String> event) -> {
            TablePosition<Customer, String> pos = event.getTablePosition();
            String newValue = event.getNewValue();
            int row = pos.getRow();
            Customer customer = event.getTableView().getItems().get(row);
            customer.setConnectionName(newValue);
            update(customer);
        });
        connection_type.setCellValueFactory(new PropertyValueFactory<Customer, String>("connectionType"));
        connection_type.setCellFactory(TextFieldTableCell.<Customer>forTableColumn());
        connection_type.setOnEditCommit((TableColumn.CellEditEvent<Customer, String> event) -> {
            TablePosition<Customer, String> pos = event.getTablePosition();
            String newValue = event.getNewValue();
            int row = pos.getRow();
            Customer customer = event.getTableView().getItems().get(row);
            customer.setConnectionType(newValue);
            update(customer);
        });
        platform.setCellValueFactory(new PropertyValueFactory<Customer, String>("platform"));
        platform.setCellFactory(TextFieldTableCell.<Customer>forTableColumn());
        platform.setOnEditCommit((TableColumn.CellEditEvent<Customer, String> event) -> {
            TablePosition<Customer, String> pos = event.getTablePosition();
            String newValue = event.getNewValue();
            int row = pos.getRow();
            Customer customer = event.getTableView().getItems().get(row);
            customer.setPlatform(newValue);
            update(customer);
        });
        protocol.setCellValueFactory(new PropertyValueFactory<Customer, String>("connectionProtocol"));
        protocol.setCellFactory(TextFieldTableCell.<Customer>forTableColumn());
        protocol.setOnEditCommit((TableColumn.CellEditEvent<Customer, String> event) -> {
            TablePosition<Customer, String> pos = event.getTablePosition();
            String newValue = event.getNewValue();
            int row = pos.getRow();
            Customer customer = event.getTableView().getItems().get(row);
            customer.setConnectionProtocol(newValue);
            update(customer);
        });
        counterparty.setCellValueFactory(new PropertyValueFactory<Customer, String>("counterpartyType"));
        counterparty.setCellFactory(TextFieldTableCell.<Customer>forTableColumn());
        counterparty.setOnEditCommit((TableColumn.CellEditEvent<Customer, String> event) -> {
            TablePosition<Customer, String> pos = event.getTablePosition();
            String newValue = event.getNewValue();
            int row = pos.getRow();
            Customer customer = event.getTableView().getItems().get(row);
            customer.setCounterpartyType(newValue);
            update(customer);
        });
        area.setCellValueFactory(new PropertyValueFactory<Customer, String>("area"));
        area.setCellFactory(TextFieldTableCell.<Customer>forTableColumn());
        area.setOnEditCommit((TableColumn.CellEditEvent<Customer, String> event) -> {
            TablePosition<Customer, String> pos = event.getTablePosition();
            String newValue = event.getNewValue();
            int row = pos.getRow();
            Customer customer = event.getTableView().getItems().get(row);
            customer.setArea(newValue);
            update(customer);
        });
        manager.setCellValueFactory(new PropertyValueFactory<Customer, String>("manager"));
        manager.setCellFactory(TextFieldTableCell.<Customer>forTableColumn());
        manager.setOnEditCommit((TableColumn.CellEditEvent<Customer, String> event) -> {
            TablePosition<Customer, String> pos = event.getTablePosition();
            String newValue = event.getNewValue();
            int row = pos.getRow();
            Customer customer = event.getTableView().getItems().get(row);
            customer.setManager(newValue);
            update(customer);
        });
        contacts.setCellValueFactory(new PropertyValueFactory<Customer, String>("contacts"));
        contacts.setCellFactory(TextFieldTableCell.<Customer>forTableColumn());
        contacts.setOnEditCommit((TableColumn.CellEditEvent<Customer, String> event) -> {
            TablePosition<Customer, String> pos = event.getTablePosition();
            String newValue = event.getNewValue();
            int row = pos.getRow();
            Customer customer = event.getTableView().getItems().get(row);
            customer.setContacts(newValue);
            update(customer);
        });
    }

    private void searchCustomer(String text) {
        List<Customer> customers = customerService.searchCustomer(text);
        ObservableList<Customer> observedCustomers = FXCollections.observableArrayList(customers);
        table.setItems(observedCustomers);
    }

    private void setRows() {
        table.setRowFactory(table -> new TableRow<Customer>() {
            @Override
            protected void updateItem(Customer item, boolean empty) {
                super.updateItem(item, empty);
                if (!empty) {
                    setStyle("-fx-background-color: TURQUOISE;\n" +
                            "    -fx-background-insets: 0, 1, 2;\n" +
                            "    -fx-background: -fx-accent;\n" +
                            "    -fx-text-fill: -fx-selection-bar-text;");
                }
            }
        });
    }

    public void openNewStage() throws IOException {
        Stage stage = new Stage();
        String fxmlFile = "/fxml/alt.fxml";
        FXMLLoader loader = new FXMLLoader();
        Parent root = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
        stage.setTitle("Customers");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.show();

        AltController altController = loader.getController();
        altController.setCustomerService(customerService);
    }

    @FXML
    protected void loadFromFile(){

        Stage stage = new Stage();
        File file = fileChooser.showOpenDialog(stage);
        System.out.println(file.getAbsoluteFile().toString());
        customerService.addCustomersFromFile(file.getAbsoluteFile());

    }

}

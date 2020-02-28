package com.gms_worldwide.controller;

import com.gms_worldwide.dto.Customer;
import com.gms_worldwide.service.CustomerService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.util.List;

public class MainController {

    private CustomerService customerService;

    public MainController() {
    }

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
    public void initialize() {
        this.customerService = new CustomerService();
        fillTable(customerService.getCustomers());

    }

    private void fillTable(List<Customer> customers) {
        ObservableList<Customer> customers1 = FXCollections.observableArrayList(customers);
        table.setEditable(true);
        table.setItems(customers1);
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
}

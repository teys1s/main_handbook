package com.gms_worldwide.controller;

import com.gms_worldwide.dto.Customer;
import com.gms_worldwide.service.CustomerService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
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
    TableColumn<Customer, List<String>> contacts;
    @FXML
    TableColumn<Customer, String> manager;


    @FXML
    public void initialize() {
        this.customerService = new CustomerService();
        customerService.setCustomers(addTestCustomers());
        fillTable(customerService.getCustomers());


    }

    private void fillTable(List<Customer> customers) {
        ObservableList<Customer> customers1 = FXCollections.observableArrayList(customers);
        table.setEditable(true);
        table.setItems(customers1);
        name.setCellValueFactory(new PropertyValueFactory<Customer, String>("name"));
        connection_name.setCellValueFactory(new PropertyValueFactory<Customer, String>("connectionName"));
        connection_type.setCellValueFactory(new PropertyValueFactory<Customer, String>("connectionType"));
        platform.setCellValueFactory(new PropertyValueFactory<Customer, String>("platform"));
        protocol.setCellValueFactory(new PropertyValueFactory<Customer, String>("connectionProtocol"));
        counterparty.setCellValueFactory(new PropertyValueFactory<Customer, String>("counterpartyType"));
        area.setCellValueFactory(new PropertyValueFactory<Customer, String>("area"));
        manager.setCellValueFactory(new PropertyValueFactory<Customer, String>("manager"));
        contacts.setCellValueFactory(new PropertyValueFactory<Customer, List<String>>("contacts"));
        System.out.println(table);
    }

    private List<Customer> addTestCustomers(){
        List<Customer> customers = new ArrayList<Customer>();
        for (int i = 0; i < 5; i++) {
            String x = Integer.toString(i);
            List<String> list = new ArrayList<String>();
            for (int j = 0; j < 2; j++) {
                String y = Integer.toString(j);
                list.add(y);
            }
            Customer customer = new Customer(i, x, x, x, x, x, x, x, list, x);
            customers.add(customer);
        }
        return customers;
    }

    public void add(){
        customerService.add();
        System.out.println(customerService.getCustomers());
        ObservableList<Customer> customers = FXCollections.observableArrayList(customerService.getCustomers());
        this.table.setItems(customers);
    }
}

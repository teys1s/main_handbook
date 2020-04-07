package com.gms_worldwide.controller;

import com.gms_worldwide.dto.Customer;
import com.gms_worldwide.dto.CustomerNote;
import com.gms_worldwide.service.CustomerService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class NoteController {

    private CustomerService service;
    private Customer currentCustomer;

    @FXML
    protected TextArea connectionNote;
    @FXML
    protected TextArea customerNote;
    @FXML
    protected Button saveBtnConnection;
    @FXML
    protected Button saveBtnCustomer;

    @FXML
    public void initialize() {
        saveBtnConnection.setDisable(true);
        saveBtnCustomer.setDisable(true);
    }

    public CustomerService getService() {
        return service;
    }

    public void setService(CustomerService service) {
        this.service = service;
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }

    public void setCurrentCustomer(Customer currentCustomer) {
        this.currentCustomer = currentCustomer;
    }

    public void initData() {
        if (currentCustomer.getNote() != null) {
            connectionNote.setText(currentCustomer.getNote());
        }

        if (currentCustomer.getCustomerNote().getNote() != null) {
            customerNote.setText(currentCustomer.getCustomerNote().getNote());
        }

        connectionNote.textProperty().addListener((ov, oldNote, newNote) -> {
            String noteFromDB = currentCustomer.getNote();
            if (noteFromDB == null) {
                noteFromDB = "";
            }
            if (!noteFromDB.equals(newNote)) {
                saveBtnConnection.setDisable(false);
            } else {
                saveBtnConnection.setDisable(true);
            }
        });

        customerNote.textProperty().addListener((ov, oldNote, newNote) -> {
            String noteFromDB = currentCustomer.getCustomerNote().getNote();
            if (noteFromDB == null) {
                noteFromDB = "";
            }
            if (!noteFromDB.equals(newNote)) {
                saveBtnCustomer.setDisable(false);
            } else {
                saveBtnCustomer.setDisable(true);
            }
        });

    }

    @FXML
    protected void saveConnectionNote() {
        currentCustomer.setNote(connectionNote.getText());
        if (service.connectionNoteSaved(currentCustomer)) {
            saveBtnConnection.setDisable(true);
        }
    }

    @FXML
    protected void saveCustomerNote() {
        CustomerNote customersNote = currentCustomer.getCustomerNote();
        customersNote.setNote(customerNote.getText());
        if (service.customerNoteSaved(customersNote)) {
            saveBtnCustomer.setDisable(true);
        }
    }
}

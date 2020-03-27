package com.gms_worldwide.controller;

import com.gms_worldwide.dto.Customer;
import com.gms_worldwide.service.CustomerService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class NoteController {

    private CustomerService service;
    private Customer currentCustomer;

    @FXML
    protected TextArea noteText;
    @FXML
    protected Button saveBtn;

    @FXML
    public void initialize() {
        saveBtn.setDisable(true);

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
            noteText.setText(currentCustomer.getNote());
        }
        noteText.textProperty().addListener((ov, oldNote, newNote) -> {
            String noteFromDB = currentCustomer.getNote();
            if (noteFromDB == null) {
                noteFromDB = "";
            }
            if (!noteFromDB.equals(newNote)) {
                saveBtn.setDisable(false);
            } else {
                saveBtn.setDisable(true);
            }
        });
    }

    @FXML
    protected void saveNote() {
        currentCustomer.setNote(noteText.getText());
        if (service.noteSaved(currentCustomer)) {
            saveBtn.setDisable(true);
        }
    }
}

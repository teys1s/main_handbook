package com.gms_worldwide.controller;

import com.gms_worldwide.service.CustomerService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class AltController {

    private CustomerService customerService;
    private int searchIndex;

    @FXML
    protected SplitMenuButton search_by;
    @FXML
    protected RadioMenuItem by_name;
    @FXML
    protected RadioMenuItem by_conn_type;
    @FXML
    protected RadioMenuItem by_protocol;
    @FXML
    protected RadioMenuItem by_platform;
    @FXML
    protected RadioMenuItem by_area;
    @FXML
    protected RadioMenuItem by_counterparty;
    @FXML
    protected RadioMenuItem by_manager;
    @FXML
    protected TextArea text;
    @FXML
    protected CheckBox name;
    @FXML
    protected CheckBox conn_name;
    @FXML
    protected CheckBox type;
    @FXML
    protected CheckBox protocol;
    @FXML
    protected CheckBox platform;
    @FXML
    protected CheckBox counterparty;
    @FXML
    protected CheckBox area;
    @FXML
    protected CheckBox contacts;
    @FXML
    protected CheckBox manager;
    @FXML
    protected TextField findText;

    @FXML
    public void initialize() {
        setSearching();
        search_by.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String textFind = findText.getText();
                List<CheckBox> checkBoxes = new ArrayList<>();
                checkBoxes.add(name);
                checkBoxes.add(conn_name);
                checkBoxes.add(type);
                checkBoxes.add(protocol);
                checkBoxes.add(platform);
                checkBoxes.add(counterparty);
                checkBoxes.add(area);
                checkBoxes.add(contacts);
                checkBoxes.add(manager);
                String result = customerService.findBy(textFind, searchIndex, checkBoxes);
                if (result==null){
                    text.setText("");
                    return;
                }
                text.setText(result);
            }
        });
        setShowingFields();


    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }


    protected void setSearching() {
        ToggleGroup group = new ToggleGroup();
        by_name.setToggleGroup(group);
        by_conn_type.setToggleGroup(group);
        by_platform.setToggleGroup(group);
        by_protocol.setToggleGroup(group);
        by_area.setToggleGroup(group);
        by_counterparty.setToggleGroup(group);
        by_manager.setToggleGroup(group);
        search_by.getItems().setAll(by_name, by_conn_type, by_protocol, by_platform, by_area, by_counterparty, by_manager);
        for (int i = 0; i < 7; i++) {
            int r = i;
            search_by.getItems().get(i).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    searchIndex = r;
                }
            });
        }
    }

    protected void setShowingFields() {

    }
}

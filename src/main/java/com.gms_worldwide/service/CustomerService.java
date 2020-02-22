package com.gms_worldwide.service;

import com.gms_worldwide.dto.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    private List<Customer> customers;

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public void add() {
        List<String> strings = new ArrayList<String>();
        strings.add("7");
        strings.add("7");
        customers.add(new Customer(7, "7", "7", "7", "7", "7", "7", "7", strings, "7"));
    }
}

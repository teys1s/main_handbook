package com.gms_worldwide.service;

import com.gms_worldwide.dto.Customer;
import com.gms_worldwide.repos.CustomerRepos;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    private List<Customer> customers;
    private CustomerRepos customerRepos;

    public CustomerService() {
       customerRepos = new CustomerRepos();
    }

    public CustomerRepos getCustomerRepos() {
        return customerRepos;
    }

    public void setCustomerRepos(CustomerRepos customerRepos) {
        this.customerRepos = customerRepos;
    }

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
        Customer customer = new Customer("7", "7", "7", "7", "7", "7", "7", strings, "7");
        customerRepos.addCustomer(customer);
        customers.add(customer);
    }
}

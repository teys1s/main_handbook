package com.gms_worldwide.service;

import com.gms_worldwide.dto.Customer;
import com.gms_worldwide.repos.CustomerRepos;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    private List<Customer> customers;
    private CustomerRepos customerRepos;

    public CustomerService() {
        this.customerRepos = new CustomerRepos();
        this.customers = getAllCustomers();
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

    public void add(Customer customer) {
        customerRepos.addCustomer(customer);
        this.customers = getAllCustomers();
    }

    private List<Customer> getAllCustomers() {
        return customerRepos.getAllCustomers();
    }

    private List<Customer> addTestCustomers() {
        List<Customer> customers = new ArrayList<Customer>();
        for (int i = 0; i < 5; i++) {
            String x = Integer.toString(i);
            Customer customer = new Customer(x, x, x, x, x, x, x, x, x);
            customers.add(customer);
        }
        return customers;
    }

    public void delete(Customer customer) {
        customerRepos.delete(customer);
    }

    public void update(Customer customer) {
        customerRepos.update(customer);

    }
}

package com.gms_worldwide.repos;

import com.gms_worldwide.dto.Customer;

import javax.persistence.TypedQuery;
import java.util.List;

public class CustomerRepos extends Repos<Customer> {



    public List<Customer> getAllCustomers() {
        super.em.getTransaction().begin();
        TypedQuery<Customer> query = em.createQuery("select c from Customer  c", Customer.class);
        em.getTransaction().commit();
        List<Customer> customers = query.getResultList();

        return customers;
    }

}

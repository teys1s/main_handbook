package com.gms_worldwide.repos;

import com.gms_worldwide.dto.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class CustomerRepos {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("main");
    private static EntityManager em = emf.createEntityManager();


    public void addCustomer(Customer customer) {
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
    }

    public List<Customer> getAllCustomers() {
        em.getTransaction().begin();
        TypedQuery<Customer> query = em.createQuery("select c from Customer c", Customer.class);
        em.getTransaction().commit();
        List<Customer> customers = query.getResultList();

        return customers;
    }

    public void delete(Customer customer) {
        em.getTransaction().begin();
        em.remove(customer);
        em.getTransaction().commit();
    }

    public void update(Customer customer) {
        em.getTransaction().begin();
        em.merge(customer);
        em.getTransaction().commit();
    }
}

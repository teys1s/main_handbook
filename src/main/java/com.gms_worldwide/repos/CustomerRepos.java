package com.gms_worldwide.repos;

import com.gms_worldwide.dto.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CustomerRepos {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("main");
    private static EntityManager em = emf.createEntityManager();


    public void addCustomer(Customer customer) {
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
    }
}

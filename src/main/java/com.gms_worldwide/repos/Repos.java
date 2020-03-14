package com.gms_worldwide.repos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Repos<P> {


    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("main");
    protected EntityManager em = emf.createEntityManager();


    public void add(P p) {
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }

    public void delete(P p) {
        em.getTransaction().begin();
        em.remove(p);
        em.getTransaction().commit();
    }

    public void update(P p) {
        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();
    }
}

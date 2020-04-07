package com.gms_worldwide.repos;

import com.gms_worldwide.dto.CustomerNote;

import javax.persistence.TypedQuery;
import java.util.List;

public class CustomerNoteRepos extends Repos<CustomerNote>{

    public List<CustomerNote> getAllCustomers() {
        super.em.getTransaction().begin();
        TypedQuery<CustomerNote> query = em.createQuery("select c from CustomerNote  c", CustomerNote.class);
        em.getTransaction().commit();
        List<CustomerNote> notes = query.getResultList();

        return notes;
    }

}

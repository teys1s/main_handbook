package com.gms_worldwide.repos;

import com.gms_worldwide.service.FilterItem;

import javax.persistence.TypedQuery;
import java.util.List;

public class FilterItemRepos extends Repos<FilterItem> {



    public List<FilterItem> getFilterTypes() {
        super.em.getTransaction().begin();
        TypedQuery<FilterItem> query = em.createQuery("select c from FilterItem  c", FilterItem.class);
        em.getTransaction().commit();
        List<FilterItem> filterTypes = query.getResultList();

        return filterTypes;
    }
}

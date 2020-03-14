package com.gms_worldwide.dto;

import javax.persistence.*;

@Entity
@Table(name = "FItems")
public class FilterItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String searchIn;
    private String searchOut;

    public FilterItem() {
    }

    public FilterItem(String searchIn, String searchOut) {
        this.searchIn = searchIn;
        this.searchOut = searchOut;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSearchIn() {
        return searchIn;
    }

    public void setSearchIn(String searchIn) {
        this.searchIn = searchIn;
    }

    public String getSearchOut() {
        return searchOut;
    }

    public void setSearchOut(String searchOut) {
        this.searchOut = searchOut;
    }

    @Override
    public String toString() {
        return "SearchFilter{" +
                "id=" + id +
                ", searchIn='" + searchIn + '\'' +
                ", searchOut='" + searchOut + '\'' +
                '}';
    }
}

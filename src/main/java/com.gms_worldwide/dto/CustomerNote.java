package com.gms_worldwide.dto;

import javax.persistence.*;
import java.util.List;

@Entity
public class CustomerNote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String customerName;
    private String note;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerNote that = (CustomerNote) o;

        if (id != that.id) return false;
        if (customerName != null ? !customerName.equals(that.customerName) : that.customerName != null) return false;
        return note != null ? note.equals(that.note) : that.note == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "CustomerNote{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}

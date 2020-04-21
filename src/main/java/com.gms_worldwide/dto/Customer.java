package com.gms_worldwide.dto;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String connectionName;
    private String connectionType;
    private String platform;
    private String connectionProtocol;
    private String counterpartyType;
    private String area;
    private String contacts;
    private String manager;
    private String note;
    @ManyToOne
    private CustomerNote customerNote;
    private LocalDateTime createTime;


    public Customer(String name, String connectionName, String connectionType, String platform, String connectionProtocol, String counterpartyType, String area, String contacts, String manager) {
        this.name = name;
        this.connectionName = connectionName;
        this.connectionType = connectionType;
        this.platform = platform;
        this.connectionProtocol = connectionProtocol;
        this.counterpartyType = counterpartyType;
        this.area = area;
        this.contacts = contacts;
        this.manager = manager;
    }

    public Customer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConnectionName() {
        return connectionName;
    }

    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getConnectionProtocol() {
        return connectionProtocol;
    }

    public void setConnectionProtocol(String connectionProtocol) {
        this.connectionProtocol = connectionProtocol;
    }

    public String getCounterpartyType() {
        return counterpartyType;
    }

    public void setCounterpartyType(String counterpartyType) {
        this.counterpartyType = counterpartyType;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public CustomerNote getCustomerNote() {
        return customerNote;
    }

    public void setCustomerNote(CustomerNote customerNote) {
        this.customerNote = customerNote;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return id == customer.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", connectionName='" + connectionName + '\'' +
                ", connectionType='" + connectionType + '\'' +
                ", platform='" + platform + '\'' +
                ", connectionProtocol='" + connectionProtocol + '\'' +
                ", counterpartyType='" + counterpartyType + '\'' +
                ", area='" + area + '\'' +
                ", contacts='" + contacts + '\'' +
                ", manager='" + manager + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}

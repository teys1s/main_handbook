package com.gms_worldwide.dto;


import javax.persistence.*;
import java.util.List;

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

    @ElementCollection
    private List<String> contacts;
    private String manager;

    public Customer(String name, String connectionName, String connectionType, String platform, String connectionProtocol, String counterpartyType, String area, List<String> contacts, String manager) {
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

    public List<String> getContacts() {
        return contacts;
    }

    public void setContacts(List<String> contacts) {
        this.contacts = contacts;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", connectionName='" + connectionName + '\'' +
                ", connectionType=" + connectionType +
                ", platform='" + platform + '\'' +
                ", connectionProtocol=" + connectionProtocol +
                ", counterpartyType=" + counterpartyType +
                ", area=" + area +
                ", contacts='" + contacts + '\'' +
                ", manager='" + manager + '\'' +
                '}';
    }
}

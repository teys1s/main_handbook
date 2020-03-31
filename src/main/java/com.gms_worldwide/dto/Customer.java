package com.gms_worldwide.dto;


import javax.persistence.*;
import java.util.Observable;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (id != customer.id) return false;
        if (name != null ? !name.equals(customer.name) : customer.name != null) return false;
        if (connectionName != null ? !connectionName.equals(customer.connectionName) : customer.connectionName != null) return false;
        if (connectionType != null ? !connectionType.equals(customer.connectionType) : customer.connectionType != null) return false;
        if (platform != null ? !platform.equals(customer.platform) : customer.platform != null) return false;
        if (connectionProtocol != null ? !connectionProtocol.equals(customer.connectionProtocol) : customer.connectionProtocol != null) return false;
        if (counterpartyType != null ? !counterpartyType.equals(customer.counterpartyType) : customer.counterpartyType != null) return false;
        if (area != null ? !area.equals(customer.area) : customer.area != null) return false;
        if (contacts != null ? !contacts.equals(customer.contacts) : customer.contacts != null) return false;
        return manager != null ? manager.equals(customer.manager) : customer.manager == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (connectionName != null ? connectionName.hashCode() : 0);
        result = 31 * result + (connectionType != null ? connectionType.hashCode() : 0);
        result = 31 * result + (platform != null ? platform.hashCode() : 0);
        result = 31 * result + (connectionProtocol != null ? connectionProtocol.hashCode() : 0);
        result = 31 * result + (counterpartyType != null ? counterpartyType.hashCode() : 0);
        result = 31 * result + (area != null ? area.hashCode() : 0);
        result = 31 * result + (contacts != null ? contacts.hashCode() : 0);
        result = 31 * result + (manager != null ? manager.hashCode() : 0);
        return result;
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

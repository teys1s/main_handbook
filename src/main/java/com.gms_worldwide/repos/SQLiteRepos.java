package com.gms_worldwide.repos;

import com.gms_worldwide.dto.Customer;
import com.gms_worldwide.dto.CustomerNote;
import com.gms_worldwide.dto.FilterItem;
import org.sqlite.JDBC;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class SQLiteRepos {

    private static final String URL = "jdbc:sqlite:SQLite.db";

    private Connection connection;

    public SQLiteRepos() {
        try {
            DriverManager.registerDriver(new JDBC());
            this.connection = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from customer");
            while (resultSet.next()) {
                Customer customer = new Customer(resultSet.getString("name"),
                        resultSet.getString("connectionName"),
                        resultSet.getString("connectionType"),
                        resultSet.getString("platform"),
                        resultSet.getString("connectionProtocol"),
                        resultSet.getString("counterpartyType"),
                        resultSet.getString("area"),
                        resultSet.getString("contacts"),
                        resultSet.getString("manager"));
                customer.setCreateTime(LocalDateTime.parse(resultSet.getString("createtime")));
                customer.setNote(resultSet.getString("note"));
                customer.setId(resultSet.getLong("id"));
                long id = resultSet.getLong("customernote_id");
                if (id > 0) {
                    customer.setCustomerNote(getCustomerNoteByID(id));
                }

                customers.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public void addCustomer(Customer customer) {
        try (PreparedStatement statement = connection.prepareStatement("insert into customer(area, connectionname, " +
                "connectionprotocol, connectiontype, contacts, counterpartytype," +
                "manager, name,note,platform,createtime,customernote_id) values(?, ?, ?, ?, ?, ?,?, ?,?,?,?,?)")) {
            statement.setObject(1, customer.getArea());
            statement.setObject(2, customer.getConnectionName());
            statement.setObject(3, customer.getConnectionProtocol());
            statement.setObject(4, customer.getConnectionType());
            statement.setObject(5, customer.getContacts());
            statement.setObject(6, customer.getCounterpartyType());
            statement.setObject(7, customer.getManager());
            statement.setObject(8, customer.getName());
            statement.setObject(9, customer.getNote());
            statement.setObject(10, customer.getPlatform());
            statement.setObject(11, customer.getCreateTime());
            if (customer.getCustomerNote() != null) {
                statement.setObject(12, customer.getCustomerNote().getId());
            } else {
                statement.setObject(12, null);
            }
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        Customer customer1 = getCustomerWithMaxId();
        if (customer1 != null) {
            customer.setId(customer1.getId());
        }
    }

    private Customer getCustomerWithMaxId() {
        Customer customer = null;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM customer WHERE id = (SELECT MAX(id) FROM customer)");

            while (resultSet.next()) {
                customer = new Customer(resultSet.getString("name"),
                        resultSet.getString("connectionName"),
                        resultSet.getString("connectionType"),
                        resultSet.getString("platform"),
                        resultSet.getString("connectionProtocol"),
                        resultSet.getString("counterpartyType"),
                        resultSet.getString("area"),
                        resultSet.getString("contacts"),
                        resultSet.getString("manager"));
                customer.setCreateTime(LocalDateTime.parse(resultSet.getString("createtime")));
                customer.setNote(resultSet.getString("note"));
                customer.setId(resultSet.getLong("id"));
                long id = resultSet.getLong("customernote_id");
                if (id > 0) {
                    customer.setCustomerNote(getCustomerNoteByID(id));
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }


    public void updateCustomer(Customer customer) {
        long id = customer.getId();
        try (PreparedStatement statement = connection.prepareStatement("update customer set area=?, connectionname=?," +
                "connectionprotocol=?, connectiontype=?, contacts=?, counterpartytype=?, manager=?, " +
                "name=?,note=?,platform=?,createtime=?,customernote_id=? where id=?")) {
            statement.setObject(1, customer.getArea());
            statement.setObject(2, customer.getConnectionName());
            statement.setObject(3, customer.getConnectionProtocol());
            statement.setObject(4, customer.getConnectionType());
            statement.setObject(5, customer.getContacts());
            statement.setObject(6, customer.getCounterpartyType());
            statement.setObject(7, customer.getManager());
            statement.setObject(8, customer.getName());
            statement.setObject(9, customer.getNote());
            statement.setObject(10, customer.getPlatform());
            statement.setObject(11, customer.getCreateTime());
            if (customer.getCustomerNote() != null) {
                statement.setObject(12, customer.getCustomerNote().getId());
            } else {
                statement.setObject(12, null);
            }
            statement.setObject(13, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomer(Customer customer) {
        long id = customer.getId();

        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM customer WHERE id = ?")) {
            statement.setObject(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addCustomerNote(CustomerNote note) {
        try (PreparedStatement statement = connection.prepareStatement("insert into customernote(customerName, note) values(?, ?)")) {
            statement.setObject(1, note.getCustomerName());
            statement.setObject(2, note.getNote());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        CustomerNote note1 = getCustomerNoteWithMaxId();
        if (note1 != null) {
            note.setId(note1.getId());
        }
    }

    private CustomerNote getCustomerNoteWithMaxId() {
        CustomerNote note = null;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM customernote WHERE id = (SELECT MAX(id) FROM customernote)");

            while (resultSet.next()) {
                note = new CustomerNote();
                note.setId(resultSet.getLong("id"));
                note.setCustomerName(resultSet.getString("customerName"));
                note.setNote(resultSet.getString("note"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return note;
    }

    private CustomerNote getCustomerNoteByID(long id) {
        CustomerNote customerNote = null;
        try (PreparedStatement statement = connection.prepareStatement("select * from customernote where id=?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getLong("id") == id) {
                    customerNote = new CustomerNote();
                    customerNote.setId(id);
                    customerNote.setCustomerName(resultSet.getString("customerName"));
                    customerNote.setNote(resultSet.getString("note"));
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerNote;
    }


    public List<CustomerNote> getCustomerNotes() {
        List<CustomerNote> customerNotes = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from customernote");
            while (resultSet.next()) {
                CustomerNote customerNote = new CustomerNote();
                customerNote.setId(resultSet.getLong("id"));
                customerNote.setCustomerName(resultSet.getString("customerName"));
                customerNote.setNote(resultSet.getString("note"));
                customerNotes.add(customerNote);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerNotes;
    }

    public void updateCustomerNote(CustomerNote customerNote) {
        long id = customerNote.getId();
        try (PreparedStatement statement = connection.prepareStatement("update customernote set customerName=?, note=? where id=?")) {
            statement.setObject(1, customerNote.getCustomerName());
            statement.setObject(2, customerNote.getNote());
            statement.setObject(3, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomerNote(CustomerNote customerNote) {
        long id = customerNote.getId();

        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM customernote WHERE id = ?")) {
            statement.setObject(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addFilterItem(FilterItem item) {
        try (PreparedStatement statement = connection.prepareStatement("insert into fitems(searchin, searchout)" +
                " values(?, ?)")) {
            statement.setObject(1, item.getSearchIn());
            statement.setObject(2, item.getSearchOut());

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<FilterItem> getFilterItems() {
        List<FilterItem> items = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from fitems");
            while (resultSet.next()) {
                FilterItem item = new FilterItem();
                item.setId(resultSet.getLong("id"));
                item.setSearchIn(resultSet.getString("searchin"));
                item.setSearchOut(resultSet.getString("searchout"));
                items.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
}

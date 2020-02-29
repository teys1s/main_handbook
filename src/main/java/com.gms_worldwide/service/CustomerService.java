package com.gms_worldwide.service;

import com.gms_worldwide.dto.Customer;
import com.gms_worldwide.repos.CustomerRepos;
import javafx.scene.control.CheckBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerService {

    private List<Customer> customers;
    private CustomerRepos customerRepos;
    private Map<String, String> exceptionStrings = new HashMap<>();

    public CustomerService() {
        this.customerRepos = new CustomerRepos();
        this.customers = getAllCustomers();
        fillExceptionStrings();
    }

    public CustomerRepos getCustomerRepos() {
        return customerRepos;
    }

    public void setCustomerRepos(CustomerRepos customerRepos) {
        this.customerRepos = customerRepos;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public void add(Customer customer) {
        customerRepos.addCustomer(customer);
        this.customers = getAllCustomers();
    }

    private List<Customer> getAllCustomers() {
        return customerRepos.getAllCustomers();
    }

    private void fillExceptionStrings() {
        exceptionStrings.put("СКАРПЕЛ", "SCARPEL");
        exceptionStrings.put("КЛІЄНТ", "CLIENT");
        exceptionStrings.put("КЛИЕНТ", "CLIENT");
        exceptionStrings.put("КИЇВ", "KYIV");
        exceptionStrings.put("KIEV", "KYIV");
        exceptionStrings.put("КИЕВ", "KYIV");
        exceptionStrings.put("KYIV", "KYIV");


    }

    private List<Customer> addTestCustomers() {
        List<Customer> customers = new ArrayList<Customer>();
        for (int i = 0; i < 5; i++) {
            String x = Integer.toString(i);
            Customer customer = new Customer(x, x, x, x, x, x, x, x, x);
            customers.add(customer);
        }
        return customers;
    }

    public void delete(Customer customer) {
        customerRepos.delete(customer);
    }

    public void update(Customer customer) {
        customerRepos.update(customer);

    }

    public List<Customer> searchCustomer(String text) {
        List<Customer> foundCustomers = new ArrayList<>();

        for (Customer customer : customers) {
            String customerText = toText(customer);
            if (translitString(customerText.toUpperCase()).
                    contains(translitString(text.toUpperCase()))) {
                foundCustomers.add(customer);
            }
        }


        return foundCustomers;
    }

    private String toText(Customer customer) {
        String result;
        result = customer.getName() + " " +
                customer.getConnectionName() + " " +
                customer.getConnectionType() + " " +
                customer.getConnectionProtocol() + " " +
                customer.getCounterpartyType() + " " +
                customer.getPlatform() + " " +
                customer.getArea() + " " +
                customer.getManager();
        return result;
    }

    private String translitLetter(char ch) {
        switch (ch) {
            case 'А':
                return "A";
            case 'Б':
                return "B";
            case 'В':
                return "V";
            case 'Г':
                return "H";
            case 'Є':
                return "IE";
            case 'Е':
            case 'Ё':
                return "E";
            case 'Д':
                return "D";
            case 'Ж':
                return "ZH";
            case 'З':
                return "Z";
            case 'И':
            case 'І':
            case 'Ї':
                return "I";
            case 'Й':
                return "Y";
            case 'К':
                return "K";
            case 'Л':
                return "L";
            case 'М':
                return "M";
            case 'Н':
                return "N";
            case 'О':
                return "O";
            case 'П':
                return "P";
            case 'Р':
                return "R";
            case 'С':
                return "S";
            case 'Т':
                return "T";
            case 'У':
                return "U";
            case 'Ф':
                return "F";
            case 'Х':
                return "KH";
            case 'Ц':
                return "C";
            case 'Ч':
                return "CH";
            case 'Ш':
                return "SH";
            case 'Щ':
                return "SCH";
            case 'Ъ':
            case 'Ь':
                return "`";
            case 'Ы':
                return "Y";
            case 'Э':
                return "EH";
            case 'Ю':
                return "JU";
            case 'Я':
                return "IA";
            default:
                return String.valueOf(ch);
        }
    }

    private String translitString(String s) {
        for (String s1 : exceptionStrings.keySet()) {
            if (s.toUpperCase().equals(s1)) {
                return exceptionStrings.get(s1);
            }
        }
        StringBuilder sb = new StringBuilder(s.length() * 2);
        for (char ch : s.toCharArray()) {
            sb.append(translitLetter(ch));
        }
        return sb.toString();
    }

    public String findBy(String findText, int searchIndex, List<CheckBox> checkBoxes) {
        switch (searchIndex) {
            case 0:
                return findByName(findText, checkBoxes);
            case 1:
                return findByConnectionType(findText, checkBoxes);
            case 2:
                return findByProtocol(findText, checkBoxes);
            case 3:
                return findByPlatform(findText, checkBoxes);
            case 4:
                return findByArea(findText, checkBoxes);
            case 5:
                return findByCounterparty(findText, checkBoxes);
            case 6:
                return findByManager(findText, checkBoxes);
        }

        return null;
    }

    private String findByName(String findText, List<CheckBox> checkBoxes) {
        String result = null;
        for (Customer customer : customers) {
            if (translitString(customer.getName().trim().toUpperCase()).contains(translitString(findText.trim().toUpperCase()))) {
                if (result == null) {
                    result = makeStringFromCustomer(customer, checkBoxes);
                } else {
                    result = result + "\n" + makeStringFromCustomer(customer, checkBoxes);
                }
            }
        }

        return result;
    }

    private String findByConnectionType(String findText, List<CheckBox> checkBoxes) {
        String result = null;
        for (Customer customer : customers) {
            if (translitString(customer.getConnectionType().trim().toUpperCase()).contains(translitString(findText.trim().toUpperCase()))) {
                if (result == null) {
                    result = makeStringFromCustomer(customer, checkBoxes);
                } else {
                    result = result + "\n" + makeStringFromCustomer(customer, checkBoxes);
                }
            }
        }

        return result;
    }

    private String findByProtocol(String findText, List<CheckBox> checkBoxes) {
        String result = null;
        for (Customer customer : customers) {
            if (translitString(customer.getConnectionProtocol().trim().toUpperCase()).contains(translitString(findText.trim().toUpperCase()))) {
                if (result == null) {
                    result = makeStringFromCustomer(customer, checkBoxes);
                } else {
                    result = result + "\n" + makeStringFromCustomer(customer, checkBoxes);
                }
            }
        }

        return result;
    }

    private String findByPlatform(String findText, List<CheckBox> checkBoxes) {
        String result = null;
        for (Customer customer : customers) {
            if (translitString(customer.getPlatform().trim().toUpperCase()).contains(translitString(findText.trim().toUpperCase()))) {
                if (result == null) {
                    result = makeStringFromCustomer(customer, checkBoxes);
                } else {
                    result = result + "\n" + makeStringFromCustomer(customer, checkBoxes);
                }
            }
        }

        return result;
    }

    private String findByCounterparty(String findText, List<CheckBox> checkBoxes) {
        String result = null;
        for (Customer customer : customers) {
            if (translitString(customer.getCounterpartyType().trim().toUpperCase()).contains(translitString(findText.trim().toUpperCase()))) {
                if (result == null) {
                    result = makeStringFromCustomer(customer, checkBoxes);
                } else {
                    result = result + "\n" + makeStringFromCustomer(customer, checkBoxes);
                }
            }
        }

        return result;
    }

    private String findByManager(String findText, List<CheckBox> checkBoxes) {
        String result = null;
        for (Customer customer : customers) {
            if (translitString(customer.getManager().trim().toUpperCase()).contains(translitString(findText.trim().toUpperCase()))) {
                if (result == null) {
                    result = makeStringFromCustomer(customer, checkBoxes);
                } else {
                    result = result + "\n" + makeStringFromCustomer(customer, checkBoxes);
                }
            }
        }

        return result;
    }

    private String findByArea(String findText, List<CheckBox> checkBoxes) {
        String result = null;
        for (Customer customer : customers) {
            if (translitString(customer.getArea().trim().toUpperCase()).contains(translitString(findText.trim().toUpperCase()))) {
                if (result == null) {
                    result = makeStringFromCustomer(customer, checkBoxes);
                } else {
                    result = result + "\n" + makeStringFromCustomer(customer, checkBoxes);
                }
            }
        }

        return result;
    }

    private String makeStringFromCustomer(Customer customer, List<CheckBox> checkBoxes) {
        String result = null;
        if (customer == null) {
            return null;
        }

        for (CheckBox checkBox : checkBoxes) {
            if (checkBox.isSelected()) {
                if (result == null) {
                    result = checkCheckBox(checkBox.getId(), customer);
                } else {
                    result = result + "| " + checkCheckBox(checkBox.getId(), customer);
                }
            }
        }

        return result;
    }

    private String checkCheckBox(String id, Customer customer) {
        switch (id) {
            case "name":
                return customer.getName();
            case "conn_name":
                return customer.getConnectionName();
            case "type":
                return customer.getConnectionType();
            case "protocol":
                return customer.getConnectionProtocol();
            case "platform":
                return customer.getPlatform();
            case "counterparty":
                return customer.getCounterpartyType();
            case "area":
                return customer.getArea();
            case "contacts":
                return customer.getContacts();
            case "manager":
                return customer.getManager();
            default:
                return null;
        }
    }
}

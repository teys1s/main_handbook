package com.gms_worldwide.service;

import com.gms_worldwide.dto.Customer;
import com.gms_worldwide.dto.FilterItem;
import com.gms_worldwide.repos.FilterItemRepos;
import com.gms_worldwide.repos.Repos;
import com.gms_worldwide.repos.CustomerRepos;
import javafx.scene.control.CheckBox;

import java.io.*;
import java.util.*;

public class CustomerService {

    private List<Customer> customers;
    private CustomerRepos customerRepos;
    private FilterItemRepos filterItemRepos;
    private Map<String, String> searchFilter = new HashMap<>();

    public CustomerService() {
        this.customerRepos = new CustomerRepos();
        this.customers = getAllCustomers();
        this.filterItemRepos = new FilterItemRepos();
        fillSearchFilter();

        /*for (Map.Entry<String, String> item : searchFilter.entrySet()) {
            FilterItem filterItem = new FilterItem(item.getKey().trim().toUpperCase(), item.getValue().trim().toUpperCase());
            filterItemRepos.add(filterItem);
        }*/
    }

    public Repos getCustomerRepos() {
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
        this.customerRepos.add(customer);
        this.customers.add(customer);
    }

    private List<Customer> getAllCustomers() {
        return customerRepos.getAllCustomers();
    }

    private void fillSearchFilter() {

        /*searchFilter.put("СКАРПЕЛ", "SCARPEL");
        searchFilter.put("СКАРПЭЛ", "SCARPEL");
        searchFilter.put("КЛІЄНТ", "CLIENT");
        searchFilter.put("КЛИЕНТ", "CLIENT");
        searchFilter.put("КИЇВ", "KYIV");
        searchFilter.put("KIEV", "KYIV");
        searchFilter.put("КИЕВ", "KYIV");
        searchFilter.put("KYIV", "KYIV");
        searchFilter.put("КОМПАТЕЛ", "COMPATEL");
        searchFilter.put("КОМПАТЭЛ", "COMPATEL");
        searchFilter.put("ИНКОМИНГ", "INCOMING");
        searchFilter.put("ІНКОМІНГ", "INCOMING");*/

        List<FilterItem> filterItems = filterItemRepos.getFilterTypes();
        for (FilterItem filterType : filterItems) {
            searchFilter.put(filterType.getSearchIn(), filterType.getSearchOut());
        }
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
        customers.remove(customer);
    }

    public void update(Customer customer) {
        customerRepos.update(customer);

    }

    public List<Customer> searchCustomer(String text) {
        List<Customer> foundCustomers = new ArrayList<>();

        for (Customer customer : customers) {
            String customerText = toText(customer);
            customerText = customerText.replaceAll("null", "");
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
                return "G";
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
        for (String s1 : searchFilter.keySet()) {
            if (s.toUpperCase().trim().equals(s1)) {
                return searchFilter.get(s1);
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
            if (customer.getName() == null) {
                continue;
            }
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
            if (customer.getConnectionType() == null) {
                continue;
            }
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
            if (customer.getConnectionProtocol() == null) {
                continue;
            }
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
            if (customer.getPlatform() == null) {
                continue;
            }
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
            if (customer.getCounterpartyType() == null) {
                continue;
            }
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
            if (customer.getManager() == null) {
                continue;
            }
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
            if (customer.getArea() == null) {
                continue;
            }
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
                    result = result + "   |   " + checkCheckBox(checkBox.getId(), customer);
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

    public boolean addCustomersFromFile(File absoluteFile) {
        List<String> strings = new ArrayList<>();
        List<Customer> newCustomers;
        if (absoluteFile.toString().endsWith(".txt")) {

            try (BufferedReader bf = new BufferedReader(new FileReader(absoluteFile))) {

                String line;
                while ((line = bf.readLine()) != null) {
                    strings.add(line);
                }
                newCustomers = customerParse(strings);
                addAll(newCustomers);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return true;
        } else {
            return false;
        }
    }

    private List<Customer> customerParse(List<String> strings) {
        List<Customer> customers = new ArrayList<>();
        for (String string : strings) {
            String[] fields = string.split("\\|");
            if (fields.length < 9) {
                continue;
            }
            Customer customer = new Customer(fields[0].trim(), fields[1].trim(),
                    fields[2].trim(), fields[3].trim(),
                    fields[4].trim(), fields[5].trim(),
                    fields[6].trim(), fields[7].trim(), fields[8].trim());

            customers.add(customer);
        }
        return customers;
    }

    private void addAll(List<Customer> customers) {
        for (Customer customer : customers) {
            add(customer);
        }
    }

    public boolean loadFilterItems(File file) {
        Map<String, String> items = new HashMap<>();
        List<FilterItem> filterItems;
        if (file.toString().endsWith(".txt")) {

            try (BufferedReader bf = new BufferedReader(new FileReader(file))) {

                String line;
                while ((line = bf.readLine()) != null) {
                    String[] strings = line.split(",");
                    if (strings.length != 2) {
                        continue;
                    }
                    items.put(strings[0], strings[1]);
                }

                for (Map.Entry<String, String> item : items.entrySet()) {
                    FilterItem filterItem = new FilterItem(item.getKey().trim().toUpperCase(), item.getValue().trim().toUpperCase());
                    filterItemRepos.add(filterItem);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return true;
        } else {
            return false;
        }
    }
}

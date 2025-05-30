package com.shreya.hibernate.service;

import com.shreya.hibernate.exception.CustomerNotFoundException;
import com.shreya.hibernate.model.Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Customer addCustomer(Customer customer) throws SQLException;

    Customer deleteCustomer(int id) throws SQLException;

    boolean updateCustomer(Customer customer) throws SQLException;

    List<Customer> retrieveCustomers();

    Optional<Customer> getCustomerById(int id) throws CustomerNotFoundException;

}

package com.shreya.hibernate.repository;

import com.shreya.hibernate.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository{

    void addCustomer(Customer customer);
    List<Customer> retrieveCustomers();
    Optional<Customer> findById(int id);
    boolean deleteCustomer(int id);
    boolean updateCustomer(Customer customer);
    boolean updatePartialCustomer(Customer customer);
}
package com.shreya.hibernate.service.impl;

import com.shreya.hibernate.domain.CustomerDomain;
import com.shreya.hibernate.exception.CustomerNotFoundException;
import com.shreya.hibernate.exception.CustomerServiceException;
import com.shreya.hibernate.model.Customer;
import com.shreya.hibernate.repository.CustomerRepository;
import com.shreya.hibernate.service.CustomerService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);
    private final CustomerRepository customerRepository;

    @Override
    public Customer addCustomer(Customer customer) {
        log.info("Saving Customer {}", customer);
        CustomerDomain domain = this.populateDomain(customer);
        try {
            customerRepository.save(domain);
            log.info("Customer added successfully{}", customer);
        } catch (Exception e) {
            log.error("Error saving customer: {}", customer, e);
            throw new CustomerServiceException("Failed to add customer");
        }
        //return customerRepository.save(domain);
        return this.populateModel(customerRepository.save(domain));
    }

    @Override
    public Customer deleteCustomer(int id) {
        log.info("Deleting Customer with ID {}", id);
        Optional<CustomerDomain> domain = customerRepository.findById(id);

        if (!domain.isEmpty()) {
            customerRepository.deleteById(id);
        } else {
            log.error("Error deleting customer with ID {}", id);
            throw new CustomerServiceException("Failed to delete customer");
        }
        return this.populateModel(domain.get());
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        log.info("Updating Customer {}", customer);
        try {
            if (updateCustomer(customer)) {
                throw new CustomerNotFoundException("Customer not found with ID: " + customer.getId());
            }
            return true;
        } catch (Exception e) {
            log.error("Error updating customer: {}", customer, e);
            throw new CustomerServiceException("Failed to update customer"); // Preserve original exception
        }
    }


    @Override
    public List<Customer> retrieveCustomers() {
        List<Customer> customers = customerRepository.findAll().stream().map(this::populateModel).toList();

        if (!customers.isEmpty()) {
            log.info("Retrieved {} customers", customers.size());
            return customers;
        } else {
            log.error("No customers found");
            throw new CustomerServiceException("No customers available");
        }
    }


    @Override
    public Optional<Customer> getCustomerById(int id) {
        Optional<CustomerDomain> customer = customerRepository.findById(id);
        log.info("Fetching customer by ID: {}", id);
        try {
            if (customer.isEmpty()) {
                throw new CustomerNotFoundException("Customer not found with ID: " + id);
            }
        } catch (Exception e) {
            log.error("Error fetching customer with ID {}", id, e);
            throw new CustomerServiceException("Failed to fetch customer");
        }
        return Optional.ofNullable(this.populateModel(customer.get()));
    }

    private Customer populateModel(CustomerDomain domain) {
        return Customer.builder()
                .id(domain.getId())
                .name(domain.getName())
                .age(domain.getAge())
                .city(domain.getCity())
                .mobileNo(Math.toIntExact(domain.getMobileNo()))
                .build();
    }

    private CustomerDomain populateDomain(Customer model) {
        return CustomerDomain.builder()
                .id(model.getId())
                .name(model.getName())
                .city(model.getCity())
                .mobileNo(model.getMobileNo())
                .age(model.getAge())
                .build();

    }
}

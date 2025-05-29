package com.shreya.hibernate.repository;

import com.shreya.hibernate.domain.CustomerDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerDomain,Integer> {


}
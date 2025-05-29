package com.shreya.hibernate.repository;

import com.shreya.hibernate.domain.CartItemDomain;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartItemRepository extends JpaRepository<CartItemDomain,Integer> {

}
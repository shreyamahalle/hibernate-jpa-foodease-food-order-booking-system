package com.shreya.hibernate.repository;

import com.shreya.hibernate.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartItemRepository extends JpaRepository<CartItem,Integer> {

}
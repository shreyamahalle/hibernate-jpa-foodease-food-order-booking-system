package com.shreya.hibernate.repository;


import com.shreya.hibernate.domain.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem,Integer> {

}

package com.shreya.hibernate.repository;


import com.shreya.hibernate.domain.MenuItemDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItemDomain,Integer> {

}

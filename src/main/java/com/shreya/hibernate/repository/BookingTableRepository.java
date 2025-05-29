package com.shreya.hibernate.repository;

import com.shreya.hibernate.domain.BookingTable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookingTableRepository extends JpaRepository<BookingTable, Integer> {

}
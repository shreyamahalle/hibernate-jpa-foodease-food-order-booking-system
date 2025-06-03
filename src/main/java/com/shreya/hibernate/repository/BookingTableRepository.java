package com.shreya.hibernate.repository;

import com.shreya.hibernate.domain.BookingTableDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingTableRepository extends JpaRepository<BookingTableDomain, Long> {
}

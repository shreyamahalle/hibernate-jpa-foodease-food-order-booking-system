package com.shreya.hibernate.repository;


import com.shreya.hibernate.domain.NotificationDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationDomain, Integer> {
    List<NotificationDomain> findByCustomerId(Long customerId);
}

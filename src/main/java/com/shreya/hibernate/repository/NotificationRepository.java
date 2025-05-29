package com.shreya.hibernate.repository;


import com.shreya.hibernate.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Integer> {

}

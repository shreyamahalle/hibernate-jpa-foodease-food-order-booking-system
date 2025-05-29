package com.shreya.hibernate.repository;

import com.shreya.hibernate.model.Notification;

import java.util.List;

public interface NotificationRepository {
    boolean saveNotification(Notification notification);
    List<Notification> getAllNotifications();
    List<Notification> getNotificationsByCustomerId(Long customerId);
    boolean markAsRead(Long id);
}

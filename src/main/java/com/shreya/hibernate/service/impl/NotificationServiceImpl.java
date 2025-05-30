package com.shreya.hibernate.service.impl;

import com.shreya.hibernate.domain.NotificationDomain;
import com.shreya.hibernate.exception.NotificationNotFoundException;
import com.shreya.hibernate.model.Notification;
import com.shreya.hibernate.repository.NotificationRepository;
import com.shreya.hibernate.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public boolean saveNotification(Notification notification) {
        log.info("Saving notification: {}", notification);
        notificationRepository.save(toDomain(notification));
        return true;
    }

    @Override
    public List<Notification> getAllNotifications() {
        log.info("Fetching all notifications");
        return notificationRepository.findAll().stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Notification> getNotificationsByCustomerId(Long customerId) {
        log.info("Fetching notifications by customerId: {}", customerId);
        return notificationRepository.findByCustomerId(customerId).stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public boolean markAsRead(Long id) {
        log.info("Marking notification as read with id: {}", id);
        Optional<NotificationDomain> optional = notificationRepository.findById(Math.toIntExact(id));
        if (optional.isPresent()) {
            NotificationDomain domain = optional.get();
            domain.setRead(true);
            notificationRepository.save(domain);
        }else{
                throw new NotificationNotFoundException("Notification not found");
            }
            return true;
    }

    // === Mapping Methods ===
    private Notification toModel(NotificationDomain domain) {
        return Notification.builder()
                .id(domain.getId())
                .customer_id(domain.getCustomerId())
                .message(domain.getMessage())
                .read(domain.isRead())
                .timestamp(String.valueOf(domain.getTimestamp()))
                .build();
    }

    private NotificationDomain toDomain(Notification model) {
        return NotificationDomain.builder()
                .id(model.getId())
                .customerId(model.getCustomer_id())
                .message(model.getMessage())
                .read(model.isRead())
                .timestamp(LocalDateTime.parse(model.getTimestamp()))
                .build();
    }
}

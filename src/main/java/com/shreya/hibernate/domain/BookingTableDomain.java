package com.shreya.hibernate.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "BOOKINGTABLE")
public class BookingTableDomain {

    @Id
    @ToString.Exclude
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "customerName")
    private String customerName;

    @Column(name = "restaurantName")
    private String restaurantName;

    @Column(name = "bookingTime")
    private LocalDateTime bookingTime;

    @Column(name = "numberOfPeople")
    private int numberOfPeople;

    @Column(name = "status")
    private String status;

}

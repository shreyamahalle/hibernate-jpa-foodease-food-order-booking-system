package com.shreya.hibernate.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "BOOKINGTABLE")

public class BookingTableDomain {

    @Id
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

package com.shreya.hibernate.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDERR") // "ORDER" is a reserved keyword in many DBs (use ORDERR or ORDER_TABLE)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id; // Use Long instead of int for generated IDs

    @Column(name = "TYPE", nullable = false)
    private String type;

    @Column(name = "NOTE")
    private String note;

    @Column(name = "PAYMENT_METHOD", nullable = false)
    private String paymentMethod;

    @Column(name = "CUSTOMER_EMAIL", nullable = false)
    private String customerEmail;
}

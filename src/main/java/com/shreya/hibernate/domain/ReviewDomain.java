package com.shreya.hibernate.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "REVIEW")
public class ReviewDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "rating")
    private int rating;

    @Column(name = "comment")
    private String comment;

    @Temporal(TemporalType.DATE)
    @Column(name = "review_date")
    private Date reviewDate;

    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private RestaurantDomain restaurant;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerDomain customer;
}

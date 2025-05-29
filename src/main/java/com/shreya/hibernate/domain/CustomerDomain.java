package com.shreya.hibernate.domain;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CUSTOMER")  // explicit table name for clarity
public class CustomerDomain {
    @Id
    @ToString.Exclude
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "name", nullable = false)  // name should not be null
    private String name;

    @Column(name = "city")
    private String city;

    @Column(name = "mobileNo", nullable = false, unique = true) // mobileNo should be unique and not null
    private long mobileNo;  // use long for mobile numbers

    @Column(name = "age")
    private int age;
}

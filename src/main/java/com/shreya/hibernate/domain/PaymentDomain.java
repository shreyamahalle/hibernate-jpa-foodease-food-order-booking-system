//package com.shreya.hibernate.domain;
//
//import jakarta.persistence.*;
//import lombok.*;
//import org.springframework.core.annotation.Order;
//
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity(name = "PAYMENT")
//public class PaymentDomain {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ID")
//    private Long id;
//
//    @OneToOne
//    @JoinColumn(name = "order_id", referencedColumnName = "ID")
//    private Order order;
//
//    @Column(name = "amount")
//    private Double amount;
//
//    @Column(name = "payment_method")
//    private String paymentMethod;
//
//    @Column(name = "payment_status")
//    private String paymentStatus;
//
//    @Column(name = "transaction_id")
//    private String transactionId;
//}
package com.shreya.hibernate.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PAYMENT")
public class PaymentDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "ID")
    private OrderDomain order; // assuming you have OrderDomain class

    @Column(name = "amount")
    private Double amount;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "transaction_id")
    private String transactionId;
}

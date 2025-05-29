package com.shreya.hibernate.repository;

import com.shreya.hibernate.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon,Integer> {

}
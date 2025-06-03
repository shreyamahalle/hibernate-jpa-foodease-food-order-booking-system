package com.shreya.hibernate.repository;

import com.shreya.hibernate.domain.CouponDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<CouponDomain, Integer> {

}
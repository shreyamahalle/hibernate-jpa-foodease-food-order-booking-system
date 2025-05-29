package com.shreya.hibernate.service;

import com.shreya.hibernate.model.Coupon;

import java.sql.SQLException;
import java.util.List;

public interface CouponService {
    boolean saveCoupon(Coupon coupon) throws SQLException;

    List<Coupon> getAllCoupons();

    Coupon getCouponById(Long id);

    boolean deleteCoupon(Long id);

    boolean updateCoupon(Coupon coupon);
}

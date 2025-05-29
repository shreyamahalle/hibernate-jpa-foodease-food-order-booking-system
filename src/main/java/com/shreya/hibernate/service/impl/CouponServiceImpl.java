package com.shreya.hibernate.service.impl;

import com.shreya.hibernate.exception.CouponAlreadyExistsException;
import com.shreya.hibernate.exception.CouponNotFoundException;
import com.shreya.hibernate.model.Coupon;
import com.shreya.hibernate.repository.CouponRepository;
import com.shreya.hibernate.service.CouponService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class CouponServiceImpl implements CouponService {

    private final Logger log = LoggerFactory.getLogger(CouponServiceImpl.class);

    @Autowired
    private CouponRepository couponRepository;

    @Override
    public boolean saveCoupon(Coupon coupon) throws SQLException {
        log.info("Saving coupon: {}", coupon);
        List<Coupon> allCoupons = couponRepository.getAllCoupons();
        boolean exists = allCoupons.stream()
                .anyMatch(c -> c.getCode().equalsIgnoreCase(coupon.getCode()));
        if (exists) {
            throw new CouponAlreadyExistsException("Coupon already exists with code: " + coupon.getCode());
        }
        return couponRepository.saveCoupon(coupon).isActive();
    }

    @Override
    public List<Coupon> getAllCoupons() {
        log.info("Fetching all coupons");
        return couponRepository.getAllCoupons();
    }

    @Override
    public Coupon getCouponById(Long id) {
        log.info("Fetching coupon by id: {}", id);
        Optional<Coupon> coupon = couponRepository.findById(id);
        if (coupon == null) {
            throw new CouponNotFoundException("Coupon not found with id: " + id);
        }
        return null;
    }

    @Override
    public boolean deleteCoupon(Long id) {
        log.info("Deleting coupon with id: {}", id);
        boolean deleted = couponRepository.deleteCoupon(id).isActive();
        if (!deleted) {
            throw new CouponNotFoundException("Coupon not found with id: " + id);
        }
        return deleted;
    }

    @Override
    public boolean updateCoupon(Coupon coupon) {
        log.info("Updating coupon: {}", coupon);
        boolean updated = couponRepository.updateCoupon(coupon).isActive();
        if (!updated) {
            throw new CouponNotFoundException("Coupon not found with id: " + coupon.getId());
        }
        return updated;
    }
}

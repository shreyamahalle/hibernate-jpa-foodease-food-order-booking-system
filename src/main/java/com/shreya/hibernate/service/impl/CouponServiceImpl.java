package com.shreya.hibernate.service.impl;

import com.shreya.hibernate.domain.CouponDomain;
import com.shreya.hibernate.exception.CouponAlreadyExistsException;
import com.shreya.hibernate.exception.CouponNotFoundException;
import com.shreya.hibernate.model.Coupon;
import com.shreya.hibernate.repository.CouponRepository;
import com.shreya.hibernate.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final Logger log = LoggerFactory.getLogger(CouponServiceImpl.class);
    private final CouponRepository couponRepository;

    @Override
    public boolean saveCoupon(Coupon coupon) throws SQLException {
        log.info("Saving coupon: {}", coupon);
        boolean exists = couponRepository.findAll().stream()
                .anyMatch(c -> c.getCode().equalsIgnoreCase(coupon.getCode()));
        if (exists) {
            throw new CouponAlreadyExistsException("Coupon already exists with code: " + coupon.getCode());
        }
        CouponDomain saved = couponRepository.save(populateDomain(coupon));
        return saved.isActive();
    }

    @Override
    public List<Coupon> getAllCoupons() {
        log.info("Fetching all coupons");
        return couponRepository.findAll().stream()
                .map(this::populateModel)
                .collect(Collectors.toList());
    }

    @Override
    public Coupon getCouponById(Long id) {
        log.info("Fetching coupon by id: {}", id);
        Optional<CouponDomain> optional = couponRepository.findById(Math.toIntExact(id));
        if (optional.isEmpty()) {
            throw new CouponNotFoundException("Coupon not found with id: " + id);
        }
        return populateModel(optional.get());
    }

    @Override
    public boolean deleteCoupon(Long id) {
        log.info("Deleting coupon with id: {}", id);
        Optional<CouponDomain> domain = couponRepository.findById(Math.toIntExact(id));
        if (domain.isEmpty()) {
            throw new CouponNotFoundException("Coupon not found with id: " + id);
        }
        couponRepository.deleteById(Math.toIntExact(id));
        return true;
    }

    @Override
    public boolean updateCoupon(Coupon coupon) {
        log.info("Updating coupon: {}", coupon);
        Optional<CouponDomain> existing = couponRepository.findById(Math.toIntExact(coupon.getId()));
        if (existing.isEmpty()) {
            throw new CouponNotFoundException("Coupon not found with id: " + coupon.getId());
        }

        CouponDomain domain = existing.get();
        domain.setCode(coupon.getCode());
        domain.setDiscountAmount(coupon.getDiscountAmount());
        domain.setActive(coupon.isActive());

        couponRepository.save(domain);
        return true;
    }

    private Coupon populateModel(CouponDomain domain) {
        return Coupon.builder()
                .id(domain.getId())
                .code(domain.getCode())
                .discountAmount(domain.getDiscountAmount())
                .description(domain.getDescription())
                .active(domain.isActive())
                .build();
    }

    private CouponDomain populateDomain(Coupon model) {
        return CouponDomain.builder()
                .id(model.getId())
                .code(model.getCode())
                .discountAmount(model.getDiscountAmount())
                .description(model.getDescription())
                .active(model.isActive())
                .build();
    }
}

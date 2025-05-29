package com.shreya.hibernate.service.impl;
import com.shreya.hibernate.model.BookingTable;
import com.shreya.hibernate.service.BookingTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingTableServiceImpl implements BookingTableService {

    @Override
    public void addBooking(BookingTable bookingTable) throws SQLException {

    }

    @Override
    public List<BookingTable> getAllBookings() {
        return List.of();
    }

    @Override
    public BookingTable getBookingById(Long id) {
        return null;
    }

    @Override
    public boolean deleteBooking(Long id) {
        return false;
    }

    @Override
    public boolean updateBooking(long id, BookingTable bookingTable) {
        return false;
    }
}
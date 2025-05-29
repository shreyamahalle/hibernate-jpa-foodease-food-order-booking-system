package com.shreya.hibernate.service;

import com.shreya.hibernate.model.BookingTable;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BookingTableService {

    BookingTable addBooking(BookingTable bookingTable) throws SQLException;

    List<BookingTable> getAllBookings();

    BookingTable getBookingById(Long id);

    Optional<BookingTable> deleteBooking(Long id);

    boolean updateBooking(long id, BookingTable bookingTable);
}

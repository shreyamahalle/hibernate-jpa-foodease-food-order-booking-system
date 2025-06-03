package com.shreya.hibernate.service.impl;

import com.shreya.hibernate.domain.BookingTableDomain;
import com.shreya.hibernate.exception.BookingNotFoundException;
import com.shreya.hibernate.model.BookingTable;
import com.shreya.hibernate.repository.BookingTableRepository;
import com.shreya.hibernate.service.BookingTableService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingTableServiceImpl implements BookingTableService {

    private static final Logger log = LoggerFactory.getLogger(BookingTableServiceImpl.class);
    private final BookingTableRepository bookingTableRepository;


    @Override
    public BookingTable addBooking(BookingTable bookingTable) throws SQLException {
        System.out.println("Inside service " + bookingTable);
        BookingTableDomain domain = this.populateDomain(bookingTable);
        return this.populateModel(bookingTableRepository.save(domain));

    }

    @Override
    public List<BookingTable> getAllBookings() {
        return bookingTableRepository.findAll().stream().map(this::populateModel).toList();
    }

    @Override
    public BookingTable getBookingById(Long id) {
        Optional<BookingTableDomain> getBookingById = bookingTableRepository.findById(id);
        if (getBookingById.isEmpty()) {
            throw new BookingNotFoundException("Booking not found");
        }
        return this.populateModel(getBookingById.get());
    }

    @Override
    public Optional<BookingTable> deleteBooking(Long id) {
        Optional<BookingTableDomain> domain = bookingTableRepository.findById(id);
        if (!domain.isEmpty()) {
            bookingTableRepository.deleteById(id);
            System.out.println("deleted booking" + domain.get());
        } else {
            throw new BookingNotFoundException("Booking not found");
        }
        return Optional.ofNullable(this.populateModel(domain.get()));
    }

    @Override
    public boolean updateBooking(long id, BookingTable bookingTable) {
        Optional<BookingTableDomain> optionalDomain = bookingTableRepository.findById(id);

        if (optionalDomain.isEmpty()) {
            throw new BookingNotFoundException("Booking not found with id: " + id);
        }

        BookingTableDomain existing = optionalDomain.get();

        // Update fields
        existing.setCustomerName(bookingTable.getCustomerName());
        existing.setRestaurantName(bookingTable.getRestaurantName());
        existing.setBookingTime(bookingTable.getBookingTime());
        existing.setNumberOfPeople(bookingTable.getNumberOfPeople());
        existing.setStatus(bookingTable.getStatus());

        bookingTableRepository.save(existing);
        return true;
    }


    private BookingTable populateModel(BookingTableDomain domain) {
        return BookingTable.builder()
                .id(domain.getId())
                .customerName(domain.getCustomerName())
                .restaurantName(domain.getRestaurantName())
                .numberOfPeople(domain.getNumberOfPeople())
                .bookingTime(domain.getBookingTime())
                .status(domain.getStatus()).build();
    }

    private BookingTableDomain populateDomain(BookingTable model) {
        return BookingTableDomain.builder()
                .id(model.getId())
                .customerName(model.getCustomerName())
                .restaurantName(model.getRestaurantName())
                .numberOfPeople(model.getNumberOfPeople())
                .status(model.getStatus())
                .bookingTime(model.getBookingTime()).build();
    }
}

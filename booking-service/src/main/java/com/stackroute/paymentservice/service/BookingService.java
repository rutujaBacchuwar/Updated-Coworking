package com.stackroute.paymentservice.service;

import com.stackroute.kafka.domain.Booking;
import com.stackroute.paymentservice.exceptions.BookedSpaceNotFound;
import com.stackroute.paymentservice.exceptions.SpaceAlreadyBooked;

import java.util.List;

public interface BookingService {

    public List<Booking> getAllBookedSpace();
    public Booking bookedSpace(Booking booking) throws SpaceAlreadyBooked;
    public boolean deleteBookedSpace(int bookingId) throws BookedSpaceNotFound;
    public Booking findByName(String name);
}

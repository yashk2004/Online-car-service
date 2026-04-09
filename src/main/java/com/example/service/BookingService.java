package com.example.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.Booking;
import com.example.repository.BookingRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository repo;

    public Booking save(Booking booking) {
        return repo.save(booking);
    }

    public List<Booking> getAll() {
        return repo.findAll();
    }

    public List<Booking> getByUserId(String userId) {
        return repo.findByUserId(userId);
    }
}

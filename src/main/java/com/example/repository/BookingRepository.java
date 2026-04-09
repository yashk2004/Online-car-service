package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.model.Booking;
import java.util.List;

public interface BookingRepository extends MongoRepository<Booking, String> {
    List<Booking> findByUserId(String userId);
}

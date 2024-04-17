package com.example.hotelApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hotelApp.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

}

package com.example.rating.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rating.model.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {

}

package com.example.hotel.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hotel.communicator.RatingServiceCommunicator;
import com.example.hotel.dto.HotelRequest;
import com.example.hotel.dto.RatingResponse;
import com.example.hotel.model.Hotel;
import com.example.hotel.repository.HotelRepository;

@Service
public class HotelService {

	private final HotelRepository hotelRepository;
	private final RatingServiceCommunicator ratingServiceCommunicator;

	public HotelService(HotelRepository hotelRepository, RatingServiceCommunicator ratingServiceCommunicator) {
		this.hotelRepository = hotelRepository;
		this.ratingServiceCommunicator = ratingServiceCommunicator;
	}

	public List<Hotel> getAllHotels() {
		return hotelRepository.findAll();
	}

	public Hotel getHotelById(Long id, String authorizationHeader) {

		String jwtToken = authorizationHeader.replace("Bearer ", "");
		Hotel hotel = hotelRepository.findById(id).get();
		System.out.print(hotel);
		RatingResponse ratingResponse = ratingServiceCommunicator.getRating(id.toString(),jwtToken);
	
		hotel.setRating(ratingResponse.getRating());
		return hotel;
	}

	public Hotel createHotel(HotelRequest hotelRequest) {
		Hotel hotel = new Hotel();
		hotel.setCity(hotelRequest.getCity());
		hotel.setName(hotelRequest.getName());
		hotel.setRating(hotelRequest.getRating());

		return hotelRepository.save(hotel);
	}

	public void deleteHotelById(Long id) {
		hotelRepository.deleteById(id);
	}

}

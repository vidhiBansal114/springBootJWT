package com.example.hotelApp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hotelApp.dto.HotelRequest;
import com.example.hotelApp.model.Hotel;
import com.example.hotelApp.repository.HotelRepository;
@Service
public class HotelService {
	private final HotelRepository hotelRepository;

	public HotelService(HotelRepository hotelRepository) {
		this.hotelRepository = hotelRepository;
	}
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id).get();
    }
	public Hotel createHotel(HotelRequest hotelRequest) {
		// TODO Auto-generated method stub
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

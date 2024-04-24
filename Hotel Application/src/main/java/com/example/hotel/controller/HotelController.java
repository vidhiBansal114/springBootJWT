package com.example.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotel.dto.HotelRequest;
import com.example.hotel.model.Hotel;
import com.example.hotel.service.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {

	@Autowired 
	HotelService hotelService;
	
	@PostMapping("/create")
	@PreAuthorize("hasRole('ADMIN')")
	public void createHotel(@RequestBody HotelRequest hotelRequest)
	{

		hotelService.createHotel(hotelRequest);
	} 
	
	@GetMapping("/id/{id}")
	@PreAuthorize("hasRole('NORMAL')")
	public Hotel getHotelById(@RequestHeader("Authorization") String authorizationHeader,@PathVariable Long id)
	{
		return hotelService.getHotelById(id,authorizationHeader);
	}
	
	@GetMapping("/getAll")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Hotel> getAllHotels()
	{
		return hotelService.getAllHotels();
	}
	
	@DeleteMapping("/remove/id/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteHotelById(@PathVariable Long id)
	{
		hotelService.deleteHotelById(id);
		
	}
}

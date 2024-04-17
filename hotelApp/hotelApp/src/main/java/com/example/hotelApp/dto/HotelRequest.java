package com.example.hotelApp.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelRequest {

	private String name;
	private Long rating;
	private String city;
	
}
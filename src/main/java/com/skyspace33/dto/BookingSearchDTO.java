package com.skyspace33.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BookingSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer bookingId;
	
	private String bookingNumber;
	
	private String seatNumber;
	
	private String classType;
	
	private double price;
	
}

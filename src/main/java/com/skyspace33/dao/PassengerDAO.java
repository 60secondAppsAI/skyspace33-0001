package com.skyspace33.dao;

import java.util.List;

import com.skyspace33.dao.GenericDAO;
import com.skyspace33.domain.Passenger;





public interface PassengerDAO extends GenericDAO<Passenger, Integer> {
  
	List<Passenger> findAll();
	






}



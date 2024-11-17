package com.skyspace33.dao;

import java.util.List;

import com.skyspace33.dao.GenericDAO;
import com.skyspace33.domain.Airline;





public interface AirlineDAO extends GenericDAO<Airline, Integer> {
  
	List<Airline> findAll();
	






}



package com.skyspace33.dao;

import java.util.List;

import com.skyspace33.dao.GenericDAO;
import com.skyspace33.domain.Destination;





public interface DestinationDAO extends GenericDAO<Destination, Integer> {
  
	List<Destination> findAll();
	






}



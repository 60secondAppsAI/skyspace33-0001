package com.skyspace33.dao;

import java.util.List;

import com.skyspace33.dao.GenericDAO;
import com.skyspace33.domain.Baggage;





public interface BaggageDAO extends GenericDAO<Baggage, Integer> {
  
	List<Baggage> findAll();
	






}



package com.skyspace33.dao;

import java.util.List;

import com.skyspace33.dao.GenericDAO;
import com.skyspace33.domain.Pilot;





public interface PilotDAO extends GenericDAO<Pilot, Integer> {
  
	List<Pilot> findAll();
	






}



package com.skyspace33.dao;

import java.util.List;

import com.skyspace33.dao.GenericDAO;
import com.skyspace33.domain.Airplane;





public interface AirplaneDAO extends GenericDAO<Airplane, Integer> {
  
	List<Airplane> findAll();
	






}



package com.skyspace33.dao;

import java.util.List;

import com.skyspace33.dao.GenericDAO;
import com.skyspace33.domain.City;





public interface CityDAO extends GenericDAO<City, Integer> {
  
	List<City> findAll();
	






}



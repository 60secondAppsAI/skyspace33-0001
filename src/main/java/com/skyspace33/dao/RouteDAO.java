package com.skyspace33.dao;

import java.util.List;

import com.skyspace33.dao.GenericDAO;
import com.skyspace33.domain.Route;





public interface RouteDAO extends GenericDAO<Route, Integer> {
  
	List<Route> findAll();
	






}



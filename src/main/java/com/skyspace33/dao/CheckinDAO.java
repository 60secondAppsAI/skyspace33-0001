package com.skyspace33.dao;

import java.util.List;

import com.skyspace33.dao.GenericDAO;
import com.skyspace33.domain.Checkin;





public interface CheckinDAO extends GenericDAO<Checkin, Integer> {
  
	List<Checkin> findAll();
	






}



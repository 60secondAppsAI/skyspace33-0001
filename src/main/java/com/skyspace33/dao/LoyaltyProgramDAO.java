package com.skyspace33.dao;

import java.util.List;

import com.skyspace33.dao.GenericDAO;
import com.skyspace33.domain.LoyaltyProgram;





public interface LoyaltyProgramDAO extends GenericDAO<LoyaltyProgram, Integer> {
  
	List<LoyaltyProgram> findAll();
	






}



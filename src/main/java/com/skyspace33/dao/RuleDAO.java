package com.skyspace33.dao;

import java.util.List;

import com.skyspace33.dao.GenericDAO;
import com.skyspace33.domain.Rule;





public interface RuleDAO extends GenericDAO<Rule, Integer> {
  
	List<Rule> findAll();
	






}



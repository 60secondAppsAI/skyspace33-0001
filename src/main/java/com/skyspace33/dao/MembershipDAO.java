package com.skyspace33.dao;

import java.util.List;

import com.skyspace33.dao.GenericDAO;
import com.skyspace33.domain.Membership;





public interface MembershipDAO extends GenericDAO<Membership, Integer> {
  
	List<Membership> findAll();
	






}



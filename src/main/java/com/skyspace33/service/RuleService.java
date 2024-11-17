package com.skyspace33.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace33.domain.Rule;
import com.skyspace33.dto.RuleDTO;
import com.skyspace33.dto.RuleSearchDTO;
import com.skyspace33.dto.RulePageDTO;
import com.skyspace33.dto.RuleConvertCriteriaDTO;
import com.skyspace33.service.GenericService;
import com.skyspace33.dto.common.RequestDTO;
import com.skyspace33.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface RuleService extends GenericService<Rule, Integer> {

	List<Rule> findAll();

	ResultDTO addRule(RuleDTO ruleDTO, RequestDTO requestDTO);

	ResultDTO updateRule(RuleDTO ruleDTO, RequestDTO requestDTO);

    Page<Rule> getAllRules(Pageable pageable);

    Page<Rule> getAllRules(Specification<Rule> spec, Pageable pageable);

	ResponseEntity<RulePageDTO> getRules(RuleSearchDTO ruleSearchDTO);
	
	List<RuleDTO> convertRulesToRuleDTOs(List<Rule> rules, RuleConvertCriteriaDTO convertCriteria);

	RuleDTO getRuleDTOById(Integer ruleId);







}






package com.skyspace33.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.skyspace33.dao.GenericDAO;
import com.skyspace33.service.GenericService;
import com.skyspace33.service.impl.GenericServiceImpl;
import com.skyspace33.dao.RuleDAO;
import com.skyspace33.domain.Rule;
import com.skyspace33.dto.RuleDTO;
import com.skyspace33.dto.RuleSearchDTO;
import com.skyspace33.dto.RulePageDTO;
import com.skyspace33.dto.RuleConvertCriteriaDTO;
import com.skyspace33.dto.common.RequestDTO;
import com.skyspace33.dto.common.ResultDTO;
import com.skyspace33.service.RuleService;
import com.skyspace33.util.ControllerUtils;





@Service
public class RuleServiceImpl extends GenericServiceImpl<Rule, Integer> implements RuleService {

    private final static Logger logger = LoggerFactory.getLogger(RuleServiceImpl.class);

	@Autowired
	RuleDAO ruleDao;

	


	@Override
	public GenericDAO<Rule, Integer> getDAO() {
		return (GenericDAO<Rule, Integer>) ruleDao;
	}
	
	public List<Rule> findAll () {
		List<Rule> rules = ruleDao.findAll();
		
		return rules;	
		
	}

	public ResultDTO addRule(RuleDTO ruleDTO, RequestDTO requestDTO) {

		Rule rule = new Rule();

		rule.setRuleId(ruleDTO.getRuleId());


		rule.setDescription(ruleDTO.getDescription());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		rule = ruleDao.save(rule);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Rule> getAllRules(Pageable pageable) {
		return ruleDao.findAll(pageable);
	}

	public Page<Rule> getAllRules(Specification<Rule> spec, Pageable pageable) {
		return ruleDao.findAll(spec, pageable);
	}

	public ResponseEntity<RulePageDTO> getRules(RuleSearchDTO ruleSearchDTO) {
	
			Integer ruleId = ruleSearchDTO.getRuleId(); 
 			String description = ruleSearchDTO.getDescription(); 
 			String sortBy = ruleSearchDTO.getSortBy();
			String sortOrder = ruleSearchDTO.getSortOrder();
			String searchQuery = ruleSearchDTO.getSearchQuery();
			Integer page = ruleSearchDTO.getPage();
			Integer size = ruleSearchDTO.getSize();

	        Specification<Rule> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, ruleId, "ruleId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, description, "description"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("description")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Rule> rules = this.getAllRules(spec, pageable);
		
		//System.out.println(String.valueOf(rules.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(rules.getTotalPages()));
		
		List<Rule> rulesList = rules.getContent();
		
		RuleConvertCriteriaDTO convertCriteria = new RuleConvertCriteriaDTO();
		List<RuleDTO> ruleDTOs = this.convertRulesToRuleDTOs(rulesList,convertCriteria);
		
		RulePageDTO rulePageDTO = new RulePageDTO();
		rulePageDTO.setRules(ruleDTOs);
		rulePageDTO.setTotalElements(rules.getTotalElements());
		return ResponseEntity.ok(rulePageDTO);
	}

	public List<RuleDTO> convertRulesToRuleDTOs(List<Rule> rules, RuleConvertCriteriaDTO convertCriteria) {
		
		List<RuleDTO> ruleDTOs = new ArrayList<RuleDTO>();
		
		for (Rule rule : rules) {
			ruleDTOs.add(convertRuleToRuleDTO(rule,convertCriteria));
		}
		
		return ruleDTOs;

	}
	
	public RuleDTO convertRuleToRuleDTO(Rule rule, RuleConvertCriteriaDTO convertCriteria) {
		
		RuleDTO ruleDTO = new RuleDTO();
		
		ruleDTO.setRuleId(rule.getRuleId());

	
		ruleDTO.setDescription(rule.getDescription());

	

		
		return ruleDTO;
	}

	public ResultDTO updateRule(RuleDTO ruleDTO, RequestDTO requestDTO) {
		
		Rule rule = ruleDao.getById(ruleDTO.getRuleId());

		rule.setRuleId(ControllerUtils.setValue(rule.getRuleId(), ruleDTO.getRuleId()));

		rule.setDescription(ControllerUtils.setValue(rule.getDescription(), ruleDTO.getDescription()));



        rule = ruleDao.save(rule);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public RuleDTO getRuleDTOById(Integer ruleId) {
	
		Rule rule = ruleDao.getById(ruleId);
			
		
		RuleConvertCriteriaDTO convertCriteria = new RuleConvertCriteriaDTO();
		return(this.convertRuleToRuleDTO(rule,convertCriteria));
	}







}

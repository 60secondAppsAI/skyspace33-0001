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
import com.skyspace33.dao.PilotDAO;
import com.skyspace33.domain.Pilot;
import com.skyspace33.dto.PilotDTO;
import com.skyspace33.dto.PilotSearchDTO;
import com.skyspace33.dto.PilotPageDTO;
import com.skyspace33.dto.PilotConvertCriteriaDTO;
import com.skyspace33.dto.common.RequestDTO;
import com.skyspace33.dto.common.ResultDTO;
import com.skyspace33.service.PilotService;
import com.skyspace33.util.ControllerUtils;





@Service
public class PilotServiceImpl extends GenericServiceImpl<Pilot, Integer> implements PilotService {

    private final static Logger logger = LoggerFactory.getLogger(PilotServiceImpl.class);

	@Autowired
	PilotDAO pilotDao;

	


	@Override
	public GenericDAO<Pilot, Integer> getDAO() {
		return (GenericDAO<Pilot, Integer>) pilotDao;
	}
	
	public List<Pilot> findAll () {
		List<Pilot> pilots = pilotDao.findAll();
		
		return pilots;	
		
	}

	public ResultDTO addPilot(PilotDTO pilotDTO, RequestDTO requestDTO) {

		Pilot pilot = new Pilot();

		pilot.setPilotId(pilotDTO.getPilotId());


		pilot.setName(pilotDTO.getName());


		pilot.setLicenseNumber(pilotDTO.getLicenseNumber());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		pilot = pilotDao.save(pilot);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Pilot> getAllPilots(Pageable pageable) {
		return pilotDao.findAll(pageable);
	}

	public Page<Pilot> getAllPilots(Specification<Pilot> spec, Pageable pageable) {
		return pilotDao.findAll(spec, pageable);
	}

	public ResponseEntity<PilotPageDTO> getPilots(PilotSearchDTO pilotSearchDTO) {
	
			Integer pilotId = pilotSearchDTO.getPilotId(); 
 			String name = pilotSearchDTO.getName(); 
 			String licenseNumber = pilotSearchDTO.getLicenseNumber(); 
 			String sortBy = pilotSearchDTO.getSortBy();
			String sortOrder = pilotSearchDTO.getSortOrder();
			String searchQuery = pilotSearchDTO.getSearchQuery();
			Integer page = pilotSearchDTO.getPage();
			Integer size = pilotSearchDTO.getSize();

	        Specification<Pilot> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, pilotId, "pilotId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, licenseNumber, "licenseNumber"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("licenseNumber")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Pilot> pilots = this.getAllPilots(spec, pageable);
		
		//System.out.println(String.valueOf(pilots.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(pilots.getTotalPages()));
		
		List<Pilot> pilotsList = pilots.getContent();
		
		PilotConvertCriteriaDTO convertCriteria = new PilotConvertCriteriaDTO();
		List<PilotDTO> pilotDTOs = this.convertPilotsToPilotDTOs(pilotsList,convertCriteria);
		
		PilotPageDTO pilotPageDTO = new PilotPageDTO();
		pilotPageDTO.setPilots(pilotDTOs);
		pilotPageDTO.setTotalElements(pilots.getTotalElements());
		return ResponseEntity.ok(pilotPageDTO);
	}

	public List<PilotDTO> convertPilotsToPilotDTOs(List<Pilot> pilots, PilotConvertCriteriaDTO convertCriteria) {
		
		List<PilotDTO> pilotDTOs = new ArrayList<PilotDTO>();
		
		for (Pilot pilot : pilots) {
			pilotDTOs.add(convertPilotToPilotDTO(pilot,convertCriteria));
		}
		
		return pilotDTOs;

	}
	
	public PilotDTO convertPilotToPilotDTO(Pilot pilot, PilotConvertCriteriaDTO convertCriteria) {
		
		PilotDTO pilotDTO = new PilotDTO();
		
		pilotDTO.setPilotId(pilot.getPilotId());

	
		pilotDTO.setName(pilot.getName());

	
		pilotDTO.setLicenseNumber(pilot.getLicenseNumber());

	

		
		return pilotDTO;
	}

	public ResultDTO updatePilot(PilotDTO pilotDTO, RequestDTO requestDTO) {
		
		Pilot pilot = pilotDao.getById(pilotDTO.getPilotId());

		pilot.setPilotId(ControllerUtils.setValue(pilot.getPilotId(), pilotDTO.getPilotId()));

		pilot.setName(ControllerUtils.setValue(pilot.getName(), pilotDTO.getName()));

		pilot.setLicenseNumber(ControllerUtils.setValue(pilot.getLicenseNumber(), pilotDTO.getLicenseNumber()));



        pilot = pilotDao.save(pilot);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public PilotDTO getPilotDTOById(Integer pilotId) {
	
		Pilot pilot = pilotDao.getById(pilotId);
			
		
		PilotConvertCriteriaDTO convertCriteria = new PilotConvertCriteriaDTO();
		return(this.convertPilotToPilotDTO(pilot,convertCriteria));
	}







}

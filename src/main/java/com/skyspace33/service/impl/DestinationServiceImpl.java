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
import com.skyspace33.dao.DestinationDAO;
import com.skyspace33.domain.Destination;
import com.skyspace33.dto.DestinationDTO;
import com.skyspace33.dto.DestinationSearchDTO;
import com.skyspace33.dto.DestinationPageDTO;
import com.skyspace33.dto.DestinationConvertCriteriaDTO;
import com.skyspace33.dto.common.RequestDTO;
import com.skyspace33.dto.common.ResultDTO;
import com.skyspace33.service.DestinationService;
import com.skyspace33.util.ControllerUtils;





@Service
public class DestinationServiceImpl extends GenericServiceImpl<Destination, Integer> implements DestinationService {

    private final static Logger logger = LoggerFactory.getLogger(DestinationServiceImpl.class);

	@Autowired
	DestinationDAO destinationDao;

	


	@Override
	public GenericDAO<Destination, Integer> getDAO() {
		return (GenericDAO<Destination, Integer>) destinationDao;
	}
	
	public List<Destination> findAll () {
		List<Destination> destinations = destinationDao.findAll();
		
		return destinations;	
		
	}

	public ResultDTO addDestination(DestinationDTO destinationDTO, RequestDTO requestDTO) {

		Destination destination = new Destination();

		destination.setDestinationId(destinationDTO.getDestinationId());


		destination.setName(destinationDTO.getName());


		destination.setCountry(destinationDTO.getCountry());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		destination = destinationDao.save(destination);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Destination> getAllDestinations(Pageable pageable) {
		return destinationDao.findAll(pageable);
	}

	public Page<Destination> getAllDestinations(Specification<Destination> spec, Pageable pageable) {
		return destinationDao.findAll(spec, pageable);
	}

	public ResponseEntity<DestinationPageDTO> getDestinations(DestinationSearchDTO destinationSearchDTO) {
	
			Integer destinationId = destinationSearchDTO.getDestinationId(); 
 			String name = destinationSearchDTO.getName(); 
 			String country = destinationSearchDTO.getCountry(); 
 			String sortBy = destinationSearchDTO.getSortBy();
			String sortOrder = destinationSearchDTO.getSortOrder();
			String searchQuery = destinationSearchDTO.getSearchQuery();
			Integer page = destinationSearchDTO.getPage();
			Integer size = destinationSearchDTO.getSize();

	        Specification<Destination> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, destinationId, "destinationId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, country, "country"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("country")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Destination> destinations = this.getAllDestinations(spec, pageable);
		
		//System.out.println(String.valueOf(destinations.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(destinations.getTotalPages()));
		
		List<Destination> destinationsList = destinations.getContent();
		
		DestinationConvertCriteriaDTO convertCriteria = new DestinationConvertCriteriaDTO();
		List<DestinationDTO> destinationDTOs = this.convertDestinationsToDestinationDTOs(destinationsList,convertCriteria);
		
		DestinationPageDTO destinationPageDTO = new DestinationPageDTO();
		destinationPageDTO.setDestinations(destinationDTOs);
		destinationPageDTO.setTotalElements(destinations.getTotalElements());
		return ResponseEntity.ok(destinationPageDTO);
	}

	public List<DestinationDTO> convertDestinationsToDestinationDTOs(List<Destination> destinations, DestinationConvertCriteriaDTO convertCriteria) {
		
		List<DestinationDTO> destinationDTOs = new ArrayList<DestinationDTO>();
		
		for (Destination destination : destinations) {
			destinationDTOs.add(convertDestinationToDestinationDTO(destination,convertCriteria));
		}
		
		return destinationDTOs;

	}
	
	public DestinationDTO convertDestinationToDestinationDTO(Destination destination, DestinationConvertCriteriaDTO convertCriteria) {
		
		DestinationDTO destinationDTO = new DestinationDTO();
		
		destinationDTO.setDestinationId(destination.getDestinationId());

	
		destinationDTO.setName(destination.getName());

	
		destinationDTO.setCountry(destination.getCountry());

	

		
		return destinationDTO;
	}

	public ResultDTO updateDestination(DestinationDTO destinationDTO, RequestDTO requestDTO) {
		
		Destination destination = destinationDao.getById(destinationDTO.getDestinationId());

		destination.setDestinationId(ControllerUtils.setValue(destination.getDestinationId(), destinationDTO.getDestinationId()));

		destination.setName(ControllerUtils.setValue(destination.getName(), destinationDTO.getName()));

		destination.setCountry(ControllerUtils.setValue(destination.getCountry(), destinationDTO.getCountry()));



        destination = destinationDao.save(destination);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public DestinationDTO getDestinationDTOById(Integer destinationId) {
	
		Destination destination = destinationDao.getById(destinationId);
			
		
		DestinationConvertCriteriaDTO convertCriteria = new DestinationConvertCriteriaDTO();
		return(this.convertDestinationToDestinationDTO(destination,convertCriteria));
	}







}

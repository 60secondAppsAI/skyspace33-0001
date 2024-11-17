package com.skyspace33.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace33.domain.Destination;
import com.skyspace33.dto.DestinationDTO;
import com.skyspace33.dto.DestinationSearchDTO;
import com.skyspace33.dto.DestinationPageDTO;
import com.skyspace33.dto.DestinationConvertCriteriaDTO;
import com.skyspace33.service.GenericService;
import com.skyspace33.dto.common.RequestDTO;
import com.skyspace33.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface DestinationService extends GenericService<Destination, Integer> {

	List<Destination> findAll();

	ResultDTO addDestination(DestinationDTO destinationDTO, RequestDTO requestDTO);

	ResultDTO updateDestination(DestinationDTO destinationDTO, RequestDTO requestDTO);

    Page<Destination> getAllDestinations(Pageable pageable);

    Page<Destination> getAllDestinations(Specification<Destination> spec, Pageable pageable);

	ResponseEntity<DestinationPageDTO> getDestinations(DestinationSearchDTO destinationSearchDTO);
	
	List<DestinationDTO> convertDestinationsToDestinationDTOs(List<Destination> destinations, DestinationConvertCriteriaDTO convertCriteria);

	DestinationDTO getDestinationDTOById(Integer destinationId);







}






package com.skyspace33.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.skyspace33.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.skyspace33.domain.Destination;
import com.skyspace33.dto.DestinationDTO;
import com.skyspace33.dto.DestinationSearchDTO;
import com.skyspace33.dto.DestinationPageDTO;
import com.skyspace33.service.DestinationService;
import com.skyspace33.dto.common.RequestDTO;
import com.skyspace33.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/destination")
@RestController
public class DestinationController {

	private final static Logger logger = LoggerFactory.getLogger(DestinationController.class);

	@Autowired
	DestinationService destinationService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Destination> getAll() {

		List<Destination> destinations = destinationService.findAll();
		
		return destinations;	
	}

	@GetMapping(value = "/{destinationId}")
	@ResponseBody
	public DestinationDTO getDestination(@PathVariable Integer destinationId) {
		
		return (destinationService.getDestinationDTOById(destinationId));
	}

 	@RequestMapping(value = "/addDestination", method = RequestMethod.POST)
	public ResponseEntity<?> addDestination(@RequestBody DestinationDTO destinationDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = destinationService.addDestination(destinationDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/destinations")
	public ResponseEntity<DestinationPageDTO> getDestinations(DestinationSearchDTO destinationSearchDTO) {
 
		return destinationService.getDestinations(destinationSearchDTO);
	}	

	@RequestMapping(value = "/updateDestination", method = RequestMethod.POST)
	public ResponseEntity<?> updateDestination(@RequestBody DestinationDTO destinationDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = destinationService.updateDestination(destinationDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}

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

import com.skyspace33.domain.Airplane;
import com.skyspace33.dto.AirplaneDTO;
import com.skyspace33.dto.AirplaneSearchDTO;
import com.skyspace33.dto.AirplanePageDTO;
import com.skyspace33.service.AirplaneService;
import com.skyspace33.dto.common.RequestDTO;
import com.skyspace33.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/airplane")
@RestController
public class AirplaneController {

	private final static Logger logger = LoggerFactory.getLogger(AirplaneController.class);

	@Autowired
	AirplaneService airplaneService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Airplane> getAll() {

		List<Airplane> airplanes = airplaneService.findAll();
		
		return airplanes;	
	}

	@GetMapping(value = "/{airplaneId}")
	@ResponseBody
	public AirplaneDTO getAirplane(@PathVariable Integer airplaneId) {
		
		return (airplaneService.getAirplaneDTOById(airplaneId));
	}

 	@RequestMapping(value = "/addAirplane", method = RequestMethod.POST)
	public ResponseEntity<?> addAirplane(@RequestBody AirplaneDTO airplaneDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = airplaneService.addAirplane(airplaneDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/airplanes")
	public ResponseEntity<AirplanePageDTO> getAirplanes(AirplaneSearchDTO airplaneSearchDTO) {
 
		return airplaneService.getAirplanes(airplaneSearchDTO);
	}	

	@RequestMapping(value = "/updateAirplane", method = RequestMethod.POST)
	public ResponseEntity<?> updateAirplane(@RequestBody AirplaneDTO airplaneDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = airplaneService.updateAirplane(airplaneDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}

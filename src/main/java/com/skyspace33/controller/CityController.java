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

import com.skyspace33.domain.City;
import com.skyspace33.dto.CityDTO;
import com.skyspace33.dto.CitySearchDTO;
import com.skyspace33.dto.CityPageDTO;
import com.skyspace33.service.CityService;
import com.skyspace33.dto.common.RequestDTO;
import com.skyspace33.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/city")
@RestController
public class CityController {

	private final static Logger logger = LoggerFactory.getLogger(CityController.class);

	@Autowired
	CityService cityService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<City> getAll() {

		List<City> citys = cityService.findAll();
		
		return citys;	
	}

	@GetMapping(value = "/{cityId}")
	@ResponseBody
	public CityDTO getCity(@PathVariable Integer cityId) {
		
		return (cityService.getCityDTOById(cityId));
	}

 	@RequestMapping(value = "/addCity", method = RequestMethod.POST)
	public ResponseEntity<?> addCity(@RequestBody CityDTO cityDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = cityService.addCity(cityDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/citys")
	public ResponseEntity<CityPageDTO> getCitys(CitySearchDTO citySearchDTO) {
 
		return cityService.getCitys(citySearchDTO);
	}	

	@RequestMapping(value = "/updateCity", method = RequestMethod.POST)
	public ResponseEntity<?> updateCity(@RequestBody CityDTO cityDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = cityService.updateCity(cityDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}

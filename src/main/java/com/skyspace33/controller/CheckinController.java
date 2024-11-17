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

import com.skyspace33.domain.Checkin;
import com.skyspace33.dto.CheckinDTO;
import com.skyspace33.dto.CheckinSearchDTO;
import com.skyspace33.dto.CheckinPageDTO;
import com.skyspace33.service.CheckinService;
import com.skyspace33.dto.common.RequestDTO;
import com.skyspace33.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/checkin")
@RestController
public class CheckinController {

	private final static Logger logger = LoggerFactory.getLogger(CheckinController.class);

	@Autowired
	CheckinService checkinService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Checkin> getAll() {

		List<Checkin> checkins = checkinService.findAll();
		
		return checkins;	
	}

	@GetMapping(value = "/{checkinId}")
	@ResponseBody
	public CheckinDTO getCheckin(@PathVariable Integer checkinId) {
		
		return (checkinService.getCheckinDTOById(checkinId));
	}

 	@RequestMapping(value = "/addCheckin", method = RequestMethod.POST)
	public ResponseEntity<?> addCheckin(@RequestBody CheckinDTO checkinDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = checkinService.addCheckin(checkinDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/checkins")
	public ResponseEntity<CheckinPageDTO> getCheckins(CheckinSearchDTO checkinSearchDTO) {
 
		return checkinService.getCheckins(checkinSearchDTO);
	}	

	@RequestMapping(value = "/updateCheckin", method = RequestMethod.POST)
	public ResponseEntity<?> updateCheckin(@RequestBody CheckinDTO checkinDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = checkinService.updateCheckin(checkinDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}

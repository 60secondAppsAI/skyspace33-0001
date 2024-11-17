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

import com.skyspace33.domain.Pilot;
import com.skyspace33.dto.PilotDTO;
import com.skyspace33.dto.PilotSearchDTO;
import com.skyspace33.dto.PilotPageDTO;
import com.skyspace33.service.PilotService;
import com.skyspace33.dto.common.RequestDTO;
import com.skyspace33.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/pilot")
@RestController
public class PilotController {

	private final static Logger logger = LoggerFactory.getLogger(PilotController.class);

	@Autowired
	PilotService pilotService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Pilot> getAll() {

		List<Pilot> pilots = pilotService.findAll();
		
		return pilots;	
	}

	@GetMapping(value = "/{pilotId}")
	@ResponseBody
	public PilotDTO getPilot(@PathVariable Integer pilotId) {
		
		return (pilotService.getPilotDTOById(pilotId));
	}

 	@RequestMapping(value = "/addPilot", method = RequestMethod.POST)
	public ResponseEntity<?> addPilot(@RequestBody PilotDTO pilotDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = pilotService.addPilot(pilotDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/pilots")
	public ResponseEntity<PilotPageDTO> getPilots(PilotSearchDTO pilotSearchDTO) {
 
		return pilotService.getPilots(pilotSearchDTO);
	}	

	@RequestMapping(value = "/updatePilot", method = RequestMethod.POST)
	public ResponseEntity<?> updatePilot(@RequestBody PilotDTO pilotDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = pilotService.updatePilot(pilotDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}

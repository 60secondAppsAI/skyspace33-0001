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

import com.skyspace33.domain.Membership;
import com.skyspace33.dto.MembershipDTO;
import com.skyspace33.dto.MembershipSearchDTO;
import com.skyspace33.dto.MembershipPageDTO;
import com.skyspace33.service.MembershipService;
import com.skyspace33.dto.common.RequestDTO;
import com.skyspace33.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/membership")
@RestController
public class MembershipController {

	private final static Logger logger = LoggerFactory.getLogger(MembershipController.class);

	@Autowired
	MembershipService membershipService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Membership> getAll() {

		List<Membership> memberships = membershipService.findAll();
		
		return memberships;	
	}

	@GetMapping(value = "/{membershipId}")
	@ResponseBody
	public MembershipDTO getMembership(@PathVariable Integer membershipId) {
		
		return (membershipService.getMembershipDTOById(membershipId));
	}

 	@RequestMapping(value = "/addMembership", method = RequestMethod.POST)
	public ResponseEntity<?> addMembership(@RequestBody MembershipDTO membershipDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = membershipService.addMembership(membershipDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/memberships")
	public ResponseEntity<MembershipPageDTO> getMemberships(MembershipSearchDTO membershipSearchDTO) {
 
		return membershipService.getMemberships(membershipSearchDTO);
	}	

	@RequestMapping(value = "/updateMembership", method = RequestMethod.POST)
	public ResponseEntity<?> updateMembership(@RequestBody MembershipDTO membershipDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = membershipService.updateMembership(membershipDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}

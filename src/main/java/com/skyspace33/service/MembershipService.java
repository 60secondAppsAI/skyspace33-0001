package com.skyspace33.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace33.domain.Membership;
import com.skyspace33.dto.MembershipDTO;
import com.skyspace33.dto.MembershipSearchDTO;
import com.skyspace33.dto.MembershipPageDTO;
import com.skyspace33.dto.MembershipConvertCriteriaDTO;
import com.skyspace33.service.GenericService;
import com.skyspace33.dto.common.RequestDTO;
import com.skyspace33.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface MembershipService extends GenericService<Membership, Integer> {

	List<Membership> findAll();

	ResultDTO addMembership(MembershipDTO membershipDTO, RequestDTO requestDTO);

	ResultDTO updateMembership(MembershipDTO membershipDTO, RequestDTO requestDTO);

    Page<Membership> getAllMemberships(Pageable pageable);

    Page<Membership> getAllMemberships(Specification<Membership> spec, Pageable pageable);

	ResponseEntity<MembershipPageDTO> getMemberships(MembershipSearchDTO membershipSearchDTO);
	
	List<MembershipDTO> convertMembershipsToMembershipDTOs(List<Membership> memberships, MembershipConvertCriteriaDTO convertCriteria);

	MembershipDTO getMembershipDTOById(Integer membershipId);







}






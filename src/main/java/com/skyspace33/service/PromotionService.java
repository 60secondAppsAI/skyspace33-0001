package com.skyspace33.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace33.domain.Promotion;
import com.skyspace33.dto.PromotionDTO;
import com.skyspace33.dto.PromotionSearchDTO;
import com.skyspace33.dto.PromotionPageDTO;
import com.skyspace33.dto.PromotionConvertCriteriaDTO;
import com.skyspace33.service.GenericService;
import com.skyspace33.dto.common.RequestDTO;
import com.skyspace33.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface PromotionService extends GenericService<Promotion, Integer> {

	List<Promotion> findAll();

	ResultDTO addPromotion(PromotionDTO promotionDTO, RequestDTO requestDTO);

	ResultDTO updatePromotion(PromotionDTO promotionDTO, RequestDTO requestDTO);

    Page<Promotion> getAllPromotions(Pageable pageable);

    Page<Promotion> getAllPromotions(Specification<Promotion> spec, Pageable pageable);

	ResponseEntity<PromotionPageDTO> getPromotions(PromotionSearchDTO promotionSearchDTO);
	
	List<PromotionDTO> convertPromotionsToPromotionDTOs(List<Promotion> promotions, PromotionConvertCriteriaDTO convertCriteria);

	PromotionDTO getPromotionDTOById(Integer promotionId);







}






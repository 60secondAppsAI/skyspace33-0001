package com.skyspace33.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace33.domain.Feedback;
import com.skyspace33.dto.FeedbackDTO;
import com.skyspace33.dto.FeedbackSearchDTO;
import com.skyspace33.dto.FeedbackPageDTO;
import com.skyspace33.dto.FeedbackConvertCriteriaDTO;
import com.skyspace33.service.GenericService;
import com.skyspace33.dto.common.RequestDTO;
import com.skyspace33.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface FeedbackService extends GenericService<Feedback, Integer> {

	List<Feedback> findAll();

	ResultDTO addFeedback(FeedbackDTO feedbackDTO, RequestDTO requestDTO);

	ResultDTO updateFeedback(FeedbackDTO feedbackDTO, RequestDTO requestDTO);

    Page<Feedback> getAllFeedbacks(Pageable pageable);

    Page<Feedback> getAllFeedbacks(Specification<Feedback> spec, Pageable pageable);

	ResponseEntity<FeedbackPageDTO> getFeedbacks(FeedbackSearchDTO feedbackSearchDTO);
	
	List<FeedbackDTO> convertFeedbacksToFeedbackDTOs(List<Feedback> feedbacks, FeedbackConvertCriteriaDTO convertCriteria);

	FeedbackDTO getFeedbackDTOById(Integer feedbackId);







}






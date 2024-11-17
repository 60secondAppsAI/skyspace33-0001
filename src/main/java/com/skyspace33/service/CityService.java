package com.skyspace33.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skyspace33.domain.City;
import com.skyspace33.dto.CityDTO;
import com.skyspace33.dto.CitySearchDTO;
import com.skyspace33.dto.CityPageDTO;
import com.skyspace33.dto.CityConvertCriteriaDTO;
import com.skyspace33.service.GenericService;
import com.skyspace33.dto.common.RequestDTO;
import com.skyspace33.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CityService extends GenericService<City, Integer> {

	List<City> findAll();

	ResultDTO addCity(CityDTO cityDTO, RequestDTO requestDTO);

	ResultDTO updateCity(CityDTO cityDTO, RequestDTO requestDTO);

    Page<City> getAllCitys(Pageable pageable);

    Page<City> getAllCitys(Specification<City> spec, Pageable pageable);

	ResponseEntity<CityPageDTO> getCitys(CitySearchDTO citySearchDTO);
	
	List<CityDTO> convertCitysToCityDTOs(List<City> citys, CityConvertCriteriaDTO convertCriteria);

	CityDTO getCityDTOById(Integer cityId);







}






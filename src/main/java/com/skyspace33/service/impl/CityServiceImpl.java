package com.skyspace33.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.skyspace33.dao.GenericDAO;
import com.skyspace33.service.GenericService;
import com.skyspace33.service.impl.GenericServiceImpl;
import com.skyspace33.dao.CityDAO;
import com.skyspace33.domain.City;
import com.skyspace33.dto.CityDTO;
import com.skyspace33.dto.CitySearchDTO;
import com.skyspace33.dto.CityPageDTO;
import com.skyspace33.dto.CityConvertCriteriaDTO;
import com.skyspace33.dto.common.RequestDTO;
import com.skyspace33.dto.common.ResultDTO;
import com.skyspace33.service.CityService;
import com.skyspace33.util.ControllerUtils;





@Service
public class CityServiceImpl extends GenericServiceImpl<City, Integer> implements CityService {

    private final static Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);

	@Autowired
	CityDAO cityDao;

	


	@Override
	public GenericDAO<City, Integer> getDAO() {
		return (GenericDAO<City, Integer>) cityDao;
	}
	
	public List<City> findAll () {
		List<City> citys = cityDao.findAll();
		
		return citys;	
		
	}

	public ResultDTO addCity(CityDTO cityDTO, RequestDTO requestDTO) {

		City city = new City();

		city.setCityId(cityDTO.getCityId());


		city.setName(cityDTO.getName());


		city.setCountry(cityDTO.getCountry());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		city = cityDao.save(city);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<City> getAllCitys(Pageable pageable) {
		return cityDao.findAll(pageable);
	}

	public Page<City> getAllCitys(Specification<City> spec, Pageable pageable) {
		return cityDao.findAll(spec, pageable);
	}

	public ResponseEntity<CityPageDTO> getCitys(CitySearchDTO citySearchDTO) {
	
			Integer cityId = citySearchDTO.getCityId(); 
 			String name = citySearchDTO.getName(); 
 			String country = citySearchDTO.getCountry(); 
 			String sortBy = citySearchDTO.getSortBy();
			String sortOrder = citySearchDTO.getSortOrder();
			String searchQuery = citySearchDTO.getSearchQuery();
			Integer page = citySearchDTO.getPage();
			Integer size = citySearchDTO.getSize();

	        Specification<City> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, cityId, "cityId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, country, "country"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("country")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<City> citys = this.getAllCitys(spec, pageable);
		
		//System.out.println(String.valueOf(citys.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(citys.getTotalPages()));
		
		List<City> citysList = citys.getContent();
		
		CityConvertCriteriaDTO convertCriteria = new CityConvertCriteriaDTO();
		List<CityDTO> cityDTOs = this.convertCitysToCityDTOs(citysList,convertCriteria);
		
		CityPageDTO cityPageDTO = new CityPageDTO();
		cityPageDTO.setCitys(cityDTOs);
		cityPageDTO.setTotalElements(citys.getTotalElements());
		return ResponseEntity.ok(cityPageDTO);
	}

	public List<CityDTO> convertCitysToCityDTOs(List<City> citys, CityConvertCriteriaDTO convertCriteria) {
		
		List<CityDTO> cityDTOs = new ArrayList<CityDTO>();
		
		for (City city : citys) {
			cityDTOs.add(convertCityToCityDTO(city,convertCriteria));
		}
		
		return cityDTOs;

	}
	
	public CityDTO convertCityToCityDTO(City city, CityConvertCriteriaDTO convertCriteria) {
		
		CityDTO cityDTO = new CityDTO();
		
		cityDTO.setCityId(city.getCityId());

	
		cityDTO.setName(city.getName());

	
		cityDTO.setCountry(city.getCountry());

	

		
		return cityDTO;
	}

	public ResultDTO updateCity(CityDTO cityDTO, RequestDTO requestDTO) {
		
		City city = cityDao.getById(cityDTO.getCityId());

		city.setCityId(ControllerUtils.setValue(city.getCityId(), cityDTO.getCityId()));

		city.setName(ControllerUtils.setValue(city.getName(), cityDTO.getName()));

		city.setCountry(ControllerUtils.setValue(city.getCountry(), cityDTO.getCountry()));



        city = cityDao.save(city);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public CityDTO getCityDTOById(Integer cityId) {
	
		City city = cityDao.getById(cityId);
			
		
		CityConvertCriteriaDTO convertCriteria = new CityConvertCriteriaDTO();
		return(this.convertCityToCityDTO(city,convertCriteria));
	}







}

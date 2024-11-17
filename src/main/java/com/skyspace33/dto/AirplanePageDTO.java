package com.skyspace33.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AirplanePageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<AirplaneDTO> airplanes;
}






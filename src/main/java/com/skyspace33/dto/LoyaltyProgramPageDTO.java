package com.skyspace33.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoyaltyProgramPageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<LoyaltyProgramDTO> loyaltyPrograms;
}






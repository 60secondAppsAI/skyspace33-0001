package com.skyspace33.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RulePageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<RuleDTO> rules;
}






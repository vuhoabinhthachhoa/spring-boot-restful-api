package com.javaweb.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuildingDTO {
	 	private Long id;
	 	private String name;
	 	private Long districtId;
	 	private Long rentPrice;
}
package com.javaweb.DTO;

import java.util.List;

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
	 	private List<Long> rentAreas;
}
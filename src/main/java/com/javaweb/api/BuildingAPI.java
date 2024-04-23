package com.javaweb.api;


import java.util.List;
import org.apache.commons.lang3.tuple.Pair;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.service.BuildingService;

@RestController
public class BuildingAPI {
	
	@Autowired
	private BuildingService buildingService;

	@GetMapping(value = "/api/findBuilding")
	public List<BuildingDTO> findBuilding(@RequestParam(value = "name", required = false) String iName,
			@RequestParam(value = "street", required = false) String iStreet,
			@RequestParam(value = "ward", required = false) String iWard,
			@RequestParam(value = "district", required = false) String iDistrictName,
			@RequestParam(value = "numberofbasement", required = false) Long iNumberOfBasement,
			@RequestParam(value = "floorarea", required = false) Long iFloorArea,
			@RequestParam(value = "direction", required = false) String iDirection,
			@RequestParam(value = "level", required = false) String iLevel,
			@RequestParam(value = "rentprice", required = false) Pair<Long, Long> iRentPrice,
			@RequestParam(value = "managername", required = false) String iManagerName,
			@RequestParam(value = "managerphonenumber", required = false) String iManagerPhoneNumber,
			@RequestParam(value = "staffid", required = false) Long iStaffID,
			@RequestParam(value = "staffid", required = false) Pair<Long, Long> iRentArea,
			@RequestParam(value = "renttype", required = false) List<String> iRentTypeName ) {
		List<BuildingDTO> result = buildingService.findBuildings(iName, iStreet, iWard, iDistrictName, iNumberOfBasement, 
				iFloorArea,  iDirection, iLevel, iRentPrice, iManagerName, iManagerPhoneNumber, iStaffID, iRentArea, 
				iRentTypeName);
		return result;	
	}
}

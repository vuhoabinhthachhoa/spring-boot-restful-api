package com.javaweb.api;


import java.util.List;

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
	public List<BuildingDTO> findBuilding(@RequestParam(value = "name", required = false) String iname,
			@RequestParam(value = "numberofbasement", required = false) Long inumberOfBasement) {
		List<BuildingDTO> result = buildingService.findAll(iname, inumberOfBasement);
		return result;
	}
}

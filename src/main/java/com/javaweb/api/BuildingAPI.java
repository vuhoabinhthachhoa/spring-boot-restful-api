package com.javaweb.api;

import java.util.List;
import java.util.Map;

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
	public List<BuildingDTO> findBuilding(@RequestParam Map<String, Object> params,
			@RequestParam(value = "typecode", required = false) List<String> typeCode) {
		List<BuildingDTO> result = buildingService.findBuildings(params, typeCode);
		
		return result;	
	}
}

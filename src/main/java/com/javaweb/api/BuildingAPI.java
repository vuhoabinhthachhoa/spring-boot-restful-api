package com.javaweb.api;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.DTO.Response;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.service.BuildingService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@RestController
@Transactional
public class BuildingAPI {
	
	@Autowired
	private BuildingService buildingService;
	
	@PersistenceContext
	private EntityManager entityManager;

	@GetMapping(value = "/api/findBuilding")
	public List<Response> findBuilding(@RequestParam Map<String, Object> params,
			@RequestParam(value = "typecode", required = false) List<String> typeCode) {
		List<Response> result = buildingService.findBuildings(params, typeCode);
		
		return result;	
	}
	
	@PostMapping(value = "api/building")
	public void createBuilding(@RequestBody BuildingDTO buildingDTO) {
		buildingService.createBuilding(buildingDTO);
	}
	
	@PutMapping(value = "api/building")
	public void updateBuilding(@RequestBody BuildingDTO buildingDTO) {
		buildingService.updateBuilding(buildingDTO);
	}
	
	@DeleteMapping(value = "api/building/{id}")
	public void deleteBuilding(@PathVariable Long id) {
		buildingService.deleteBuilding(id);
	}
	
//	@DeleteMapping(value = "api/building/{ids}/{name}")
//	public void deleteBuilding(@PathVariable Long[] ids, @PathVariable String name) {
//		buildingService.deleteBuilding(null);
//	}
}

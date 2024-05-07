package com.javaweb.service;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.DTO.Response;

import jakarta.transaction.Transactional;

@Service
public interface BuildingService {
	public List<Response> findBuildings(Map<String, Object> params, List<String> typeCode);
	
	@Transactional
	public void createBuilding(BuildingDTO buildingDTO);
	
	@Transactional
	public void deleteBuilding(Long id);
	
	@Transactional
	public void updateBuilding(BuildingDTO buildingDTO);
}

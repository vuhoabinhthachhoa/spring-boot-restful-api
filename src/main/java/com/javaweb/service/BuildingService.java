package com.javaweb.service;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.javaweb.DTO.BuildingDTO;

@Service
public interface BuildingService {
	public List<BuildingDTO> findBuildings(Map<String, Object> params, List<String> typeCode);
}

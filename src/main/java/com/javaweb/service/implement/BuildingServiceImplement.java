package com.javaweb.service.implement;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.service.BuildingService;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.DTO.BuildingDTO;
import com.javaweb.converter.BuildingConverter;


@Service
public class BuildingServiceImplement implements BuildingService {
	
	@Autowired
	private BuildingRepository buidldingRepository;
	@Autowired
	private BuildingConverter buildingConverter;
	
	@Override
	public List<BuildingDTO> findBuildings(Map<String, Object> params, List<String> typeCode) {
		
		List<BuildingEntity> receivedData = buidldingRepository.findBuildings(params, typeCode); 
		
		List<BuildingDTO> result = new ArrayList<BuildingDTO>();
		for(BuildingEntity it : receivedData) { 
		    result.add(buildingConverter.BuildingEntityToBuildingDTO(it));
		}
		return result;
	}
}

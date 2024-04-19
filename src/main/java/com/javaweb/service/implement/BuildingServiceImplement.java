package com.javaweb.service.implement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

import com.javaweb.service.BuildingService;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.DTO.BuildingDTO;

@Service
public class BuildingServiceImplement implements BuildingService {
	
	@Autowired
	private BuildingRepository buidldingRepository;
	
	@Override
	public List<BuildingDTO> findAll(String iname, Long inumberOfBasement) {
		List<BuildingEntity> receivedData = buidldingRepository.findAll(iname, inumberOfBasement); 
		List<BuildingDTO> result = new ArrayList<BuildingDTO>();
		
		for(BuildingEntity it : receivedData) { 
			String name = it.getName();
		    String address = it.getStreet() + ", "+ it.getWard() + ", " + it.getDistrictId(); // street, ward, district name
		    String managerName = it.getManagerName();
		    
		    result.add(new BuildingDTO(name, address, managerName));
		}
		return result;
	}
}

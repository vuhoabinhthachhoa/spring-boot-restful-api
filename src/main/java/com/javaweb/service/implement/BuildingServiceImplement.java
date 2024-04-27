package com.javaweb.service.implement;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.service.BuildingService;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.DTO.BuildingDTO;


@Service
public class BuildingServiceImplement implements BuildingService {
	
	@Autowired
	private BuildingRepository buidldingRepository;
	@Autowired
	private DistrictRepository districtRepository;
	@Autowired
	private RentAreaRepository rentAreaRepository;
	
	
	String convertRentAreasToString(List<RentAreaEntity> rentAreas) {
	    if (rentAreas.isEmpty()) {
	        return "";
	    }

	    List<String> values = new ArrayList<>();
	    for (RentAreaEntity rentArea : rentAreas) {
	        values.add(rentArea.getValue().toString());
	    }

	    return String.join(", ", values);
	}
	
	@Override
	public List<BuildingDTO> findBuildings(Map<String, Object> params, List<String> typeCode) {
		
		List<BuildingEntity> receivedData = buidldingRepository.findBuildings(params, typeCode); 
		
		if(receivedData == null) {
			return null;
		}
		
		List<BuildingDTO> result = new ArrayList<BuildingDTO>();
		
		for(BuildingEntity it : receivedData) { 
			String name = it.getName();
			Long districtID = it.getDistrictId();			
			DistrictEntity district = districtRepository.findDistrictByID(districtID);
		    String address = it.getStreet() + ", "+ it.getWard() + ", " + district.getName(); // street, ward, district name
		    Long numberOfBasement = it.getNumberOfBasement();
		    String managerName = it.getManagerName();
		    String managerPhoneNumber = it.getManagerPhoneNumber();
		    Long floorArea = it.getFloorArea();
		    String rentArea = convertRentAreasToString(rentAreaRepository.findRentAreaByBuildingID(it.getDistrictId()));
		    String brokerageFee = String.format("%.1f", it.getBrokerageFee());
		    
		    result.add(new BuildingDTO(name, address, numberOfBasement, managerName, managerPhoneNumber, floorArea, rentArea, brokerageFee));
		}
		return result;
	}
}

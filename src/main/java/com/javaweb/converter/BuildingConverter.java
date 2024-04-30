package com.javaweb.converter;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.DTO.BuildingDTO;

@Component
public class BuildingConverter {
	
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
	
	public BuildingDTO BuildingEntityToBuildingDTO(BuildingEntity entity) {
	    BuildingDTO DTO = new BuildingDTO();

	    DTO.setName(entity.getName());
	    Long districtID = entity.getDistrictId();			
	    DistrictEntity district = districtRepository.findDistrictByID(districtID);
	    DTO.setAddress(entity.getStreet() + ", "+ entity.getWard() + ", " + district.getName()); // street, ward, district name
	    DTO.setNumberOfBasement(entity.getNumberOfBasement());
	    DTO.setManagerName(entity.getManagerName());
	    DTO.setManagerPhoneNumber(entity.getManagerPhoneNumber());
	    DTO.setFloorArea(entity.getFloorArea());
	    DTO.setRentArea(convertRentAreasToString(rentAreaRepository.findRentAreaByBuildingID(entity.getId()))); // Assuming entity.getId() returns building ID
	    DTO.setBrokerageFee(String.format("%.1f", entity.getBrokerageFee()));

	    return DTO;
	}

}

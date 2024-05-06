package com.javaweb.converter;

import java.util.List;
import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.DTO.BuildingDTO;

@Component
public class BuildingConverter {
	
	@Autowired
	private ModelMapper modelMapper;

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
	    BuildingDTO DTO = modelMapper.map(entity, BuildingDTO.class);
	    DTO.setAddress(entity.getStreet() + ", "+ entity.getWard() + ", " + entity.getDistrict().getName()); // street, ward, district name
	    DTO.setRentArea(convertRentAreasToString(entity.getRentAreas()));		   
	    return DTO;
	}

}

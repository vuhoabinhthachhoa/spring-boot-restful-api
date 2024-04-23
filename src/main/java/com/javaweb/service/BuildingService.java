package com.javaweb.service;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import java.util.List;
import com.javaweb.DTO.BuildingDTO;

@Service
public interface BuildingService {
	public List<BuildingDTO> findBuildings(String iName, String iStreet, String iWard, String iDistrictName,
			Long iNumberOfBasement, Long iFloorArea, String iDirection, String iLevel, Pair<Long, Long> iRentPrice, 
			String iManagerName, String iManagerPhoneNumber, Long iStaffID, Pair<Long, Long> iRentArea, List<String> iRentTypeName);
}

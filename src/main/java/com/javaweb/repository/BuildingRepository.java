package com.javaweb.repository;

import com.javaweb.repository.entity.BuildingEntity;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository {
	public List<BuildingEntity> findBuildings(String iName, String iStreet, String iWard, String iDistrictName,
			Long iNumberOfBasement, Long iFloorArea, String iDirection, String iLevel, Pair<Long, Long> iRentPrice, 
			String iManagerName, String iManagerPhoneNumber, Long iStaffID, Pair<Long, Long> iRentArea,
			List<String> iRentTypeName);
}

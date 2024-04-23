package com.javaweb.repository;

import com.javaweb.repository.entity.RentAreaEntity;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface RentAreaRepository {
	public List<RentAreaEntity> findRentAreaByBuildingID(Long iBuildingID);
}

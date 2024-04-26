package com.javaweb.repository;

import com.javaweb.repository.entity.BuildingEntity;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository {
	public List<BuildingEntity> findBuildings(Map<String, Object> params, List<String> typeCode);
}

package com.javaweb.repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.entity.BuildingEntity;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository {
	public List<BuildingEntity> findBuildings(BuildingSearchBuilder builder);
}

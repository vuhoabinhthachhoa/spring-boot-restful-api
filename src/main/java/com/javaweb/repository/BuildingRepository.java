package com.javaweb.repository;

import com.javaweb.repository.entity.BuildingEntity;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository {
	List<BuildingEntity> findAll(String iname, Long inumberOfBasement);
}

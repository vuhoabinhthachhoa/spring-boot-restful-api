package com.javaweb.repository;


import org.springframework.stereotype.Repository;
import com.javaweb.repository.entity.DistrictEntity;

@Repository
public interface DistrictRepository {
	public DistrictEntity findDistrictByID(Long iDistrictID);
}

package com.javaweb.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.javaweb.DTO.BuildingDTO;

@Service
public interface BuildingService {
	public List<BuildingDTO> findAll(String iname, Long inumberOfBasement);
}

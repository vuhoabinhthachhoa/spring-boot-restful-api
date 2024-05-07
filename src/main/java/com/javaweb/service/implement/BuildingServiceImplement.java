package com.javaweb.service.implement;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.service.BuildingService;
import com.javaweb.utils.customExceptions.BuildingNotFoundException;
import com.javaweb.utils.customExceptions.DistrictNotFoundException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.DTO.BuildingDTO;
import com.javaweb.DTO.Response;
import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;


@Service
public class BuildingServiceImplement implements BuildingService {
	
	@Autowired
	private BuildingRepository buidldingRepository;
	@Autowired
	private BuildingConverter buildingConverter;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Response> findBuildings(Map<String, Object> params, List<String> typeCode) {
		BuildingSearchBuilder builder = BuildingSearchBuilderConverter.toBuildingSearchBuilder(params, typeCode);
		List<BuildingEntity> receivedData = buidldingRepository.findBuildings(builder); 
		
		List<Response> result = new ArrayList<Response>();
		for(BuildingEntity it : receivedData) { 
		    result.add(buildingConverter.BuildingEntityToBuildingDTO(it));
		}
		return result;
	}
	
	@Override
	@Transactional
	public void createBuilding(BuildingDTO buildingDTO) {
		DistrictEntity districtEntity = entityManager.find(DistrictEntity.class, buildingDTO.getDistrictId());
		
		if (districtEntity == null) {
		    throw new DistrictNotFoundException("District with id " + buildingDTO.getDistrictId() + " does not exist");
		}
		
		BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
		buildingEntity.setDistrict(districtEntity);
		entityManager.persist(buildingEntity);
	}
	
	@Override
	@Transactional
	public void updateBuilding(BuildingDTO buildingDTO) {
		BuildingEntity buildingEntity = entityManager.find(BuildingEntity.class, buildingDTO.getId());
		if(buildingEntity == null) {
			throw new BuildingNotFoundException("Building with id " + buildingDTO.getId() + " does not exist");
		}
		
		DistrictEntity districtEntity = entityManager.find(DistrictEntity.class, buildingDTO.getDistrictId());
		if (districtEntity == null) {
		    throw new DistrictNotFoundException("District with id " + buildingDTO.getDistrictId() + " does not exist");
		}
		
		modelMapper.map(buildingDTO, buildingEntity);
		buildingEntity.setDistrict(districtEntity);
		
		entityManager.merge(buildingEntity);
	}
	
	@Override
	@Transactional
	public void deleteBuilding(Long id) {
		BuildingEntity buildingEntity = entityManager.find(BuildingEntity.class, id);
		if(buildingEntity == null) {
			throw new BuildingNotFoundException("Building with id " + id + " does not exist");
		}
		entityManager.remove(buildingEntity);
	}
	
}

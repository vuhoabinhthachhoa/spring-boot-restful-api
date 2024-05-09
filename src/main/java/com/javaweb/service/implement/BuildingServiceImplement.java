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
import com.javaweb.repository.entity.RentAreaEntity;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.DTO.BuildingDTO;
import com.javaweb.DTO.Response;
import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;


@Service
public class BuildingServiceImplement implements BuildingService {
	
	@Autowired
	private BuildingRepository buildingRepository;
	
	@Autowired
	private BuildingConverter buildingConverter;
	
	@Autowired
	private RentAreaRepository rentAreaRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public List<Response> findBuildings(Map<String, Object> params, List<String> typeCode) {
		BuildingSearchBuilder builder = BuildingSearchBuilderConverter.toBuildingSearchBuilder(params, typeCode);
		List<BuildingEntity> receivedData = buildingRepository.findBuildings(builder); 
		
		List<Response> result = new ArrayList<Response>();
		for(BuildingEntity it : receivedData) { 
		    result.add(buildingConverter.BuildingEntityToBuildingDTO(it));
		}
		return result;
	}

	@Override
	public void createBuilding(BuildingDTO buildingDTO) {
		// we have to add building before adding corresponding rentareas and vice versa
		// delete rentareas after deleting building
		BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
		buildingRepository.save(buildingEntity); // save: if there is id in DTO, will update, else there is no id it  will create
		
		List<Long> rentAreas = buildingDTO.getRentAreas();
		if(rentAreas != null) {
			for(Long rentArea : rentAreas) {
				RentAreaEntity rentAreaEntity = new RentAreaEntity();
				rentAreaEntity.setBuilding(buildingEntity);
				rentAreaEntity.setValue(rentArea);
				rentAreaRepository.save(rentAreaEntity);
			}
		}
		
	}

	@Override
	public void deleteBuilding(Long[] ids) {
		for(Long id : ids) {
			BuildingEntity buildingEntity = buildingRepository.findById(id).get();
			rentAreaRepository.deleteAllByBuilding(buildingEntity);
			buildingRepository.deleteByIdIn(ids);
		}
		
	}

}
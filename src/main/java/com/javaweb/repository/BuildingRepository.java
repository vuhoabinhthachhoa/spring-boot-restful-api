package com.javaweb.repository;

import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.repository.entity.BuildingEntity;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// we use interface here to make easily to maintain and update in implementation
// another reason is interface allow multilevel inheritance whereas a normal class does not
@Repository
public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>, BuildingRepositoryCustom{
	public List<BuildingEntity> findByNameContaining(String name);
	public void deleteByIdIn(Long[] ids);
}

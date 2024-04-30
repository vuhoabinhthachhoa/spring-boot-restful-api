package com.javaweb.repository.impl;

import com.javaweb.repository.entity.RentAreaEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.javaweb.repository.RentAreaRepository;
import org.springframework.stereotype.Repository;
import com.javaweb.utils.GetDBConnectionUtil;
import com.javaweb.utils.IsExistingParamUtil;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository{
	@Override
	public List<RentAreaEntity> findRentAreaByBuildingID(Long iBuildingId) {
		String query = "SELECT* FROM rentarea";
		String conditions = " WHERE 1=1";

		if (IsExistingParamUtil.isExistingStringParam(iBuildingId.toString())) {
			conditions += " AND buildingid = " + iBuildingId;
		}
		query += conditions;
		
		
		List<RentAreaEntity> rentAreas = new ArrayList<>();
		 try (Connection conn = GetDBConnectionUtil.getConnection(); 
	        		Statement stm = conn.createStatement(); 
	        		ResultSet rs = stm.executeQuery(query.toString())) {
			
			while(rs.next()) {
				RentAreaEntity rentArea = new RentAreaEntity();
				rentArea.setValue(rs.getLong("value"));
				rentAreas.add(rentArea);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}

		return rentAreas;
	}
}













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

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository{
	@Override
	public List<RentAreaEntity> findRentAreaByBuildingID(Long iBuildingID) {
		String query = "SELECT* FROM rentarea";
		String conditions = " WHERE 1=1";

		if (iBuildingID != null ) {
			conditions += " AND buildingid = " + iBuildingID;
		}
		query += conditions;
		
		Connection conn = GetDBConnectionUtil.getConnection();
		if(conn == null) {
			return null;
		}
		
		List<RentAreaEntity> rentAreas = new ArrayList<>();
		try(Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(query)) {
			
			while(rs.next()) {
				Long id = rs.getLong("id");
				Long value = rs.getLong("value");
				Long buildingid = rs.getLong("buildingid");
				
				rentAreas.add(new RentAreaEntity(id, value, buildingid));
			}
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection failed");
			return null;
		}

		return rentAreas;
	}
}













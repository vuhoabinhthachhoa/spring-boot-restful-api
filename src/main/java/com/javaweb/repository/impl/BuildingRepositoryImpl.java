package com.javaweb.repository.impl;

import com.javaweb.repository.entity.BuildingEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.javaweb.repository.BuildingRepository;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Repository;
import com.javaweb.utils.GetDBConnectionUtil;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {

	@Override
	public List<BuildingEntity> findBuildings(String iName, String iStreet, String iWard, String iDistrictName,
			Long iNumberOfBasement, Long iFloorArea, String iDirection, String iLevel, Pair<Long, Long> iRentPrice, 
			String iManagerName, String iManagerPhoneNumber, Long iStaffID, Pair<Long, Long> iRentArea,
			List<String> iRentTypeName) {

		String query = 
				"SELECT* FROM Building bd "
				+ " LEFT JOIN assignmentbuilding ab ON bd.id = ab.buildingid"
				+ " LEFT JOIN rentarea ra ON bd.id = ra.buildingid";
		String conditions = " WHERE 1=1";

		if (iName != null && iName != "") {
			conditions += " AND bd.name LIKE '%" + iName + "%' ";
		}
		
		if (iStreet != null && iStreet != "") {
			conditions += " AND bd.street LIKE '%" + iStreet + "%' ";
		}
		
		if (iWard != null && iWard != "") {
			conditions += " AND bd.ward LIKE '%" + iWard + "%' ";
		}
		
		if (iDistrictName != null && iDistrictName != "") {
			conditions += " AND bd.districtid LIKE '%" + iDistrictName + "%' ";
		}

		if (iNumberOfBasement != null) {
			conditions += " AND bd.numberofbasement = " + iNumberOfBasement;
		}
		
		if (iFloorArea != null) {
			conditions += " AND bd.floorarea = " + iFloorArea;
		}
		
		if (iDirection != null && iDirection != "") {
			conditions += " AND bd.direction LIKE '%" + iDirection + "%' ";
		}
		
		if (iLevel != null && iLevel != "") {
			conditions += " AND bd.level LIKE '%" + iLevel + "%' ";
		}
		
		if (iRentPrice != null) {
			conditions += " AND bd.rentprice >= " + iRentPrice.getLeft() + " AND bd.rentprice <= "  + iRentPrice.getRight();
		}
		
		if (iManagerName != null && iManagerName != "") {
			conditions += " AND bd.managername LIKE '%" + iManagerName + "%' ";
		}
		
		if (iManagerPhoneNumber != null && iManagerPhoneNumber != "") {
			conditions += " AND bd.managerphonenumber LIKE '%" + iManagerPhoneNumber + "%' ";
		}
		
		if (iStaffID != null) {
			conditions += " AND ab.staffid = " + iStaffID;
		}
		
		if (iRentArea != null) {
			conditions += " AND ra.value >= " + iRentArea.getLeft() + " AND ra.value <= "  + iRentArea.getRight();
		}
		
		query += conditions;
		
		Connection conn = GetDBConnectionUtil.getConnection();
		if(conn == null) {
			return null;
		}
		
		List<BuildingEntity> buildings = new ArrayList<>();
		try(Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(query)) {
			
			while(rs.next()) {
				String buildingName = rs.getString("name");
				String street = rs.getString("street");
				String ward = rs.getString("ward");
				long districtId = rs.getLong("districtid");
				Long numberOfBasement = rs.getLong("numberofbasement");
				Long floorArea = rs.getLong("floorarea");
				String managerName = rs.getString("managername");
				String managerPhoneNumber = rs.getString("managerphonenumber");
				Double brokerageFee = rs.getDouble("brokeragefee");
				
				buildings.add(new BuildingEntity(buildingName, street, ward, districtId, numberOfBasement, floorArea, managerName,managerPhoneNumber, brokerageFee ));
			}
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection failed");
			return null;
		}

		return buildings;
	}
}

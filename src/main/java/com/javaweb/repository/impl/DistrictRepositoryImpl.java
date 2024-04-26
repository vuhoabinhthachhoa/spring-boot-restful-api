package com.javaweb.repository.impl;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.utils.GetDBConnectionUtil;
import com.javaweb.utils.IsExistingParamUtil;


@Repository
public class DistrictRepositoryImpl implements DistrictRepository{
	
	@Override
	public DistrictEntity findDistrictByID(Long iDistrictID) {
		String query = "SELECT* FROM District";
		String conditions = " WHERE 1=1";

		if (IsExistingParamUtil.isExistingStringParam(iDistrictID.toString())) {
			conditions += " AND id = " + iDistrictID ;
		}
		query += conditions;
		
		Connection conn = GetDBConnectionUtil.getConnection();
		if(conn == null) {
			return null;
		}
		
		
		try(Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(query)) {
			
			if(rs.next()) {
				Long id = rs.getLong("id");
				String code = rs.getString("code");
				String name = rs.getString("name");
				
				return new DistrictEntity(id, code, name);
			}

		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection failed");
		}
		
		return null;
	}
}




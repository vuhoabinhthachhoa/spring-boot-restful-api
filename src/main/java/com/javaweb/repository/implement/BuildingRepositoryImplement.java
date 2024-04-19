package com.javaweb.repository.implement;

import com.javaweb.repository.entity.BuildingEntity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import org.springframework.stereotype.Repository;

@Repository
public class BuildingRepositoryImplement implements BuildingRepository {

	static String DB_URL = "jdbc:mysql://localhost:3306/estatebasic";
	static String USER = "root";
	static String PASS = "Lecaotuanvu.2004_mysql";

	@Override
	public List<BuildingEntity> findAll(String iname, Long inumberOfBasement) {

		String query = "SELECT* FROM Building";
		String conditions = " WHERE 1=1";

		if (iname != null && iname != "") {
			conditions += " AND name LIKE '%" + iname + "%' ";
		}

		if (inumberOfBasement != null) {
			conditions += " AND numberofbasement = " + inumberOfBasement;
		}

		query += conditions;

		List<BuildingEntity> buildings = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(query)) {

			while (rs.next()) {
				String buildingName = rs.getString("name");
				String street = rs.getString("street");
				String ward = rs.getString("ward");
				long districtId = rs.getLong("districtid");
				Long numberOfBasement = rs.getLong("numberofbasement");
				String managerName = rs.getString("managername");
				String managerPhoneNumber = rs.getString("managerphonenumber");

				BuildingEntity building = new BuildingEntity(buildingName, street, ward, districtId, numberOfBasement,
						managerName, managerPhoneNumber);
				buildings.add(building);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection failed");
		}

		return buildings;
	}
}

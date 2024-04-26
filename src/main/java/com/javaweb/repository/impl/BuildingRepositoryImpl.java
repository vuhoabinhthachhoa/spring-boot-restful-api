package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.utils.GetDBConnectionUtil;
import com.javaweb.utils.IsExistingParamUtil;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {

	void joinWithTables(Map<String, Object> params, List<String> typeCode, StringBuilder query) {
		String rentAreaFrom = (String) params.get("rentareafrom");
		String rentAreaTo = (String) params.get("rentareato");
		String staffId = (String) params.get("staffid");

		if (IsExistingParamUtil.isExistingStringParam(rentAreaFrom)
				|| IsExistingParamUtil.isExistingStringParam(rentAreaTo)) {
			query.append(" JOIN rentarea ra ON ra.buildingid=b.id ");
		}
		if (IsExistingParamUtil.isExistingStringParam(staffId)) {
			query.append(" JOIN assignmentbuilding ab ON ab.buildingid=b.id ");
		}
		if (IsExistingParamUtil.isExistingListParam(typeCode)) {
			query.append(" JOIN buildingrenttype brt ON b.id=brt.buildingid \n"
					+ "JOIN renttype rt ON rt.id=brt.renttypeid ");
		}

	}

	void selectConditions(Map<String, Object> params, List<String> typeCode, StringBuilder query) {
		query.append(" WHERE 1=1 ");

		for (Map.Entry<String, Object> it : params.entrySet()) {
			if (it.getKey().equals("rentareafrom")) {
				String value = (String) it.getValue();
				if (IsExistingParamUtil.isExistingStringParam(value)) {
					query.append(" AND ra.value >= " + value);
				}
			}
			if (it.getKey().equals("rentareato")) {
				String value = (String) it.getValue();
				if (IsExistingParamUtil.isExistingStringParam(value)) {
					query.append(" AND ra.value <= " + value);
				}
			}
			if (it.getKey().equals("staffid")) {
				String value = (String) it.getValue();
				if (IsExistingParamUtil.isExistingStringParam(value)) {
					query.append(" AND ab.staffid = " + value);
				}
			}
			if (it.getKey().equals("rentpricefrom")) {
				String value = (String) it.getValue();
				if (IsExistingParamUtil.isExistingStringParam(value)) {
					query.append(" AND b.rentprice >= " + value);
				}
			}
			if (it.getKey().equals("rentpriceto")) {
				String value = (String) it.getValue();
				if (IsExistingParamUtil.isExistingStringParam(value)) {
					query.append(" AND b.rentprice <= " + value);
				}
			}


			if (!it.getKey().equals("rentareafrom") && !it.getKey().equals("rentareato")
					&& !it.getKey().equals("staffid") && !it.getKey().equals("rentpricefrom")
					&& !it.getKey().equals("rentpriceto") && !it.getKey().equals("typecode")) {
				String value = (String) it.getValue();
				if (IsExistingParamUtil.isExistingStringParam(value)) {
					if (value.matches("\\d+")) {
						query.append(" AND b." + it.getKey() + " = " + value);
					} else {
						query.append(" AND b." + it.getKey() + " LIKE '%" + value + "%'");
					}
				}
			}
		}

		if (IsExistingParamUtil.isExistingListParam(typeCode)) {
			StringJoiner codeNames = new StringJoiner(", ");
			for (String codeName : typeCode) {
				codeNames.add("N'" + codeName + "'");
			}

			query.append(" AND rt.name IN (" + codeNames.toString() + ")");
		}
	}

	@Override
	public List<BuildingEntity> findBuildings(Map<String, Object> params, List<String> typeCode) {

		StringBuilder query = new StringBuilder();
		query.append("SELECT DISTINCT b.* FROM building b");

		joinWithTables(params, typeCode, query);
		selectConditions(params, typeCode, query);

		Connection conn = GetDBConnectionUtil.getConnection();
		if (conn == null) {
			return null;
		}

		String sql = query.toString();
		System.out.println(sql);

		List<BuildingEntity> buildings = new ArrayList<>();
		try (Statement stm = conn.createStatement(); ResultSet rs = stm.executeQuery(sql)) {

			while (rs.next()) {
				String buildingName = rs.getString("name");
				String street = rs.getString("street");
				String ward = rs.getString("ward");
				long districtId = rs.getLong("districtid");
				Long numberOfBasement = rs.getLong("numberofbasement");
				Long floorArea = rs.getLong("floorarea");
				String managerName = rs.getString("managername");
				String managerPhoneNumber = rs.getString("managerphonenumber");
				Double brokerageFee = rs.getDouble("brokeragefee");

				buildings.add(new BuildingEntity(buildingName, street, ward, districtId, numberOfBasement, floorArea,
						managerName, managerPhoneNumber, brokerageFee));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Can not excute query!");
			return null;
		}

		return buildings;
	}
}

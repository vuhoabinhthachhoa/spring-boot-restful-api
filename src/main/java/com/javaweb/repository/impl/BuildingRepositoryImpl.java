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
        String rentAreaFrom = (String) params.get("rentAreaFrom");
        String rentAreaTo = (String) params.get("rentAreaTo");
        String staffId = (String) params.get("staffId");

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

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey();
            Object valueObj = entry.getValue();

            if (key.equals("rentAreaFrom")) {
                String value = (String) valueObj;
                if (IsExistingParamUtil.isExistingStringParam(value)) {
                    query.append(" AND ra.value >= " + value);
                }
            }
            if (key.equals("rentAreaTo")) {
                String value = (String) valueObj;
                if (IsExistingParamUtil.isExistingStringParam(value)) {
                    query.append(" AND ra.value <= " + value);
                }
            }
            if (key.equals("staffId")) {
                String value = (String) valueObj;
                if (IsExistingParamUtil.isExistingStringParam(value)) {
                    query.append(" AND ab.staffid = " + value);
                }
            }
            if (key.equals("rentPriceFrom")) {
                String value = (String) valueObj;
                if (IsExistingParamUtil.isExistingStringParam(value)) {
                    query.append(" AND b.rentprice >= " + value);
                }
            }
            if (key.equals("rentPriceTo")) {
                String value = (String) valueObj;
                if (IsExistingParamUtil.isExistingStringParam(value)) {
                    query.append(" AND b.rentprice <= " + value);
                }
            }

            if (!key.equals("rentAreaFrom") && !key.equals("rentAreaTo")
                    && !key.equals("staffId") && !key.equals("rentPriceFrom")
                    && !key.equals("rentPriceTo") && !key.equals("typeCode")) {
                String value = (String) valueObj;
                if (IsExistingParamUtil.isExistingStringParam(value)) {
                    if (value.matches("\\d+")) {
                        query.append(" AND b." + key.toLowerCase() + " = " + value);
                    } else {
                        query.append(" AND b." + key.toLowerCase() + " LIKE '%" + value + "%'");
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
        
        // check query
        System.out.println(query.toString());

        List<BuildingEntity> buildings = new ArrayList<>();
        try (Connection conn = GetDBConnectionUtil.getConnection(); 
        		Statement stm = conn.createStatement(); 
        		ResultSet rs = stm.executeQuery(query.toString())) {

        	 while (rs.next()) {
                 BuildingEntity buildingEntity = new BuildingEntity(); // Create an instance

                 // Set properties
                 buildingEntity.setName(rs.getString("name"));
                 buildingEntity.setStreet(rs.getString("street"));
                 buildingEntity.setWard(rs.getString("ward"));
                 buildingEntity.setDistrictId(rs.getLong("districtid"));
                 buildingEntity.setNumberOfBasement(rs.getLong("numberofbasement"));
                 buildingEntity.setFloorArea(rs.getLong("floorarea"));
                 buildingEntity.setManagerName(rs.getString("managername"));
                 buildingEntity.setManagerPhoneNumber(rs.getString("managerphonenumber"));
                 buildingEntity.setBrokerageFee(rs.getDouble("brokeragefee"));

                 buildings.add(buildingEntity);
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return buildings;
    }
}

package com.javaweb.repository.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.utils.GetDBConnectionUtil;
import com.javaweb.utils.IsExistingParamUtil;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository {

    void joinWithTables(BuildingSearchBuilder builder, StringBuilder query) {
    	Object obj;
        obj = builder.getRentAreaFrom();
        String rentAreaFrom = (String) obj;
        obj = builder.getRentAreaTo();
        String rentAreaTo = (String) obj;
        obj = builder.getStaffId();
        String staffId = (String) obj;

        if (IsExistingParamUtil.isExistingStringParam(rentAreaFrom)
                || IsExistingParamUtil.isExistingStringParam(rentAreaTo)) {
            query.append(" JOIN rentarea ra ON ra.buildingid=b.id ");
        }
        if (IsExistingParamUtil.isExistingStringParam(staffId)) {
            query.append(" JOIN assignmentbuilding ab ON ab.buildingid=b.id ");
        }
        if (IsExistingParamUtil.isExistingListParam(builder.getTypeCode())) {
            query.append(" JOIN buildingrenttype brt ON b.id=brt.buildingid \n"
                    + "JOIN renttype rt ON rt.id=brt.renttypeid ");
        }

    }

    void selectConditions(BuildingSearchBuilder builder, StringBuilder query) {
        query.append(" WHERE 1=1 ");
        
        try {
        	Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
        	for(Field item : fields) {
        		item.setAccessible(true);
        		String fieldName = item.getName();
        		Object obj = item.get(builder);
        		String key = item.getName();
        		
        		 if (key.equals("rentAreaFrom")) {
        		     String value = (String)obj;        			 
                     if (IsExistingParamUtil.isExistingStringParam(value)) {
                         query.append(" AND ra.value >= " + value);
                     }
                 }
                 if (key.equals("rentAreaTo")) {
        		     String value = (String)obj;
                     if (IsExistingParamUtil.isExistingStringParam(value)) {
                         query.append(" AND ra.value <= " + value);
                     }
                 }
                 if (key.equals("staffId")) {
                     String value = (String) obj;
                     if (IsExistingParamUtil.isExistingStringParam(value)) {
                         query.append(" AND ab.staffid = " + value);
                     }
                 }
                 if (key.equals("rentPriceFrom")) {
                     String value = (String) obj;
                     if (IsExistingParamUtil.isExistingStringParam(value)) {
                         query.append(" AND b.rentprice >= " + value);
                     }
                 }
                 if (key.equals("rentPriceTo")) {
                     String value = (String) obj;
                     if (IsExistingParamUtil.isExistingStringParam(value)) {
                         query.append(" AND b.rentprice <= " + value);
                     }
                 }

                 if (!key.equals("rentAreaFrom") && !key.equals("rentAreaTo")
                         && !key.equals("staffId") && !key.equals("rentPriceFrom")
                         && !key.equals("rentPriceTo") && !key.equals("typeCode")) {
                     String value = (String) obj;
                     if (IsExistingParamUtil.isExistingStringParam(value)) {
                         if (value.matches("\\d+")) {
                             query.append(" AND b." + key.toLowerCase() + " = " + value);
                         } else {
                             query.append(" AND b." + key.toLowerCase() + " LIKE '%" + value + "%'");
                         }
                     }
                 }
                 
                 if(key.equals("typeCode")) {
                	 List<String> typeCode = (List<String>) obj;
            		 if (IsExistingParamUtil.isExistingListParam(typeCode)) {
                         StringJoiner codeNames = new StringJoiner(", ");
                         for (String codeName : typeCode) {
                             codeNames.add("N'" + codeName + "'");
                         }

                         query.append(" AND rt.name IN (" + codeNames.toString() + ")");
                     }
            	 }
                
             }	
        	
        }
        catch(Exception ex) {
        	ex.printStackTrace();
        }

    }

    @Override
    public List<BuildingEntity> findBuildings(BuildingSearchBuilder builder) {

        StringBuilder query = new StringBuilder();
        query.append("SELECT DISTINCT b.* FROM building b");

        joinWithTables(builder, query);
        selectConditions(builder, query);
        
        // check query
        System.out.println(query.toString());

        List<BuildingEntity> buildings = new ArrayList<>();
        try (Connection conn = GetDBConnectionUtil.getConnection(); 
        		Statement stm = conn.createStatement(); 
        		ResultSet rs = stm.executeQuery(query.toString())) {

        	 while (rs.next()) {
                 BuildingEntity buildingEntity = new BuildingEntity(); // Create an instance

                 // Set properties
                 buildingEntity.setId(rs.getLong("id"));
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

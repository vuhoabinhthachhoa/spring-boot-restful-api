package com.javaweb.converter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.utils.MapUtil;

@Component
public class BuildingSearchBuilderConverter {
    public static BuildingSearchBuilder toBuildingSearchBuilder(Map<String, Object> params, List<String> typeCode) {
    	
    	// another way
//    	BuildingSearchBuilder.Builder builder1 = new BuildingSearchBuilder.Builder();
//    	BuildingSearchBuilder builder = builder1.setName(), ...
    	
        BuildingSearchBuilder builder = new BuildingSearchBuilder.Builder()
        		.setName(MapUtil.getObject(params, "name", String.class))
                .setStreet(MapUtil.getObject(params, "street", String.class))
                .setWard(MapUtil.getObject(params, "ward", String.class))
                .setDistrictName(MapUtil.getObject(params, "districtName", String.class))
                .setNumberOfBasement(MapUtil.getObject(params, "numberOfBasement", Long.class))
                .setFloorArea(MapUtil.getObject(params, "floorArea", Long.class))
                .setDirection(MapUtil.getObject(params, "direction", String.class))
                .setLevel(MapUtil.getObject(params, "level", String.class))
                .setRentPriceFrom(MapUtil.getObject(params, "rentPriceFrom", Long.class))
                .setRentPriceTo(MapUtil.getObject(params, "rentPriceTo", Long.class))
                .setManagerName(MapUtil.getObject(params, "managerName", String.class))
                .setManagerPhoneNumber(MapUtil.getObject(params, "managerPhoneNumber", String.class))
                .setStaffId(MapUtil.getObject(params, "staffId", Long.class))
                .setRentAreaFrom(MapUtil.getObject(params, "rentAreaFrom", Long.class))
                .setRentAreaTo(MapUtil.getObject(params, "rentAreaTo", Long.class))
                .setTypeCode(typeCode)
                .build();
        return builder;
    }
}

package com.javaweb.repository.entity;

public class RentAreaEntity {
	private Long id;
	private Long value;
	private Long buildingID;
	
	public RentAreaEntity() {}
	
	public RentAreaEntity(Long id, Long value, Long buildingID) {
		super();
		this.id = id;
		this.value = value;
		this.buildingID = buildingID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public Long getBuildingID() {
		return buildingID;
	}

	public void setBuildingID(Long buildingID) {
		this.buildingID = buildingID;
	}
	
	
}
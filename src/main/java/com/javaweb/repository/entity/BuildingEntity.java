package com.javaweb.repository.entity;

public class BuildingEntity {
	private String name;
	private String street;
	private String ward;
	private Long districtId;
	private Long numberOfBasement;
	private Long floorArea;
	private String managerName;
	private String managerPhoneNumber;
	private double brokerageFee;
	
	public BuildingEntity(String name, String street, String ward, Long districtId, Long numberOfBasement,
			Long floorArea, String managerName, String managerPhoneNumber, double brokerageFee) {
		super();
		this.name = name;
		this.street = street;
		this.ward = ward;
		this.districtId = districtId;
		this.numberOfBasement = numberOfBasement;
		this.floorArea = floorArea;
		this.managerName = managerName;
		this.managerPhoneNumber = managerPhoneNumber;
		this.brokerageFee = brokerageFee;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public Long getNumberOfBasement() {
		return numberOfBasement;
	}

	public void setNumberOfBasement(Long numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}

	public Long getFloorArea() {
		return floorArea;
	}

	public void setFloorArea(Long floorArea) {
		this.floorArea = floorArea;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getManagerPhoneNumber() {
		return managerPhoneNumber;
	}

	public void setManagerPhoneNumber(String managerPhoneNumber) {
		this.managerPhoneNumber = managerPhoneNumber;
	}
	
	public double getBrokerageFee() {
		return brokerageFee;
	}

	public void setManagerPhoneNumber(double brokerageFee) {
		this.brokerageFee = brokerageFee;
	}
	
	
}



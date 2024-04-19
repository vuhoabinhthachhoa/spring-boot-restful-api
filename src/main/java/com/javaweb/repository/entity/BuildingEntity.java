package com.javaweb.repository.entity;

public class BuildingEntity {
	private String name;
	private String street;
	private String ward;
	private Long districtId;
	private Long numberOfBasement;
	private String managerName;
	private String managerPhoneNumber;
	
	
	public BuildingEntity(String name, String street, String ward, Long districtId, Long numberOfBasement,
			String managerName, String managerPhoneNumber) {
		super();
		this.name = name;
		this.street = street;
		this.ward = ward;
		this.districtId = districtId;
		this.numberOfBasement = numberOfBasement;
		this.managerName = managerName;
		this.managerPhoneNumber = managerPhoneNumber;
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
	public void setStreet(String Street) {
		this.street = Street;
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
	public Long getNumberOfBaseMent() {
		return numberOfBasement;
	}
	public void setNumberOfBaseMent(Long numberOfBaseMent) {
		this.numberOfBasement = numberOfBaseMent;
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
	
	
}

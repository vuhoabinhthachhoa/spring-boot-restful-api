package com.javaweb.DTO;

public class BuildingDTO {
	 	private String name;
	    private String address; // street, ward, district name
	    private Long numberOfBasement;
	    private String managerName;
	    private String managerPhoneNumber;
	    private Long floorArea;
	    private String rentArea; // Ex: 200, 300, 400
	    private String brokerageFee; // Ex: 2.0 (1 precision)
	    
	    public BuildingDTO() {}
 	    
		public BuildingDTO(String name, String address, Long numberOfBasement, String managerName,
				String managerPhoneNumber, Long floorArea, String rentArea, String brokerageFee) {
			super();
			this.name = name;
			this.address = address;
			this.numberOfBasement = numberOfBasement;
			this.managerName = managerName;
			this.managerPhoneNumber = managerPhoneNumber;
			this.floorArea = floorArea;
			this.rentArea = rentArea;
			this.brokerageFee = brokerageFee;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public Long getNumberOfBasement() {
			return numberOfBasement;
		}

		public void setNumberOfBasement(Long numberOfBasement) {
			this.numberOfBasement = numberOfBasement;
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

		public Long getFloorArea() {
			return floorArea;
		}

		public void setFloorArea(Long floorArea) {
			this.floorArea = floorArea;
		}

		public String getRentArea() {
			return rentArea;
		}

		public void setRentArea(String rentArea) {
			this.rentArea = rentArea;
		}

		public String getBrokerageFee() {
			return brokerageFee;
		}

		public void setBrokerageFee(String brokerageFee) {
			this.brokerageFee = brokerageFee;
		}

	    
	    
	    
}

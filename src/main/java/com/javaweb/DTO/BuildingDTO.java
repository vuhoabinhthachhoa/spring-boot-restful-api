package com.javaweb.DTO;

public class BuildingDTO {
	 	private String name;
	    private String address; // street, ward, district name
	    private String managerName;

	    // Constructors
	    public BuildingDTO() {
	    }

		public BuildingDTO(String name, String address, String managerName) {
			super();
			this.name = name;
			this.address = address;
			this.managerName = managerName;
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

		public String getManagerName() {
			return managerName;
		}

		public void setManagerName(String managerName) {
			this.managerName = managerName;
		}

	    
}

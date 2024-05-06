package com.javaweb.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuildingDTO {
	 	private String name;
	    private String address; // street, ward, district name
	    private Long numberOfBasement;
	    private String managerName;
	    private String managerPhoneNumber;
	    private Long floorArea;
	    private String rentArea; // Ex: 200, 300, 400
	    private Double brokerageFee; // Ex: 2.0 (1 precision)
	    
}

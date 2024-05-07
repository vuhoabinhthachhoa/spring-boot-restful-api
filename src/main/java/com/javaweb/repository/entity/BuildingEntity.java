package com.javaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "building")
public class BuildingEntity {
	// Notes: The data type of each fields has to map with data type in database:
	// - char, nvarchar, text -> Long
	// - int -> Integer
	// - so on, ...
	
	@Id // indicate this field is the primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto generate
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "ward")
	private String ward;
	
	@Column(name = "numberofbasement")
	private Long numberOfBasement;
	
	@Column(name = "floorarea")
	private Long floorArea;
	
	@Column(name = "rentprice")
	private Long rentPrice;
	
	@Column(name = "managername")
	private String managerName;
	
	@Column(name = "managerphonenumber")
	private String managerPhoneNumber;
	
	@Column(name = "brokeragefee")
	private Double brokerageFee;
	
	
	// mappedBy has to map to this 
	//	@ManyToOne
	//	@JoinColumn(name = "buildingid")
	//	private BuildingEntity building;
	@OneToMany(mappedBy = "building", fetch = FetchType.LAZY)
	private List<RentAreaEntity> rentAreas = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="districtid")
	private DistrictEntity district;
}



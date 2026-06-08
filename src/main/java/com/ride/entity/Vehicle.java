package com.ride.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.ride.enums.VehicleType;

import javax.validation.constraints.Pattern;
@Entity
public class Vehicle {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	private String name;
	@Enumerated(EnumType.STRING)
	private VehicleType type;
	@Pattern(regexp = "^[A-Z]{2}[0-9]{2}[A-Z]{2}[0-9]{4}$")
	private String vahicalNo;
	
	 @OneToOne(mappedBy = "vehicle")
	    private Driver driver;
	 
	 public int getId() {
		 return id;
	 }

	 public void setId(int id) {
		 this.id = id;
	 }

	 public String getName() {
		 return name;
	 }

	 public void setName(String name) {
		 this.name = name;
	 }

	 

	 public VehicleType getType() {
		return type;
	}

	 public void setType(VehicleType type) {
		 this.type = type;
	 }

	 public String getVahicalNo() {
		 return vahicalNo;
	 }

	 public void setVahicalNo(String vahicalNo) {
		 this.vahicalNo = vahicalNo;
	 }

	 public Driver getDriver() {
		 return driver;
	 }

	 public void setDriver(Driver driver) {
		 this.driver = driver;
	 }
	 
	 
	 

}

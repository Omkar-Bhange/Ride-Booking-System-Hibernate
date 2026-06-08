package com.ride.entity;

import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.ride.enums.DriverStatus;

import javax.validation.constraints.Email;
@Entity
public class Driver {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id ;
	private String name;
	@Email
	private String email;
	private String phoNo;
	
	private double langitude;
	private double latitude;
	@Enumerated(EnumType.STRING)
	private DriverStatus driverStatus;
	
	  @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "vehicle_id")
	    private Vehicle vehicle;
	  
	  
	  @OneToMany(mappedBy = "driver")
	  private List<Ride> rides;


	  
	  
	  public DriverStatus getDriverStatus() {
		return driverStatus;
	}

	  
	  

	  public double getLangitude() {
		return langitude;
	}




	  public void setLangitude(double langitude) {
		  this.langitude = langitude;
	  }



 




	  public double getLatitude() {
		return latitude;
	}




	  public void setLatitude(double latitude) {
		  this.latitude = latitude;
	  }




	  public void setDriverStatus(DriverStatus driverStatus) {
		  this.driverStatus = driverStatus;
	  }


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


	  public String getEmail() {
		  return email;
	  }


	  public void setEmail(String email) {
		  this.email = email;
	  }


	  public String getPhoNo() {
		  return phoNo;
	  }


	  public void setPhoNo(String phoNo) {
		  this.phoNo = phoNo;
	  }


	  public Vehicle getVehicle() {
		  return vehicle;
	  }


	  public void setVehicle(Vehicle vehicle) {
		  this.vehicle = vehicle;
	  }


	  public List<Ride> getRides() {
		  return rides;
	  }


	  public void setRides(List<Ride> rides) {
		  this.rides = rides;
	  }
	  
	  
	
	

}

package com.ride.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.PositiveOrZero;

import com.ride.enums.RideStatus;
@Entity
public class Ride {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	private String source;
	private String destination;
	@PositiveOrZero(message = "cant negative ")
	private double fare;
	@Enumerated(EnumType.STRING)
	private RideStatus status;
	
	
	 @ManyToOne
	    @JoinColumn(name = "user_id")
	    private User user;

	    @ManyToOne
	    @JoinColumn(name = "driver_id")
	    private Driver driver;
	    
	    @OneToOne(mappedBy = "ride")
	    private Transaction transaction;
	    
	    

		public Transaction getTransaction() {
			return transaction;
		}

		public void setTransaction(Transaction transaction) {
			this.transaction = transaction;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getSource() {
			return source;
		}

		public void setSource(String source) {
			this.source = source;
		}

		public String getDestination() {
			return destination;
		}

		public void setDestination(String destination) {
			this.destination = destination;
		}

		public double getFare() {
			return fare;
		}

		public void setFare(double fare) {
			this.fare = fare;
		}

		 

		public RideStatus getStatus() {
			return status;
		}

		public void setStatus(RideStatus status) {
			this.status = status;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Driver getDriver() {
			return driver;
		}

		public void setDriver(Driver driver) {
			this.driver = driver;
		}
	    
	    
	
	

}

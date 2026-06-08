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

import org.hibernate.tool.schema.TargetType;

//import com.ride.enums.TransactionStatus;
import com.ride.enums.TransactionStatusRide;
import com.ride.enums.TransatctionType;

@Entity
public class Transaction {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	@Enumerated(EnumType.STRING)
	private TransatctionType type;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne
	@JoinColumn(name = "ride_id")
	private Ride ride;

	private TransactionStatusRide status;

	private double ammount;

	public double getAmmount() {
		return ammount;
	}

	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}

	public TransactionStatusRide getStatus() {
		return status;
	}

	public void setStatus(TransactionStatusRide status) {
		this.status = status;
	}

	public Ride getRide() {
		return ride;
	}

	public void setRide(Ride ride) {
		this.ride = ride;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TransatctionType getType() {
		return type;
	}

	public void setType(TransatctionType type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

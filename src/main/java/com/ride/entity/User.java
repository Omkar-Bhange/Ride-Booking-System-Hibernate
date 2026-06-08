package com.ride.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Entity
public class User {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	private String name;
	@Email
	private String email;
	@Pattern(regexp = "^[6-9]\\d{9}$")
	private String phoNo;
	
	private double langitude;
	private double latitude;
	
	private double wallet_balance;
	
	
	
	
	public double getWallet_balance() {
		return wallet_balance;
	}
	public void setWallet_balance(double wallet_balance) {
		this.wallet_balance = wallet_balance;
	}
	
	
	 @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	    private List<Ride> rides;
	 
	 @OneToMany(mappedBy = "user")
	 private List<Transaction> transactions;
	
	 
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
	public List<Ride> getRides() {
		return rides;
	}
	public void setRides(List<Ride> rides) {
		this.rides = rides;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	
	

}

package com.ride.dao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.resource.transaction.spi.TransactionStatus;

import com.ride.entity.Driver;
import com.ride.entity.Ride;
import com.ride.entity.Transaction;
import com.ride.entity.User;
import com.ride.enums.DriverStatus;
import com.ride.enums.RideStatus;
import com.ride.enums.TransactionStatusRide;
import com.ride.enums.TransatctionType;
import com.ride.enums.VehicleType;

public class RideDao {
	UserDao ud = new UserDao();
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
	EntityManager em = emf.createEntityManager();
	EntityTransaction transaction = em.getTransaction();
	Scanner sc = new Scanner(System.in);

	public void createRide() {

		System.out.println("Enter User Email:");
		String email = sc.nextLine();

		System.out.println("Enter Source:");
		String source = sc.nextLine();

		System.out.println("Enter Destination:");
		String destination = sc.nextLine();

		System.out.println("Enter Distance (KM):");
		double distance = sc.nextDouble();
		System.out.println("Enter The Driver email :");
		String emai = sc.next();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> query = cb.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root).where(cb.equal(root.get("email"), email));

		User user = em.createQuery(query).getSingleResult();

		CriteriaQuery<Driver> query2 = cb.createQuery(Driver.class);
		Root<Driver> root2 = query2.from(Driver.class);
		query2.select(root2).where(cb.and(cb.equal(root2.get("email"), emai),
				cb.equal(root2.get("driverStatus"), DriverStatus.AVAILABLE)));

		Driver driver = em.createQuery(query2).getSingleResult();

		Ride ride = new Ride();
		ride.setUser(user);
		ride.setDriver(driver);
		ride.setSource(source);
		ride.setDestination(destination);

		VehicleType type = ride.getDriver().getVehicle().getType();

		ride.setFare(calculateFare(distance, type));

		ride.setStatus(RideStatus.REQUESTED);

		transaction.begin();
		
		em.persist(ride);

		transaction.commit();
	 
		

		System.out.println("========== Ride Details ==========");

		System.out.println("Ride Id      : " + ride.getId());

		System.out.println("User Name    : " + ride.getUser().getName());

		System.out.println("Driver Name  : " + ride.getDriver().getName());

		System.out.println("Source       : " + ride.getSource());

		System.out.println("Destination  : " + ride.getDestination());

		System.out.println("Fare         : ₹" + ride.getFare());

		System.out.println("Status       : " + ride.getStatus());

		System.out.println("=================================");
		
		

	}

	public void acceptRide() {

		System.out.println("Enter Ride Id:");
		int rideId = sc.nextInt();

		Ride ride = em.find(Ride.class, rideId);

		if (ride == null) {
			System.out.println("Ride Not Found");
			return;
		}

		if (ride.getTransaction() != null) {
			System.out.println("Transaction Already Exists");
			return;
		}

		transaction.begin();

		// Accept / Start Ride
		ride.setStatus(RideStatus.STARTED);

		// Driver becomes busy
		ride.getDriver().setDriverStatus(DriverStatus.BUSY);

		// Create Transaction
		Transaction t = new Transaction();

		t.setRide(ride);
		t.setUser(ride.getUser());
		t.setAmmount(ride.getFare());
		t.setStatus(TransactionStatusRide.PENDING);

		System.out.println("Select Payment Method:");
		System.out.println("1. CASH");
		System.out.println("2. UPI");
		System.out.println("3. CARD");

		int choice = sc.nextInt();

		switch (choice) {

		case 1:
			t.setType(TransatctionType.CASH);
			break;

		case 2:
			t.setType(TransatctionType.UPI);
			break;

		case 3:
			t.setType(TransatctionType.CARDS);
			break;

		default:
			System.out.println("Invalid Payment Method");
			transaction.rollback();
			return;
		}

		em.persist(t);

		transaction.commit();

		System.out.println("Ride Accepted By ==> " + ride.getDriver().getName());
		System.out.println("Driver Phone No ==> " + ride.getDriver().getPhoNo());

		System.out.println("\n===== Transaction Created =====");
		System.out.println("Transaction Status : " + t.getStatus());
		System.out.println("Amount             : ₹" + t.getAmmount());
		System.out.println("Payment Type       : " + t.getType());
		System.out.println("===============================");
	}

	public void fechRideByUser() {
		System.out.println("Enter the user email user:");
		String email = sc.next();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Ride> query = cb.createQuery(Ride.class);
		Root<Ride> root = query.from(Ride.class);

		query.select(root).where(cb.equal(root.get("user").get("email"), email));

		List<Ride> rideList = em.createQuery(query).getResultList();

		for (Ride ride : rideList) {

			System.out.println("========== Ride Details ==========");

			System.out.println("Ride Id      : " + ride.getId());

			System.out.println("User Name    : " + ride.getUser().getName());

			System.out.println("Driver Name  : " + ride.getDriver().getName());

			System.out.println("Source       : " + ride.getSource());

			System.out.println("Destination  : " + ride.getDestination());

			System.out.println("Fare         : ₹" + ride.getFare());

			System.out.println("Status       : " + ride.getStatus());

			System.out.println("=================================");

		}

	}

	public void cancelRide() {

		System.out.println("Enter Ride Id:");
		int rideId = sc.nextInt();

		Ride ride = em.find(Ride.class, rideId);

		if (ride == null) {
			System.out.println("Ride Not Found");
			return;
		}

		if (ride.getStatus() == RideStatus.STARTED || ride.getStatus() == RideStatus.COMPLETED) {

			System.out.println("Ride Cannot Be Cancelled");
			return;
		}

		transaction.begin();

		ride.setStatus(RideStatus.CANCELLED);

		ride.getDriver().setDriverStatus(DriverStatus.AVAILABLE);

		transaction.commit();

		System.out.println("Ride Cancelled Successfully");
	}

	public void updateRide() {

		System.out.println("Enter the ride id :");
		int rideId = sc.nextInt();

		Ride ride = em.find(Ride.class, rideId);

		if (ride == null) {
			System.out.println("ride not found");
			return;
		}

		if (ride.getStatus() == RideStatus.ACCEPTED || ride.getStatus() == RideStatus.COMPLETED
				|| ride.getStatus() == RideStatus.STARTED) {
			System.out.println("Ride can not be Updated :");
			return;
		}

		System.out.println("What do you want to updte in ride ");
		System.out.println("1.Source");
		System.out.println("2.destination");

		int choice = sc.nextInt();

		transaction.begin();

		switch (choice) {

		case 1:
			sc.nextLine();
			System.out.println("Enter New Source:");
			ride.setSource(sc.nextLine());

			System.out.println("Enter Distance:");
			double distance = sc.nextDouble();

			ride.setFare(calculateFare(distance, ride.getDriver().getVehicle().getType()));
			break;

		case 2:
			sc.nextLine();
			System.out.println("Enter New Destination:");
			ride.setDestination(sc.nextLine());

			System.out.println("Enter Distance:");
			distance = sc.nextDouble();

			ride.setFare(calculateFare(distance, ride.getDriver().getVehicle().getType()));
			break;
		}

		transaction.commit();
	}

	public void getCancaledRides() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Ride> query = cb.createQuery(Ride.class);
		Root<Ride> root = query.from(Ride.class);

		query.select(root).where(cb.equal(root.get("status"), RideStatus.CANCELLED));

		List<Ride> cannceledList = em.createQuery(query).getResultList();

		for (Ride ride : cannceledList) {
			System.out.println("========== Ride Details ==========");

			System.out.println("Ride Id      : " + ride.getId());

			System.out.println("User Name    : " + ride.getUser().getName());

			System.out.println("Driver Name  : " + ride.getDriver().getName());

			System.out.println("Source       : " + ride.getSource());

			System.out.println("Destination  : " + ride.getDestination());

			System.out.println("Fare         : ₹" + ride.getFare());

			System.out.println("Status       : " + ride.getStatus());

			System.out.println("=================================");
		}

	}

	public void startRide() {

		System.out.println("Enter Ride Id:");
		int rideId = sc.nextInt();

		Ride ride = em.find(Ride.class, rideId);

		if (ride == null) {
			System.out.println("Ride Not Found");
			return;
		}

		if (ride.getStatus() != RideStatus.ACCEPTED) {
			System.out.println("Only ACCEPTED rides can be started");
			return;
		}

		transaction.begin();

		ride.setStatus(RideStatus.STARTED);

		transaction.commit();

		System.out.println("Ride Started Successfully");
	}

	public void completeRide() {

		System.out.println("Enter Ride Id:");
		int rideId = sc.nextInt();

		Ride ride = em.find(Ride.class, rideId);

		if (ride == null) {
			System.out.println("Ride Not Found");
			return;
		}
		
		
		if(ride.getTransaction()==null || ride.getTransaction().getStatus()==com.ride.enums.TransactionStatusRide.PENDING) {
			System.out.println("BEFORE COMPLETING RIDE YOU MUST COMPLETE YOUR PAYMENT !!!!!!!!!!!!!!!!!!!!");
			return;
		}
		transaction.begin();

		ride.setStatus(RideStatus.COMPLETED);

		ride.getDriver().setDriverStatus(DriverStatus.AVAILABLE);

		transaction.commit();

		System.out.println("Ride Completed Successfully");
		System.out.println("YOU HAVE BEEN ARRRIVED YOUR DESTINATION ::::=========>   " + ride.getDestination());
	}

	public void getRideByDriver() {
		System.out.println("Enter email of Driver:");
		String email = sc.next();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Ride> query = cb.createQuery(Ride.class);
		Root<Ride> root = query.from(Ride.class);
		query.select(root).where(cb.equal(root.get("driver").get("email"), email));

		List<Ride> rideList = em.createQuery(query).getResultList();

		for (Ride ride : rideList) {

			System.out.println("========== Ride Details ==========");

			System.out.println("Ride Id      : " + ride.getId());

			System.out.println("User Name    : " + ride.getUser().getName());

			System.out.println("Driver Name  : " + ride.getDriver().getName());

			System.out.println("Source       : " + ride.getSource());

			System.out.println("Destination  : " + ride.getDestination());

			System.out.println("Fare         : ₹" + ride.getFare());

			System.out.println("Status       : " + ride.getStatus());

			System.out.println("=================================");

		}

	}

	public void viewRideById() {

		System.out.println("Enter Ride Id:");
		int rideId = sc.nextInt();

		Ride ride = em.find(Ride.class, rideId);

		if (ride == null) {
			System.out.println("Ride Not Found");
			return;
		}

		System.out.println("========== Ride Details ==========");

		System.out.println("Ride Id      : " + ride.getId());

		System.out.println("User Name    : " + ride.getUser().getName());

		System.out.println("Driver Name  : " + ride.getDriver().getName());

		System.out.println("Source       : " + ride.getSource());

		System.out.println("Destination  : " + ride.getDestination());

		System.out.println("Fare         : ₹" + ride.getFare());

		System.out.println("Status       : " + ride.getStatus());

		System.out.println("=================================");
	}

	public void viewAllRides() {

		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Ride> query = cb.createQuery(Ride.class);

		Root<Ride> root = query.from(Ride.class);

		query.select(root);

		List<Ride> rides = em.createQuery(query).getResultList();

		if (rides.isEmpty()) {

			System.out.println("No Rides Found");
			return;
		}

		for (Ride ride : rides) {

			System.out.println("========== Ride Details ==========");

			System.out.println("Ride Id      : " + ride.getId());

			System.out.println("User Name    : " + ride.getUser().getName());

			System.out.println("Driver Name  : " + ride.getDriver().getName());

			System.out.println("Source       : " + ride.getSource());

			System.out.println("Destination  : " + ride.getDestination());

			System.out.println("Fare         : ₹" + ride.getFare());

			System.out.println("Status       : " + ride.getStatus());

			System.out.println("=================================");
		}
	}

	public double calculateFare(double distance, VehicleType type) {

		if (type == VehicleType.BIKE) {
			return distance * 15;
		} else if (type == VehicleType.AUTO) {
			return distance * 18;
		} else {
			return distance * 20;
		}
	}

}

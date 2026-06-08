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

import com.ride.entity.Driver;
import com.ride.entity.Ride;
import com.ride.entity.User;
import com.ride.enums.DriverStatus;

public class DriverDao {

	EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
	EntityManager em = emf.createEntityManager();
	EntityTransaction transaction = em.getTransaction();
	Scanner sc = new Scanner(System.in);

	public void createDriver() {
		Driver d = new Driver();

		sc.nextLine(); // consume newline

		System.out.println("Enter Driver Name :");
		d.setName(sc.nextLine());

		System.out.println("Enter Driver Email:");
		d.setEmail(sc.nextLine());

		System.out.println("Enter phone no Driver");
		d.setPhoNo(sc.nextLine());

		System.out.println("Enter Latitude:");
		double latitude = sc.nextDouble();
		d.setLatitude(latitude);
		System.out.println("Enter Longitude:");
		double longitude = sc.nextDouble();
		d.setLangitude(longitude);

		System.out.println("Enter Driver Status :");
		System.out.println("1.Avalable");
		System.out.println("2.Busy");
	 
		int choice = sc.nextInt();
		switch (choice) {
		case 1:	d.setDriverStatus(DriverStatus.AVAILABLE);

			break;

		case 2:d.setDriverStatus(DriverStatus.BUSY);

			break;
		 

		default:d.setDriverStatus(DriverStatus.OFFLINE);
			break;
		}

	

		transaction.begin();
		em.persist(d);
		transaction.commit();

		System.out.println("Driver Added Successfully");

	}

	public void updateDriver() {

		sc.nextLine();
		System.out.println("Enter Driver email to which you want to edit data :");
		String email = sc.nextLine();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Driver> query = cb.createQuery(Driver.class);
		Root<Driver> root = query.from(Driver.class);
		query.select(root).where(cb.equal(root.get("email"), email));

		Driver driver = em.createQuery(query).getSingleResult();

		if (driver != null) {

			System.out.println("1. Update Name");
			System.out.println("2. Update Phone");
			System.out.println("3. Update langitude");
			System.out.println("4. Update latitude");

			int choice = sc.nextInt();

			transaction.begin();

			switch (choice) {

			case 1:
				sc.nextLine();
				System.out.println("Enter New Name:");
				driver.setName(sc.nextLine());
				break;

			case 2:
				sc.nextLine();
				System.out.println("Enter New Phone:");
				driver.setPhoNo(sc.nextLine());
				break;

			case 3:
				System.out.println("Enter New langitude :");
				driver.setLangitude(sc.nextDouble());
				break;
			case 4:
				System.out.println("Enter New latitude:");
				driver.setLatitude(sc.nextDouble());
				break;
			}

			transaction.commit();

		} else {
			System.out.println("User not found...............................");

		}

	}

	public void getAllDriver() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Driver> query = cb.createQuery(Driver.class);
		Root<Driver> root = query.from(Driver.class);

		query.select(root);

		List<Driver> resultList = em.createQuery(query).getResultList();

		for (Driver driver : resultList) {
			System.out.println("++++++++++++++++++================++++++++++++++++++++++++");
			System.out.println("Driver Name :" + driver.getName());
			System.out.println("Driver email :" + driver.getEmail());
			System.out.println("Driver PhoNO :" + driver.getPhoNo());
			System.out.println("Driver Langitude :" + driver.getLangitude());
			System.out.println("Driver Latitude :" + driver.getLatitude());

			System.out.println("++++++++++++++++++================++++++++++++++++++++++++");
		}

	}

	public void deleteDriverByEmail() {

		sc.nextLine();
		System.out.println("Enter user email to which you want to edit data :");
		String email = sc.nextLine();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Driver> query = cb.createQuery(Driver.class);
		Root<Driver> root = query.from(Driver.class);
		query.select(root).where(cb.equal(root.get("email"), email));

		Driver driver = em.createQuery(query).getSingleResult();

		transaction.begin();

		em.remove(driver);

		transaction.commit();

		System.out.println("User Deleted Successfully");
	}

	public void viewDriverByEmail() {

		sc.nextLine();
		System.out.println("Enter user email to which you want to edit data :");
		String email = sc.nextLine();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Driver> query = cb.createQuery(Driver.class);
		Root<Driver> root = query.from(Driver.class);
		query.select(root).where(cb.equal(root.get("email"), email));

		Driver driver = em.createQuery(query).getSingleResult();

		System.out.println("++++++++++++++++++================++++++++++++++++++++++++");
		System.out.println("Driver Name :" + driver.getName());
		System.out.println("Driver email :" + driver.getEmail());
		System.out.println("Driver PhoNO :" + driver.getPhoNo());
		System.out.println("Driver Langitude :" + driver.getLangitude());
		System.out.println("Driver Latitude :" + driver.getLatitude());

		System.out.println("++++++++++++++++++================++++++++++++++++++++++++");

	}

	public void viewRequestedRides() {
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<Ride> query = cb.createQuery(Ride.class);
		Root<Ride> root = query.from(Ride.class);
		query.select(root);
		List<Ride> resultList = em.createQuery(query).getResultList();

		for (Ride ride : resultList) {
			System.out.println("Ride ID :" + ride.getId());
			System.out.println("Ride Name :" + ride.getUser().getName());
			System.out.println("Ride Source :" + ride.getSource());
			System.out.println("Ride Destination :" + ride.getDestination());
			System.out.println("Ride Fare :" + ride.getFare());
		}

	}

}

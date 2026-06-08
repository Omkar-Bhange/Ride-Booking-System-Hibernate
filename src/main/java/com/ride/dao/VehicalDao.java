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
import com.ride.entity.User;
import com.ride.entity.Vehicle;
import com.ride.enums.VehicleType;

public class VehicalDao {

	Scanner sc = new Scanner(System.in);
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
	EntityManager em = emf.createEntityManager();
	EntityTransaction transaction = em.getTransaction();

	public void assignVehical(String email) {

		sc.nextLine();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Driver> query = cb.createQuery(Driver.class);
		Root<Driver> root = query.from(Driver.class);
		query.select(root).where(cb.equal(root.get("email"), email));

		Driver driver = em.createQuery(query).getSingleResult();

		if (driver != null) {
			Vehicle v = new Vehicle();
			System.out.println("Enter the vehical no :");
			v.setVahicalNo(sc.next());
			System.out.println("Enter the vehical name :");
			v.setName(sc.next());

			
			System.out.println("Select Vehicle Type:");
			System.out.println("1. BIKE");
			System.out.println("2. AUTO");
			System.out.println("3. MINI");
			System.out.println("4. SEDAN");

			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				v.setType(VehicleType.BIKE);
				break;

			case 2:
				v.setType(VehicleType.AUTO);
				break;

			case 3:
				v.setType(VehicleType.MINI);
				break;

			case 4:
				v.setType(VehicleType.SEDAN);
				break;

			default:
				System.out.println("Invalid Vehicle Type");
				return;
			}
			driver.setVehicle(v);

			transaction.begin();

			driver.setVehicle(v);

			em.merge(driver);

			transaction.commit();

			System.out.println("Vehicle Assigned Successfully");
		} else {
			System.out.println("user not  found :");
		}

	}

	public void updateVehical() {

		sc.nextLine();
		System.out.println("Enter Vehical NO which you want to update :");
		String vno = sc.nextLine();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Vehicle> query = cb.createQuery(Vehicle.class);
		Root<Vehicle> root = query.from(Vehicle.class);
		query.select(root).where(cb.equal(root.get("vahicalNo"), vno));

		Vehicle v = em.createQuery(query).getSingleResult();

		System.out.println("1. Update Name");
		System.out.println("2. Update Vehicle Number");
		System.out.println("3. Update Vehicle Type");

		int choice = sc.nextInt();

		transaction.begin();

		switch (choice) {

		case 1:
			sc.nextLine();
			System.out.println("Enter New Name:");
			v.setName(sc.nextLine());
			break;

		case 2:
			sc.nextLine();
			System.out.println("Enter New Vehicle Number:");
			v.setVahicalNo(sc.nextLine());
			break;

		case 3:

			System.out.println("1. BIKE");
			System.out.println("2. AUTO");
			System.out.println("3. MINI");
			System.out.println("4. SEDAN");

			int type = sc.nextInt();

			switch (type) {

			case 1:
				v.setType(VehicleType.BIKE);
				break;

			case 2:
				v.setType(VehicleType.AUTO);
				break;

			case 3:
				v.setType(VehicleType.MINI);
				break;

			case 4:
				v.setType(VehicleType.SEDAN);
				break;

			default:
				System.out.println("Invalid Type");
				transaction.rollback();
				return;
			}
			break;

		default:
			System.out.println("Invalid Choice");
			transaction.rollback();
			return;
		}

		transaction.commit();

		System.out.println("Vehicle Updated Successfully");

	}

	public void viewByVNO() {

		sc.nextLine();
		System.out.println("Enter Vehical NO :");
		String vno = sc.nextLine();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Vehicle> query = cb.createQuery(Vehicle.class);
		Root<Vehicle> root = query.from(Vehicle.class);
		query.select(root).where(cb.equal(root.get("vahicalNo"), vno));

		Vehicle v = em.createQuery(query).getSingleResult();

		System.out.println("++++++++++++++++++================++++++++++++++++++++++++");
		System.out.println("User Name :" + v.getName());
		System.out.println("User Vehical NO :" + v.getVahicalNo());
		System.out.println("User Vehical Type :" + v.getType());
		if(v.getDriver() != null) {
		    System.out.println("Driver Name : " + v.getDriver().getName());
		} else {
		    System.out.println("Driver Name : Not Assigned");
		}
		System.out.println("++++++++++++++++++================++++++++++++++++++++++++");
	}
	
	
	public void viewAllVehicals() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Vehicle> query = cb.createQuery(Vehicle.class);
		Root<Vehicle> root = query.from(Vehicle.class);
		query.select(root);
		
		List<Vehicle> resultList = em.createQuery(query).getResultList();
		
		
		for (Vehicle v : resultList) {
			System.out.println("++++++++++++++++++================++++++++++++++++++++++++");
			System.out.println("User Name :" + v.getName());
			System.out.println("User Vehical NO :" + v.getVahicalNo());
			System.out.println("User Vehical Type :" + v.getType());
			
			if(v.getDriver() != null) {
			    System.out.println("Driver Name : " + v.getDriver().getName());
			} else {
			    System.out.println("Driver Name : Not Assigned");
			}
			System.out.println("++++++++++++++++++================++++++++++++++++++++++++");
			
			
		}
		
		
		
	}
	
	public void deleteVehicalById() {
		
		sc.nextLine();
		System.out.println("Enter Vehical NO :");
		String vno = sc.nextLine();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Vehicle> query = cb.createQuery(Vehicle.class);
		Root<Vehicle> root = query.from(Vehicle.class);
		query.select(root).where(cb.equal(root.get("vahicalNo"), vno));

		Vehicle v = em.createQuery(query).getSingleResult();
		
		transaction.begin();
		em.remove(v);
		transaction.commit();
		System.out.println("deleted sucefully .....................................");
		
	}

}

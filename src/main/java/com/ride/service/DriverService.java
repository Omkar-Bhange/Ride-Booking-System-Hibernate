package com.ride.service;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ride.dao.DriverDao;
import com.ride.entity.Driver;
import com.ride.entity.Vehicle;
import com.ride.enums.DriverStatus;
import com.ride.enums.VehicleType;

public class DriverService {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
	EntityManager em = emf.createEntityManager();
	EntityTransaction transaction = em.getTransaction();
	Scanner sc = new Scanner(System.in);
	
	DriverDao dd=new DriverDao();
	
	
	public void driverMenue() {
	
	while (true) {

		System.out.println("++++++++++++++++++++=============================+++++++++++++++++++++++++");
		System.out.println("1. Add Driver ");
		System.out.println("2. Update Driver ");
		System.out.println("3. View Driver By Id");
		System.out.println("4. View All Driver");
		System.out.println("5. Delete Driver");
//		System.out.println("6. Assign Vehical");
		System.out.println("6. Return ");
		System.out.println("!......  PRESS ANY INPUT..........!");
		System.out.println("++++++++++++++++++++=============================+++++++++++++++++++++++++");
		int choice = sc.nextInt();

		switch (choice) {
		case 1: dd.createDriver();

			break;

		case 2:dd.updateDriver();

			break;

		case 3:dd.viewDriverByEmail();

			break;

		case 4:dd.getAllDriver();

			break;

		case 5:dd.deleteDriverByEmail();

			break;

		case 6: return;
		 

		 

	}
	}
	}
	
	

}

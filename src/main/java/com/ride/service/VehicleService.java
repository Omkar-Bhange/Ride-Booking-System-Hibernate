package com.ride.service;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ride.dao.VehicalDao;
import com.ride.entity.Vehicle;
import com.ride.enums.VehicleType;


public class VehicleService {
	VehicalDao vd= new VehicalDao(); 
	Scanner sc = new Scanner(System.in);
	
	
	
	public void vehicalMenue() {
		
	
	while (true) {

		System.out.println("++++++++++++++++++++=============================+++++++++++++++++++++++++");
		System.out.println("1. Assign Vehicle ");
		System.out.println("2. Update Vehicle ");
		System.out.println("3. View Vehicle By no");
		System.out.println("4. View All Vehicles");
		System.out.println("5. Delete Vehicle");
		System.out.println("6. Return ");
		System.out.println("!......  PRESS ANY INPUT..........!");
		System.out.println("++++++++++++++++++++=============================+++++++++++++++++++++++++");
		int choice = sc.nextInt();

		switch (choice) {
		
		case 1: {System.out.println("Enter the Email of driver :"); 
				vd.assignVehical(sc.next());}

			break;

		case 2:vd.updateVehical();

			break;

		case 3: vd.viewByVNO();

			break;

		case 4: vd.viewAllVehicals();

			break;

		case 5:vd.deleteVehicalById();

			break;

		case 6: return;
		 

		 

	}
	}
	}
	
	 
}

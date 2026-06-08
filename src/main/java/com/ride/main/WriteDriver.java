package com.ride.main;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ride.service.AdvanceFeaturesService;
import com.ride.service.DriverService;
import com.ride.service.RideService;
import com.ride.service.TransactionService;
import com.ride.service.UserService;
import com.ride.service.VehicleService;

public class WriteDriver {
	
	public static void main(String[] args) {
		
		
		UserService us = new UserService();
		DriverService ds= new DriverService();
		VehicleService vs=new VehicleService();
		RideService rd =new RideService();
		TransactionService ts =new TransactionService();
		AdvanceFeaturesService ad= new AdvanceFeaturesService();
		Scanner sc =new Scanner(System.in);
		

		int choice = -1;
		
		while (choice != 0) {
		System.out.println("++++++++++++++++++++=============================+++++++++++++++++++++++++");
		System.out.println("1.Manage User");
		
		System.out.println("2.Manage Driver");
		System.out.println("3.Manage Vahical");
		System.out.println("4.Manage Ride");
		System.out.println("5.Manage Transaction");
		System.out.println("6.Manage Reports");
		System.out.println("7.Advance Features");
		System.out.println("!......  PRESS ANY KEY TO EXIT..........!");
		System.out.println("++++++++++++++++++++=============================+++++++++++++++++++++++++");

		choice=sc.nextInt();

			switch (choice) {
			case 1:us.userMenue();

				break;

			case 2:ds.driverMenue();

				break;
			case 3:vs.vehicalMenue();

				break;
			case 4:rd.ridelMenue();

				break;
			case 5: ts.transactionMenu();

				break;
			case 6:

				break;
			case 7:ad.AdvanceFeactureMenue();

				break;

			default:System.exit(0);
				
				break;
			}

		}

	}

}

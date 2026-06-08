package com.ride.service;

import java.util.Scanner;

import com.ride.dao.RideDao;
//import com.ride.dao.VehicalDao;

public class RideService {
	RideDao rd = new RideDao();
	Scanner sc = new Scanner(System.in);

	public void ridelMenue() {

		while (true) {

			System.out.println("++++++++++++++++++++=============================+++++++++++++++++++++++++");
			System.out.println("1. Book Ride ");
			System.out.println("2. Accept Ride ");
			System.out.println("3. Fetch Ride BY USers :");
			System.out.println("4. Cancel Ride");
			System.out.println("5. Update Ride");
			System.out.println("6.Fetch Cancaled Rides");
			System.out.println("7.Complete Ride");	
			System.out.println("8.Fetch Ride By Driver");
			System.out.println("9. Return");
			System.out.println("!......  PRESS ANY INPUT..........!");
			System.out.println("++++++++++++++++++++=============================+++++++++++++++++++++++++");
			int choice = sc.nextInt();

			switch (choice) {

			case 1:
				rd.createRide();

				break;

			case 2:
				rd.acceptRide();

				break;

			case 3:
				rd.fechRideByUser();

				break;

			case 4:
				rd.cancelRide();

				break;

			case 5:
				rd.updateRide();

				break;

			case 6:
				rd.getCancaledRides();
				break;
				
			case 7:
				rd.completeRide();
				break;
			case 8:
				rd.getRideByDriver();
				break;
			case 9:return;
				
				
				
				
				
				

			}
		}
	}

}

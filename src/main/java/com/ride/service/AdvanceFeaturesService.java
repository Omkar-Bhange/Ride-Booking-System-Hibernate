package com.ride.service;

import java.util.Scanner;

import com.ride.dao.AdvanceFeaturesDao;

public class AdvanceFeaturesService {
	
	
	Scanner sc = new Scanner(System.in);
	AdvanceFeaturesDao afd = new AdvanceFeaturesDao();

	public void AdvanceFeactureMenue() {

		while (true) {

			System.out.println("++++++++++++++++++++=============================+++++++++++++++++++++++++");
			System.out.println("1. Recharge Wallet");
			System.out.println("2. View Wallet Balance");
			System.out.println("3. Find Nearest Available Driver");
			System.out.println("4. Top Driver");
			System.out.println("5. Most Active User");
			System.out.println("6. Search Ride By Status");
			System.out.println("7. Search Transaction By Payment Type");	
			System.out.println("8. Return");
			System.out.println("!......  PRESS ANY INPUT..........!");
			System.out.println("++++++++++++++++++++=============================+++++++++++++++++++++++++");
			int choice = sc.nextInt();

			switch (choice) {

			case 1:
				afd.rechargeWallet();

				break;

			case 2:
				afd.getWalletBalance();

				break;

			case 3:
				System.out.println("This featur is comming soon :");

				break;

			case 4:
				System.out.println("This featur is comming soon :");

				break;

			case 5:
				System.out.println("This featur is comming soon :");

				break;

			case 6:
				afd.getRideByStatus();
				break;
				
			case 7:
				afd.getTransactionByPaymentType();
				break;
			 
			case 8:return;
				
				
				
				
				
				

			}
		}
	}

}

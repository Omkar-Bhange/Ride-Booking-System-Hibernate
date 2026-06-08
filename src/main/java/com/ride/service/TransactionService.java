package com.ride.service;

import java.util.Scanner;

import com.ride.dao.TransactionDao;

public class TransactionService {
	TransactionDao td = new TransactionDao();
	Scanner sc = new Scanner(System.in);

	public void transactionMenu() {

		while (true) {

			//System.out.println("1. Create Transaction");
			System.out.println("2. Complete Transaction");
			System.out.println("3. Cancel Transaction");
			System.out.println("4. Fetch Transaction By User");
			System.out.println("5. Fetch Transaction By Ride");
			System.out.println("6. Fetch Pending Transaction");
			System.out.println("7. Fetch cancalled transaction");
			System.out.println("8  Return");

			int choice = sc.nextInt();

			switch (choice) {

			case 1:
				td.createTransaction();
				break;

			case 2:
				td.completeTransaction();
				break;

			case 3:
				td.cancelTransaction();
				// td.viewTransactionByUser();
				break;

			case 4:
				td.getTransacationByUser();
				break;

			case 5:
				td.getTransacationByRide();
				break;

			case 6:
				td.getPendingTransactions();
				break;

			case 7:
				td.getCancelledTransactions();
				break;

			case 8:
				return;
			}
		}
	}

}

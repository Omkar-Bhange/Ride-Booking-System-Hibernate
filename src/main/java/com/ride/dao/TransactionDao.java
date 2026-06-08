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

import com.ride.entity.Ride;
import com.ride.entity.Transaction;
import com.ride.enums.RideStatus;
import com.ride.enums.TransactionStatusRide;
import com.ride.enums.TransatctionType;

public class TransactionDao {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
	EntityManager em = emf.createEntityManager();
	EntityTransaction transaction = em.getTransaction();
	Scanner sc = new Scanner(System.in);

	public void createTransaction() {

//		System.out.println("Enter the ride id :");
//		int id = sc.nextInt();
//		Ride ride = em.find(Ride.class, id);
//
//		if (ride == null) {
//			System.out.println("Ride not found");
//			return;
//		}
//
//		if (ride.getStatus() != RideStatus.STARTED) {
//			System.out.println("Transaction can be created only for STARTED rides");
//			return;
//		}
//
//		if (ride.getTransaction() != null) {
//			System.out.println("Transaction already exists");
//			return;
//		}
//
//		Transaction t = new Transaction();
//		t.setRide(ride);
//		t.setStatus(TransactionStatusRide.PENDING);
//		System.out.println("Select Payment Method :");
//		System.out.println("1. cash");
//		System.out.println("2  UPI");
//		System.out.println("3  Cards");
//
//		int choice = sc.nextInt();
//		switch (choice) {
//		case 1:
//			t.setType(TransatctionType.CASH);
//
//			break;
//
//		case 2:
//			t.setType(TransatctionType.UPI);
//
//			break;
//		case 3:
//			t.setType(TransatctionType.CARDS);
//
//			break;
//
//		default:
//			System.out.println("INVALID INPUT  ");
//			return;
//		}
//
//		t.setUser(ride.getUser());
//		t.setAmmount(ride.getFare());
//
//		transaction.begin();
//		em.persist(t);
//		transaction.commit();
//
//		System.out.println("Transaction created sucefully your fare ammount is =============>>    :" + t.getAmmount());

	}

	public void completeTransaction() {
		System.out.println("Enter the ride id :");
		int id = sc.nextInt();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Transaction> query = cb.createQuery(Transaction.class);
		Root<Transaction> root = query.from(Transaction.class);
		query.select(root).where(cb.equal(root.get("ride").get("id"), id));

		Transaction t = em.createQuery(query).getSingleResult();

		if (t.getStatus() == TransactionStatusRide.SUCCESS) {
			System.out.println("Transaction already completed");
			return;
		}
		if (t.getStatus() == TransactionStatusRide.FAILED) {
			System.out.println("The Transaction Is Failed So This Transaction Can Not be Completed :");
			return;
		}

		if (t.getType() == TransatctionType.CASH) {
			transaction.begin();
			t.setStatus(TransactionStatusRide.SUCCESS);
			transaction.commit();
		} else {
			transaction.begin();
			double wallet_balance = t.getRide().getUser().getWallet_balance();
			if (wallet_balance < 0 || wallet_balance < t.getAmmount()) {

				System.out.println("Balanc is not suffecient ");
				transaction.rollback();
				return;

			}
			t.getRide().getUser().setWallet_balance(wallet_balance - t.getAmmount());
			t.setStatus(TransactionStatusRide.SUCCESS);

			transaction.commit();
		}

		System.out.println("User :" + t.getUser().getName());
		System.out.println("Transaction Completed Sucefully");
		System.out.println("your remaining blance is :" + t.getUser().getWallet_balance());

		System.out.println("----------------------------NOW YOU CAN COMPLETE YOUR RIDE ---------------------------");

	}

	public void getTransacationByUser() {

		System.out.println("Enter User Email :");
		String email = sc.next();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Transaction> query = cb.createQuery(Transaction.class);
		Root<Transaction> root = query.from(Transaction.class);

		query.select(root).where(cb.equal(root.get("email"), email));
		Transaction t = em.createQuery(query).getSingleResult();

		System.out.println("==========================+++++++++++++++++++++++++++++++=============================");
		System.out.println("User Name 				:" + t.getUser().getName());
		System.out.println("Fare Ammount 			:" + t.getAmmount());
		System.out.println("Transaction Status 		:" + t.getStatus());
		System.out.println("Transaction Type 		:" + t.getType());
		System.out.println("User Remaining Balance 	:" + t.getUser().getWallet_balance());
		System.out.println("Ride Status 			:" + t.getRide().getStatus());
		System.out.println("==========================+++++++++++++++++++++++++++++++=============================");

	}

	public void cancelTransaction() {
		System.out.println("Enter ride id :");
		int id = sc.nextInt();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Transaction> query = cb.createQuery(Transaction.class);
		Root<Transaction> root = query.from(Transaction.class);
		query.select(root).where(cb.equal(root.get("ride").get("id"), id));
		Transaction t = em.createQuery(query).getSingleResult();

		if (t == null) {
			System.out.println("For this ride Transaction is not created :");
			return;
		}
		if (t.getStatus() == TransactionStatusRide.SUCCESS || t.getStatus() == TransactionStatusRide.FAILED) {
			System.out.println("This Transaction Can not be Canceled :");
			return;
		}

		t.setStatus(TransactionStatusRide.FAILED);

		System.out.println("==========================+++++++++++++++++++++++++++++++=============================");
		System.out.println("User Name 				:" + t.getUser().getName());
		System.out.println("Fare Ammount 			:" + t.getAmmount());
		System.out.println("Transaction Status 		:" + t.getStatus());
		System.out.println("Transaction Type 		:" + t.getType());
		System.out.println("User Remaining Balance 	:" + t.getUser().getWallet_balance());
		System.out.println("Ride Status 			:" + t.getRide().getStatus());
		System.out.println("==========================+++++++++++++++++++++++++++++++=============================");

	}

	public void getTransacationByRide() {

		System.out.println("Enter Ride ID :");
		String id = sc.next();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Transaction> query = cb.createQuery(Transaction.class);
		Root<Transaction> root = query.from(Transaction.class);

		query.select(root).where(cb.equal(root.get("ride").get("id"), id));
		Transaction t = em.createQuery(query).getSingleResult();

		System.out.println("==========================+++++++++++++++++++++++++++++++=============================");
		System.out.println("User Name 				:" + t.getUser().getName());
		System.out.println("Fare Ammount 			:" + t.getAmmount());
		System.out.println("Transaction Status 		:" + t.getStatus());
		System.out.println("Transaction Type 		:" + t.getType());
		System.out.println("User Remaining Balance 	:" + t.getUser().getWallet_balance());
		System.out.println("Ride Status 			:" + t.getRide().getStatus());
		System.out.println("==========================+++++++++++++++++++++++++++++++=============================");

	}

	public void getPendingTransactions() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Transaction> query = cb.createQuery(Transaction.class);
		Root<Transaction> root = query.from(Transaction.class);

		query.select(root).where(cb.equal(root.get("status"), TransactionStatusRide.PENDING));
		List<Transaction> tra = em.createQuery(query).getResultList();

		for (Transaction t : tra) {

			System.out
					.println("==========================+++++++++++++++++++++++++++++++=============================");
			System.out.println("User Name 				:" + t.getUser().getName());
			System.out.println("Fare Ammount 			:" + t.getAmmount());
			System.out.println("Transaction Status 		:" + t.getStatus());
			System.out.println("Transaction Type 		:" + t.getType());
			System.out.println("User Remaining Balance 	:" + t.getUser().getWallet_balance());
			System.out.println("Ride Status 			:" + t.getRide().getStatus());
			System.out
					.println("==========================+++++++++++++++++++++++++++++++=============================");

		}

	}

	public void getCancelledTransactions() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Transaction> query = cb.createQuery(Transaction.class);
		Root<Transaction> root = query.from(Transaction.class);

		query.select(root).where(cb.equal(root.get("status"), TransactionStatusRide.FAILED));
		List<Transaction> tra = em.createQuery(query).getResultList();

		for (Transaction t : tra) {

			System.out
					.println("==========================+++++++++++++++++++++++++++++++=============================");
			System.out.println("User Name 				:" + t.getUser().getName());
			System.out.println("Fare Ammount 			:" + t.getAmmount());
			System.out.println("Transaction Status 		:" + t.getStatus());
			System.out.println("Transaction Type 		:" + t.getType());
			System.out.println("User Remaining Balance 	:" + t.getUser().getWallet_balance());
			System.out.println("Ride Status 			:" + t.getRide().getStatus());
			System.out
					.println("==========================+++++++++++++++++++++++++++++++=============================");

		}

	}

}

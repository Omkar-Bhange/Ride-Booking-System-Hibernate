package com.ride.dao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.ride.entity.Ride;
import com.ride.entity.Transaction;
import com.ride.entity.User;
import com.ride.enums.RideStatus;
import com.ride.enums.TransatctionType;

//import javax.persistence.Persistence;

public class AdvanceFeaturesDao {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
	Scanner sc = new Scanner(System.in);
//	CriteriaBuilder cb= emf.getCriteriaBuilder();
	EntityManager em = emf.createEntityManager();
	CriteriaBuilder cb = em.getCriteriaBuilder();

	public void rechargeWallet() {

		System.out.println("Enter the user email :");
		String email = sc.next();
		CriteriaQuery<User> query = cb.createQuery(User.class);
		Root<User> root = query.from(User.class);

		query.select(root).where(cb.equal(root.get("email"), email));

		User user = em.createQuery(query).getSingleResult();
		if (user == null) {
			System.out.println("user not exisit !!!!!!!!!!!!!");
			return;
		}

		double balance = user.getWallet_balance();

		System.out.println("Enter the recharge Ammount :");
		double rechargeAmmount = sc.nextDouble();
		if (rechargeAmmount <= 0) {
			System.out.println("Invalid Amount");
			return;
		}

		em.getTransaction().begin();
		user.setWallet_balance(balance + rechargeAmmount);
		em.getTransaction().commit();
		em.close();

		System.out.println("YOUR ACCOUNT HAS BEEN RECHARGED WITH AMMOUNT :======> " + user.getWallet_balance());

	}

	public void getWalletBalance() {
		System.out.println("Enter the email of user :");
		String email = sc.next();

		CriteriaQuery<User> query = cb.createQuery(User.class);
		Root<User> root = query.from(User.class);

		query.select(root).where(cb.equal(root.get("email"), email));

		User user = em.createQuery(query).getSingleResult();

		System.out
				.println("Balance of MR." + user.getName() + " is :============> " + user.getWallet_balance() + " .rs");

	}

	public void getRideByStatus() {
		System.out.println("Enter the status :");
		System.out.println("1. REQUESTED");
		System.out.println("2. ACCEPTED");
		System.out.println("3. STARTED");
		System.out.println("4. COMPLETED");
		System.out.println("5. CANCELLED");
		
		int choice=sc.nextInt();
		RideStatus status=null;
		
		switch (choice) {
		case 1: status=RideStatus.REQUESTED;
			
			break;
			
		case 2: status=RideStatus.ACCEPTED;
		
		break;
		
		case 3: status=RideStatus.STARTED;
		
		break;
		
		case 4: status=RideStatus.COMPLETED;
		
		break;
		case 5: status=RideStatus.CANCELLED;
		
		break;

		default:System.out.println("Invalid Choice !!!!!!!!!!!!");
			return;
		}
		
		CriteriaQuery<Ride> query = cb.createQuery(Ride.class);
		Root<Ride> root = query.from(Ride.class);
		
		query.select(root).where(cb.equal(root.get("status"), status));
		
		System.out.println("Enter page no :");
		int pageNo=sc.nextInt();
		System.out.println("Enter page record size :");
		int recordSize=sc.nextInt();
		
		
		List<Ride> resultList = em.createQuery(query).setFirstResult((pageNo-1)*recordSize).setMaxResults(recordSize).getResultList();
		
		for (Ride ride : resultList) {
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
	
	public void  getTransactionByPaymentType() {
		
		System.out.println("Enter the payment type :");
		System.out.println("1. Cash");
		System.out.println("2. Card");
		System.out.println("3. UPI");
		
		int choice=sc.nextInt();
		TransatctionType type=null;
		switch (choice) {
		case 1: type=TransatctionType.CASH;
			
			break;
			
		case 2: type=TransatctionType.CARDS;
		
		break;
		
		case 3: type=TransatctionType.UPI;
		
		break;

		default:System.out.println("Invalid Choice !!!!!!!!!!!!!!!");
			return;
		}
		
		
		CriteriaQuery<Transaction> query = cb.createQuery(Transaction.class);
		Root<Transaction> root = query.from(Transaction.class);
		
		query.select(root).where(cb.equal(root.get("type"), type));
		
		System.out.println("Enter the page no :");
		int pageNo=sc.nextInt();
		System.out.println("Enter the pageSize :");
		int pageSize=sc.nextInt();
		
		
		List<Transaction> resultList = em.createQuery(query).setFirstResult((pageNo -1)* pageSize).setMaxResults(pageSize).getResultList();
		
		for (Transaction t : resultList) {
			
			System.out.println("==========================+++++++++++++++++++++++++++++++=============================");
			System.out.println("User Name 				:" + t.getUser().getName());
			System.out.println("Fare Ammount 			:" + t.getAmmount());
			System.out.println("Transaction Status 		:" + t.getStatus());
			System.out.println("Transaction Type 		:" + t.getType());
			System.out.println("User Remaining Balance 	:" + t.getUser().getWallet_balance());
			System.out.println("Ride Status 			:" + t.getRide().getStatus());
			System.out.println("==========================+++++++++++++++++++++++++++++++=============================");

		}
	}

}

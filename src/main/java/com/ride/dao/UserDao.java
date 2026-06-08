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
import com.ride.entity.User;

public class UserDao {
	Scanner sc = new Scanner(System.in);
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPU");
	EntityManager em = emf.createEntityManager();
	EntityTransaction transaction = em.getTransaction();

	public void createUser() {

		sc.nextLine();
		User u = new User();
		System.out.println("Enter the user name :");
		u.setName(sc.nextLine());
		System.out.println("Enter the valid email :");
		u.setEmail(sc.nextLine());
		System.out.println("Enter the user phone no :");
		u.setPhoNo(sc.nextLine());
		u.setLangitude(12345.33);
		u.setLatitude(12345.66);
		System.out.println("Enter ammount balance for user :");
		u.setWallet_balance(sc.nextDouble());

		transaction.begin();
		em.persist(u);
		transaction.commit();
		// em.close();

		System.out.println("Data Inserted Successfully");
	}

	public void updateUser() {

		sc.nextLine();
		System.out.println("Enter user email to which you want to edit data :");
		String email = sc.nextLine();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> query = cb.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root).where(cb.equal(root.get("email"), email));

		User user = em.createQuery(query).getSingleResult();

		if (user != null) {

			System.out.println("1. Update Name");
			System.out.println("2. Update Phone");
			System.out.println("3. Update Wallet");

			int choice = sc.nextInt();

			transaction.begin();

			switch (choice) {

			case 1:
				sc.nextLine();
				System.out.println("Enter New Name:");
				user.setName(sc.nextLine());
				break;

			case 2:
				sc.nextLine();
				System.out.println("Enter New Phone:");
				user.setPhoNo(sc.nextLine());
				break;

			case 3:
				System.out.println("Enter New Wallet Balance:");
				user.setWallet_balance(sc.nextDouble());
				break;
			}

			transaction.commit();

		} else {
			System.out.println("User not found...............................");

		}

	}

	public void getAllUser() {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> query = cb.createQuery(User.class);
		Root<User> root = query.from(User.class);

		query.select(root);

		System.out.println("Enter page no :");
		int pageNo =sc.nextInt();
		System.out.println("Enter how many records you want  :");
		int pageSize =sc.nextInt();
		List<User> resultList = em.createQuery(query).setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).getResultList();

		for (User user : resultList) {
			System.out.println("++++++++++++++++++================++++++++++++++++++++++++");
			System.out.println("User Name :" + user.getName());
			System.out.println("User email :" + user.getEmail());
			System.out.println("User PhoNO :" + user.getPhoNo());
			System.out.println("User balance :" + user.getWallet_balance());
			System.out.println("++++++++++++++++++================++++++++++++++++++++++++");
		}

	}

	public void deleteUserByEmail() {

		sc.nextLine();
		System.out.println("Enter user email to which you want to edit data :");
		String email = sc.nextLine();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> query = cb.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root).where(cb.equal(root.get("email"), email));

		User user = em.createQuery(query).getSingleResult();

		transaction.begin();

		em.remove(user);

		transaction.commit();

		System.out.println("User Deleted Successfully");
	}

	public void viewUserByEmail() {

		sc.nextLine();
		System.out.println("Enter user email to which you want to edit data :");
		String email = sc.nextLine();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<User> query = cb.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root).where(cb.equal(root.get("email"), email));

		User user = em.createQuery(query).getSingleResult();

		System.out.println("++++++++++++++++++================++++++++++++++++++++++++");
		System.out.println("User Name :" + user.getName());
		System.out.println("User email :" + user.getEmail());
		System.out.println("User PhoNO :" + user.getPhoNo());
		System.out.println("User balance :" + user.getWallet_balance());
		System.out.println("++++++++++++++++++================++++++++++++++++++++++++");

	}

}

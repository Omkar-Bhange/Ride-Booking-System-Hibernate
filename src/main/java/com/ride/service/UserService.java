package com.ride.service;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.ride.dao.UserDao;
import com.ride.entity.User;

public class UserService {
	UserDao ud=new UserDao();
	//UserService us =new UserService();
	Scanner sc = new Scanner(System.in);
	public void userMenue() {
		 
		while (true) {

			System.out.println("++++++++++++++++++++=============================+++++++++++++++++++++++++");
			System.out.println("1.Create User ");
			System.out.println("2.Update User");
			System.out.println("3.view all User");
			System.out.println("4.Delete User");
			System.out.println("5.View user by ID");
			System.out.println("6.return");
			System.out.println("!......  PRESS ANY INPUT..........!");
			int choice = sc.nextInt();

			switch (choice) {
			case 1: ud.createUser();	

				break;

			case 2:ud.updateUser();

				break;

			case 3:ud.getAllUser();

				break;

			case 4:ud.deleteUserByEmail();

				break;

			case 5:ud.viewUserByEmail();

				break;

			case 6: return;
			 

			 

		}
	}
		
		

	}
	

	
}

# Ride Booking System Using Hibernate

## Project Overview

A console-based Ride Booking System developed using Java, Hibernate (JPA), and MySQL. The application simulates the core functionalities of ride-booking platforms such as Uber and Ola.

## Features

### User Management

* Create User
* Update User
* Delete User
* View User Details
* Wallet Recharge

### Driver Management

* Create Driver
* Update Driver
* Delete Driver
* View Driver Details

### Vehicle Management

* Assign Vehicle to Driver
* Update Vehicle
* Delete Vehicle
* Search Vehicle by Number

### Ride Management

* Create Ride Request
* Accept Ride
* Reject Ride
* Start Ride
* Complete Ride
* Cancel Ride
* View Ride Details

### Transaction Management

* Create Transaction
* Complete Transaction
* Wallet Deduction
* Transaction History

### Reports

* User Ride History
* Driver Ride History
* Transaction History
* Total Revenue Generated
* Total Completed Rides
* Total Cancelled Rides

## Technologies Used

* Java
* Hibernate ORM
* JPA
* MySQL
* Maven
* Eclipse IDE

## Database Design

Entities:

* User
* Driver
* Vehicle
* Ride
* Transaction

Relationships:

* User → Ride (One-to-Many)
* Driver → Ride (One-to-Many)
* Driver → Vehicle (One-to-One)
* User → Transaction (One-to-Many)
* Ride → Transaction (One-to-One)

## Project Workflow

User creates ride request
↓
Driver accepts ride
↓
Ride starts
↓
Transaction created
↓
Transaction completed
↓
Ride completed

## Learning Outcomes

* Hibernate ORM
* JPA Mappings
* Criteria API
* Entity Relationships
* Transaction Management
* Maven Project Structure
* Real-world Business Logic Implementation

## Author

Omkar Bhange

The Payment Gateway Application is a Java Swing–based desktop project integrated with MySQL. It simulates real-world payment systems with secure login, user registration, and multiple payment modes: Credit Card, PayPal, and Bank Transfer.

✨ Features

User registration and login with input validation.

View account details (balance, account number, email, mobile).

Perform payments using username, email, or account number.

Updates sender and receiver balances in real time.

Error handling for invalid inputs or insufficient balance.

Interactive GUI with reset and logout options.

🛠️ Technologies Used

Java Swing – for GUI development

MySQL – for database management

JDBC – for database connectivity

⚙️ Installation & Setup
Prerequisites

Java JDK (version 8 or above)

MySQL Server(eg.xamapp)

MySQL JDBC Connector

Steps

Clone or download this repository.

Import the project into your IDE (e.g., Eclipse, IntelliJ, or NetBeans).

Create a MySQL database (e.g., myapp_db).

Run the provided SQL script to create necessary tables.

Update the JDBC connection details (username, password, DB name) in the code.

Compile and run the Paymentjava file.


SQL query

CREATE DATABASE IF NOT EXISTS myappdb;
USE myappdb;

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `mobile` varchar(100) NOT NULL,
  `amount` int(10) NOT NULL,
  `account` varchar(20) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



▶️ Usage

Register as a new user or log in with existing credentials.

View your account details.

Choose a payment method: Credit Card, PayPal, or Bank Transfer.

Enter recipient details and amount.

Confirm payment – balances update automatically.

📜 License

This project is licensed under the MIT License

Author:Alok Prakash

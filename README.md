secure-heroku-application
=========================

This is a demo for a secure application deployment on Heroku

Features

	Spring Security,
	Spring MVC.
	JQuery UI,
	JQ Grid,
	Anti Samy Layer{With Spring AOP},
	BASE 64 Encryption,
	Postgresql {Heroku Cloud},
	IText,
	JQuery,
	Parsley js,
	My own Lib for converting Forms to JSON,
	Java Mail with Spring Mail template,
	Spring JDBC,
	Dynamic Tomcat Instance invocation for Heroku

Demo app deployed @ http://quiet-lowlands-8438.herokuapp.com/

USERNAME saurabh
PASSWORD saurabh.shah

This Application requires a heroku postgresql db instance.

CREATE TABLE contacts (id serial PRIMARY KEY, name VARCHAR(20) NOT NULL, email VARCHAR(50) NOT NULL, message VARCHAR(200) NOT NULL, mobile VARCHAR(20) NOT NULL);

CREATE TABLE users (id serial PRIMARY KEY, username VARCHAR(20) NOT NULL, encpass VARCHAR(30) NOT NULL, role VARCHAR(30) NOT NULL );

INSERT INTO USERS (username,encpass,role) VALUES('saurabh','kHCiDWy3n6U7sfkszCpzGA==','user')

//To change this value in master script use the CrpticUtility.java's Main method{PSVM} to generate the new encpass


Please update the creds.properties

db.url=jdbc:postgresql://host:port/dbname?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory
db.driver.class=org.postgresql.Driver
db.username=username
db.password=password

mail.host=smtp.gmail.com
mail.port=587
mail.username=username@gmail.com
mail.password=yourpass

Step 1: GIT Clone
Step 2: Import in Eclispe
Step 3: Create SQL Table & nsert Master Script
Step 4: Update creds.properties
Step 5: Maven Clean, Maven Install
Step 6: Via Console goto the project path and type in sh target/bin/webapp
Step 7: access @ http://localhost:8080
 

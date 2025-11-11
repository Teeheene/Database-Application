CREATE DATABASE IF NOT EXISTS basketball_app;
USE basketball_app;

DROP TABLE IF EXISTS enthusiast;
CREATE TABLE enthusiast (
	enthusiast_id INT NOT NULL AUTO_INCREMENT,
	username VARCHAR(25) NOT NULL UNIQUE,
   lastname VARCHAR(50) NOT NULL,
   firstname VARCHAR(50) NOT NULL,
   middlename VARCHAR(50),
   sex VARCHAR(10) NOT NULL,
   date_of_birth TIMESTAMP NOT NULL,
	hashed_password INT, 
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (enthusiast_id)
);

DROP TABLE IF EXISTS engagement;
CREATE TABLE engagement (
	engagement_id INT AUTO_INCREMENT,
   engagement_category VARCHAR(10) NOT NULL,
   engagement_type VARCHAR(10) NOT NULL,
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   enthusiast_id INT,
   PRIMARY KEY (engagement_id),
   FOREIGN KEY (enthusiast_id) REFERENCES enthusiast(enthusiast_id)
);

DROP TABLE IF EXISTS player;
CREATE TABLE player (
	player_id INT NOT NULL AUTO_INCREMENT,
   lastname VARCHAR(50) NOT NULL,
   firstname VARCHAR(50) NOT NULL,
   middlename VARCHAR(50),
   sex VARCHAR(10) NOT NULL,
   date_of_birth TIMESTAMP NOT NULL,
   height INT,
	weight INT,
	rStatus BOOLEAN,
	PRIMARY KEY (player_id)
);

DROP TABLE IF EXISTS coach;
CREATE TABLE coach (
	coach_id INT NOT NULL AUTO_INCREMENT,
	lastname VARCHAR(50) NOT NULL,
	firstname VARCHAR(50) NOT NULL,
	middlename VARCHAR(50),
	sex VARCHAR(10) NOT NULL,
	date_of_birth TIMESTAMP NOT NULL,
	start_year INT NOT NULL,
	end_year INT,
	years_in_field INT NOT NULL,
	status BOOLEAN,
	PRIMARY KEY (coach_id)
);
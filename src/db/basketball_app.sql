CREATE DATABASE IF NOT EXISTS basketball_app;
USE basketball_app;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS enthusiast;
DROP TABLE IF EXISTS engagement;
DROP TABLE IF EXISTS player;

SET FOREIGN_KEY_CHECKS = 1;

DROP TABLE IF EXISTS enthusiast;
CREATE TABLE enthusiast (
	enthusiast_id INT NOT NULL AUTO_INCREMENT,
	username VARCHAR(25) NOT NULL UNIQUE,
   lastname VARCHAR(50) NOT NULL,
   firstname VARCHAR(50) NOT NULL,
   middlename VARCHAR(50),
   sex VARCHAR(10) NOT NULL,
   date_of_birth DATE NOT NULL,
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

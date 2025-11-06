CREATE DATABASE IF NOT EXISTS basketball_app;
USE basketball_app;
-- CORE RECORDS
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
    team_id INT,
	PRIMARY KEY (player_id),
    FOREIGN KEY (team_id) REFERENCES team(team_ID) ON DELETE SET NULL
);

-- TRANSACTION RECORDS
DROP TABLE IF EXISTS team;
CREATE TABLE team (
   team_ID INT AUTO_INCREMENT,
    team_name VARCHAR(50) NOT NULL,
    -- coach_id INT, (for when its added)
    registry_status BOOLEAN,
    tCreated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
 PRIMARY KEY(team_ID)
 -- FOREIGN KEY(coach_id) REFERENCES coach(coach_id)
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


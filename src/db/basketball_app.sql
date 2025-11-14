CREATE DATABASE IF NOT EXISTS basketball_app;
USE basketball_app;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS enthusiast;
DROP TABLE IF EXISTS engagement;
DROP TABLE IF EXISTS player;
DROP TABLE IF EXISTS tournament;

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

DROP TABLE IF EXISTS player;
CREATE TABLE player (
	player_id INT NOT NULL AUTO_INCREMENT,
	lastname VARCHAR(50) NOT NULL,
	firstname VARCHAR(50) NOT NULL,
	middlename VARCHAR(50),
	sex VARCHAR(10) NOT NULL,
	date_of_birth DATE NOT NULL,
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
	date_of_birth DATE NOT NULL,
	start_year INT NOT NULL,
	end_year INT,
	years_in_field INT NOT NULL,
	status BOOLEAN,
	PRIMARY KEY (coach_id)
);

DROP TABLE IF EXISTS tournament;
CREATE TABLE tournament (
	tournament_id INT AUTO_INCREMENT PRIMARY KEY,	
    tournament_name VARCHAR(200) NOT NULL,
	season_year INT,
	tournament_type VARCHAR(50),
	start_date DATE,
	end_date DATE
);

DROP TABLE IF EXISTS engagement;
CREATE TABLE engagement (
	engagement_id INT NOT NULL AUTO_INCREMENT,
	engagement_category VARCHAR(10) NOT NULL,
	engagement_type VARCHAR(10) NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	enthusiast_id INT NOT NULL,
	player_id INT,
	coach_id INT,
	tournament_id INT,
	PRIMARY KEY (engagement_id),
	FOREIGN KEY (enthusiast_id) REFERENCES enthusiast(enthusiast_id),
	FOREIGN KEY (player_id) REFERENCES player(player_id),
	FOREIGN KEY (coach_id) REFERENCES coach(coach_id),
	FOREIGN KEY (tournament_id) REFERENCES tournament(tournament_id),
	CONSTRAINT chk_category CHECK (
		(engagement_category = 'player' AND player_id IS NOT NULL) OR
		(engagement_category = 'coach' AND coach_id IS NOT NULL) OR
		(engagement_category = 'tournament' AND tournament_id IS NOT NULL)
	)
);

DROP TABLE IF EXISTS team;
CREATE TABLE team (
    team_ID INT AUTO_INCREMENT,
    coach_id INT, 
	team_name VARCHAR(50) NOT NULL,
	number_of_players INT,
    registry_status BOOLEAN,
    tCreated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY(team_ID),
FOREIGN KEY(coach_id) REFERENCES coach(coach_id)
);

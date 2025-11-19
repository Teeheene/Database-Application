CREATE DATABASE IF NOT EXISTS basketball_app;
USE basketball_app;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS enthusiast;
DROP TABLE IF EXISTS player;
DROP TABLE IF EXISTS tournament;
DROP TABLE IF EXISTS coach;
DROP TABLE IF EXISTS engagement;
DROP TABLE IF EXISTS engagement_player;
DROP TABLE IF EXISTS engagement_coach;
DROP TABLE IF EXISTS engagement_tournament;
DROP TABLE IF EXISTS team;
DROP TABLE IF EXISTS competing_team;
DROP TABLE IF EXISTS game;

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
	status BOOLEAN NOT NULL,	
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
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
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
	status BOOLEAN,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (coach_id)
);

DROP TABLE IF EXISTS tournament;
CREATE TABLE tournament (
	tournament_id INT AUTO_INCREMENT PRIMARY KEY,	
    tournament_name VARCHAR(200) NOT NULL,
	season_year INT,
	tournament_type VARCHAR(50),
	start_date DATE,
	end_date DATE,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS engagement;
CREATE TABLE engagement (
	engagement_id INT NOT NULL AUTO_INCREMENT,
	engagement_type VARCHAR(10) NOT NULL,
	enthusiast_id INT NOT NULL,
	status BOOLEAN NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (engagement_id),
	FOREIGN KEY (enthusiast_id) REFERENCES enthusiast(enthusiast_id)
);

-- subtype engagement tables
DROP TABLE IF EXISTS engagement_player;
CREATE TABLE engagement_player (
	engagement_id INT NOT NULL,
	player_id INT NOT NULL,
	PRIMARY KEY (engagement_id),
	FOREIGN KEY (engagement_id) REFERENCES engagement(engagement_id),
	FOREIGN KEY (player_id) REFERENCES player(player_id) 
);

DROP TABLE IF EXISTS engagement_coach;
CREATE TABLE engagement_coach (
	engagement_id INT NOT NULL,
	coach_id INT NOT NULL,
	PRIMARY KEY (engagement_id),
	FOREIGN KEY (engagement_id) REFERENCES engagement(engagement_id),
	FOREIGN KEY (coach_id) REFERENCES coach(coach_id) 
);

DROP TABLE IF EXISTS engagement_tournament;
CREATE TABLE engagement_tournament (
	engagement_id INT NOT NULL,
	tournament_id INT NOT NULL,
	PRIMARY KEY (engagement_id),
	FOREIGN KEY (engagement_id) REFERENCES engagement(engagement_id),
	FOREIGN KEY (tournament_id) REFERENCES tournament(tournament_id) 
);

DROP TABLE IF EXISTS team;
CREATE TABLE team (
    team_ID INT NOT NULL AUTO_INCREMENT,
    coach_id INT, 
	team_name VARCHAR(50) NOT NULL,
	number_of_players INT,
    registry_status BOOLEAN,
    tCreated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
PRIMARY KEY(team_ID),
FOREIGN KEY(coach_id) REFERENCES coach(coach_id)
);

INSERT INTO enthusiast (username, lastname, firstname, middlename, sex, date_of_birth, status) VALUES
('SmOhn23','Smith','John','Alexander','Male','1990-01-05',true),
('Basketballer3030','Doe','Jane','Beatrice','Female','1992-02-10',true),
('Owo 7070','Brown','Charlie','Christopher','Male','1988-03-15',true),
('fanBall 6767','Johnson','Emily','Danielle','Female','1995-04-20',true),
('DavisMichE','Davis','Michael','Edward','Male','1993-05-25',true),
('Miller_Kobe67','Miller','Sarah','Felicity','Female','1991-06-30',true),
('Wavid','Wilson','David','Gregory','Male','1989-07-18',true),
('TaylorSwiftBall','Taylor','Laura','Helena','Female','1994-08-22',true),
('AnderFan','Anderson','Kevin','Isaiah','Male','1996-09-12',true),
('Kobe142','Thomas','Olivia','Julianne','Female','1997-10-27',true);

INSERT INTO player (lastname, firstname, middlename, sex, date_of_birth, height, weight, rStatus) VALUES
('Allen','Mark','Andrew','Male','1990-01-01',180,75,1),
('Baker','Luke','Benjamin','Male','1991-02-02',182,78,1),
('Carter','Nina','Celeste','Female','1992-03-03',170,60,1),
('Diaz','Oscar','Dominic','Male','1993-04-04',185,82,0),
('Evans','Paula','Elaine','Female','1994-05-05',168,58,1),
('Frost','Quinn','Francisco','Male','1995-06-06',188,85,1),
('Gray','Rita','Gabrielle','Female','1996-07-07',165,55,0),
('Hall','Sam','Harrison','Male','1997-08-08',190,90,1),
('Irwin','Tina','Isabelle','Female','1998-09-09',167,57,1),
('Jones','Umar','Jeremiah','Male','1999-10-10',178,72,0);

INSERT INTO coach (lastname, firstname, middlename, sex, date_of_birth, start_year, end_year, status) VALUES
('King','Adam','Anthony','Male','1970-01-01',1995,NULL,true),
('Lewis','Bella','Catherine','Female','1975-02-02',2000,NULL,true),
('Moore','Carl','Douglas','Male','1972-03-03',1998,NULL,true),
('Nelson','Diana','Evelyn','Female','1978-04-04',2002,NULL,true),
('Owens','Ethan','Frederick','Male','1980-05-05',2005,NULL,true),
('Parker','Fiona','Grace','Female','1982-06-06',2007,NULL,true),
('Quinn','George','Howard','Male','1974-07-07',1999,NULL,true),
('Reed','Hannah','Isadora','Female','1985-08-08',2010,NULL,true),
('Stone','Ian','Jonathan','Male','1977-09-09',2001,NULL,true),
('Turner','Julia','Katherine','Female','1983-10-10',2008,2025,true);

INSERT INTO tournament (tournament_name, season_year, tournament_type, start_date, end_date) VALUES
('Metro Cup',2020,'Open','2020-01-10','2020-02-10'),
('City League',2021,'Regional','2021-03-05','2021-04-01'),
('National Series',2022,'National','2022-05-12','2022-06-20'),
('Summer Invitational',2023,'Invitational','2023-07-01','2023-07-30'),
('Winter Classic',2019,'Seasonal','2019-11-10','2019-12-05'),
('Championship Cup',2018,'Major','2018-08-15','2018-09-15'),
('Rookie Cup',2024,'Amateur','2024-02-01','2024-02-20'),
('Legends Tournament',2020,'Veteran','2020-09-01','2020-09-28'),
('Basketball Masters',2021,'Major','2021-06-10','2021-07-05'),
('Regional Clash',2023,'Regional','2023-10-01','2023-10-25');

INSERT INTO team (coach_id, team_name, number_of_players, registry_status)
VALUES
(1, 'Red Hawks', 15, TRUE),
(2, 'Blue Titans', 14, TRUE),
(3, 'Golden Vipers', 12, FALSE),
(4, 'Silver Wolves', 16, TRUE),
(5, 'Iron Stallions', 13, TRUE),
(6, 'Crimson Blades', 11, FALSE),
(7, 'Emerald Eagles', 17, TRUE),
(8, 'Shadow Panthers', 10, TRUE),
(9, 'Phantom Foxes', 14, FALSE),
(10, 'Azure Serpents', 15, TRUE);

INSERT INTO engagement (engagement_type, enthusiast_id, status) VALUES
('follow',1,true),
('like',1,true),
('follow',1,true),
('like',1,true),
('follow',1,true),
('like',1,true),
('follow',1,true),
('like',1,true),
('follow',1,true),
('like',1,true),

('follow',2,true),
('like',2,true),
('follow',2,true),
('like',2,true),
('follow',2,true),
('like',2,true),
('follow',2,true),
('like',2,true),
('follow',2,true),
('like',2,true),

('follow',3,true),
('like',3,true),
('follow',3,true),
('like',3,true),
('follow',3,true),
('like',3,true),
('follow',3,true),
('like',3,true),
('follow',3,true),
('like',3,true),

('follow',4,true),
('like',4,true),
('follow',4,true),
('like',4,true),
('follow',4,true),
('like',4,true),
('follow',4,true),
('like',4,true),
('follow',4,true),
('like',4,true),

('follow',5,true),
('like',5,true),
('follow',5,true),
('like',5,true),
('follow',5,true),
('like',5,true),
('follow',5,true),
('like',5,true),
('follow',5,true),
('like',5,true),

('follow',6,true),
('like',6,true),
('follow',6,true),
('like',6,true),
('follow',6,true),
('like',6,true),
('follow',6,true),
('like',6,true),
('follow',6,true),
('like',6,true),

('follow',7,true),
('like',7,true),
('follow',7,true),
('like',7,true),
('follow',7,true),
('like',7,true),
('follow',7,true),
('like',7,true),
('follow',7,true),
('like',7,true),

('follow',8,true),
('like',8,true),
('follow',8,true),
('like',8,true),
('follow',8,true),
('like',8,true),
('follow',8,true),
('like',8,true),
('follow',8,true),
('like',8,true),

('follow',9,true),
('like',9,true),
('follow',9,true),
('like',9,true),
('follow',9,true),
('like',9,true),
('follow',9,true),
('like',9,true),
('follow',9,true),
('like',9,true),

('follow',10,true),
('like',10,true),
('follow',10,true),
('like',10,true),
('follow',10,true),
('like',10,true),
('follow',10,true),
('like',10,true),
('follow',10,true),
('like',10,true);

INSERT INTO engagement_player (engagement_id, player_id) VALUES
(1,1),
(4,2),
(7,3),
(10,4),
(13,5),
(16,6),
(19,7),
(22,8),
(25,9),
(28,10),

(31,1),
(34,2),
(37,3),
(40,4),
(43,5),
(46,6),
(49,7),
(52,8),
(55,9),
(58,10),

(61,1),
(64,2),
(67,3),
(70,4),
(73,5),
(76,6),
(79,7),
(82,8),
(85,9),
(88,10),

(91,1),
(94,2),
(97,3),
(100,4);

INSERT INTO engagement_coach (engagement_id, coach_id) VALUES
(2,1),
(5,2),
(8,3),
(11,4),
(14,5),
(17,6),
(20,7),
(23,8),
(26,9),
(29,10),

(32,1),
(35,2),
(38,3),
(41,4),
(44,5),
(47,6),
(50,7),
(53,8),
(56,9),
(59,10),

(62,1),
(65,2),
(68,3),
(71,4),
(74,5),
(77,6),
(80,7),
(83,8),
(86,9),
(89,10),

(92,1),
(95,2),
(98,3);

INSERT INTO engagement_tournament (engagement_id, tournament_id) VALUES
(3,1),
(6,2),
(9,3),
(12,4),
(15,5),
(18,6),
(21,7),
(24,8),
(27,9),
(30,10),

(33,1),
(36,2),
(39,3),
(42,4),
(45,5),
(48,6),
(51,7),
(54,8),
(57,9),
(60,10),

(63,1),
(66,2),
(69,3),
(72,4),
(75,5),
(78,6),
(81,7),
(84,8),
(87,9),
(90,10),

(93,1),
(96,2),
(99,3);


use basketball_app;

-- ======================================
-- Sample Enthusiasts
-- ======================================
INSERT INTO enthusiast (username, lastname, firstname, middlename, sex, date_of_birth)
VALUES
('hoopster1', 'Reyes', 'Juan', 'Santos', 'Male', '1995-04-12'),
('hoopfan2', 'Garcia', 'Maria', 'L', 'Female', '1998-09-30'),
('bballman3', 'Lopez', 'Carlos', NULL, 'Male', '2000-01-15'),
('shootqueen4', 'Dela Cruz', 'Ana', NULL, 'Female', '1997-06-21'),
('dribbleking5', 'Martinez', 'Luis', 'R', 'Male', '1996-03-10');

-- ======================================
-- Sample Players
-- ======================================
INSERT INTO player (lastname, firstname, middlename, sex, date_of_birth, height, weight, rStatus)
VALUES
('Smith', 'James', NULL, 'Male', '1998-02-20', 198, 95, 1),
('Johnson', 'Michael', NULL, 'Male', '1997-05-14', 200, 98, 1),
('Williams', 'Kevin', NULL, 'Male', '1999-08-03', 195, 90, 1),
('Brown', 'Chris', NULL, 'Male', '2000-11-22', 202, 102, 1),
('Jones', 'Anthony', NULL, 'Male', '1996-07-18', 197, 93, 1);

-- ======================================
-- Sample Coaches
-- ======================================
INSERT INTO coach (lastname, firstname, middlename, sex, date_of_birth, start_year, end_year, status)
VALUES
('Miller', 'Tom', NULL, 'Male', '1975-03-12', 2000, NULL, 1),
('Davis', 'Sarah', NULL, 'Female', '1980-08-30', 2005, NULL, 1),
('Garcia', 'Luis', NULL, 'Male', '1978-11-09', 2002, NULL, 1);

-- ======================================
-- Sample Tournaments
-- ======================================
INSERT INTO tournament (tournament_name, season_year, tournament_type, start_date, end_date)
VALUES
('Summer Slam', 2025, 'League', '2025-06-01', '2025-06-30'),
('Winter Cup', 2025, 'Cup', '2025-12-01', '2025-12-15');

-- ======================================
-- Sample Engagements
-- ======================================
INSERT INTO engagement (engagement_type, enthusiast_id, status)
VALUES
('like', 1, 1),
('follow', 2, 1),
('like', 3, 1),
('follow', 4, 1),
('like', 5, 1),
('like', 1, 1),
('follow', 2, 1),
('like', 3, 1),
('follow', 4, 1),
('like', 5, 1);

-- ======================================
-- Engagement Subtypes
-- ======================================
-- Player engagements
INSERT INTO engagement_player (engagement_id, player_id)
VALUES
(1, 1),
(3, 2),
(5, 3),
(6, 4),
(9, 5);

-- Coach engagements
INSERT INTO engagement_coach (engagement_id, coach_id)
VALUES
(2, 1),
(4, 2),
(7, 3);

-- Tournament engagements
INSERT INTO engagement_tournament (engagement_id, tournament_id)
VALUES
(8, 1),
(10, 2);

